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

                Controller.controller(MainActivity.this, pregunta, respuesta, items);

                StringBuilder sb = new StringBuilder();
                for (Item s : items) { sb.append(s); }
                tv.setText(sb.toString());
                pregunta.setText("");
                respuesta.setText("");
            }
        });

    }
}