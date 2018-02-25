package com.almundo.enums;

public enum Position {

    OPERATOR(1, "Operator"),
    SUPERVISOR(2, "Supervisor"),
    DIRECTOR(3, "Director");

    private Integer id;
    private String namePosition;

    Position(Integer id, String namePosition) {
        this.id = id;
        this.namePosition = namePosition;
    }

    public Integer getId() {
        return id;
    }

    public String getNamePosition() {
        return namePosition;
    }
}
