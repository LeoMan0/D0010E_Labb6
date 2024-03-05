package specific;

import general.Event;

public class EnterEvent extends Event {


    public EnterEvent(float time) {
        super(time);
        this.nameOfCurrentEvent = "EnterEvent";
    }


    @Override
    public Event execute() {
        getEventStatus();

        float next = scheduleNextEventTime();

        return new PickEvent(next);
    }

    public float scheduleNextEventTime() {
        //This number is a random number in a range.
        // This is the pick time. Can be taken from state.
        return ++time;

    }

}
