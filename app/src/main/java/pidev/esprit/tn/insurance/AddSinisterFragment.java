package pidev.esprit.tn.insurance;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


public class AddSinisterFragment extends Fragment {

    View rootviews;
    ListView listView;
    public static SinisterAdapter adapter;

    public AddSinisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        Log.e("onCreateView","onCreateView");

        rootviews = inflater.inflate(R.layout.fragment_add_sinister, container, false);

        listView = (ListView) rootviews.findViewById(R.id.listView);


        adapter = new SinisterAdapter(getActivity(),MainActivity.listSinister);
        if (!adapter.isEmpty())
            Log.i("adapter", adapter.getItem(0).toString());



        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();



        return inflater.inflate(R.layout.fragment_add_sinister, container, false);
    }








}
