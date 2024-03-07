package specific;

import general.Event;

import java.util.Queue;

public class PickEvent extends Event {
    StoreState specificState;
    MakeCustomer customer;

    public PickEvent(float time, MakeCustomer customer, StoreState storeState) {
        super(time, customer, storeState);
        this.nameOfCurrentEvent = "PickEvent";
    }

    // In this excute we have to make it so that customer is added to the queue
    @Override
    public Event execute() {
        this.state.setTimePassed(this.getTime());

        specificState = (StoreState) this.state;
        customer = (MakeCustomer) this.eventTarget;

        specificState.setCustomerId(customer.getCustomerId());
        specificState.setEventName(this.nameOfCurrentEvent);


        float next = scheduleNextEventTime();
        return new QueueEvent(next, (MakeCustomer) this.getEventTarget(), (StoreState) this.getState());
    }


    public float scheduleNextEventTime() {
        // Here we have to calculate the time
        // The customer have to wait in queque.
        // Have to create a queque somewhere. And get the paytime for everyone in front of this customer.
        return time;

    }

}
