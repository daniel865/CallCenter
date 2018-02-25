package com.almundo;

import com.almundo.behavior.IEmployee;
import com.almundo.domain.Call;
import com.almundo.domain.Client;
import com.almundo.enums.CallStatus;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class CallTask extends NotificationThread {

    private Call call;
    private CallStatus callStatus;

    public CallTask(Call call, CallStatus callStatus) {
        this.call = call;
        this.callStatus = callStatus;
    }

    public Call getCall() {
        return call;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public CallStatus getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(CallStatus callStatus) {
        this.callStatus = callStatus;
    }

    @Override
    public void doWork() {
        setCallStatus(CallStatus.IN_PROGRESS);
        // Seconds of the call. Represent minutes
        int max = 10;
        int min = 5;
        int timeElapsedCall = ThreadLocalRandom.current().nextInt(min, max + 1);
        try {
            Thread.sleep(timeElapsedCall * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setCallStatus(CallStatus.FINISHED);
    }

}
