package krys.threer.user.negocio;

import krys.threer.user.dominio.User;

/**
 * Created by Krys on 22/10/2015.
 */
public interface GetUserCallback {
    public abstract void done (User user);
}
