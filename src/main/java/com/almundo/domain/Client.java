package com.almundo.domain;

import com.almundo.CallManager;

import java.util.Objects;
import java.util.UUID;

public class Client {

    private final String id;
    private final String name;
    private final String lastname;
    private final String telephoneNumber;

    /**
     * Manager to make calls
     */
    private CallManager manager;

    public Client(String id, String name, String lastname, String telephoneNumber, CallManager callManager) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.telephoneNumber = telephoneNumber;
        this.manager = callManager;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(getId(), client.getId()) &&
                Objects.equals(getName(), client.getName()) &&
                Objects.equals(getLastname(), client.getLastname()) &&
                Objects.equals(getTelephoneNumber(), client.getTelephoneNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLastname(), getTelephoneNumber());
    }

    public void makeCall(){
        UUID uuidCall = UUID.randomUUID();
        Call call = new Call(uuidCall.toString(), this);
        manager.receiveCall(call);
    }



}
