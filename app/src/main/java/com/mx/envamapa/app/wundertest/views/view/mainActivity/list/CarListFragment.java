package com.mx.envamapa.app.wundertest.views.view.mainActivity.list;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Toast;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.EndlessScrollListener;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.commons.adapters.CarAdapter;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.views.presenter.listPresenter.CarListPresenter;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.MainActivityInterface;

import java.util.List;

public class CarListFragment extends Fragment implements CarListInterface{

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 2;
    private OnListFragmentInteractionListener mListener;
    private MainActivityInterface mainViewInterface;


    //UI
    private View rootView;
    private RecyclerView recyclerView;

    private CarListPresenter presenter;
    private int initCount = 0;
    private CarAdapter carAdapter;
    private EndlessScrollListener scrollListener;

    public CarListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_car_list, container, false);

        presenter = new CarListPresenter(this, ((Application)getContext().getApplicationContext()));

        initView();

        presenter.get15Cars(initCount);

        return rootView;
    }

    private void initView(){
        if (rootView instanceof RecyclerView) {
            Context context = rootView.getContext();
            recyclerView = (RecyclerView) rootView;
            if (mColumnCount <= 1) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                scrollListener = new EndlessScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        updateCount();
                    }
                };
            } else {
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, mColumnCount);
                recyclerView.setLayoutManager(gridLayoutManager);
                scrollListener = new EndlessScrollListener(gridLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        updateCount();
                    }
                };
            }
        }

        recyclerView.addOnScrollListener(scrollListener);
    }

    private void updateCount(){
        initCount+= Constants.ITEM_COUNT;
        presenter.get15Cars(initCount);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mainViewInterface = (MainActivityInterface) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentToActivity");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Car item);
    }

    @Override
    public void reloadList(List<Car> carList){
        if(initCount != 0){
            carAdapter.addElements(carList);
        }else{
            carAdapter = new CarAdapter(getContext(), carList, mListener);
        }
        Toast.makeText(getContext(), "Showing "+carAdapter.getItemCount()+" cars", Toast.LENGTH_SHORT).show();
        recyclerView.setAdapter(carAdapter);
    }
}
