package specific;

import general.State;
import general.View;

import java.util.Observable;

public class StoreView extends View {

    private State StoreState;

    public StoreView(State StoreState) {
        this.StoreState = StoreState;
        StoreState.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Success");
    }
}
