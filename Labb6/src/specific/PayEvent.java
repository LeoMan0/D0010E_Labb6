package specific;

import general.Event;

/**
 * Represents an event where a customer pays for their items at the checkout.
 * From being ready to pay to actually completing their payment.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */
public class PayEvent extends Event {

    /**
     * Constructs an PayEvent with specified time, customer, and store state.
     * Initializes the event with the given parameters and sets the event name to "Ankomst".
     *
     * @param time     The execution time of this event.
     * @param customer The customer associated with this event.
     * @param state    The current state of the store when this event occurs.
     */

    public PayEvent(float time, MakeCustomer customer, StoreState state) {
        super(time, customer, state);
        this.nameOfCurrentEvent = "Betalning";

    }

    /**
     * Executes the payment process by updating the store state, setting customer
     * information, and then scheduling a new LeaveEvent for the customer to exit
     * the store.
     *
     * @return A new LeaveEvent indicating the customer is ready to leave the store.
     */

    @Override
    public Event execute() {

        StoreState specificState = (StoreState) this.state;
        specificState.updateTime(this.getTime());
        MakeCustomer customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        float next = scheduleNextEventTime();
        return new LeaveEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    public float scheduleNextEventTime() {
        return time;
    }


}
