package es.hugoalvarezajenjo.hrank.domain;

import lombok.Getter;

@Getter
public enum ProgressPhase {
    REQUISITOS("Requisitos"),
    DISEÑO("Diseño"),
    DESARROLLO("Desarrollo"),
    PRUEBAS("Pruebas"),
    FINALIZADO("Finalizado");

    private final String displayName;

    ProgressPhase(String displayName) {
        this.displayName = displayName;
    }

}
