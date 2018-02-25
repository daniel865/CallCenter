package com.almundo.domain;

import com.almundo.enums.Position;

public class BasicInformation {

    private final String id;
    private final String name;
    private final String lastName;
    private final Position position;

    public BasicInformation(String id, String name, String lastName, Position position) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.position = position;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "BasicInformation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position=" + position +
                '}';
    }
}
