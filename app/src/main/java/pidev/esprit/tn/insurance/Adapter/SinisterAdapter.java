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
import android.widget.LinearLayout;
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

        if(convertView == null ) {
            convertView = LayoutInflater.from(activity.getApplicationContext()).inflate(R.layout.row_sinister, null);
        }

        TextView num = (TextView) convertView.findViewById(R.id.numSinister);
        LinearLayout layoutRowSinister = convertView.findViewById(R.id.layoutRowSinister);
        sinister = sinisterItems.get(position);
        bundle.clear();
        if (num != null)
            Log.i("*****",num.getText().toString()) ;
        num.setText("Num Statement : "+String.valueOf(sinister.getId()));

        /*layoutRowSinister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity.getApplicationContext(), "Hello World", Toast.LENGTH_LONG).show();
            }
        });**/
        //add a ClickListener to the num TextView
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(activity.getApplicationContext(), sinister.getDescription(), Toast.LENGTH_LONG).show();
                //sinisterItems.clear();
                fragment = new DetailsFragment();
                //Log.d("IdInsuredInfo",String.valueOf(sinister.getId()));
                bundle.putString("id", String.valueOf(sinisterItems.get(position).getId()));
                bundle.putString("nameFirstnameText", sinisterItems.get(position).getNameFirstname());
                bundle.putString("telText", sinisterItems.get(position).getTel());
                bundle.putString("emailText", sinisterItems.get(position).getEmail());
                bundle.putString("nameInsurancCompanyText", sinisterItems.get(position).getNameInsurancCompany());
                bundle.putString("policyNumText", sinisterItems.get(position).getPolicyNum());
                bundle.putString("ivPreview", sinisterItems.get(position).getUrlImage());
                bundle.putString("stateText", sinisterItems.get(position).getState());
                Log.i("stateText",sinisterItems.get(position).getState());
                fragment.setArguments(bundle);
                sinisterItems.clear();
                //bundle is an Object where we can put extra data to pass it with a fragment
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
        });


        return convertView;
    }




}
