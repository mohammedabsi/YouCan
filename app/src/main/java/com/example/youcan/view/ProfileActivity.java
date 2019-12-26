package com.example.youcan.view;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.youcan.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {


    TextInputEditText username_profile, gender_profile, height_profile, weight_profile, phone_profile, password_profile;
    ProgressBar progressBarprofile;
    private Uri mImageUri;
    private ImageView edit_image;
    CircularImageView mImageView;
    Map<String, Object> map = new HashMap<>();

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    DocumentReference userprofilenode = FirebaseFirestore.getInstance().collection("UserData").document(firebaseUser.getUid());
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference("uploads");
    private StorageTask mUploadTask;


    private static final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(Html.fromHtml("<font color='#10A5A5'> Profile </font>"));
        setSupportActionBar(toolbar);


        username_profile = findViewById(R.id.username_profile);
        password_profile = findViewById(R.id.password_profile);
        phone_profile = findViewById(R.id.phone_profile);
        gender_profile = findViewById(R.id.gender_profile);
        height_profile = findViewById(R.id.height_profile);
        weight_profile = findViewById(R.id.weight_profile);
        progressBarprofile = findViewById(R.id.progressBarprofile);
        mImageView = findViewById(R.id.profile_photo);
        edit_image = findViewById(R.id.edit_image);

        progressBarprofile.setVisibility(View.VISIBLE);


        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_button));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, HomePageActivity.class));
                finish();
            }
        });

        userprofilenode.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (firebaseUser != null) {
                    if (documentSnapshot.getId()!= null){
                    username_profile.setText(documentSnapshot.getString("username"));
                    phone_profile.setText(documentSnapshot.getString("userphone"));
                    password_profile.setText(documentSnapshot.getString("userpassword"));
                    weight_profile.setText(documentSnapshot.getString("weight"));
                    height_profile.setText(documentSnapshot.getString("height"));

                    if (documentSnapshot.getString("imageprofile")!= null){
                        Glide
                                .with(ProfileActivity.this)
                                .load(documentSnapshot.getString("imageprofile"))
                                .into(mImageView);

                    }
                    if (documentSnapshot.getBoolean("gender") == true) {
                        gender_profile.setText("male");
                    } else {
                        gender_profile.setText("female");

                    }
                    progressBarprofile.setVisibility(View.INVISIBLE);

                }} else {
                    Toast.makeText(ProfileActivity.this, "Failed!", Toast.LENGTH_SHORT).show();

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "This Account Deleted!", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this, HomePageActivity.class));
        finish();
        super.onBackPressed();
    }

    public void UpdateProfile(View view) {
        progressBarprofile.setVisibility(View.VISIBLE);
        String usernameupdate = username_profile.getText().toString();
        String phoneupdate = phone_profile.getText().toString();
        String passwordupdate = password_profile.getText().toString();
        String weightupdate = weight_profile.getText().toString();
        String heightupdate = height_profile.getText().toString();
        if (mUploadTask != null && mUploadTask.isInProgress()) {
            Toast.makeText(ProfileActivity.this, "Upload in progress", Toast.LENGTH_SHORT).show();
        } else {
            uploadFile();
        }
        map.put("username", usernameupdate);
        map.put("userphone", phoneupdate);
        map.put("userpassword", passwordupdate);
        map.put("weight", weightupdate);
        map.put("height", heightupdate);
        userprofilenode.update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ProfileActivity.this, "Update Success", Toast.LENGTH_SHORT).show();
                progressBarprofile.setVisibility(View.INVISIBLE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ProfileActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                progressBarprofile.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void UploadImage(View view) {
        openFileChooser();
    }

    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Glide.with(this).load(mImageUri).into(mImageView);
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mUploadTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    progressBarprofile.setVisibility(View.INVISIBLE);
                                }
                            }, 500);
                            Uri downloadUrl = taskSnapshot.getUploadSessionUri();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String ImageUrl = uri.toString();
                                    map.put("imageprofile", ImageUrl);
                                    System.out.println(ImageUrl);
                                    userprofilenode.update(map);
//                                    Toast.makeText(ProfileActivity.this, "Upload Image successful", Toast.LENGTH_LONG).show();

                                }
                            });


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            progressBarprofile.setVisibility(View.VISIBLE);
                        }
                    });
        } else {
//            Toast.makeText(this, "No file selected For Image", Toast.LENGTH_SHORT).show();
        }
    }


}
