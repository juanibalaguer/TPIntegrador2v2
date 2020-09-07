package com.example.tpintegrador2v2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_SMS;

public class MainActivity extends AppCompatActivity {
    Intent intentMensaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentMensaje = new Intent(this, MostrarMensajes.class);
        //Pedir permisos
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                !=PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.READ_SMS},1000);
        }
        startService(intentMensaje);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(intentMensaje);
    }
}