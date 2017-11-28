package com.example.oualhaomar.insurancepi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.oualhaomar.insurancepi.Adabter.TopicAdabter;
import com.example.oualhaomar.insurancepi.Entities.SousCategory;
import com.example.oualhaomar.insurancepi.Entities.Topic;
import com.example.oualhaomar.insurancepi.Entities.User;
import com.example.oualhaomar.insurancepi.util.MySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TopicActivity extends AppCompatActivity {

    public List<Topic> lst_Topic=new ArrayList<Topic>();


    private ListView listView;
    TopicAdabter adapter;
    private Button delete;

    String json_url="http://10.0.2.2:18080/insurance-web/pidev/Topic";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(TopicActivity.this, AddTopicActivity.class);
                startActivity(mIntent);
            }
        });



        viewTopic();

    }







    public  void viewTopic()
    {

        listView = (ListView) findViewById(R.id.listTopic);




        JsonArrayRequest jar = new JsonArrayRequest(json_url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {


                        Intent intent=getIntent();
                        int idsccategory=(int) intent.getSerializableExtra("idsouscategory");


                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject jsonObject=response.getJSONObject(i);
                                Topic t =new Topic();
                                SousCategory souscat= new SousCategory();
                                User user = new User();


                                souscat.setIdSousCategory(jsonObject.getJSONObject("sousCategory").getInt("idSousCategory"));
                                souscat.setName(jsonObject.getJSONObject("sousCategory").getString("name"));

                                user.setAvatar(jsonObject.getJSONObject("idCreateur").getString("avatar"));


                                System.out.println("aa"+idsccategory);
                                System.out.println("bb"+souscat.getIdSousCategory());
                                t.setIdCreateur(user);
                                System.out.println("aaaaho"+t.getIdCreateur().getAvatar());
                                System.out.println("kkkk"+jsonObject.getJSONObject("idCreateur").getString("avatar"));

                                if(idsccategory==souscat.getIdSousCategory())
                                {
                                    t.setIdTopic(jsonObject.getInt("idTopic"));
                                    t.setSujet(jsonObject.getString("sujet"));
                                    t.setContenu( jsonObject.getString("contenu"));
                                    t.setUsername(jsonObject.getJSONObject("idCreateur").getString("login"));
                                    t.setIdSouscategory(souscat);
                                    t.setDateCreation(jsonObject.getString("dateCreation"));



                                    lst_Topic.add(t);
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




        adapter = new TopicAdabter(this, lst_Topic);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent myIntent = new Intent(TopicActivity.this, MessagesActivity.class);
                myIntent.putExtra("idtopic", lst_Topic.get(position).getIdTopic());


                startActivity(myIntent);

            }
        });





    }

}
