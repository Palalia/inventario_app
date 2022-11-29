package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
public class buscar_product extends AppCompatActivity {
    protected EditText codigo;
    protected TextView articulo,departamento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_product);
        codigo=(EditText) findViewById(R.id.ingresaCodigo);
        articulo=(TextView) findViewById(R.id.articulo);
        departamento=(TextView) findViewById(R.id.departamento);
    }
    public void buscarProducto(View view){
        InputStream archivo = getResources().openRawResource(R.raw.inventario);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(archivo));
        boolean seguir=true;
        String linea;
        String[] producto;
        String searchingCodigo=codigo.getText().toString();
        searchingCodigo=searchingCodigo.trim();
        while(seguir){
            try{
                 linea=buffer.readLine();
                 producto=linea.split(",");
                 if(producto[0].equals(searchingCodigo)){
                    articulo.setText(producto[1]);
                    departamento.setText(producto[2]);
                    seguir=false;
                 }
            }catch(Exception e){
                seguir=false;
            }
        }
    }
}