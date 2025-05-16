package es.hugoalvarezajenjo.hrank.domain;

import lombok.Data;

@Data
public class Team {
    private Long id;
    private String name;
    private int percentage;
    private ProgressPhase phase;
}
