package pidev.esprit.tn.insurance;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pidev.esprit.tn.insurance.Model.Sinister;

/**
 * Created by aboud on 24/11/2017.
 */

public class SinisterAdapter extends ArrayAdapter<Sinister> {
    public SinisterAdapter(Context context, List<Sinister> sinisters) {
        super(context, 0, sinisters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_sinister,parent, false);
        }

        SinisterViewHolder viewHolder = (SinisterViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new SinisterViewHolder();
            viewHolder.num = (TextView) convertView.findViewById(R.id.num);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Sinister sinister = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.num.setText(sinister.getId());


        return convertView;
    }

    private class SinisterViewHolder{
        public TextView num;

    }
}
