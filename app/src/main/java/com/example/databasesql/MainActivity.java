package com.example.databasesql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button b1, b2;
    private EditText nom, mail, phone;
    Database dbb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button4);
        nom = (EditText) findViewById(R.id.name);
        mail = (EditText) findViewById(R.id.mail);
        phone = (EditText) findViewById(R.id.phone);
        dbb = new Database(this);
    }
    b1.setOnClickListener(v -> {
        if (!nom.getText().toString().isEmpty() &&
                !mail.getText().toString().isEmpty() &&
                !phone.getText().toString().isEmpty()) {
            boolean inserted = dbb.insertData(nom.getText().toString(), mail.getText().toString(), phone.getText().toString());
            if (inserted) {
                Toast.makeText(MainActivity.this, "Insertion avec succès", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Echec d'insertion", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Tous les champs doivent être remplis", Toast.LENGTH_SHORT).show();
        }
    };

b2.setOnClickListener(new View.OnClickListener(){
        Intent int1 = new Intent(MainActivity.this, managingDB.class);
        startActivity(int1);
    }
};

