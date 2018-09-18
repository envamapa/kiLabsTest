package com.mx.envamapa.app.wundertest.views.view.mainActivity.maps;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.Application;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.service.respCars.Car;
import com.mx.envamapa.app.wundertest.views.presenter.mapPresenter.MapPresenter;
import com.mx.envamapa.app.wundertest.views.view.mainActivity.MainActivityInterface;

import java.util.List;

public class MapFragment extends Fragment implements MapInterface, OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private OnFragmentInteractionListener mListener;

    private MainActivityInterface mainViewInterface;
    private View rootView;

    private GoogleMap mMap;

    private MapPresenter presenter;
    private List<Car> carList;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.activity_maps, container, false);

        presenter = new MapPresenter(this, ((Application)getContext().getApplicationContext()));
        presenter.getCars();

        return rootView;
    }

    @Override
    public void initMap(List<Car> carList){
        this.carList = carList;
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        addMarkers();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private void addMarkers(){
        //Add markers
        for(int i = 0; i < carList.size() ; i++){
            final Car car = carList.get(i);
            final LatLng sydney = new LatLng(car.getCoordinates()[0], car.getCoordinates()[1]);
            mMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    .icon(Utils.bitmapDescriptorFromVector(getContext(), R.drawable.ic_car)));
            mMap.setOnMarkerClickListener(this);
        }
    }

    private boolean tappedMarker = false;

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(tappedMarker){
            tappedMarker = false;
            addMarkers();
        }else{
            tappedMarker = true;
            //Hide all markers
            mMap.clear();
            mMap.addMarker(new MarkerOptions()
                    .position(marker.getPosition())
                    .icon(Utils.bitmapDescriptorFromVector(getContext(), R.drawable.ic_car)));
        }

        return tappedMarker;
    }
}
