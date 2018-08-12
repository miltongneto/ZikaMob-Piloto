package com.zika.lika.zikamob.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.zika.lika.zikamob.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Collect1Fragment extends Fragment {


    private ImageButton btn_camera;

    public Collect1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collect1, container, false);

        btn_camera = (ImageButton) view.findViewById(R.id.collect1_btn_camera);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new Collect2Fragment())
                        .commit();
            }
        });

        return view;
    }

}
