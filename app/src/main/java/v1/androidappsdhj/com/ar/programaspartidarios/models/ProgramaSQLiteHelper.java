package v1.androidappsdhj.com.ar.programaspartidarios.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dario on 9/10/2017.
 */

public class ProgramaSQLiteHelper extends SQLiteOpenHelper{

    //Sentencia para crear la tabla
    String sqlCreate = "CREATE TABLE programas (_id integer primary key autoincrement not null, " +
            "_nombre TEXT, " +
            "_imagen INT," +
            "_conductores TEXT," +
            "_emisora TEXT" +
            "_eMail TEXT," +
            "_web TEXT," +
            "_twitter TEXT," +
            "_facebook TEXT," +
            "_telefono TEXT," +
            "_lunes BOOLEAN," +
            "_martes BOOLEAN," +
            "_miercoles BOOLEAN," +
            "_jueves BOOLEAN," +
            "_viernes BOOLEAN," +
            "_sabado BOOLEAN," +
            "_domingo BOOLEAN," +
            "_diaPartido BOOLEAN," +
            "_diaUno TEXT," +
            "_diaDos TEXT," +
            "_medio TEXT," +
            "_link TEXT," +
            "_favorito BOOLEAN," +
            "_notificar BOOLEAN," +
            "_topicNotificacion TEXT," +
            "_manana BOOLEAN," +
            "_tarde BOOLEAN," +
            "_noche BOOLEAN)";

    public ProgramaSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se elimina la version anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS programas");
        //Se crea nuevamente la nueva version de la tabla
        db.execSQL(sqlCreate);

    }
}
