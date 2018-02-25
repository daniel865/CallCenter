package com.almundo;

import com.almundo.behavior.IDispatcher;
import com.almundo.behavior.IEmployee;
import com.almundo.behavior.TaskListener;
import com.almundo.domain.Call;
import com.almundo.domain.Director;
import com.almundo.domain.Operator;
import com.almundo.domain.Supervisor;
import com.almundo.factories.CallFactory;

import java.util.concurrent.*;

public class Dispatcher implements IDispatcher, TaskListener {

    /**
     * Executor to execute the concurrent tasks
     */
    private ThreadPoolExecutor executor;

    /**
     * Available Operators
     */
    private LinkedBlockingQueue<IEmployee> availableOperatorsQueue;

    /**
     * Available Supervisors
     */
    private LinkedBlockingQueue<IEmployee> availableSupervisorQueue;

    /**
     * Available Directors
     */
    private LinkedBlockingQueue<IEmployee> availableDirectorQueue;

    /**
     * Available Directors
     */
    private LinkedBlockingQueue<IEmployee> employeesAttendingCalls = new LinkedBlockingQueue<>();

    /**
     * Pending call tasks
     */
    private ConcurrentLinkedQueue<Call> pendingCalls;


    public Dispatcher(LinkedBlockingQueue<IEmployee> availableOperatorsQueue, LinkedBlockingQueue<IEmployee> availableSupervisorQueue,
                      LinkedBlockingQueue<IEmployee> availableDirectorQueue, ConcurrentLinkedQueue<Call> pendingCalls,
                      ThreadPoolExecutor executor) {
        this.executor = executor;
        this.availableOperatorsQueue = availableOperatorsQueue;
        this.availableSupervisorQueue = availableSupervisorQueue;
        this.availableDirectorQueue = availableDirectorQueue;
        this.pendingCalls = pendingCalls;
    }

    public LinkedBlockingQueue<IEmployee> getAvailableOperatorsQueue() {
        return availableOperatorsQueue;
    }

    public LinkedBlockingQueue<IEmployee> getAvailableSupervisorQueue() {
        return availableSupervisorQueue;
    }

    public LinkedBlockingQueue<IEmployee> getAvailableDirectorQueue() {
        return availableDirectorQueue;
    }

    public LinkedBlockingQueue<IEmployee> getEmployeesAttendingCalls() {
        return employeesAttendingCalls;
    }

    public ConcurrentLinkedQueue<Call> getPendingCalls() {
        return pendingCalls;
    }

    @Override
    public void dispatchCall(Call call) {
        if (availableOperatorsQueue.isEmpty() && availableSupervisorQueue.isEmpty() && availableDirectorQueue.isEmpty()) {
            pendingCalls.add(call);
        }  else if (!availableOperatorsQueue.isEmpty()){
            processCall(call, availableOperatorsQueue);
        } else if (!availableSupervisorQueue.isEmpty()) {
            processCall(call, availableSupervisorQueue);
        } else if (!availableDirectorQueue.isEmpty()) {
            processCall(call, availableDirectorQueue);
        }
    }

    public void processCall(Call call, LinkedBlockingQueue<IEmployee> employeesQueue){
        IEmployee employee = (IEmployee)employeesQueue.poll();
        CallTask callTask = new CallFactory().createCallStarted(call, employee);
        executor.execute(callTask);
    }

    @Override
    public void transerCall() {
        // To-Do: Define Behaviour
    }

    @Override
    public void cancellCall() {
        // To-Do: Define Behaviour
    }

    @Override
    public void threadComplete(Runnable runner) {
        IEmployee employee = employeesAttendingCalls.poll();
        if (employee instanceof Operator) {
            availableOperatorsQueue.add(employee);
        } else if (employee instanceof Supervisor) {
            availableSupervisorQueue.add(employee);
        } else if (employee instanceof Director) {
            availableDirectorQueue.add(employee);
        }
    }

    public Boolean checkAvailability() {
        if (!availableOperatorsQueue.isEmpty() && !availableSupervisorQueue.isEmpty() && !availableDirectorQueue.isEmpty()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public void processPendingTasks() {
        if (checkAvailability() && !pendingCalls.isEmpty()) {
            dispatchCall(pendingCalls.poll());
        }
    }

}
