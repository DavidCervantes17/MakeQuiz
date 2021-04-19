package com.virtuallabs.makequiz.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.virtuallabs.makequiz.R;
import com.virtuallabs.makequiz.controller.Controller;
import com.virtuallabs.makequiz.data.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText pregunta, respuesta;
    Button enviar;
    TextView tv;

    List<Item> items = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.tvSend);
        pregunta = findViewById(R.id.etPregunta);
        respuesta = findViewById(R.id.etRespuesta);
        enviar = findViewById(R.id.bEnviar);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d("PREGUNTA",pregunta.getText().toString());
                //Log.d("RESPUESTA",respuesta.getText().toString());

                Map<String, Object> item = new HashMap<>();
                item.put("pregunta", pregunta.getText().toString());
                item.put("respuesta", respuesta.getText().toString());

                Item miCuestionario = new Item();
                miCuestionario.setPregunta(pregunta.getText().toString());
                miCuestionario.setRespuesta(respuesta.getText().toString());
                items.add(miCuestionario);

                Controller.controller(MainActivity.this,item);

                StringBuilder sb = new StringBuilder();

                for (Item s : items)
                {
                    sb.append(s);
                }
                Log.d("String",sb.toString());

                tv.setText(sb.toString());

                pregunta.setText("");
                respuesta.setText("");
            }
        });

    }
}