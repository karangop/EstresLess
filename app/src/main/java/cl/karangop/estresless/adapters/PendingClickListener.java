package cl.karangop.estresless.adapters;

import cl.karangop.estresless.models.Pending;

/**
 * Created by karan_000 on 20-02-2017.
 */

public interface PendingClickListener {

    void clickedId(long id); //En Sugar el id es long
    void clickedPending(Pending pending);
}
