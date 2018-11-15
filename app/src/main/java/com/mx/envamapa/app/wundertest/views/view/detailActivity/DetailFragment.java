package com.mx.envamapa.app.wundertest.views.view.detailActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mx.envamapa.app.wundertest.R;
import com.mx.envamapa.app.wundertest.commons.ImageSize;
import com.mx.envamapa.app.wundertest.commons.Utils;
import com.mx.envamapa.app.wundertest.data.sources.service.respPhotos.Photo;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

import static com.mx.envamapa.app.wundertest.commons.Constants.EXTRA_ITEM;

/**
 * Created by enya on 11/15/18.
 */

public class DetailFragment extends Fragment {

    //UI Elements
    private PhotoView image;
    private View root;


    public static Fragment newInstance(Photo item) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_ITEM, item);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_detail, container, false);
        initView();
        return root;
    }

    private void initView(){
        image = root.findViewById(R.id.image);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Photo item = (Photo) getArguments().getSerializable(EXTRA_ITEM);
        Utils.bindImage(item.getImageUrl(ImageSize.LARGE), image, false, R.drawable.ic_image_24dp, R.drawable.ic_broken_image_24dp);
        image.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
            }
        });
    }

}
