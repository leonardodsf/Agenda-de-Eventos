package com.flores.agendadeeventos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class EventosAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Evento> eventos;
    private FirebaseAuth mAuth;

    public EventosAdapter(Context context, ArrayList<Evento> items) {
        this.context = context;
        this.eventos = items;
    }

    @Override
    public int getCount() {
        return eventos.size();
    }

    @Override
    public Object getItem(int position) {return eventos.get(position);}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.adapter_evento, parent, false);
        }

        Evento evento = (Evento) getItem(position);

        TextView textEvento = (TextView) convertView.findViewById(R.id.textEvento);
        TextView textEmailEvent = (TextView) convertView.findViewById(R.id.textEmailEvent);
        TextView textData = (TextView) convertView.findViewById(R.id.textData);
        TextView textDescricao = (TextView) convertView.findViewById(R.id.textDescricao);

        //colocando email do usuario que criou
        textEmailEvent.setText("Evento criado por " + evento.email);

        textEvento.setText(evento.evento);
        textData.setText(evento.data);
        textDescricao.setText(evento.descricao);

        return convertView;
    }

}
