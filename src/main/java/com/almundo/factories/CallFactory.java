package com.almundo.factories;

import com.almundo.CallTask;
import com.almundo.behavior.IEmployee;
import com.almundo.domain.Call;
import com.almundo.enums.CallStatus;

public class CallFactory {

    public CallTask createCallStarted(Call call, IEmployee employee) {
        return new CallTask(new Call(call.getIdCall(), call.getClient(), employee), CallStatus.STARTED);
    }

    public CallTask createCallInProgress(Call call, IEmployee employee) {
        return new CallTask(new Call(call.getIdCall(), call.getClient(), employee), CallStatus.IN_PROGRESS);
    }

    public CallTask createCallFinished(Call call, IEmployee employee) {
        return new CallTask(new Call(call.getIdCall(), call.getClient(), employee), CallStatus.FINISHED);
    }

    public CallTask createCallCancelled(Call call, IEmployee employee) {
        return new CallTask(new Call(call.getIdCall(), call.getClient(), employee), CallStatus.CANCELLED);
    }

}
