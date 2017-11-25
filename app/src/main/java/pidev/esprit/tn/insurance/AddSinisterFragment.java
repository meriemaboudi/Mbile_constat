package pidev.esprit.tn.insurance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class AddSinisterFragment extends Fragment {
    View rootviews;


    public AddSinisterFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootviews = inflater.inflate(R.layout.fragment_add_sinister, container, false);

        return inflater.inflate(R.layout.fragment_add_sinister, container, false);
    }


}
