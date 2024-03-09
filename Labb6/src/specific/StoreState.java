package specific;

import general.State;

import random.*;

public class StoreState extends State {
    // StoreState is the state the simulation is in
    // But it is also here setting for how the simulation will be run is.
    //

    private float previousTime = 0;

    //-Stuff printing-----------------------------

    private float totalQueueTime = 0;
    private String eventName;
    private int customerId;

    private int missedCustomers;

    private int paidCustomers;

    private int totalCustomerWhoHasQueued = 0;

    //----------------------------------------------
    private CheckOutQueue checkOutQueue = new CheckOutQueue();

    private UniformRandomStream pickTime;

    private UniformRandomStream payTime;

    //Stuff for event-----------------------------
    private int seed;
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


    public StoreState(double lambda, int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, double minPayTime, double maxPayTime, double minPickTime, double maxPickTime) {
        this.lambda = lambda;
        this.seed = seed;
        this.closeStoreTime = closeStoreTime;
        this.checkoutsOpen = checkoutsOpen;
        this.maxCapacityInStore = maxCapacityInStore;
        this.minPayTime = minPayTime;
        this.maxPayTime = maxPayTime;
        this.minPickTime = minPickTime;
        this.maxPickTime = maxPickTime;

        pickTime = new UniformRandomStream(minPickTime, maxPickTime, seed);
        payTime = new UniformRandomStream(minPayTime, maxPayTime, seed);
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

    public void increaseCheckOutsOpen() {
        ++this.checkoutsOpen;
    }

    public void decreaseCheckOutsOpen() {
        --this.checkoutsOpen;
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


    public int getCurrentCapacityInStore() {
        return this.currentCapacityInStore;
    }

    public void decreaseCurrentCapacity() {
        this.currentCapacityInStore--;
    }

    public void increaseCurrentCapacity() {
        this.currentCapacityInStore++;
    }

    public void increasePaidCustomers() {
        this.paidCustomers++;
    }

    public int getPaidCustomers() {
        return paidCustomers;
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

    public CheckOutQueue getCheckOutQueue() {
        return checkOutQueue;
    }

    //test


    public double getPickTime() {
        return pickTime.next();
    }

    public double getPayTime() {
        return payTime.next();
    }

    public int getTotalCustomerWhoHasQueued() {
        return totalCustomerWhoHasQueued;
    }

    public void increaseTotalCustomerWhoHasQueued() {
        this.totalCustomerWhoHasQueued++;
    }

    public float getPreviousTime() {
        return previousTime;
    }

    public void setPreviousTime(float previousTime) {
        this.previousTime = previousTime;
    }

    public float getTotalQueueTime() {
        return totalQueueTime;
    }

    public void setTotalQueueTime(float totalQueueTime) {
        this.totalQueueTime = this.getTotalQueueTime() - this.getPreviousTime();
    }


    //Methods with logic follows here

    public void updateTime(float currentTime) {

        this.setPreviousTime(this.getTimePassed());
        this.setTimePassed(currentTime);

        setTotalQueueTime(this.getTimePassed() - this.getPreviousTime());
        

    }


}
