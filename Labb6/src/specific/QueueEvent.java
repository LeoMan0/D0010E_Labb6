package specific;

import general.Event;

public class QueueEvent extends Event {

    public QueueEvent(float time) {
        super(time);
        this.nameOfCurrentEvent = "QueueEvent";
    }

    // Likewise we have to make it so that the customer leaves the queue here
    @Override
    public Event execute() {
        getEventStatus();

        float next = scheduleNextEventTime();

        return new PayEvent(next);
    }

    public float scheduleNextEventTime() {
        //This number is a random number in a range.
        // This is the pay time. Can be taken from state.
        return time;

    }


}