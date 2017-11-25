package pidev.esprit.tn.insurance;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aboud on 23/11/2017.
 */

public class JSONTestActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private CoordinatorLayout mCLayout;
    private Button mButtonDo;
    private TextView mTextView;
    private String mJSONURLString = "http://10.0.2.2:18080/insurance-web/api/sinister";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsontest);

        // Get the application context
        mContext = getApplicationContext();
        mActivity = JSONTestActivity.this;

        // Get the widget reference from XML layout
        mCLayout = (CoordinatorLayout) findViewById(R.id.coordinator_layout);
        mButtonDo = (Button) findViewById(R.id.btn_do);
        mTextView = (TextView) findViewById(R.id.tv);



        final String storeName = "xyz";
        final String couponType = "sale";







        Map<String,String> params = new HashMap<String,String>();
        params.put("nameInsured",storeName);
        params.put("nameConductor",couponType);

        JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, mJSONURLString,new JSONObject(params),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {


                        Context context = getApplicationContext();
                        CharSequence text ="inscription reussite , votre compte sera activé dans une certaine délai";

                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Context context = getApplicationContext();


                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, error.getMessage(), duration);
                        toast.show();
                    }
                })

        {

            @Override
            public Map<String, String> getHeaders()  {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);
























        Log.i("1","1");
        // Set a click listener for button widget
        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Empty the TextView
                mTextView.setText("");

                // Initialize a new RequestQueue instance
                Log.i("1","2");
                RequestQueue requestQueue = Volley.newRequestQueue(mContext);

                // Initialize a new JsonArrayRequest instance
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                        Request.Method.GET,
                        mJSONURLString,
                        null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                int count=0;
                                while (count<response.length()){
                                    try {
                                    // Loop through the array elements

                                        // Get current json object
                                        JSONObject student = response.getJSONObject(count);
                                        Log.i("1","4");
                                        // Get the current student (json object) data
                                        String firstName = student.getString("nameInsured");
                                        String lastName = student.getString("nameConductor");
                                        String age = student.getString("tel");

                                        // Display the formatted json data in text view
                                        mTextView.append(firstName +" " + lastName +"\nAge : " + age);
                                        mTextView.append("\n\n");
                                        count++;
                                }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

            }
                                     }, new Response.ErrorListener(){
                                         @Override
                                         public void onErrorResponse(VolleyError error){
                                             // Do something when error occurred
                                             Log.i("1","*****");
                                             Snackbar.make(
                                                     mCLayout,
                                                     "Error..."+error.getMessage(),
                                                     Snackbar.LENGTH_LONG
                                             ).show();
                                         }
                                     }
        );
                // Add JsonArrayRequest to the RequestQueue
                requestQueue.add(jsonArrayRequest);
            }
        });
    }



}
