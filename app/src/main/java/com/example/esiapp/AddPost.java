package com.example.esiapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class AddPost extends AppCompatActivity {

    public ImageView exit, addImage, postImage, postButton;
    public EditText subject, descreption;
    public ProgressBar progressbar;
    String Ssubdject, Sdescription;
    private static final int PReqCode = 2;
    private static final int REQUESCODE = 2;

    private Uri pickedImgUri = null;
    /* FirebaseAuth mAuth;
        //gitna7i les commentaire qui t'mplimenter el fire base !!
        implimentiha berk ma tdir walo el code rah wadjed

        FirebaseUser currentUser ;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        // mAuth = FirebaseAuth.getInstance();
        //currentUser = mAuth.getCurrentUser();

        // kif kif li fo9 hada el commentaire dakhin mea el fire base ki timportiha nahihom mel commentaire tweli temchi nrml


        // hadi tbedel les données ta3 l'utilisateur ki yedkhol

        setView();

        // hadi ki thab tekhredj bla ma dir post !

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // hadi ki tkemel el post w thab tpostih rah ndir lazem ykoun subdject kayen fel post
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
                if (Validate()) {

                    postButton.setVisibility(View.INVISIBLE);
                    progressbar.setVisibility(View.VISIBLE);



                   /*StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("blog_images");
                    final StorageReference imageFilePath = storageReference.child(pickedImgUri.getLastPathSegment());
                    imageFilePath.putFile(pickedImgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            imageFilePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageDownlaodLink = uri.toString();
                                    // create post Object
                                    Post post = new Post(subject.getText().toString(),
                                            descreption.getText().toString(),
                                            imageDownlaodLink,
                                            currentUser.getUid(),
                                            currentUser.getPhotoUrl().toString());

                                    // Add post to firebase database

                                    addPost(post);



                                }
                            }).addOnFailureListener(new OnFailureListener()
                            {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    // something goes wrong uploading picture

                                    showMessage(e.getMessage());
                                    postButton.setVisibility(View.VISIBLE);
                                    progressbar.setVisibility(View.INVISIBLE);



                                }
                            });


                        }
                    });*/


                    Intent in = new Intent(getApplicationContext(), Main3Activity.class);
                    startActivity(in);
                    overridePendingTransition(R.anim.slide_in_top, R.anim.none);

                }
            }
        });

        // hadi ki thab tzid immage fel post ga3k !

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAndRequestForPermission();
            }
        });
    }

    /*private void addPost(Post post)
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Posts").push();

         //get post unique ID and upadte post key
        String key = myRef.getKey();

        post.setPostKey(key);


         //add post data to firebase database

        myRef.setValue(post).addOnSuccessListener(new OnSuccessListener<Void>()
        {
            @Override
            public void onSuccess(Void aVoid) {
                showMessage("Post Added successfully");
                progressbar.setVisibility(View.INVISIBLE);
                postButton.setVisibility(View.VISIBLE);

            }
        });
    }*/

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.none, R.anim.slide_in_top);
    }


    // elle permet de definir les vues de l'activity
    public void setView() {
        exit = findViewById(R.id.addpost_exit);
        addImage = findViewById(R.id.add_image_to_post);
        postImage = findViewById(R.id.post_photo);
        postButton = findViewById(R.id.post_button);
        subject = findViewById(R.id.subject);
        descreption = findViewById(R.id.descreption);
        progressbar = findViewById(R.id.post_progressBar);
        postImage.setVisibility(View.INVISIBLE);
    }


    // pour tester si les champs de l'activity sont remplis au momment du click
    public boolean Validate() {
        boolean result;

        Ssubdject = subject.getText().toString();
        Sdescription = descreption.getText().toString();

        if (TextUtils.isEmpty(Ssubdject)) {
            subject.setError("remplis tout les champs svp");
            result = false;
        } else if (TextUtils.isEmpty(Sdescription)) {
            subject.setError("remplis tout les champs svp");
            result = false;
        } else result = true;
        return result;
    }


    //pour demmander la permition a acceder aux photo pour qu'il puisse ajouter une photo dans sans post
    public void checkAndRequestForPermission() {

        /*if (ContextCompat.checkSelfPermission(AddPost.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(AddPost.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                Toast.makeText(AddPost.this, "Please accept for required permission", Toast.LENGTH_SHORT).show();

            } else {
                ActivityCompat.requestPermissions(AddPost.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        PReqCode);
            }
        } else*/
            // everything goes well : we have permission to access user gallery
            openGallery();

    }

    public void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, REQUESCODE);

    }

    public void showMessage(String message) {
        Toast.makeText(AddPost.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == REQUESCODE && data != null) {

            // the user has successfully picked an image
            // we need to save its reference to a Uri variable
            pickedImgUri = data.getData();
            postImage.setImageURI(pickedImgUri);
            postImage.setVisibility(View.VISIBLE);

        }

    }
}