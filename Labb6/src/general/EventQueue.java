package general;

import specific.*;

import java.util.ArrayList;

public class EventQueue {

    //Just here to test stuff
    public static void main(String[] args) {

    }

    // Utility method to print the event queue for tests
    public void printQueue(EventQueue eventQueue) {
        for (Event event : eventQueue.queueList) {
            System.out.println(event);
        }
    }

    //Creating a List such that
    // {Event,Event,Event,etc}
    ArrayList<Event> queueList = new ArrayList<>();
    private State state;

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

    public boolean isEmpty() {
        return queueList.isEmpty();
    }

}
