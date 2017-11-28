package com.example.oualhaomar.insurancepi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.oualhaomar.insurancepi.Adabter.MessagesAdabter;
import com.example.oualhaomar.insurancepi.Adabter.TopicAdabter;
import com.example.oualhaomar.insurancepi.Entities.Messages;
import com.example.oualhaomar.insurancepi.Entities.SousCategory;
import com.example.oualhaomar.insurancepi.Entities.Topic;
import com.example.oualhaomar.insurancepi.Entities.User;
import com.example.oualhaomar.insurancepi.util.MySingleton;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessagesActivity extends AppCompatActivity {

    public List<Messages> lst_Msg=new ArrayList<Messages>();


    private ListView listView;
    MessagesAdabter adapter;

    private ImageButton msgsend;
    private EditText msgtext;

    public static int idtopic;


    String json_url="http://10.0.2.2:18080/insurance-web/pidev/Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        msgtext=(EditText) findViewById(R.id.confirm);
        msgsend=(ImageButton) findViewById(R.id.bt_send);

        viewTopic();
        ajoutermsg();

        ShareLinkContent content1 = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://stackoverflow.com/"))
                //.setContentTitle("slt")
                //.setContentDescription("slt")
                .build();

        ShareButton shareButton = (ShareButton) findViewById(R.id.shareButton);
        shareButton.setShareContent(content1);





    }


    public  void viewTopic()
    {

        listView = (ListView) findViewById(R.id.listMsg);




        JsonArrayRequest jar = new JsonArrayRequest(json_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        Intent myintent=getIntent();

                         idtopic=(int) myintent.getSerializableExtra("idtopic");


                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject=response.getJSONObject(i);

                                Topic t =new Topic();
                                User user = new User();
                                Messages msg = new Messages();


                              t.setIdTopic(jsonObject.getJSONObject("topic").getInt("idTopic"));


                                user.setAvatar(jsonObject.getJSONObject("idPosteur").getString("avatar"));
                                user.setLogin(jsonObject.getJSONObject("idPosteur").getString("login"));



                               // t.setIdCreateur(user);
                                t.setIdTopic(jsonObject.getJSONObject("topic").getInt("idTopic"));
                                t.setSujet(jsonObject.getJSONObject("topic").getString("sujet"));
                                t.setContenu( jsonObject.getJSONObject("topic").getString("contenu"));
                                t.setUsername(jsonObject.getJSONObject("topic").getJSONObject("idCreateur").getString("login"));
                                t.setDateCreation(jsonObject.getJSONObject("topic").getString("dateCreation"));




                                if(idtopic==t.getIdTopic())
                                {

                                    msg.setTopic(t);
                                    msg.setUser(user);
                                    msg.setIdMessage(jsonObject.getInt("idMessage"));

                                    msg.setDateEdit(jsonObject.getString("dateEdit"));
                                    msg.setDatePost(jsonObject.getString("datePost"));

                                    msg.setContenu(jsonObject.getString("contenu"));
                                    lst_Msg.add(msg);
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }


                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TopicActivity.class.getSimpleName() , "Error: " + error.getMessage());
                //  hidePDialog();
            }
        });



        MySingleton.getIns(this).addToRequ(jar);




        adapter = new MessagesAdabter(this, lst_Msg);
        listView.setAdapter(adapter);








    }



    public void ajoutermsg() {

        msgsend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myintent=getIntent();

                final int idtopic=(int) myintent.getSerializableExtra("idtopic");

                String urlpost ="http://10.0.2.2:18080/insurance-web/pidev/Message?idtopic="+idtopic;


                Map<String,String> params = new HashMap<String, String>();
                params.put("contenu",msgtext.getText().toString());

                JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, urlpost,new JSONObject(params),
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {

                                CharSequence text ="ajout reussite ";

                                int duration = Toast.LENGTH_SHORT;



                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                int duration = Toast.LENGTH_SHORT;

                                Toast.makeText(getApplicationContext(),"messages ajouter avec succées",duration).show();
                              //  MessagesActivity.this.finish();

                                Intent intent = new Intent(MessagesActivity.this,MessagesActivity.class);
                                intent.putExtra("idtopic", idtopic);


                                startActivity(intent);
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

                RequestQueue requestQueue = Volley.newRequestQueue(getApplication());
                requestQueue.add(jsonRequest);
                //  Intent mIntent = new Intent(TopicActivity.this, TopicActivity.class);

                Intent intent=MessagesActivity.this.getIntent();
                MessagesActivity.this.finish();
                MessagesActivity.this.startActivity(intent);
            }
        });
    }



}
