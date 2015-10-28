package krys.threer.RecycleStore.negocio;

import java.util.ArrayList;

import krys.threer.RecycleStore.domino.RecycleStore;

/**
 * Created by Krys on 24/10/2015.
 */
public interface GetRecylceCallback {

    public abstract void done(ArrayList<RecycleStore> recycleList);

}
