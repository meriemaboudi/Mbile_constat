package pidev.esprit.tn.insurance;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pidev.esprit.tn.insurance.Adapter.SinisterAdapter;
import pidev.esprit.tn.insurance.Model.Sinister;
import pidev.esprit.tn.insurance.util.MySingleton;


public class ListSinisterFragment extends Fragment {

    View rootviews;
    ListView listView;
    public static SinisterAdapter adapter;
    private String mJSONURLString = "http://10.0.2.2:18080/insurance-web/api/sinister";
    public  static List<Sinister> lstSinister=new ArrayList<Sinister>();
    private Context mContext;
    public ListSinisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootviews = inflater.inflate(R.layout.fragment_add_sinister, container, false);
        viewSinister(inflater, container);

        return rootviews;

    }
    public void viewSinister(LayoutInflater inflater, ViewGroup container)
    {
        mContext = getActivity();
        rootviews = inflater.inflate(R.layout.fragment_list_sinister, container, false);

        listView = (ListView) rootviews.findViewById(R.id.listView);



        JsonArrayRequest jar = new JsonArrayRequest(mJSONURLString,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response){
                    int count = 0;
                        lstSinister.clear();
                        while (count < response.length()) {
                            try {
                                // Loop through the array elements

                                // Get current json object
                                JSONObject student = response.getJSONObject(count);
                                Log.i("1", "4");
                                // Get the current student (json object) data
                                String nameInsured = student.getString("nameInsured");
                                String nameConductor = student.getString("nameConductor");
                                int id = student.getInt("id");
                                Sinister sinister = new Sinister();
                                sinister.setNameFirstname(nameInsured);
                                sinister.setPolicyNum(nameConductor);
                                sinister.setId(id);
                                Log.i("NameInsured", sinister.getNameFirstname());
                                lstSinister.add(sinister);

                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //  hidePDialog();
            }
        });



        MySingleton.getIns(mContext).addToRequ(jar);
        adapter = new SinisterAdapter(getActivity(), lstSinister);
        listView.setAdapter(adapter);




    }








}
