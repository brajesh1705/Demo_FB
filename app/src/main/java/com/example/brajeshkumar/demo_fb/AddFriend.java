package com.example.brajeshkumar.demo_fb;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddFriend extends AppCompatActivity {
    Database mydb;
    private static final String[] Qualification = {"10 pass", "12 pass", "Undergraduate","Postgraduate","Other"};
    private Spinner spinner;
    EditText editText,editText2,editText3;
    Button save;
    String Qualification1,name,gender,email,Ame;
    DatePicker datePicker;
    ImageButton mProfileImageButton;
    Uri mCapturedImageURI;
    String mCurrentImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mydb = new Database(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        editText= (EditText)findViewById(R.id.editText);
        editText2= (EditText)findViewById(R.id.editText2);
        editText3= (EditText)findViewById(R.id.editText3);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton checkedRadioButton = (RadioButton) findViewById(checkedId);
                gender = checkedRadioButton.getText().toString();

            }
        });

        Spinner dynamicSpinner = (Spinner) findViewById(R.id.spinner);

        String[] items = new String[] { "10 pass", "12 pass", "Undergraduate","Postgraduate","Other" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, items);

        dynamicSpinner.setAdapter(adapter);

        dynamicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
                Qualification1 = (String) parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        save=(Button)findViewById(R.id.button);
        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = false;
                        FriendDetails fd = new FriendDetails(FB.num, editText.getText().toString(), Qualification1, gender, editText3.getText().toString(), editText2.getText().toString(), mCurrentImagePath);
                        try {
                            mydb.insertData(fd);
                        } catch (Exception e) {
                            Toast.makeText(AddFriend.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        FB.num++;
                        Intent intent = new Intent();
                        setResult(Activity.RESULT_OK);
                        finish();
                    }
                }
        );
        mProfileImageButton = (ImageButton) findViewById(R.id.imageButton);
        mProfileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }
    private void chooseImage(){

        //We need the customer's name to to save the image file
        if (editText.getText() != null && !editText.getText().toString().isEmpty()) {
            // Determine Uri of camera image to save.
            final File rootDir = new File(Environment.getExternalStorageDirectory()
                    + File.separator + "DCIM" + File.separator + "ProfilePicture" + File.separator);

            // Create the destination path if it does not exist.
            //noinspection ResultOfMethodCallIgnored
            rootDir.mkdirs();

            // Create the temporary file and get it's URI.

            //Get the customer name
            String customerName = editText.getText().toString();

            //Remove all white space in the customer name
            customerName.replaceAll("s+", "");

            //Use the customer name to create the file name of the image that will be captured
            File file = new File(rootDir, FileUtils.generateImageName(customerName));
            Uri mCapturedImageURI = Uri.fromFile(file);

            // Initialize a list to hold any camera application intents.
            final List cameraIntents = new ArrayList();

            // Get the default camera capture intent.
            final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            // Get the package manager.
            final PackageManager packageManager = AddFriend.this.getPackageManager();

            // Ensure the package manager exists.
            if (packageManager != null) {

                // Get all available image capture app activities.
                final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);

                // Create camera intents for all image capture app activities.
                for(ResolveInfo res : listCam) {

                    // Ensure the activity info exists.
                    if (res.activityInfo != null) {

                        // Get the activity's package name.
                        final String packageName = res.activityInfo.packageName;

                        // Create a new camera intent based on android's default capture intent.
                        final Intent intent = new Intent(captureIntent);

                        // Set the intent data for the current image capture app.
                        intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
                        intent.setPackage(packageName);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, mCapturedImageURI);

                        // Add the intent to available camera intents.
                        cameraIntents.add(intent);
                    }
                }
            }

            // Create an intent to get pictures from the filesystem.
            final Intent galleryIntent = new Intent();
            galleryIntent.setType("image/*");
            galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

            // Chooser of filesystem options.
            final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

            // Add the camera options.
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                    cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

            // Start activity to choose or take a picture.
            startActivityForResult(chooserIntent, 3);
        } else {
            editText.setError("Please enter warrior name");
        }









    }

}

