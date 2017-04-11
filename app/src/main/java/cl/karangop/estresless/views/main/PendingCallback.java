package cl.karangop.estresless.views.main;

import cl.karangop.estresless.models.Pending;

/**
 * Created by karan_000 on 15-02-2017.
 */

public interface PendingCallback {

    void created(Pending pending);
    void noName();
}
