package com.almundo.behavior;

import com.almundo.domain.Call;

public interface IDispatcher {

    void dispatchCall(Call call);

    void transerCall();

    void cancellCall();

}
