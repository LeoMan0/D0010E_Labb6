package specific;

import general.State;

import random.*;

import java.util.Arrays;


/**
 * Represents the state of the store during the simulation, including time management,
 * customer queue management, and keeping track of various statistics such as missed customers,
 * paid customers, and total queue time. This class is responsible for initializing the simulation
 * settings based on input parameters and updating the state as events occur.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public class StoreState extends State {
    // StoreState is the state the simulation is in
    // But it is also here setting for how the simulation will be run is.
    //
    private float previousTime = 0;

    // Statistics and state variables
    private float totalQueueTime = 0;
    private String eventName;
    private int customerId;
    private int missedCustomers = 0;
    private int paidCustomers = 0;
    private int totalCustomerWhoHasQueued = 0;
    private float checkoutIdleTime = 0;
    private boolean storeIsOpen = true;

    // Queue management
    private CheckOutQueue checkOutQueue = new CheckOutQueue();

    // Random time generation for customer actions
    private UniformRandomStream pickTime;
    private UniformRandomStream payTime;

    // Simulation parameters
    private int seed;
    private int checkoutsOpen;
    private int maxCapacityInStore;
    private int currentCapacityInStore = 0;

    private double lambda;
    private double minPayTime;
    private double maxPayTime;
    private double minPickTime;
    private double maxPickTime;
    private float closeStoreTime;

    /**
     * Constructs a StoreState with specified simulation parameters.
     * Initializes random streams for pick and pay times using provided min/max values and seed.
     *
     * @param lambda             The average arrival rate of customers.
     * @param seed               The seed for random number generation to ensure reproducibility.
     * @param closeStoreTime     The time at which the store closes and no more customers are allowed to enter.
     * @param checkoutsOpen      The number of checkouts open for processing payments.
     * @param maxCapacityInStore The maximum number of customers allowed in the store at one time.
     * @param minPayTime         The minimum time it takes for a customer to pay.
     * @param maxPayTime         The maximum time it takes for a customer to pay.
     * @param minPickTime        The minimum time it takes for a customer to pick their items.
     * @param maxPickTime        The maximum time it takes for a customer to pick their items.
     */
    public StoreState(double lambda, int seed, float closeStoreTime, int checkoutsOpen, int maxCapacityInStore, double minPayTime, double maxPayTime, double minPickTime, double maxPickTime, boolean print) {
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

    // Getter and setter methods

    // Additional methods to update and manage the store state

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


    public float getTotalQueueTime() {
        return totalQueueTime;
    }

    public void setTotalQueueTime() {
        this.totalQueueTime += (this.getTimePassed() - this.previousTime) * queueLength();
    }


    //Methods with logic follows here


    public int queueLength() {
        return this.getCheckOutQueue().size();
    }

    public String getLookInsideCustomerQueue() {
        return Arrays.toString(checkOutQueue.toArray());
    }


    public void setPreviousTime(float previousTime) {
        this.previousTime = previousTime;
    }

    public void setCheckoutIdleTime() {
        this.checkoutIdleTime += (this.getTimePassed() - this.previousTime) * checkoutsOpen;
    }

    public float getCheckoutIdleTime() {
        return checkoutIdleTime;
    }

    public boolean getStoreIsOpen() {
        return this.storeIsOpen;
    }

    /**
     * Updates the store's time and calculates the checkout idle time and total queue time.
     * Also updates the store's open/closed status based on the current time.
     *
     * @param currentTime The current simulation time to be updated to.
     */
    public void updateTime(float currentTime) {
        if (currentTime >= this.closeStoreTime) {
            this.storeIsOpen = false;
        }

        this.setPreviousTime(this.getTimePassed());
        this.setTimePassed(currentTime);


        setCheckoutIdleTime();
        setTotalQueueTime();
    }

}
