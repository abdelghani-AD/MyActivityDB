package com.example.myactivitydb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /*private DataBase dataBase;
    private EditText name , prenom , filier;
    private Button ajouter , afficher;
    private ListView listView;*/

    EditText id , name ,prenom , age;
    Button ajouter,afficher , modifier , supprimer;
    DataBase dataBase;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*dataBase = new DataBase(this);

        name = findViewById(R.id.name);
        prenom = findViewById(R.id.prenom);
        filier = findViewById(R.id.filier);
        listView = findViewById(R.id.listView);
        ajouter = findViewById(R.id.ajouter);
        afficher = findViewById(R.id.afficher);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valideChamps();
            }
        });
        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficheEtud();
            }
        });*/

        id = findViewById(R.id.userId);
        name = findViewById(R.id.name);
        prenom = findViewById(R.id.prenom);
        age = findViewById(R.id.age);
        ajouter = findViewById(R.id.ajouter);
        afficher = findViewById(R.id.afficher);
        modifier = findViewById(R.id.modifie);
        supprimer = findViewById(R.id.supprimer);
        listView = findViewById(R.id.listView);

        dataBase = new DataBase(this);

        afficher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Afficher();
            }
        });
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = name.getText().toString();
                String p = prenom.getText().toString();
                Integer a = Integer.parseInt(age.getText().toString());

                Boolean ajouterElev = dataBase.Ajouter(n,p,a);
                if (ajouterElev){
                    Toast.makeText(MainActivity.this, "Personne est ajouter", Toast.LENGTH_SHORT).show();
                    Afficher();
                }else {
                    Toast.makeText(MainActivity.this, "Personne n'est pas ajouter", Toast.LENGTH_SHORT).show();
                }
            }
        });
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iD = Integer.parseInt(id.getText().toString());
                String n = name.getText().toString();
                String p = prenom.getText().toString();
                int a = Integer.parseInt(age.getText().toString());

                Boolean update = dataBase.Modifier(iD,n,p,a);
                if (update){
                    Toast.makeText(MainActivity.this, "Personne est Modifier", Toast.LENGTH_SHORT).show();
                    Afficher();
                }else {
                    Toast.makeText(MainActivity.this, "Personne n'est pas Modifier", Toast.LENGTH_SHORT).show();
                }
            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iD = Integer.parseInt(id.getText().toString());
                Boolean supprimer = dataBase.Supprimer(iD);
                if (supprimer){
                    Toast.makeText(MainActivity.this, "Personne est Supprimer", Toast.LENGTH_SHORT).show();
                    Afficher();
                }
                else {
                    Toast.makeText(MainActivity.this, "Personne n'est pas Supprimer", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    ArrayList<String > data = dataBase.getAll();
    public void Afficher(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

    }


    /*public void valideChamps(){
        String n = name.getText().toString();
        String p = prenom.getText().toString();
        String f = filier.getText().toString();
        if (!n.isEmpty() && !p.isEmpty() && !f.isEmpty()){
            boolean isValide = dataBase.AjouterEtudiant(n,p,f);
            if (isValide){
                Toast.makeText(MainActivity.this, "Étudiant ajouté avec succès", Toast.LENGTH_SHORT).show();
                name.setText("");
                prenom.setText("");
                filier.setText("");
            }
            else {
                Toast.makeText(MainActivity.this, "Etudiant n'est pas ajouter ", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(MainActivity.this, "Les Champs non valider", Toast.LENGTH_SHORT).show();
        }
    }
    public void afficheEtud(){
        ArrayList<String> listeEtud = dataBase.getEtudiants();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listeEtud);
        listView.setAdapter(adapter);
    }*/
}