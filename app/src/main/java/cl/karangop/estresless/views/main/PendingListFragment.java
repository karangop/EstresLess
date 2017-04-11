package cl.karangop.estresless.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.karangop.estresless.R;
import cl.karangop.estresless.adapters.PendingAdapters;
import cl.karangop.estresless.adapters.PendingClickListener;
import cl.karangop.estresless.models.Pending;
import cl.karangop.estresless.views.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PendingListFragment extends Fragment implements PendingClickListener{

    public static final String PENDING= "cl.karangop.estresless.views.main.PendingListFragment.PENDING";
    private PendingAdapters adapter;

    public PendingListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pending_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView= (RecyclerView) view.findViewById(R.id.pendingsRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter= new PendingAdapters(this);
        recyclerView.setAdapter(adapter);

        final SwipeRefreshLayout refreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.reloadSr);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.resetEverything();
                       refreshLayout.setRefreshing(false);
                    }
                },600);
            }
        });
    }

    public void addPending(Pending pending){
        adapter.addPending(pending);

    }

    @Override
    public void clickedId(long id) {
        Intent intent= new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("id", id); //La clave es "id", en la otra activity rescatamos ese dato
        startActivity(intent);

    }

    @Override
    public void clickedPending(Pending pending) {
        /*Intent intent= new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra("pending", pending);
        intent.putExtra(PENDING, pending);
        startActivity(intent);*/

    }
}
