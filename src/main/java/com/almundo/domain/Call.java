package com.almundo.domain;

import com.almundo.behavior.IEmployee;

public class Call {

    private String idCall;
    private Client client;
    private IEmployee employee;

    public Call(String idCall, Client client) {
        this.idCall = idCall;
        this.client = client;
    }

    public Call(String idCall, Client client, IEmployee employee) {
        this.idCall = idCall;
        this.client = client;
        this.employee = employee;
    }

    public String getIdCall() {
        return idCall;
    }

    public Client getClient() {
        return client;
    }

    public IEmployee getEmployee() {
        return employee;
    }
}
