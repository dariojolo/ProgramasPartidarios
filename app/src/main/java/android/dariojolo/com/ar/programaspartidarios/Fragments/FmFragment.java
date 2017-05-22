package android.dariojolo.com.ar.programaspartidarios.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.dariojolo.com.ar.programaspartidarios.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FmFragment extends Fragment {


    public FmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fm, container, false);
    }

}
