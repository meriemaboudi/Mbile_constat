package pidev.esprit.tn.insurance;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pidev.esprit.tn.insurance.Model.Sinister;

/**
 * Created by aboud on 24/11/2017.
 */

public class Consommation extends Activity {
    private String mJSONURLString = "http://10.0.2.2:18080/insurance-web/api/sinister";
    private Context mContext;

    public void getLisSinister() {

        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;
                        while (count < response.length()) {
                            try {
                                // Loop through the array elements

                                // Get current json object
                                JSONObject student = response.getJSONObject(count);
                                Log.i("1", "4");
                                // Get the current student (json object) data
                                String nameInsured = student.getString("nameInsured");
                                String nameConductor = student.getString("nameConductor");
                                Sinister sinister = new Sinister();
                                sinister.setNameInsured(nameInsured);
                                sinister.setNameConductor(nameConductor);
                                MainActivity.listSinister.add(sinister);
                                count++;
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Do something when error occurred
                Log.i("1", "*****");

            }
        }
        );
        // Add JsonArrayRequest to the RequestQueue
        requestQueue.add(jsonArrayRequest);


    }
}
