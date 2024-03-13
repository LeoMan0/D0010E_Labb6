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
     * @param print 			 The state of whether the state should be printed or not.
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
    
    /**
     * Method that returns the simulations seed.
     * 
     * @return The simulations seed.
     */
    public long getSeed() {
        return seed;
    }
    
    /**
     * Method that returns the simulations lambda.
     * 
     * @return The simulations lambda.
     */
    public double getLambda() {
        return lambda;
    }

    /**
     * Method that returns the amount of checkouts that are open.
     * 
     * @return The amount of checkouts that are open.
     */
    public int getCheckoutsOpen() {
        return checkoutsOpen;
    }
    
    /**
     * Method that increases the amount of of checkouts that are open by.
     */
    public void increaseCheckOutsOpen() {
        ++this.checkoutsOpen;
    }
    
    /**
     * Method that decreases the amount of checkouts that are open.
     */
    public void decreaseCheckOutsOpen() {
        --this.checkoutsOpen;
    }

    /**
     * Method that returns the maximum capacity of customers for the store.
     * 
     * @return The maximum capacity of customers.
     */
    public int getMaxCapacityInStore() {
        return maxCapacityInStore;
    }

    /**
     * Method that returns the minimum time it takes to pay.
     * 
     * @return The minimum time it takes to pay.
     */
    public double getMinPayTime() {
        return minPayTime;
    }

    /**
     * Method that returns the maximum time it takes to pay.
     * 
     * @return The maximum time it takes to pay.
     */
    public double getMaxPayTime() {
        return maxPayTime;
    }

    /**
     * Method that returns the minimum time it takes to pick groceries.
     * 
     * @return The minimum time it takes to pick groceries.
     */
    public double getMinPickTime() {
        return minPickTime;
    }

    /**
     * Method that returns the maximum time it takes to pick groceries.
     * 
     * @return The maximum time it takes to pick groceries.
     */
    public double getMaxPickTime() {
        return maxPickTime;
    }

    /**
     * Method that returns the closing time for the store.
     * 
     * @return The closing time for the store.
     */
    public float getCloseStoreTime() {
        return closeStoreTime;
    }

    /**
     * Method that returns the current amount of customers in the store.
     * 
     * @return The closing time for the store.
     */
    public int getCurrentCapacityInStore() {
        return this.currentCapacityInStore;
    }
    
    /**
     * Method that decreases the current amount of customers in the store.
     */
    public void decreaseCurrentCapacity() {
        this.currentCapacityInStore--;
    }
    
    /**
     * Method that increases the current amount of customers in the store.
     */
    public void increaseCurrentCapacity() {
        this.currentCapacityInStore++;
    }
    
    /**
     * Method that increases the amount of customers that have payed.
     */
    public void increasePaidCustomers() {
        this.paidCustomers++;
    }
    
    /**
     * Method that returns the amount of customers that have payed.
     * 
     * @return The amount of customers that have payed.
     */
    public int getPaidCustomers() {
        return paidCustomers;
    }

    //Here follows the print methods

    /**
     * Method that sets the current events name to a new event name.
     * 
     * @param eventName The name of an event.
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
    /**
     * Method that returns the current events name.
     * 
     * @return The current events name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Method that sets the current customer id to a new customer id.
     * 
     * @param customerId The id of a customer.
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    /**
     * Method that returns the current customers id.
     * 
     * @return The current customers id.
     */
    public int getCustomerId() {
        return customerId;
    }
    
    /**
     * Method that returns the amount of customers that never entered the store.
     * 
     * @return The amount of customers that never entered the store.
     */
    public int getMissedCustomers() {
        return missedCustomers;
    }
    
    /**
     * Method that increases the amount of missed customers.
     */
    public void increaseMissedCustomers() {
        ++this.missedCustomers;
    }
    
    /**
     * Method that returns the queue for the checkouts.
     * 
     * @return The queue for the checkouts.
     */
    public CheckOutQueue getCheckOutQueue() {
        return checkOutQueue;
    }

    //test

    /**
     * Method that returns the time it takes to pick groceries.
     * 
     * @return The time it takes to pick groceries.
     */
    public double getPickTime() {
        return pickTime.next();
    }
    
    /**
     * Method that returns the time it takes to pay.
     * 
     * @return The time it takes to pay.
     */
    public double getPayTime() {
        return payTime.next();
    }
    
    /**
     * Method that returns the total amount of customers who have been in the queue.
     * 
     * @return The total amount of customers who have been in the queue.
     */
    public int getTotalCustomerWhoHasQueued() {
        return totalCustomerWhoHasQueued;
    }
    
    /**
     * Method that increases the total amount of customers who have been in the queue.
     */
    public void increaseTotalCustomerWhoHasQueued() {
        this.totalCustomerWhoHasQueued++;
    }
  
    /**
     * Method that returns the total time that customers have queued for.
     * 
     * @return The total time that customers have queued for.
     */
    public float getTotalQueueTime() {
        return totalQueueTime;
    }

    /**
     * Method that sets the total queue time to a new total queue time 
     */
    public void setTotalQueueTime() {
        this.totalQueueTime += (this.getTimePassed() - this.previousTime) * queueLength();
    }


    //Methods with logic follows here

    /**
     * Method that returns the length of the checkout queue.
     * 
     * @return The length of the checkout queue.
     */
    public int queueLength() {
        return this.getCheckOutQueue().size();
    }
    
    /**
     * Method that returns the checkout queue as a string.
     * 
     * @return The checkout queue as a string.
     */
    public String getLookInsideCustomerQueue() {
        return Arrays.toString(checkOutQueue.toArray());
    }


    /**
     * Method that sets the previous current time to a new previous current time.
     * 
     * @param previousTime The new previous current time.
     */
    public void setPreviousTime(float previousTime) {
        this.previousTime = previousTime;
    }

    /**
     * Method that sets the time that the checkouts are not occupied to new time.
     */
    public void setCheckoutIdleTime() {
        this.checkoutIdleTime += (this.getTimePassed() - this.previousTime) * checkoutsOpen;
    }
    
    /**
     * Method that returns the time that the checkouts are not occupied.
     * 
     * @return The time that the checkouts are not occupied.
     */
    public float getCheckoutIdleTime() {
        return checkoutIdleTime;
    }
    
    /**
     * Method that returns true if the store is open. Otherwise false.
     * 
     * @return True if the store is open. Otherwise false. 
     */
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
