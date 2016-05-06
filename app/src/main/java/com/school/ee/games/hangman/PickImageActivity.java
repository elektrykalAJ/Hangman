package com.school.ee.games.hangman;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class PickImageActivity extends AppCompatActivity {


    private static final String TAG = "PickImageActivity" ;
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);

        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                Log.d(TAG,"image string: "+imgDecodableString);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.pickImage);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

                Intent intent = new Intent(PickImageActivity.this,LoginActivity.class);
                intent.putExtra("imagePath",imgDecodableString);

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    /*String imagepath;
    Uri uriImagePath;
    private static final int REQUEST_CODE_CHOOSE_PICTURE_FROM_GALLARY = 0;
    private static final String IMAGE_DATA = null;
    private static final String TYPE =null ;

    private View.OnClickListener photoAlbumListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
            imagepath = Environment.getExternalStorageDirectory() + "/sharedresources/" + *//*HelperFunctions.getDateTimeForFileName() +*//* ".png";
            uriImagePath = Uri.fromFile(new File(imagepath));
            photoPickerIntent.setType("image*//*");
            photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT,uriImagePath);
            photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.name());
            photoPickerIntent.putExtra("return-data", true);
            startActivityForResult(photoPickerIntent, REQUEST_CODE_CHOOSE_PICTURE_FROM_GALLARY);

        }
    };






    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (resultCode == RESULT_OK) {
            switch(requestCode){


                case 22:
                    Log.d("onActivityResult","uriImagePath Gallary :"+data.getData().toString());
                    Intent intentGallary = new Intent(PickImageActivity.this, GamePlayActivity.class);
                    intentGallary.putExtra(IMAGE_DATA, uriImagePath);
                    intentGallary.putExtra(TYPE, "photo");
                    File f = new File(imagepath);
                    if (!f.exists())
                    {
                        try {
                            f.createNewFile();
                            copyFile(new File(getRealPathFromURI(data.getData())), f);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    startActivity(intentGallary);
                    finish();
                    break;


            }
        }





    }

    private void copyFile(File sourceFile, File destFile) throws IOException {
        if (!sourceFile.exists()) {
            return;
        }

        FileChannel source = null;
        FileChannel destination = null;
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        if (destination != null && source != null) {
            destination.transferFrom(source, 0, source.size());
        }
        if (source != null) {
            source.close();
        }
        if (destination != null) {
            destination.close();
        }


    }

    private String getRealPathFromURI(Uri contentUri) {

        String[] proj = { MediaStore.Video.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
*/
}
