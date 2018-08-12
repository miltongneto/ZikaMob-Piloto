package com.zika.lika.zikamob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.zika.lika.zikamob.R;
import com.zika.lika.zikamob.enums.LocalFocus;
import com.zika.lika.zikamob.enums.TypeFocus;

/**
 * A simple {@link Fragment} subclass.
 */
public class Collect2Fragment extends Fragment {

    private Spinner spn_local;
    private Spinner spn_type;
    private Spinner spn_solution;
    private EditText edt_obs;
    private Button btn_confirm;

    public Collect2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collect2, container, false);

        spn_local = (Spinner) view.findViewById(R.id.collect2_spn_local);
        spn_type = (Spinner) view.findViewById(R.id.collect2_spn_type);
        spn_solution = (Spinner) view.findViewById(R.id.collect2_spn_solution);
        edt_obs = (EditText) view.findViewById(R.id.collect2_edt_obs);
        btn_confirm = (Button) view.findViewById(R.id.collect2_btn_confirm);

        loadSpinners();

        return view;
    }

    private void loadSpinners() {
        ArrayAdapter<String> local_adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, LocalFocus.getDescriptions());
        local_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_local.setAdapter(local_adapter);

        ArrayAdapter<String> type_adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, TypeFocus.getDescriptions());
        type_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_type.setAdapter(type_adapter);

        ArrayAdapter<String> solution_adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, LocalFocus.getDescriptions());
        solution_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_solution.setAdapter(solution_adapter);
    }

}
