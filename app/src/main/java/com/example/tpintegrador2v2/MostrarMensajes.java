package com.example.tpintegrador2v2;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

public class MostrarMensajes extends Service {
    private int x = 0;
    public MostrarMensajes() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
     Runnable tarea = new Runnable() {
            public void run() {
                while (true) {
                    try {
                        LeerMensajes();
                        Thread.sleep(9000);
                    } catch (InterruptedException e) {
                        Log.e("Error: ", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        };
            Thread trabajador=new Thread(tarea);
            trabajador.start();
            return START_STICKY;
        }


    public void LeerMensajes() {
        Uri uriMensajes = Uri.parse("content://sms");
        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(uriMensajes, null, null, null, null);
        if(cursor.getCount() > 0) {
            int i = 0;
            while(cursor.moveToNext() && i < 5) {
                String adress = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.ADDRESS));
                String mensaje = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.BODY));
                String dateString = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Inbox.DATE));
                long dateLong = Long.parseLong(dateString);
                Date date = new Date(dateLong);
                Log.d("Salida", "Mensaje del número " + adress + ": " + mensaje + ". Fecha: " + date.toString());
                i++;
            }
        }
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}




