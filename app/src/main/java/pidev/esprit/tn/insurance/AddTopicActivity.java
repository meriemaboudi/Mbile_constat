package com.example.oualhaomar.insurancepi;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.oualhaomar.insurancepi.Entities.Category;
import com.example.oualhaomar.insurancepi.Entities.SousCategory;
import com.example.oualhaomar.insurancepi.R;
import com.example.oualhaomar.insurancepi.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddTopicActivity extends AppCompatActivity {

    private List<SousCategory> categList = new ArrayList<SousCategory>();
    private String[] categ;
    Spinner spin;
    ArrayAdapter aa;
    Map<String,String> params = new HashMap<String, String>();
    String json_url="http://10.0.2.2:18080/insurance-web/pidev/SousCategory";
    String urlpost;
    Button ajouter;
    TextView sujet;
    TextView contenu;
    private RequestQueue requestQueue ;
    private StringRequest request ;
    AlertDialog.Builder alertDialogBuilder ;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);
        spin = (Spinner) this.findViewById(R.id.spinner);
       //  spin.setOnItemSelectedListener();
        ajouter = (Button) this.findViewById(R.id.add);
        sujet = (TextView) this.findViewById(R.id.subject);
        contenu = (TextView) this.findViewById(R.id.contenu);

        afficherCateg();
        ajouterTopic();




    }







    public void afficherCateg(){


        JsonArrayRequest jar = new JsonArrayRequest(json_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject=response.getJSONObject(i);
                                SousCategory soucat =new SousCategory();
                                soucat.setIdSousCategory(jsonObject.getInt("idSousCategory"));
                                soucat.setName(jsonObject.getString("name"));

                                categList.add(soucat);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }


                        aa = new ArrayAdapter(AddTopicActivity.this,android.R.layout.simple_spinner_item,categList);

                        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spin.setAdapter(aa);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(AddTopicActivity.class.getSimpleName() , "Error: " + error.getMessage());

            }
        });


        MySingleton.getIns(this).addToRequ(jar);


    }




    public void ajouterTopic(){




        ajouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SousCategory c=(SousCategory) spin.getSelectedItem();
               // System.out.println("category:"+c.getId());
                urlpost = "http://10.0.2.2:18080/insurance-web/pidev/Topic?id="+c.getIdSousCategory();


                Map<String,String> params = new HashMap<String,String>();
                params.put("sujet",sujet.getText().toString());
                params.put("contenu",contenu.getText().toString());
                JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, urlpost,new JSONObject(params),
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                Context context = AddTopicActivity.this.getApplicationContext();
                                CharSequence text ="ajout reussite ";

                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();



                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // dialog.hide();

                                Context context =AddTopicActivity.this.getApplicationContext();


                                int duration = Toast.LENGTH_SHORT;
                                Intent m = new Intent(AddTopicActivity.this,SousCatActivity.class);
                                startActivity(m);


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

                RequestQueue requestQueue = Volley.newRequestQueue(AddTopicActivity.this);
                requestQueue.add(jsonRequest);



            }



        });




    }




}
