package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class inventario_departamento extends AppCompatActivity {
    private String nombre_depa;
    private  TextView encabezado;
    private  EditText codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("inventario_departamento","dentro de inventario");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario_departamento);
        nombre_depa=getIntent().getStringExtra("departamento");
        nombre_depa=nombre_depa.trim();
        encabezado=(TextView) findViewById(R.id.titulo_departamento);
        encabezado.setText(nombre_depa);
        codigo=(EditText) findViewById(R.id.codigo);
    }
    public void checarArchivo(View view){//checa si existe un archivo titulo.txt si no, lo crea. Al final de los 2 casos llama a una funcion que escribe en el archvo

        String direccion = Environment.getExternalStorageDirectory() +nombre_depa+".txt";
        Log.d("inventario_departamento", "dentro de checarArchivo");
        File file = new File(direccion);
        Log.d("inventario_departamento", "despues de new file");
        if(file.exists()){

            escribirCodigo();
        }else{
            try {
                //Crea archivo
                if(file.createNewFile())
                    Log.d("inventario_departamento", "archivo no existe");
                    escribirCodigo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void escribirCodigo(){
        try {
            Log.d("inventario_departamento", "dentro de escribir");
            FileWriter archivo = new FileWriter(Environment.getExternalStorageDirectory() +nombre_depa+".txt", true);
            String linea=codigo.getText().toString();//llamar function que verifique que exista el codigo
            Log.d("inventario_departamento", "antes de escribir");
            archivo.write( linea.trim()+"\n" );
            Log.d("inventario_departamento", "despues de escribir ");
            archivo.flush();
            archivo.close();
            codigo.setText("");
        } catch (IOException e) {
            Log.d("inventario_departamento", String.valueOf(e));
        }
    }
    public void imprimirArchivo(View view){
        Intent opc=new Intent(this, nuevo_inventario.class);

        try {
            FileReader archivo = new FileReader(Environment.getExternalStorageDirectory() + nombre_depa + ".txt");
            int band=archivo.read();
            while(band!=-1){
                Log.d("inventario_departamento", String.valueOf(band));
                band= archivo.read();
            }
        }catch(Exception e){
            Log.d("inventario_departamento", String.valueOf(e));
        }
        startActivity(opc);
    }
}