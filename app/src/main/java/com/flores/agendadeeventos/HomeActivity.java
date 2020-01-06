package com.flores.agendadeeventos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference refEventos;
    ArrayList<Evento> eventos;
    EventosAdapter adapter;
    ListView listEventos;
    ImageView btnHome, btnAdd, btnUser;
    TextView textEmailEvent;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnHome = findViewById(R.id.btnHome);
        btnAdd = findViewById(R.id.btnAdd);
        btnUser = findViewById(R.id.btnUser);
        listEventos = findViewById(R.id.listEventos);
        textEmailEvent = findViewById(R.id.textEmailEvent);

        database = FirebaseDatabase.getInstance();
        refEventos = database.getReference("eventos");

        mAuth = FirebaseAuth.getInstance();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        refEventos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                eventos = new ArrayList<Evento>();
                //pega os filhos da arvore eventos, pegando cada snapshot da arvore eventos
                for (DataSnapshot dataEvento : dataSnapshot.getChildren()) {
                    //para pegar um evento da arvore
                    Evento evento = dataEvento.getValue(Evento.class);
                    //guardando a chave que esta no firebase
                    evento.setKey(dataEvento.getKey());
                    //adicionando um evento na lista
                    eventos.add(evento);
                }
                adapter = new EventosAdapter(HomeActivity.this, eventos);
                //adicionando a lista eventos no adapter
                listEventos.setAdapter((ListAdapter) adapter);

                    listEventos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Evento evento = eventos.get(i);
                            if (mAuth.getCurrentUser().getEmail().equals(evento.email)){
                                excluirEvento(evento.getKey());
                            }else {
                                Toast.makeText(HomeActivity.this, "Apenas o criador do evento, poderá excluir", Toast.LENGTH_SHORT).show();
                            }
                            return true;
                        }
                    });

                    listEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Evento evento = eventos.get(i);
                            if (mAuth.getCurrentUser().getEmail().equals(evento.email)) {
                                Bundle b = new Bundle();
                                b.putString("key", evento.getKey());
                                Intent intent = new Intent(HomeActivity.this, AlterarEventoActivity.class);
                                intent.putExtras(b);
                                startActivity(intent);
                            } else {
                                Toast.makeText(HomeActivity.this, "Só o criador do evento pode alterá-lo!", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void excluirEvento(final String key) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                refEventos.child(key).removeValue();
                Toast.makeText(HomeActivity.this, "Evento excluído com sucesso!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setTitle("Excluindo Evento");
        builder.setMessage("Deseja realmente excluir este evento?");
        AlertDialog dialog = builder.create();
        //mostrar a dialog pro usuario
        dialog.show();
    }



}
