package com.example.oualhaomar.insurancepi.Adabter;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oualhaomar.insurancepi.R;
import com.example.oualhaomar.insurancepi.Entities.Topic;
import com.example.oualhaomar.insurancepi.TopicActivity;

import java.util.List;
import java.util.Random;


public class TopicAdabter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<Topic> topicItems;
    Topic topic;
    int id;

    public TopicAdabter(Activity activity, List<Topic> topicItems ) {
        this.activity = activity;
        this.topicItems = topicItems;
        this.id=id;
    }



    @Override
    public int getCount() {
        return topicItems.size();
    }

    @Override
    public Object getItem(int location) {
        return topicItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.topic, null);



        TextView nom = (TextView) convertView.findViewById(R.id.name);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView sujet = (TextView) convertView.findViewById(R.id.sujet);
        ImageView img=(ImageView) convertView.findViewById(R.id.avatar);







        topic = topicItems.get(position);
     //   img.setImageResource(Integer.parseInt();





        String ch=topic.getIdCreateur().getAvatar();
        Context context = img.getContext();
        int id = context.getResources().getIdentifier("com.example.oualhaomar.insurancepi:drawable/"+ch, null, null);
        System.out.println("maman"+id);


       // img.setImageResource(id);
        img.setBackgroundResource(id);



        nom.setText("@"+topic.getUsername());


        sujet.setText(topic.getSujet());
        date.setText(topic.getDateCreation());


        return convertView;
    }


}







