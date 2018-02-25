package com.almundo.domain;

import com.almundo.behavior.IEmployee;
import com.almundo.enums.Position;
import org.apache.log4j.Logger;


public class Operator implements IEmployee {

    private Logger logger = Logger.getLogger(Operator.class);

    private BasicInformation basicInformation;

    public Operator(String id, String name, String lastName, Position position) {
        this.basicInformation = new BasicInformation(id, name, lastName, position);
    }

    public void attendCall(Call call) {

    }

}
