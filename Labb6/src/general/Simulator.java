package general;


import specific.*;

import java.util.ArrayList;

public class Simulator {

    public static void main(String[] args) {
        // Initialize your StoreState here
        StoreState storeState = new StoreState(1, 1234, 5, 100f, 2, 5, 2, 3, 0.5f, 1);
        //StoreState storeState = new StoreState(3.0, 13, 5, 8f, 2, 7, 0.35f, 0.6f, 0.6f, 0.9f);

        Simulator simulator = new Simulator(storeState);
        simulator.start(); // Start the simulation
    }

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
    }


}
