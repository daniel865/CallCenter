package com.almundo.behavior;

import com.almundo.domain.BasicInformation;
import com.almundo.domain.Call;
import org.apache.log4j.Logger;

public interface IEmployee {

    Logger logger = Logger.getLogger(IEmployee.class);

    void attendCall(Call call);

    default void presentYourself(BasicInformation information) {
        logger.info(String.format("Hello, My name is %s %s. I'm a %s. How can I help you?",
                information.getName(), information.getLastName(), information.getPosition().getNamePosition()));
    }

}
