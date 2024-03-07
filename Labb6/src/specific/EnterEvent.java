package specific;

import general.Event;

public class EnterEvent extends Event {


    public EnterEvent(float time, MakeCustomer customer) {
        super(time, customer);
        this.nameOfCurrentEvent = "EnterEvent";
    }


    @Override
    public Event execute() {

        float next = scheduleNextEventTime();

        return new PickEvent(next, (MakeCustomer) this.getEventTarget());
    }

    public float scheduleNextEventTime() {
        //This number is a random number in a range.
        // This is the pick time. Can be taken from state.
        return ++time;

    }

}
