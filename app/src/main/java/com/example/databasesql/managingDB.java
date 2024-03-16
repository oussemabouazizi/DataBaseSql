package com.example.databasesql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class managingDB extends AppCompatActivity {
    private Button b;
    private ListView lv;
    Database database;
    private Context context;

    public managingDB(Button b, Context context) {
        this.b = b;
        this.context = context;
        AdapterView = null;
        view = null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_managing_db);
        b= (Button) findViewById(R.id.retourButton);
        lv= (ListView) findViewById(R.id.listView) ;
        database=new Database(context);
        viewData();
    }

    public void viewData() {
        Cursor c=database.getAllData();
        ArrayList<String> list =new ArrayList<>();
        if(c.getCount()==0){
            Toast.makeText(managingDB.this, "La base est vide", Toast.LENGTH_SHORT).show();
        }
        else {
            while (c.moveToNext()) {
                list.add(c.getString(0)+" "+c.getString(1)+" "+c.getString(2)+" "+c.getString(3));
            }
        }
        ListAdapter listAdapter = new ArrayAdapter<>(managingDB.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, list);
        lv.setAdapter(listAdapter);
    }
    b.setOnClickListener(v -> {
        Intent int2=new Intent(managingDB.this, MainActivity.class);
        startActivity(int2);
    });
lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    private Object AdapterView;

    private Object view;

    private final Object View = null;

    {
        public void onItemClick(AdapterView,final int position,long id {
            String[] items = {"Modifier", "Supprimer"};
            AlertDialog.Builder builder=new AlertDialog.Builder(managingDB.this);
            builder.setTitle("Action");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (which==0) {
                        showUpdate(managingDB.this, lv, position);
                    } else if (which==1) {
                        delete(lv, position);
                    }
                }

                private void delete(ListView lv, int position) {
                }

                private void showUpdate(managingDB managingDB, ListView lv, int position) {
                }
            });
            builder.show();
        }
    };
    private void showUpdate(Activity ac, ListView lv, int p) {
        Dialog dialog=new Dialog(ac);
        dialog.setContentView(R.layout.update_db);
        dialog.setTitle("Update");
        final EditText name= (EditText) dialog.findViewById(R.id.name);
        final EditText mail= (EditText) dialog.findViewById(R.id.mail );
        final EditText phone= (EditText) dialog.findViewById(R.id.phone);
        Button bt= (Button) dialog.findViewById(R.id.button2);
        final String[] chaine=lv.getAdapter().getItem(p).toString().split(" ");
        name.setText(chaine[1]);
        mail.setText(chaine[2]);
        phone.setText(chaine[3]);
        int width=(int)(ac.getResources().getDisplayMetrics().widthPixels*0.9);
        int height=(int)(ac.getResources().getDisplayMetrics().heightPixels*0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i=Integer.parseInt(chaine[0]);
                Database.update(name.getText().toString(), mail.getText().toString(), phone.getText().toString(),1);
                Toast.makeText(managingDB.this, "Mise à jour avec succès", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(managingDB.this, managingDB.class);
                startActivity(intent);
                viewData();
            }
        });
    }
    private void delete(ListView lv, int p) {
        String[] chaine = lv.getAdapter().getItem(p).toString().split(" ");
        int i = Integer.parseInt(chaine[0]);
        Database.delete(i);
        Toast.makeText(this, "Suppression avec succès", Toast.LENGTH_SHORT).show();
        viewData();
    }
}