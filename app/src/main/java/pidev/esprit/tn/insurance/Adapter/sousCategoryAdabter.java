package com.example.oualhaomar.insurancepi.Adabter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oualhaomar.insurancepi.R;
import com.example.oualhaomar.insurancepi.Entities.SousCategory;

import java.util.List;
import java.util.Random;


public class sousCategoryAdabter extends BaseAdapter{

    private Activity activity;
    private LayoutInflater inflater;
    private List<SousCategory> categItems;
    SousCategory c;

    public sousCategoryAdabter(Activity activity, List<SousCategory> categItems) {
        this.activity = activity;
        this.categItems = categItems;
    }



    @Override
    public int getCount() {
        return categItems.size();
    }

    @Override
    public Object getItem(int location) {
        return categItems.get(location);
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
            convertView = inflater.inflate(R.layout.souscategory, null);


        c = categItems.get(position);




        TextView nom = (TextView) convertView.findViewById(R.id.name);
        ImageView img = (ImageView) convertView.findViewById(R.id.avatar);
        TextView first = (TextView) convertView.findViewById(R.id.first);



        nom.setText(c.getName());

        String s=c.getName().substring(0,1);

        first.setText(s);




        Context context = img.getContext();
        Random r = new Random();
        int i1 = r.nextInt(6 - 1) + 1;


        String ch="c"+i1;







        int id = context.getResources().getIdentifier("com.example.oualhaomar.insurancepi:drawable/"+ch, null, null);
        System.out.println("maman"+id);
        img.setBackgroundResource(id);




        return convertView;
    }

}







