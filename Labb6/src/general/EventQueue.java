package general;

import specific.*;

import java.util.ArrayList;

/**
 * Manages a queue of events for the simulation, handling the scheduling, execution,
 * and dynamic insertion of events based on their scheduled time. This class is
 * essential for controlling the flow of the simulation, ensuring events are processed
 * in chronological order and allowing for new events to be inserted into the queue at
 * the correct position.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */
public class EventQueue {


    // Utility method to print the event queue for tests
    private void printQueue(EventQueue eventQueue) {
        for (Event event : eventQueue.queueList) {
            System.out.println(event);
        }
    }

    //Creating a List such that
    // {Event,Event,Event,etc}

    ArrayList<Event> queueList = new ArrayList<>();
    private State state;

    /**
     * Constructs an event queue associated with a specific state of the simulation.
     *
     * @param state The state object that this queue will interact with during event execution.
     */
    public EventQueue(State state) {
        this.state = state;
    }


    // This method executes and removes the first event in ArrayList
    // The event that is created from the execution is then
    // inserted after the last element that has event.getTime()
    // Example: (Each element is a event)
    // {1,2,3,4,5,6,7} -> {2,3,4,5,5,5,6,7}
    //  newEvent = 1 -> newEvent = 5
    // inserting it -> {2,3,4,5,5,5,newEvent,6,7}

    /**
     * Executes the first event in the queue and then inserts the resultant event (if any)
     * back into the queue in the correct chronological position. After execution, it notifies
     * the state to update any observers, in this case view.
     */
    public void executeAndInsert() {
        if (queueList.isEmpty()) {

            this.state.notifyView();

            return; // Guard against an empty list
        }
        // Remove the first event from the queue and execute it

        Event executedEvent = this.queueList.remove(0).execute();

        this.state.notifyView();

        // Use addInsert to add the executed event into the queue at the correct position based on its time
        addInsert(executedEvent);

    }

    /**
     * Inserts an event into the queue in its correct chronological position.
     * If the event's time that is to be inserted is the same as another
     * already existing event, it is inserted after that event.
     *
     * @param addEvent The event to be added to the queue.
     */

    public void addInsert(Event addEvent) {
        if (addEvent == null) {
            return;
        }

        float addEventTime = addEvent.getTime();


        int insertIndex = queueList.size();

        for (int i = 0; i < queueList.size(); i++) {
            if (queueList.get(i).getTime() > addEventTime) {
                insertIndex = i;
                break;
            }
        }
        queueList.add(insertIndex, addEvent);

    }

    /**
     * Checks if the event queue is empty.
     *
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queueList.isEmpty();
    }

}
