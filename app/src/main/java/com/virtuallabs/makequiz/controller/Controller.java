package com.virtuallabs.makequiz.controller;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.virtuallabs.makequiz.data.FirebaseConnect;
import com.virtuallabs.makequiz.data.Item;
import com.virtuallabs.makequiz.view.MainActivity;

import java.util.List;
import java.util.Map;

public class Controller {
    public static void controller (final Context context, Map<String, Object> item, EditText pregunta, EditText respuesta, List<Item> items){

        Item miCuestionario = new Item();
        miCuestionario.setPregunta(pregunta.getText().toString());
        miCuestionario.setRespuesta(respuesta.getText().toString());
        items.add(miCuestionario);

        FirebaseFirestore db = FirebaseConnect.connect();
        db.collection("preguntas")
                .add(item).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(context,"Respuesta guardada",Toast.LENGTH_LONG).show();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TAG", "Error adding document", e);
                    }
                });

    }


}
