package com.example.conteocaloriaselfos;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView elfochidoTextView = findViewById(R.id.elfochidoTextView);

        //A continuación le pido a la app que lea el archivo con
        //los inventarios de los elfos que recolectan fruta mágica.
        //Debe encontrar al elfo que provee a los otros.

        InputStream inputStream;
        try {
            inputStream = getResources().openRawResource(R.raw.frutamagica);
            ArrayList<Integer> suma = processFile(inputStream);

            int elfoChido = encontrarElfoChido(suma);

            elfochidoTextView.setText("El elfo chido es el que tiene estas calorías: " + elfoChido);

            //Log.d(TAG, "El elfo chido es el que tiene estas calorías: " + elfoChido);

        } catch (IOException e) {
            e.printStackTrace();
            elfochidoTextView.setText("Oh, no, los elfos se perdieron en el bosque.");
        }
    }
        private ArrayList<Integer> processFile(InputStream inputStream) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            ArrayList<Integer> suma = new ArrayList<>();
            String line;
            int Sumitas = 0;

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    suma.add(Sumitas);
                    Sumitas = 0;
                } else {
                    Sumitas += Integer.parseInt(line);
                }
            }
            if (Sumitas > 0) {
                suma.add(Sumitas);
            }
            reader.close();
            return suma;
        }
        private int encontrarElfoChido (ArrayList < Integer > suma) {
            int maxCalorias = Integer.MIN_VALUE;
            for (int valor : suma) {
                if (valor > maxCalorias) {
                    maxCalorias = valor;
                }
            }
            return maxCalorias;
        }
    }