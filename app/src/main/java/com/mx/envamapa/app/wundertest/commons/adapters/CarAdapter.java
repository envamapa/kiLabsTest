package com.mx.envamapa.app.wundertest.commons.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Constants;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.list.CarListFragment;

import java.util.List;

/**
 * Created by enya on 18/09/18.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private final List<Car> mValues;
    private final CarListFragment.OnListFragmentInteractionListener mListener;
    private final Context context;

    public CarAdapter(Context context, List<Car> items, CarListFragment.OnListFragmentInteractionListener listener) {
        this.context = context;
        this.mValues = items;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false);
        ViewHolder item = new ViewHolder(v);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull CarAdapter.ViewHolder holder, int position) {
        Car car = mValues.get(position);

        holder.carName.setText(car.getName() != null ? car.getName() : "No information");
        holder.exteriorAnim.setAnimation(Utils.getAnimation(car.getExterior()));
        holder.interiorAnim.setAnimation(Utils.getAnimation(car.getInterior()));
        holder.fuel.setText(Integer.toString(car.getFuel()));
        holder.engineType.setText(car.getEngineType());
        holder.address.setText(car.getAddress());
        holder.vin.setText(car.getVin());
    }

    public void addElements(List<Car> items){
        this.mValues.addAll(items);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView carName;
        final TextView exterior;
        final LottieAnimationView exteriorAnim;
        final TextView interior;
        final LottieAnimationView interiorAnim;
        final TextView fuel;
        final TextView engineType;
        final TextView address;
        final TextView vin;

        ViewHolder(View view) {
            super(view);
            mView = view;
            carName =  view.findViewById(R.id.car_name_tv);
            exterior = view.findViewById(R.id.exterior_tv);
            exteriorAnim = view.findViewById(R.id.exterior_animation);
            interior = view.findViewById(R.id.interior_tv);
            interiorAnim = view.findViewById(R.id.interior_animation);
            fuel = view.findViewById(R.id.fuel_tv);
            engineType = view.findViewById(R.id.engine_type_tv);
            address = view.findViewById(R.id.address_tv);
            vin = view.findViewById(R.id.vin_tv);
        }
    }
}
