package com.flores.agendadeeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity {

    FirebaseDatabase eventos;
    EditText textEvento;
    EditText textData;
    EditText textDescricao;
    Button btnCadastrar;
    DatabaseReference refEventos;
    ImageView btnHome, btnUser;
    private FirebaseAuth mAuth;
    TextView textEmailEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mAuth = FirebaseAuth.getInstance();

        textEvento = findViewById(R.id.textEvento);
        textData = findViewById(R.id.textData);
        textDescricao = findViewById(R.id.textDescricao);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnHome = findViewById(R.id.btnHome);
        btnUser = findViewById(R.id.btnUser);
        textEmailEvent = findViewById(R.id.textEmailEvent);

        eventos = FirebaseDatabase.getInstance();
        //pegando referencia da arvore "eventos" para assim colocar as informações
        refEventos = eventos.getReference("eventos");

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nomeEvento = textEvento.getText().toString();
                String dataEvento = textData.getText().toString();
                String descricaoEvento = textDescricao.getText().toString();
                String email = mAuth.getCurrentUser().getEmail();
                if (nomeEvento.equalsIgnoreCase("")){
                   textEvento.setError("Campo Obrigatório");
                }
                if(dataEvento.equalsIgnoreCase("")){
                    textData.setError("Campo Obrigatório");
                }
                if(descricaoEvento.equalsIgnoreCase("")){
                    textDescricao.setError("Campo Obrigatório");
                }
                else {
                    Evento evento = new Evento(nomeEvento,dataEvento,descricaoEvento, email);
                    //guardando informações dentro da arvore aluno
                    refEventos.push().setValue(evento);
                    Intent intent = new Intent(AddActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(AddActivity.this, "Evento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this,AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
