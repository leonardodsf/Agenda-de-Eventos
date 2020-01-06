package com.flores.agendadeeventos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AlterarEventoActivity extends AppCompatActivity {

    ImageView btnHome, btnAdd, btnUser;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_evento);

        final EditText textEvento;
        final EditText textData;
        final EditText textDescricao;
        Button btnAlterar;
        FirebaseDatabase eventos;
        final DatabaseReference refEventos;
        String key;


        mAuth = FirebaseAuth.getInstance();


        textEvento = findViewById(R.id.textEvento);
        textData = findViewById(R.id.textData);
        textDescricao = findViewById(R.id.textDescricao);
        btnAlterar = findViewById(R.id.btnAlterar);
        btnHome = findViewById(R.id.btnHome);
        btnAdd = findViewById(R.id.btnAdd);
        btnUser = findViewById(R.id.btnUser);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlterarEventoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlterarEventoActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlterarEventoActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });


        eventos = FirebaseDatabase.getInstance();

        Bundle b = getIntent().getExtras();
        key = b.getString("key");

        refEventos = eventos.getReference("eventos").child(key);

        refEventos.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                com.flores.agendadeeventos.Evento evento = dataSnapshot.getValue(Evento.class);
                textEvento.setText(evento.evento);
                textData.setText(evento.data);
                textDescricao.setText(evento.descricao);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mAuth.getCurrentUser().getEmail();
                String nomeEvento = textEvento.getText().toString();
                String dataEvento = textData.getText().toString();
                String descricaoEvento = textDescricao.getText().toString();
                Evento evento = new Evento(nomeEvento, dataEvento, descricaoEvento, email);
                refEventos.setValue(evento, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        Toast.makeText(AlterarEventoActivity.this, "Evento alterado com sucesso!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

            }
        });

    }
}

