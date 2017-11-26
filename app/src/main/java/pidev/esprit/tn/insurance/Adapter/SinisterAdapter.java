package pidev.esprit.tn.insurance.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pidev.esprit.tn.insurance.DetailsFragment;
import pidev.esprit.tn.insurance.Model.Sinister;
import pidev.esprit.tn.insurance.R;

//import android.app.FragmentManager;

//import android.support.v4.app.FragmentManager;

//import android.app.FragmentManager;

//import android.app.Fragment;

/**
 * Created by aboud on 24/11/2017.
 */

public class SinisterAdapter extends ArrayAdapter<Sinister> {

    private Context context;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Sinister> sinisterItems;
    Sinister sinister;
    private int resourceId=0;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private Bundle bundle;

    public SinisterAdapter(FragmentActivity activity, List<Sinister> sinisters) {
        super(activity, 0, sinisters);
        this.activity = activity;
        this.sinisterItems = sinisters;
        fragmentManager = activity.getSupportFragmentManager();
        bundle = new Bundle();
    }

    @Override
    public int getCount() {
        return sinisterItems.size();
    }

    @Override
    public Sinister getItem(int location) {
        return sinisterItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        /*if (inflater == null){
            Log.i("*****","inflater is null");
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);}
        if (inflater == null){
            Log.i("*****","inflater still null");
            }
        if (convertView == null){
            Log.i("*****","convertView is null");
            convertView = inflater.inflate(R.layout.row_sinister, null);}
        if (convertView == null){
            Log.i("*****","convertView still null");}*/
        if(convertView == null ) {
            convertView = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.row_sinister, null);
        }

        TextView num = (TextView) convertView.findViewById(R.id.numSinister);
        sinister = sinisterItems.get(position);
        bundle.clear();
        if (num != null)
            Log.i("*****",num.getText().toString()) ;
        num.setText(String.valueOf(sinister.getId()));

        //add a ClickListener to the num TextView
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity.getApplicationContext(), sinister.getDescription(), Toast.LENGTH_LONG).show();
                //sinisterItems.clear();
                fragment = new DetailsFragment();
                //Log.d("IdInsuredInfo",String.valueOf(sinister.getId()));
                bundle.putString("nameInsured", sinisterItems.get(position).getNameInsured());
                //Log.d("NameInsured",sinister.getNameInsured());
                fragment.setArguments(bundle);
                sinisterItems.clear();
                //bundle is an Object where we can put extra data to pass it with a fragment
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        });


        return convertView;
    }




}
