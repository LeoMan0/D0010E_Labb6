/*
 * @ Leo Man
 * @ Leo Vedberg
 * @ Jacky Phuong
 * @ Viktor Sundén
 */

package general;

import java.util.Observable;

/**
 * Provides a foundational abstraction for managing the state of the simulation.
 * This class holds the core state variables such as the simulation's current time
 * and the running status. It extends {@link Observable} to implement the observer pattern.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

@SuppressWarnings("deprecation")
public abstract class State extends Observable {

    private boolean run = true;
    private float timePassed = 0;


    // Check if simulator is stopped

    public void stop() {
        run = false;
    }

    public boolean getRun() {
        return run;
    }

    /**
     * Retrieves the amount of time that has passed in the simulation.
     *
     * @return The elapsed time in the simulation.
     */
    public float getTimePassed() {
        return timePassed;
    }

    /**
     * Updates the elapsed time in the simulation.
     *
     * @param newTimePassed The new time value to be set as the elapsed time.
     */
    public void setTimePassed(float newTimePassed) {
        this.timePassed = newTimePassed;
    }


    /**
     * Notifies all observers of a change in the simulation's state. This method
     * is called whenever the state changes in a way that observers, such
     * as views displaying simulation data, need to be updated.
     */
    public void notifyView() {
        setChanged();
        notifyObservers();
    }


}
