package com.example.picgetter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.os.Build;
import android.widget.ImageView;
import android.os.Bundle;
import android.widget.Toast;
import android.net.Uri;
import java.io.File;
import android.Manifest;
import android.content.pm.PackageManager;

public class MainActivity extends AppCompatActivity {

    // create path variable
    String path = "/sdcard/1.jpeg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get file
        if(checkPermissions()) {
            showFile(path);
        }else {
            Toast.makeText(this, "Permission are needed", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults); // Call super method
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission granted
            showFile(path);
        } else {
            // Permission denied
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
        }

    }

    // Check storage permissions
    private boolean checkPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                    String[] PERMISSIONS_STORAGE = {
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                };
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 1);
                return false;
            }
        } else {
            return true;
        }
    }
    private void showFile(String path)
    {
        File file = new File(path);

        if (file.exists()) {
            // Display image
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageURI(Uri.fromFile(file));
        } else {
            // Display error message
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
        }
    }

}