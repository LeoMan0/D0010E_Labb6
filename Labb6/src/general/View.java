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
@SuppressWarnings("deprecation")
public abstract class View implements Observer {
    public abstract void update(Observable o, Object arg);
}
