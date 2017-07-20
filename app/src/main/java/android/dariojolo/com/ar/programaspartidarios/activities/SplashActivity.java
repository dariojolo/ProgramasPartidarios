package android.dariojolo.com.ar.programaspartidarios.activities;

import android.content.Intent;
import android.dariojolo.com.ar.programaspartidarios.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class SplashActivity extends AppCompatActivity {

    private Bundle extras;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        extras = getIntent().getExtras();
        SystemClock.sleep(3000);
        if (extras != null){
            try {
                Log.d("Algo llego", "TAG");
                id = Integer.parseInt(extras.getString("ID"));
                Intent intent = new Intent(this, DetalleActivity.class);
                intent.putExtra("Programa", id);
                intent.putExtra("Fragment", 1);
                startActivity(intent);
                //finish();
            }catch (Exception ex){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
               // finish();
            }
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            //finish();
        }
    }
}
