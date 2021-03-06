package v1.androidappsdhj.com.ar.programaspartidarios.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String LOGTAG = "programas-partidarios";

    @Override
    public void onTokenRefresh() {
        //Se obtiene el token actualizado
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(LOGTAG, "Token actualizado: " + refreshedToken);
    }
}
