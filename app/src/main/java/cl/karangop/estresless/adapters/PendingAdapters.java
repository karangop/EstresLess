package cl.karangop.estresless.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cl.karangop.estresless.R;
import cl.karangop.estresless.data.Queries;
import cl.karangop.estresless.models.Pending;

/**
 * Created by karan_000 on 15-02-2017.
 */

public class PendingAdapters extends RecyclerView.Adapter<PendingAdapters.ViewHolder>{

    private List<Pending> pendings = new Queries().notDone();
    private PendingClickListener listener; //creamos interface

    public PendingAdapters(PendingClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_pending, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Pending pending= pendings.get(position);

        holder.textView.setText(pending.getName());
        holder.checkBox.setChecked(pending.isDone());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    final int adapterPosition= holder.getAdapterPosition();

                    Pending auxPending= pendings.get(adapterPosition);
                    auxPending.setDone(true); //cambia el estado cuando se chequea
                    auxPending.save(); //guardar en la base de datos

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            pendings.remove(adapterPosition);
                            notifyItemRemoved(adapterPosition);

                        }
                    },200);

                }

            }
        });

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Pending auxPending= pendings.get(holder.getAdapterPosition());
                listener.clickedId(auxPending.getId());
//                listener.clickedPending(auxPending);

            }
        });

    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }

    public void addPending(Pending pending){
        pendings.add(pending);
        notifyDataSetChanged();
        //cambia la data cuando se modifica
    }
    
    public void resetEverything()
    {
        List<Pending> done= new Queries().done();

        int listSize= pendings.size();

        for (int i = 0; i < done.size(); i++) {
            Pending pending= done.get(i);
            pending.setDone(false);
            pending.save();
            pendings.add(pending);
        }
        //notifyDataSetChanged();
        notifyItemRangeInserted(listSize, pendings.size());
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        //Los mismos elementos que xml
        private final CheckBox checkBox;
        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox= (CheckBox) itemView.findViewById(R.id.pendingCb);
            textView= (TextView) itemView.findViewById(R.id.pendingTv);


        }

    }
}
