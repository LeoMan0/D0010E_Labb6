package general;


import specific.*;

import java.util.ArrayList;

public class Simulator {

    private StoreState storeState;
    private StoreView storeView;
    private EventQueue eventQueue;

    public Simulator(StoreState storeState) {

        this.storeState = storeState;
        this.storeView = new StoreView(this.storeState);
        storeState.addObserver(storeView);

        MakeAllCustomers allCustomersGenerator = new MakeAllCustomers(storeState);
        ArrayList<MakeCustomer> allCustomers = allCustomersGenerator.getAllCustomers();
        eventQueue = new EventQueue(storeState);


        // Convert all MakeCustomer instances to ArrivalEvents and add them to the event queue
        for (MakeCustomer customer : allCustomers) {
            ArrivalEvent arrivalEvent = new ArrivalEvent(customer.getArrivalTime(), customer, storeState);
            eventQueue.addInsert(arrivalEvent);
        }

    }

    public void start() {
        while (!eventQueue.isEmpty()) {
            eventQueue.executeAndInsert();
        }
        storeState.stop();
        eventQueue.executeAndInsert();
    }


}
