package com.example.oualhaomar.insurancepi.Adabter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oualhaomar.insurancepi.Entities.Messages;
import com.example.oualhaomar.insurancepi.Entities.SousCategory;
import com.example.oualhaomar.insurancepi.R;
import com.example.oualhaomar.insurancepi.UpdateMessagesActivity;

import java.util.List;

/**
 * Created by oualhaomar on 26/11/2017.
 */

public class MessagesAdabter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Messages> msgItems;
    Messages msg;

    public MessagesAdabter(Activity activity, List<Messages> msgItems) {
        this.activity = activity;
        this.msgItems = msgItems;
    }




    @Override
    public int getCount() {
        return msgItems.size();
    }

    @Override
    public Object getItem(int location) {
        return msgItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.messages, null);



        TextView nom = (TextView) convertView.findViewById(R.id.name);
        ImageButton modifier=(ImageButton) convertView.findViewById(R.id.modifier);



        modifier.setFocusable(false);
        modifier.setFocusableInTouchMode(false);
        modifier.setClickable(true);

        modifier.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent mIntent = new Intent(activity, UpdateMessagesActivity.class);




                mIntent.putExtra("contenu",msgItems.get(position).getContenu() );
                mIntent.putExtra("idmsg",msgItems.get(position).getIdMessage() );
                System.out.println("contenu:"+msgItems.get(position).getContenu());
                activity.startActivity(mIntent);

            }
        });


        msg = msgItems.get(position);

        nom.setText("@"+msg.getContenu());


        return convertView;
    }
}
