// ========== Configuración Inicial ==========
let stompClient = null;

// Colores para las barras de progreso (uno por equipo)
const colors = ["#4CAF50", "#2196F3", "#FF9800", "#9C27B0", "#E91E63", "#3F51B5", "#009688"];

// ========== Conexión WebSocket ==========
// Se conecta al endpoint del backend y se suscribe a las actualizaciones
function connectWebSocket() {
    const socket = new SockJS('/live-updates');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        stompClient.subscribe('/topic/display-updates', function () {
            fetchTeams(); // Cuando llega un mensaje, se actualizan los equipos
            fetchConfig(); // También refrescar la configuración (título, tiempo fin)
        });
    });
}

// ========== Función para obtener y mostrar equipos ==========
async function fetchTeams() {
    const response = await fetch('/api/teams');
    const teams = await response.json();

    // Actualiza estadísticas (contadores y tiempo)
    if (teams.length > 0) {
        document.getElementById('teamCount').textContent = teams.length;

        const avg = teams.reduce((sum, team) => sum + parseInt(team.percentage), 0) / teams.length;
        document.getElementById('avgProgress').textContent = Math.round(avg) + '%';
        document.getElementById('lastUpdate').textContent = new Date().toLocaleTimeString();
    }

    const container = document.getElementById('teamContainer');

    if (teams.length > 0) {
        container.innerHTML = '';

        const gridContainer = document.createElement('div');
        gridContainer.className = 'compact-view grid gap-4 p-4';

        teams.forEach((team, index) => {
            const card = document.createElement('div');
            card.className = 'bg-white rounded-lg shadow-md overflow-hidden';

            // Header del equipo: nombre + porcentaje
            const header = document.createElement('div');
            header.className = 'flex justify-between items-center p-4 bg-gray-50 border-b';

            const nameDiv = document.createElement('div');
            nameDiv.className = 'font-bold text-lg text-gray-800 truncate';
            nameDiv.textContent = team.name;

            const percentage = document.createElement('div');
            percentage.className = 'bg-blue-100 text-blue-800 px-3 py-1 rounded-full text-sm font-semibold';
            percentage.textContent = team.percentage + '%';

            header.appendChild(nameDiv);
            header.appendChild(percentage);

            // Barra de progreso
            const barContainer = document.createElement('div');
            barContainer.className = 'p-4';

            const phaseLabel = document.createElement('div');
            phaseLabel.className = 'flex justify-between mb-2 text-sm';

            const phaseText = document.createElement('span');
            phaseText.className = 'font-medium';
            phaseText.textContent = team.phase;

            phaseLabel.appendChild(phaseText);

            const barOuter = document.createElement('div');
            barOuter.className = 'w-full bg-gray-200 rounded-full h-4';

            const barInner = document.createElement('div');
            barInner.className = 'progress-bar-animate h-4 rounded-full';
            barInner.style.width = team.percentage + '%';
            barInner.style.backgroundColor = colors[index % colors.length];

            barOuter.appendChild(barInner);
            barContainer.appendChild(phaseLabel);
            barContainer.appendChild(barOuter);

            // Montaje de la tarjeta
            card.appendChild(header);
            card.appendChild(barContainer);
            gridContainer.appendChild(card);
        });

        container.appendChild(gridContainer);

        // Animación de barras
        setTimeout(() => {
            document.querySelectorAll('.progress-bar-animate').forEach(bar => {
                bar.style.transition = 'width 0.5s ease';
            });
        }, 100);
    }
}

// ========== Configuración de Competición ==========
let competitionConfig = null;

async function fetchConfig() {
    try {
        const response = await fetch('/api/config');
        competitionConfig = await response.json();

        // Actualizar título dinámicamente si existe el elemento
        const titleEl = document.querySelector('h1.font-bold');
        if (titleEl && competitionConfig.title) {
            titleEl.textContent = competitionConfig.title;
        }

        updateCountdown();
    } catch (e) {
        console.error("Error fetching config", e);
    }
}

function updateCountdown() {
    if (!competitionConfig || !competitionConfig.endTime) return;

    const end = new Date(competitionConfig.endTime).getTime();
    const now = new Date().getTime();

    const countdownElement = document.getElementById('countdown');

    const distance = end - now;

    if (distance < 0) {
        countdownElement.textContent = "¡FINALIZADO!";
        countdownElement.className = "font-bold text-lg text-red-600 animate-pulse uppercase";
        return;
    }

    const hours = Math.floor((distance % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
    const minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
    const seconds = Math.floor((distance % (1000 * 60)) / 1000);

    countdownElement.textContent =
        (hours < 10 ? "0" + hours : hours) + ":" +
        (minutes < 10 ? "0" + minutes : minutes) + ":" +
        (seconds < 10 ? "0" + seconds : seconds);

    if (distance < 1000 * 60 * 15) { // Últimos 15 min en rojo
        countdownElement.className = "font-bold text-lg text-red-600";
    } else {
        countdownElement.className = "font-bold text-lg text-indigo-700";
    }
}

// ========== Inicio del script ==========
window.onload = () => {
    fetchTeams();
    fetchConfig();
    connectWebSocket();

    // Actualizar cada segundo
    setInterval(updateCountdown, 1000);
    // Refrescar configuración cada minuto (por si el admin la cambia)
    setInterval(fetchConfig, 60000);
};
