package specific;

import general.State;

public class StoreState extends State {
    // StoreState is the state the simulation is in
    // But it is also here setting for how the simulation will be run is.
    //
    //Stuff for event-----------------------------
    private String eventName;
    private int customerId;

    private int missedCustomers;


    //Stuff for event-----------------------------
    private long seed;
    private int checkoutsOpen;
    private int maxCapacityInStore;

    private int currentCapacityInStore = 0;

    // The checkouts pay time are assumed to be uniformly distributed within an interval [min max]
    //  And likewise for the time it takes for customers to pay

    private double lambda;
    private double minPayTime;
    private double maxPayTime;

    private double minPickTime;
    private double maxPickTime;

    private float closeStoreTime;

    private float maxWaitTime;


    public StoreState(double lambda, long seed, float maxWaitTime, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, float minPayTime, float maxPayTime, float minPickTime, float maxPickTime) {
        this.lambda = lambda;
        this.seed = seed;
        this.closeStoreTime = closeStoreTime;
        this.checkoutsOpen = checkoutsOpen;
        this.maxCapacityInStore = maxCapacityInStore;
        this.minPayTime = minPayTime;
        this.maxPayTime = maxPayTime;
        this.minPickTime = minPickTime;
        this.maxPickTime = maxPickTime;
        this.maxWaitTime = maxWaitTime;
    }


    public long getSeed() {
        return seed;
    }

    public double getLambda() {
        return lambda;
    }

    public int getCheckoutsOpen() {
        return checkoutsOpen;
    }


    public int getMaxCapacityInStore() {
        return maxCapacityInStore;
    }


    public double getMinPayTime() {
        return minPayTime;
    }


    public double getMaxPayTime() {
        return maxPayTime;
    }


    public double getMinPickTime() {
        return minPickTime;
    }


    public double getMaxPickTime() {
        return maxPickTime;
    }


    public float getCloseStoreTime() {
        return closeStoreTime;
    }

    public float getMaxWaitTime() {
        return maxWaitTime;
    }

    public int getCurrentCapacityInStore() {
        return this.currentCapacityInStore;
    }

    public void decreaseCurrentCapacity() {
        this.currentCapacityInStore--;
    }

    public void increaseCurrentCapacity() {
        this.currentCapacityInStore++;
    }

    //Here follows the print methods


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getMissedCustomers() {
        return missedCustomers;
    }

    public void increaseMissedCustomers() {
        ++this.missedCustomers;
    }
}
