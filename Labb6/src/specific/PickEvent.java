package specific;

import general.Event;

import java.util.Queue;

public class PickEvent extends Event {

    public PickEvent(float time, MakeCustomer customer) {
        super(time, customer);
        this.nameOfCurrentEvent = "PickEvent";
    }

    // In this excute we have to make it so that customer is added to the queue
    @Override
    public Event execute() {


        float next = scheduleNextEventTime();

        return new QueueEvent(next, (MakeCustomer) this.getEventTarget());
    }


    public float scheduleNextEventTime() {
        // Here we have to calculate the time
        // The customer have to wait in queque.
        // Have to create a queque somewhere. And get the paytime for everyone in front of this customer.
        return ++time;

    }

}
