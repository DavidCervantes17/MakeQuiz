package com.virtuallabs.makequiz.controller;
import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.virtuallabs.makequiz.data.FirebaseConnect;
import com.virtuallabs.makequiz.data.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Controller {
    static int lastId = 0;
    static FirebaseFirestore db;
    static Map<String, Object> item;
    static Item miCuestionario;

    public static void sendtoDb(FirebaseFirestore db, final Context context){


        Log.d("Lastid2",String.valueOf(lastId));

        db.collection("preguntas")
                .document(String.valueOf(lastId+1))
                .set(item)
                .addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
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

    public static void controller (final Context context,  EditText pregunta,  EditText respuesta, final List<Item> items){
         db = FirebaseConnect.connect();
        //Obtenemos ultimo id de la BD
        item = new HashMap<>();
        item.put("pregunta", pregunta.getText().toString());
        item.put("respuesta", respuesta.getText().toString());

        miCuestionario = new Item();
        miCuestionario.setPregunta(pregunta.getText().toString());
        miCuestionario.setRespuesta(respuesta.getText().toString());
        items.add(miCuestionario);

        db.collection("preguntas")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("DOCS", document.getId() + " => " + document.getData());
                                if(lastId < Integer.parseInt(document.getId())){
                                    lastId = Integer.parseInt(document.getId());
                                }
                                Log.d("Lastid",String.valueOf(lastId));
                            }


                            sendtoDb(db, context);
                        } else {
                            Log.d("DOCS", "Error getting documents: ", task.getException());
                        }
                    }
                });



    }




}
