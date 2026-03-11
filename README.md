<h1 align="center">🏆 Hrank 🏆</h1>
<p align="center">Hackathons ranking board.</p>

**Hrank** (short for **HackRank**) is a dynamic leaderboard system designed for hackathons.

## Features
- Live scoreboard for events
- Admin panel to manage teams and scores
- Team panel for participants
- WebSocket updates for real-time changes

## Prerequisites
- Java 11 or newer
- Docker (optional)
- Git
- Internet access to download Maven dependencies (or use the included wrapper)

## Build & run

Unix / macOS:
```bash
./mvnw clean package
./mvnw spring-boot:run
# or run the produced jar
java -jar target/*.jar
```

Windows (cmd.exe / PowerShell):
```batch
mvnw.cmd clean package
mvnw.cmd spring-boot:run
# or run the produced jar
java -jar target\*.jar
```

## Docker
```bash
docker build -t hrank .
docker run -p 8080:8080 hrank
```

## Available pages
- / — Home
- /scoreboard — Scoreboard display
- /admin-panel — Admin interface
- /team-panel — Team interface

## Development notes
- Static assets: src/main/resources/static/
- Templates: src/main/resources/templates/
- Controllers and services: src/main/java/es/hugoalvarezajenjo/hrank/

Run tests:
```bash
./mvnw test
```

## Contributing
Contributions and bug reports are welcome. Open pull requests against the repository and follow existing code style.