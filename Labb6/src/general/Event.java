package general;


/**
 * Serves as the base class for all events within the simulation, encapsulating the common
 * functionalities and properties that every event must have. Events are created with a specific
 * execution time and, when executed, can result in the creation of a subsequent event, effectively
 * driving the simulation forward.
 * <p>
 * The execution time of an event represents the moment it is completed rather than when it started.
 * For example, for a payment event, the time signifies the completion of the payment process.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

public abstract class Event {


    protected float time;
    protected String nameOfCurrentEvent;
    protected Object eventTarget;
    protected State state;

    /**
     * Constructs a new Event with a specified execution time, target, and state.
     *
     * @param time        The time at which the event is to be executed.
     * @param eventTarget The target object of the event, in this project only customer.
     * @param state       The current state of the simulation that the event can modify.
     */

    public Event(float time, Object eventTarget, State state) {
        this.time = time;
        this.eventTarget = eventTarget;
        this.state = state;
    }

    /**
     * Executes the event, affecting the simulation state as necessary and possibly generating
     * a subsequent event to be added to the event queue.
     *
     * @return The next event to occur following the execution of this event, or null if no
     * subsequent event is generated.
     */
    public abstract Event execute();


    public float getTime() {
        return this.time;
    }

    public Object getEventTarget() {
        return this.eventTarget;
    }

    public State getState() {
        return state;
    }

    /**
     * Provides a string representation of the event for debugging and testing purposes,
     * including its execution time and the type or name of the event.
     *
     * @return A string description of the event.
     */
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
