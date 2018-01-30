package v1.androidappsdhj.com.ar.programaspartidarios.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.List;

import v1.androidappsdhj.com.ar.programaspartidarios.R;
import v1.androidappsdhj.com.ar.programaspartidarios.models.Programa;

/**
 * Created by Dario on 08/05/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Programa> programas;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;
    private Boolean partido = Boolean.FALSE;

    public MyAdapter(List<Programa> programas, int layout,OnItemClickListener itemClickListener) {
        this.programas = programas;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }
    public MyAdapter(List<Programa> programas, int layout,OnItemClickListener itemClickListener,Boolean partido) {
        this.programas = programas;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.partido = partido;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        context = parent.getContext();
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        holder.bind(programas.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return programas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView textViewEmisora;
        public TextView textViewHora;
        public ImageView imageViewPoster;

        public ViewHolder(View v) {
            super(v);
            textViewName = (TextView) v.findViewById(R.id.txtNombrePrograma);
            textViewEmisora = (TextView)v.findViewById(R.id.txtEmisoraPrograma);
            textViewHora = (TextView)v.findViewById(R.id.txtHoraInicio);
            imageViewPoster = (ImageView) v.findViewById(R.id.imagenPrograma);
        }

        public void bind(final Programa programa, final OnItemClickListener itemClickListener) {
            //Procesamos los datos a renderizar
            textViewName.setText(programa.getNombre());
            textViewEmisora.setText(programa.getEmisora());

            if ( partido ){
                if (programa.getTopicNotificacion().equalsIgnoreCase("EquipoDesafio")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("SanLorenzoEnAccion")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("SentimientoAzulGrana")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("ATodoSanLorenzo")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("GloriosoSanLorenzo")) {
                    textViewHora.setText("Partido ");
                }
            }else {
                if (programa.getTopicNotificacion().equalsIgnoreCase("EquipoDesafio")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("SanLorenzoEnAccion")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("SentimientoAzulGrana")) {
                    textViewHora.setText("Partido ");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("EquipoDesafioTV")) {
                    textViewHora.setText("20Hs");
                } else if (programa.getTopicNotificacion().equalsIgnoreCase("PeriodismoSanto")) {
                    textViewHora.setText("20:30");
                } else {
                    textViewHora.setText(programa.getDiaUno().substring(programa.getDiaUno().length() - 4, programa.getDiaUno().length()) + " - ");
                }
            }
            //Picasso
            //  Picasso.with(context).load(programa.getImagen()).fit().into(imageViewPoster);
            //imageViewPoster.setImageResource(movie.getPoster());
            /*Glide.with(context)
                    .load(programa.getImagen())
                    .fitCenter()
                    .into(imageViewPoster);*/
            Glide.with(context)
                    .load(programa.getImagen())
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .skipMemoryCache(true)
                            .optionalFitCenter()
                            .placeholder(R.drawable.coloresazulgrana)
                    )
                    .into(imageViewPoster);

            //Definimos que por cada elemento de nuestro RecyclerView, tenemos un clickListener
            //Que se comporta de la siguiente manera
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onItemClick(programa, getAdapterPosition());
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Programa programa, int position);
    }
}