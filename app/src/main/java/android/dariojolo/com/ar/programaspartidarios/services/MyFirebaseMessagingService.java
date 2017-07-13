package android.dariojolo.com.ar.programaspartidarios.services;

import android.app.NotificationManager;
import android.content.Context;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

/**
 * Created by rodrigrl on 07/06/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

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

            for (Map.Entry<String, String> dato: datos.entrySet()){
                Log.d(LOGTAG,"Key: " + dato.getKey() + " Dato: " + dato.getValue());
            }
            //Opcional: mostramos la notificación en la barra de estado
            showNotification(titulo, texto, remoteMessage);
        }
    }

    private void showNotification(String title, String text, RemoteMessage remoteMessage) {

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_chico)
                        .setContentTitle(title)
                        .setContentText(text)
                        .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                        .setVibrate(new long[]{0, 300, 200, 300});

        /*
            //Revisar esto
            Intent notIntent = new Intent(getApplicationContext(), MainActivity.class);

            PendingIntent contIntent = PendingIntent.getActivity(getApplicationContext(), 0, notIntent, 0);
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
            notificationBuilder.setContentIntent(contIntent);

            Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notificationBuilder.setSound(alarmSound);
         */
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}