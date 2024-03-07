package specific;

import general.Event;

public class QueueEvent extends Event {
    StoreState specificState;
    MakeCustomer customer;

    public QueueEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "QueueEvent";
    }

    // Likewise we have to make it so that the customer leaves the queue here
    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);
        float next = scheduleNextEventTime();

        return new PayEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }

    public float scheduleNextEventTime() {
        //This number is a random number in a range.
        // This is the pay time. Can be taken from state.
        System.out.println(customer.getTimeToPay());
        return time + customer.getTimeToPay();

    }


}