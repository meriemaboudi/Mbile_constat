package pidev.esprit.tn.insurance;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import pidev.esprit.tn.insurance.Model.Sinister;
import pidev.esprit.tn.insurance.util.GMailSender;

import static android.app.Activity.RESULT_OK;


public class AddSinisterFragment extends Fragment implements View.OnClickListener{
    private String mJSONURLString = "http://192.168.56.1:18080/insurance-web/api/sinister";
    private static final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;
    String phoneNo;
    String message;
    View rootviews;
    String mCurrentPhotoPath;
    ImageView ivPreview;
    private Button openCameraButton;
    private Button buttonAdd;
    private EditText nameFirstnameText;
    private EditText telText;
    private EditText emailText;
    private EditText nameInsurancCompanyText;
    private EditText policyNumText;

    private String nameFirstname;
    private String tel;
    private String email;
    private String nameInsurancCompany;
    private String policyNum;
    private String urlImage;

    Intent takePictureIntent;
    private static int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {



        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = takePictureIntent.getExtras();
            if(extras == null){
                Log.i("bondulle","is  null");}
            if(extras != null){
                Log.i("bondulle","is not null");
                Bitmap imageBitmap = (Bitmap) extras.get("data");
            //ivPreview.setImageBitmap(imageBitmap);
                }
            urlImage = mCurrentPhotoPath;
            ivPreview.setImageURI(Uri.parse(mCurrentPhotoPath));
            Log.d("Uri.parse(mCurr",Uri.parse(mCurrentPhotoPath).toString());

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootviews = inflater.inflate(R.layout.fragment_add_sinister, container, false);
        ivPreview = (ImageView) rootviews.findViewById(R.id.ivPreview);
        openCameraButton = (Button) rootviews.findViewById(R.id.openCameraButton);
        buttonAdd = (Button) rootviews.findViewById(R.id.buttonSave);
        nameFirstnameText = (EditText) rootviews.findViewById(R.id.nameInsuredText) ;
        telText = (EditText) rootviews.findViewById(R.id.telText) ;
        emailText = (EditText) rootviews.findViewById(R.id.emailText) ;
        nameInsurancCompanyText = (EditText) rootviews.findViewById(R.id.nameCompanyText) ;
        policyNumText = (EditText) rootviews.findViewById(R.id.numPolicyText) ;



        Log.i("cc",openCameraButton.getText().toString());
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            openCameraButton.setEnabled(false);
            ActivityCompat.requestPermissions(getActivity(), new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        openCameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("***","bbn66");
                 takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        //Toast.makeText(getApplicationContext(), "Error while saving picture.", Toast.LENGTH_LONG).show();
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        Uri photoURI = FileProvider.getUriForFile(getActivity(),
                                "com.example.marek.myapplication.fileprovider",
                                photoFile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                    }
                }


            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameFirstname = nameFirstnameText.getText().toString();
                tel = telText.getText().toString();
                email = emailText.getText().toString();
                nameInsurancCompany = nameInsurancCompanyText.getText().toString();
                policyNum = policyNumText.getText().toString();


                Sinister sinister = new Sinister();
                sinister.setNameFirstname(nameFirstname);
                sinister.setTel(tel);
                sinister.setEmail(email);
                sinister.setNameInsurancCompany(nameInsurancCompany);
                sinister.setPolicyNum(policyNum);
                sinister.setUrlImage(urlImage);

                Map<String,String> params = new HashMap<String,String>();

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

                params.put("nameFirstname",nameFirstname);
                params.put("tel",tel);
                params.put("email",email);
                params.put("nameInsurancCompany",nameInsurancCompany);
                params.put("policyNum",policyNum);
                params.put("urlImage",urlImage);
                //params.put("dateCreation;",timeStamp);

                JsonObjectRequest jsonRequest = new JsonObjectRequest (Request.Method.POST, mJSONURLString,new JSONObject(params),
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {


                                Context context = getActivity().getApplicationContext();
                                CharSequence text ="inscription reussite , votre compte sera activé dans une certaine délai";

                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, text, duration);
                                toast.show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {


                                Context context = getActivity().getApplicationContext();


                                int duration = Toast.LENGTH_SHORT;

                                Toast toast = Toast.makeText(context, error.getMessage(), duration);
                                toast.show();
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

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(jsonRequest);
                sendEmail();
                sendSMS();



            }
        });

        return rootviews;
    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }



    @Override
    public void onClick(View v) {
        Log.i("***","bbn");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    protected void sendEmail() {

        try {
            GMailSender sender = new GMailSender("aboudimariem93@gmail.com", "58323183A");
            sender.sendMail("Accident Statement",
                    "We successfuly received you Accident Statement.",
                    "aboudimariem93@gmail.com",
                    "meriem.aboudi@esprit.tn");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }



    }

    protected void sendSMS() {
        String phoneNo = "0021658354602";
        String sms = "We successfuly received you Accident Statement.";

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, message, null, null);
                    Toast.makeText(getActivity().getApplicationContext(), "SMS sent.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }

    }
}

