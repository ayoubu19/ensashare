package com.example.myapplication.firebaseHelper;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class FileUploader {
    private StorageReference mStorageRef;
    private StorageReference  fileReference;

    private Context context ;
    public static Uri uri;

    public StorageReference getFileReference() {
        return fileReference;
    }

    public FileUploader(Context context, String location ) {
        mStorageRef = FirebaseStorage.getInstance().getReference(location);
        this.context=context;
    }


    private String getFileExtension(Uri uri ) {

        ContentResolver cR = context.getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }



    public void uploadFile(Uri uri , final ProgressDialog p ) {
        p.setProgress(0);
        p.setMax(100);
        if (uri != null) {
            fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(uri));

            fileReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                          fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                              @Override
                                public void onSuccess(Uri uri) {
                                //  Toast.makeText(context,  uri.toString(), Toast.LENGTH_SHORT).show();
                                  FileUploader.uri= uri;

                                //  Toast.makeText(context, " this is url " + url, Toast.LENGTH_SHORT).show();

                                }
                            });

                          // url=taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();

                          // Toast.makeText(context, "File Uploaded "+" "+ url, Toast.LENGTH_LONG).show();

                        }

                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override

                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context , "failed to upload", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());

                    p.setMessage("Loding Data....");
                    p.setCancelable(false);
                    p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

                   p.setProgress((int)progress);
                   if (p.getProgress()==100){
                       p.dismiss();
                   }else{
                       p.show();
                   }

                }
            });

        } else {
            Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show();
        }

    }


}



