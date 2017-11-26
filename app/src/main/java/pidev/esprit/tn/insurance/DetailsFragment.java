package pidev.esprit.tn.insurance;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {
    private EditText nameInsured;
    private Bundle bundle;
    private Button openCameraButton;
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

        nameInsured = view.findViewById(R.id.nameInsured);


        if(!bundle.isEmpty()){
            // "nameInsured" is the key of our first entry in the bundle
            nameInsured.setText(bundle.getString("nameInsured"));
        }
       /* openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getActivity().startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });*/


        return view;
    }

}
