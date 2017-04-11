package cl.karangop.estresless.views.main;

import cl.karangop.estresless.models.Pending;

/**
 * Created by karan_000 on 15-02-2017.
 */

public class CreatePending {

    private PendingCallback callback;

    public CreatePending(PendingCallback callback) {
        this.callback = callback;
    }

    //esta validacion se envia a la MainActivity
    public void validation(String name){
        if (name.trim().length() >0)
        {
            Pending pending= new Pending();
            pending.setName(name);
            pending.setDone(false);
            pending.save();
            callback.created(pending);
        }else{
            callback.noName();
        }
    }
}
