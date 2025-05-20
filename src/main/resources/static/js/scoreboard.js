let stompClient = null;
const colors = ["#4CAF50", "#2196F3", "#FF9800", "#9C27B0", "#E91E63", "#3F51B5", "#009688"];

function connectWebSocket() {
    const socket = new SockJS('/live-updates');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        stompClient.subscribe('/topic/display-updates', function () {
            fetchTeams();
        });
    });
}

async function fetchTeams() {
    const response = await fetch('/api/teams');
    const teams = await response.json();

    // === Actualizar estadísticas ===
    if (teams.length > 0) {
        document.getElementById('teamCount').textContent = teams.length;
        const avg = teams.reduce((sum, team) => sum + parseInt(team.percentage), 0) / teams.length;
        document.getElementById('avgProgress').textContent = Math.round(avg) + '%';
        document.getElementById('lastUpdate').textContent = new Date().toLocaleTimeString();
    }

    // === Actualizar visualización de equipos ===
    const container = document.getElementById('teamContainer');
    container.innerHTML = '';

    const gridContainer = document.createElement('div');
    gridContainer.className = 'compact-view grid gap-4 p-4';

    teams.forEach((team, index) => {
        const card = document.createElement('div');
        card.className = 'bg-white rounded-lg shadow-md overflow-hidden';

        // Header
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

        // Progress bar
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

        card.appendChild(header);
        card.appendChild(barContainer);
        gridContainer.appendChild(card);
    });

    container.appendChild(gridContainer);

    // Animación
    setTimeout(() => {
        document.querySelectorAll('.progress-bar-animate').forEach(bar => {
            bar.style.transition = 'width 0.5s ease';
        });
    }, 100);
}

// Ejecutar todo cuando la página cargue
window.onload = () => {
    fetchTeams();
    connectWebSocket();
};