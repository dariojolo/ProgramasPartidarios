package v1.androidappsdhj.com.ar.programaspartidarios.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;

/**
 * Created by rodrigrl on 05/10/2017.
 */

public class MyAdapterListView extends BaseAdapter{

    private Context context;
    private List<Programa> programas;
    private int layout;


    public MyAdapterListView(Context context, int layout, List<Programa> programas) {
        this.programas = programas;
        this.layout = layout;
        this.context = context;

    }

    @Override
    public int getCount() {
        return this.programas.size();
    }

    @Override
    public Object getItem(int position) {
        return this.programas.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);
        v = layoutInflater.inflate(R.layout.list_item_listview,null);

        Programa currentPrograma = programas.get(position);

        TextView textViewName;
        TextView textViewEmisora;
        TextView textViewHora;
        ImageView imageViewPoster;
        textViewName = (TextView) v.findViewById(R.id.txtNombrePrograma);
        textViewEmisora = (TextView)v.findViewById(R.id.txtEmisoraPrograma);
        textViewHora = (TextView)v.findViewById(R.id.txtHoraInicio);
        imageViewPoster = (ImageView) v.findViewById(R.id.imagenPrograma);

        //Picasso
        Picasso.with(context).load(currentPrograma.getImagen()).fit().into(imageViewPoster);

        textViewName.setText(currentPrograma.getNombre());
        textViewEmisora.setText(currentPrograma.getEmisora());
        if (currentPrograma.getId()== 24){
            textViewHora.setText("");
        }else{
            textViewHora.setText(currentPrograma.getDiaUno().substring(currentPrograma.getDiaUno().length()-4,currentPrograma.getDiaUno().length()) + " - ");
        }
        return v;
    }
}
