package com.virtuallabs.makequiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String nombreCuestionario = "cuestionarios";
    EditText pregunta, respuesta;
    Button enviar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregunta = findViewById(R.id.etPregunta);
        respuesta = findViewById(R.id.etRespuesta);

        enviar = findViewById(R.id.bEnviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PREGUNTA",pregunta.getText().toString());
                Log.d("RESPUESTA",respuesta.getText().toString());

                // Create a new user with a first and last name
                Map<String, Object> item = new HashMap<>();
                item.put("pregunta", pregunta.getText().toString());
                item.put("respuesta", respuesta.getText().toString());

                // Add a new document with a generated ID
                db.collection("preguntas")
                        .add(item)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });

            }
        });

    }
}