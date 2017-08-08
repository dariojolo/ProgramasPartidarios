package dario4.dariojolo.com.ar.programaspartidarios.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import dario4.dariojolo.com.ar.programaspartidarios.activities.DetalleActivity;
import dario4.dariojolo.com.ar.programaspartidarios.activities.MainActivity;

import dario4.dariojolo.com.ar.programaspartidarios.R;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by rodrigrl on 07/06/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private SharedPreferences prefs;
    private static final String LOGTAG = "programas-partidarios";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getNotification() != null) {

            String titulo = remoteMessage.getNotification().getTitle();
            String texto = remoteMessage.getNotification().getBody();


            Log.d(LOGTAG, "NOTIFICACION RECIBIDA");
            Log.d(LOGTAG, "Título: " + titulo);
            Log.d(LOGTAG, "Texto: " + texto);
            Log.d(LOGTAG, "Tamaño data: "+ remoteMessage.getData().size());

            Map<String,String> datos = remoteMessage.getData();

            int _id = 0;
            for (Map.Entry<String, String> dato: datos.entrySet()){
                Log.d(LOGTAG,"Key: " + dato.getKey() + " Dato: " + dato.getValue());
                _id = Integer.parseInt(dato.getValue());
            }
            //Opcional: mostramos la notificación en la barra de estado
            showNotification(titulo, texto, remoteMessage,_id);
        }
    }

    private void showNotification(String title, String text, RemoteMessage remoteMessage, int id) {

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        long notificatioId = System.currentTimeMillis();

        Intent intent;
        PendingIntent contentIntent;
        if (id != 0) {
            intent  = new Intent(getApplicationContext(), DetalleActivity.class); // Here pass your activity where you want to redirect.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            contentIntent = PendingIntent.getActivity(this, (int) (Math.random() * 100), intent, 0);
            prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt("IDPrograma", id);
            editor.apply();
        }else{
            intent  = new Intent(getApplicationContext(), MainActivity.class); // Here pass your activity where you want to redirect.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            contentIntent = PendingIntent.getActivity(this, (int) (Math.random() * 100), intent, 0);
            intent.putExtra("Programa", id);
            intent.putExtra("Fragment", 0);
        }

        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= android.os.Build.VERSION_CODES.LOLLIPOP){
            currentapiVersion = R.mipmap.ic_launcher_chico;
        } else{
            currentapiVersion = R.mipmap.ic_launcher_chico;
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(currentapiVersion)
                .setContentTitle(this.getResources().getString(R.string.app_name))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(title))
                .setContentText(text)
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)
                .setDefaults(Notification.FLAG_AUTO_CANCEL | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND)
                .setContentIntent(contentIntent);
        mNotificationManager.notify((int) notificatioId, notificationBuilder.build());

    }
}