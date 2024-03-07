package general;


import specific.*;

import java.util.ArrayList;

public class Simulator {

    public static void main(String[] args) {
        // Initialize your StoreState here
        StoreState storeState = new StoreState(1, 1234L, 5, 10f, 1, 5, 0.5f, 0.1f, 2, 3);

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
            ArrivalEvent arrivalEvent = new ArrivalEvent(customer.getArrivalTime(), customer);
            eventQueue.addInsert(arrivalEvent);
        }

    }

    public void start() {
        while (!eventQueue.isEmpty()) {
            eventQueue.executeAndInsert();
        }
    }

    // Main method to run the simulation


}
