/**
 * Serves as an abstract base class for creating views that observe and react to changes
 * in the simulation state.
 *
 * @author Leo Man, Jacky Phuong, Leo Vedberg, Viktor Sundén
 */

package general;

import java.util.Observer;
import java.util.Observable;

//  A class for general view
/**
 * Abstract class that implements an observer.
 */
@SuppressWarnings("deprecation")
public abstract class View implements Observer {
	
	/**
	 * Method that updates the view by using the observer.
	 * 
	 * @param o The observable object.
	 * @param arg An argument passed to the {@code notifyObservers} method.
	 */
    public abstract void update(Observable o, Object arg);
}
