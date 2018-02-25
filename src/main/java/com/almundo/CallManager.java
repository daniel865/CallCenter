package com.almundo;

import com.almundo.behavior.IEmployee;
import com.almundo.domain.Call;
import com.almundo.domain.Director;
import com.almundo.domain.Operator;
import com.almundo.domain.Supervisor;
import com.almundo.enums.Position;
import com.github.javafaker.Faker;

import java.util.concurrent.*;

public class CallManager {

    private static Faker faker = new Faker();

    /**
     * Core pool size of the executor. Initialize with 10
     */
    private static int CORE_POOL_SIZE = 10;

    /**
     * Maximum pool size of the executor. Initialize with 10
     */
    private static int MAXIMUM_POOL_SIZE = 10;

    /**
     * Time the threads of the executor can be idle
     */
    private static long KEEP_ALIVE_TIME = 10;

    /**
     * Dispatcher of calls
     */
    private Dispatcher dispatcher;

    /**
     * Available Operators
     */
    private LinkedBlockingQueue<IEmployee> availableOperatorsQueue;

    /**
     * Available Supervisors
     */
    private LinkedBlockingQueue<IEmployee> availableSupervisorsQueue;

    /**
     * Available Directors
     */
    private LinkedBlockingQueue<IEmployee> availableDirectorsQueue;

    /**
     * Available Directors
     */
    private LinkedBlockingQueue<IEmployee> employeesAttendingCalls = new LinkedBlockingQueue<>();

    /**
     * Pending call tasks
     */
    private ConcurrentLinkedQueue<Call> pendingCalls = new ConcurrentLinkedQueue<>();;

    public CallManager() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, new PriorityBlockingQueue<>());

        initialize();
        this.dispatcher = new Dispatcher(availableOperatorsQueue, availableSupervisorsQueue, availableDirectorsQueue, pendingCalls, executor);
    }


    public void receiveCall(Call call){
        dispatcher.dispatchCall(call);
        dispatcher.processPendingTasks();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }

    /**
     * Fake method for initialization. Get Data for queue from BD or another place
     */
    public void initialize() {
        initializeOperatorsQueue();
        initializeSupervisorsQueue();
        initializeDirectorsQueue();
    }

    public void initializeOperatorsQueue() {
        availableOperatorsQueue = new LinkedBlockingQueue<>(20);
        availableOperatorsQueue.add(new Operator("1", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("2", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("3", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("4", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("5", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("6", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("7", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
        availableOperatorsQueue.add(new Operator("8", faker.name().firstName(), faker.name().lastName(), Position.OPERATOR));
    }

    public void initializeSupervisorsQueue() {
        availableSupervisorsQueue = new LinkedBlockingQueue<>(4);
        availableSupervisorsQueue.add(new Supervisor("9", faker.name().firstName(), faker.name().lastName(), Position.SUPERVISOR));
    }

    public void initializeDirectorsQueue() {
        availableDirectorsQueue = new LinkedBlockingQueue<>(1);
        availableDirectorsQueue.add(new Director("10", faker.name().firstName(), faker.name().lastName(), Position.DIRECTOR));
    }


}
