package general;

import specific.ArrivalEvent;

public abstract class Event {


    //The event class works like this. A initial event is created as an object. When that event is excuted,
    //The object is then changed to the event that comes after the first one and so on.
    //So currentEvent = ArrivalEvent
    //  currentEvent = currentEvent.execute() -> currentEvent = nextEvent


    // The time of each Event is when the event is to be executed, not when the event started.
    // Meaning if we have an event called EventPay, it is not the time of when he started to pay.
    // It is the time when he is done paying.
    // Therefore, the time for the next event has to be calculated as the current event is executed.


    protected float time;

    protected String nameOfCurrentEvent;

    protected Object eventTarget;


    public Event(float time, Object eventTarget) {
        this.time = time;
        this.eventTarget = eventTarget;
    }

    public abstract Event execute();


    public float getTime() {
        return this.time;
    }

    public Object getEventTarget() {
        return this.eventTarget;
    }

    //This method is just used to run tests

    @Override
    public String toString() {
        return "Event{" +
                "time=" + time +
                ", nameOfCurrentEvent='" + nameOfCurrentEvent +
                " CustomerNumber= " + eventTarget +
                '\'' +
                '}';
    }


}
