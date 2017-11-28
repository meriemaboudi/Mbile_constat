package com.example.oualhaomar.insurancepi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateMessagesActivity extends AppCompatActivity {

    TextView tv_username;
    TextView tv_date;
    EditText tv_text_comment;
    Button ajouter;
    private String json_url_updatemsg;
    private String cnt;
    private int idmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_messages);


        tv_text_comment = (EditText) findViewById(R.id.tv_text_comment);
        ajouter =(Button) findViewById(R.id.ajouter);


        Intent mIntent = getIntent();
        cnt = "";
        cnt=mIntent.getStringExtra("contenu");
        idmsg=(int) mIntent.getSerializableExtra("idmsg");
        tv_text_comment.setText(cnt);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                UpdateMsg(idmsg);


            }
        });
    }


    public void UpdateMsg(int idmsg){
        json_url_updatemsg="http://10.0.2.2:18080/insurance-web/pidev/Message?idMessage="+idmsg;

        Map<String,String> params = new HashMap<String, String>();
        params.put("contenu",tv_text_comment.getText().toString());

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.PUT, json_url_updatemsg,new JSONObject(params),
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

                        Intent intent = new Intent(UpdateMessagesActivity.this,MessagesActivity.class);
                        intent.putExtra("idtopic", MessagesActivity.idtopic);


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

    }

}
