package com.flores.agendadeeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Button btnSair;
    ImageView btnHome, btnAdd;
    TextView textEmailUser;
    EditText textEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        btnSair = findViewById(R.id.btnSair);
        btnHome = findViewById(R.id.btnHome);
        btnAdd = findViewById(R.id.btnAdd);
        textEmailUser = findViewById(R.id.textEmailUser);
        textEmail = findViewById(R.id.textEmail);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //inserindo email na tela account e substituindo o textview pelo email
        textEmailUser.setText(mAuth.getCurrentUser().getEmail());

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(AccountActivity.this, "Você saiu da conta ", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccountActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
