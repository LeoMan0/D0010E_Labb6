package general;


import specific.*;

import java.util.ArrayList;

public class Simulator {

    private StoreState storeState;
    private StoreView storeView;
    private EventQueue eventQueue;

    public Simulator(double lambda, int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, double minPayTime, double maxPayTime, double minPickTime, double maxPickTime, boolean print) {

        this.storeState = new StoreState(lambda, seed, closeStoreTime, checkoutsOpen, maxCapacityInStore, minPayTime, maxPayTime, minPickTime, maxPickTime, print);


        MakeAllCustomers allCustomersGenerator = new MakeAllCustomers(storeState);
        ArrayList<MakeCustomer> allCustomers = allCustomersGenerator.getAllCustomers();
        eventQueue = new EventQueue(storeState);


        // Convert all MakeCustomer instances to ArrivalEvents and add them to the event queue
        for (MakeCustomer customer : allCustomers) {
            ArrivalEvent arrivalEvent = new ArrivalEvent(customer.getArrivalTime(), customer, storeState);
            eventQueue.addInsert(arrivalEvent);
        }

        if (print) {
            this.storeView = new StoreView(this.storeState);
            storeState.addObserver(storeView);
        }

    }

    public void start() {
        while (!eventQueue.isEmpty()) {
            eventQueue.executeAndInsert();
        }
        storeState.stop();
        eventQueue.executeAndInsert();
    }

    public int getMissedCustomers() {
        return storeState.getMissedCustomers();
    }


}
