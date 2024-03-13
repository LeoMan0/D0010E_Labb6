package specific;

import general.*;

/**
 * Represents a specific type of event when a customer arrives at the store.
 * This class extends the Event class to handle the arrival of customers specifically.
 * It sets up the initial state for a customer entering the store, including updating
 * the store state and scheduling the next event.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public class ArrivalEvent extends Event {


    /**
     * Constructs an ArrivalEvent with specified time, customer, and store state.
     * Initializes the event with the given parameters and sets the event name to "Ankomst".
     *
     * @param time       The execution time of this event.
     * @param customer   The customer associated with this event.
     * @param storeState The current state of the store when this event occurs.
     */

    public ArrivalEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "Ankomst";
    }

    /**
     * Executes the event by updating the store state and potentially scheduling
     * a new EnterEvent if the store is open. This method updates the time passed,
     * sets the current customer ID, and event name in the store state.
     *
     * @return An EnterEvent if the store is open, otherwise null.
     */
    @Override
    public Event execute() {
        //this.state.setTimePassed(this.getTime());

        StoreState specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());

        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        float next = scheduleNextEventTime();
        if (specificState.getStoreIsOpen()) {
            return new EnterEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
        }
        return null;
    }
    
    /**
     * Method that returns the time for the next event.
     * 
     * @return The time for the next event.
     */
    public float scheduleNextEventTime() {
        return time;
    }


}
