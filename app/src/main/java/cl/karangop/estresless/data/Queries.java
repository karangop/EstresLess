package cl.karangop.estresless.data;

import java.util.ArrayList;
import java.util.List;

import cl.karangop.estresless.models.Pending;

/**
 * Created by karan_000 on 15-02-2017.
 */

public class Queries {

    public List<Pending> notDone(){
        List<Pending> pendings= new ArrayList<>();

        //List<Pending> pendingList= Pending.listAll(Pending.class);

        List<Pending> pendingList= Pending.find(Pending.class,"done = 0"); //done= 0 -->significa falso

        if(pendingList != null && pendingList.size() > 0){
            pendings.addAll(pendingList);
        }
        return pendings;
    }

    public List<Pending> done()
    {
        List<Pending> pendings= new ArrayList<>();
        List<Pending> pendingList= Pending.find(Pending.class, "done=1"); //done= 1 -->verdadero
        if(pendingList != null && pendingList.size() > 0){
            pendings.addAll(pendingList);
        }
        return pendings;
    }
}
