package pidev.esprit.tn.insurance;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements View.OnClickListener{

    private String mJSONURLString = "http://192.168.56.1:18080/insurance-web/api/sinister/";
    ImageView ivPreview;
    private EditText nameFirstnameText;
    private EditText telText;
    private EditText emailText;
    private EditText nameInsurancCompanyText;
    private EditText policyNumText;
    private EditText stateText;
    private  String id;


    private Bundle bundle;
    private Button buttonCancel;
    private static int REQUEST_IMAGE_CAPTURE = 1;
    public DetailsFragment() {
        // Required empty public constructor
        //bundle = new Bundle();
        //Here we get the Bundle object Associated with the Fragment


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //mImageView.setImageBitmap(imageBitmap); ==> Load the Bitmap image in an Image View

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_details, null);

        bundle = getArguments();


        buttonCancel = view.findViewById(R.id.buttonCancel);
        nameFirstnameText = view.findViewById(R.id.nameFirstnameText);
        telText = (EditText) view.findViewById(R.id.telText) ;
        emailText = (EditText) view.findViewById(R.id.emailText) ;
        nameInsurancCompanyText = (EditText) view.findViewById(R.id.nameCompanyText) ;
        policyNumText = (EditText) view.findViewById(R.id.numPolicyText) ;
        ivPreview = (ImageView) view.findViewById(R.id.ivPreview);
        stateText = (EditText) view.findViewById(R.id.stateText) ;


        if(!bundle.isEmpty()){
            // "nameInsured" is the key of our first entry in the bundle
            id = bundle.getString("id");
            nameFirstnameText.setText(bundle.getString("nameFirstnameText"));
            telText.setText(bundle.getString("telText"));
            emailText.setText(bundle.getString("emailText"));
            nameInsurancCompanyText.setText(bundle.getString("nameInsurancCompanyText"));
            policyNumText.setText(bundle.getString("policyNumText"));
            ivPreview.setImageURI(Uri.parse(bundle.getString("ivPreview")));
            stateText.setText(bundle.getString("stateText"));
        }

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,String> params = new HashMap<String,String>();
                String url = mJSONURLString + id;
                StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                        new Response.Listener<String>()
                        {
                            @Override
                            public void onResponse(String response) {
                                // response
                                Log.d("Response", response);
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error

                            }
                        }
                ) {

                    @Override
                    protected Map<String, String> getParams()
                    {
                        Map<String, String>  params = new HashMap<String, String> ();



                        return params;
                    }

                };


                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(putRequest);



            }
        });



        return view;
    }

    @Override
    public void onClick(View v) {
        Log.i("***","bbn");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

}
