package dario.dariojolo.com.ar.programaspartidarios.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class SplashActivity extends AppCompatActivity {

    private int id;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();

        //SystemClock.sleep(2000);

        if (extras != null){
            try {
                Log.d("Algo llego", "TAG");
                id = Integer.parseInt(extras.getString("ID"));
                Intent intent = new Intent(this, DetalleActivity.class);
                intent.putExtra("Programa", id);
                intent.putExtra("Fragment", 1);
                startActivity(intent);
                finish();
            }catch (Exception ex){
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("Fragment", 1);
                startActivity(intent);
                finish();
            }
        }else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("Fragment", 1);
            startActivity(intent);
            finish();
        }

    }
}
