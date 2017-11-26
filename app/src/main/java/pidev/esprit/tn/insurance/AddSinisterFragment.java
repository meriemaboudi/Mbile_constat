package pidev.esprit.tn.insurance;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import static android.app.Activity.RESULT_OK;


public class AddSinisterFragment extends Fragment implements View.OnClickListener{
    View rootviews;
    ImageView ivPreview;
    private Button openCameraButton;
    private static int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ivPreview.setImageBitmap(imageBitmap);

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootviews = inflater.inflate(R.layout.fragment_add_sinister, container, false);
        ivPreview = (ImageView) rootviews.findViewById(R.id.ivPreview);
        openCameraButton = (Button) rootviews.findViewById(R.id.openCameraButton);
        Log.i("cc",openCameraButton.getText().toString());
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            openCameraButton.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("***","bbn66");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    getActivity().startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);}
            }
        });

        return rootviews;
    }




    @Override
    public void onClick(View v) {
        Log.i("***","bbn");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }
}

