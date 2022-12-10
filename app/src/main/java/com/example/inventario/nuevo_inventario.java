package com.example.inventario;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.view.ViewGroup.LayoutParams;

public class nuevo_inventario extends AppCompatActivity {

    public static String[] departamentos;
    public static CheckBox[] checkbox_departamentos;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_inventario);
        departamentos= CargarDepartamentos();
        checkbox_departamentos=new CheckBox[departamentos.length];
        Log.d("nuevo_inventario", String.valueOf(departamentos.length));
        checkboxprint();
    }
    public String[] CargarDepartamentos(){
        InputStream archivo = getResources().openRawResource(R.raw.departamentos);
        BufferedReader buffer = new BufferedReader(new InputStreamReader(archivo));
        boolean seguir=true;
        String linea;
        ArrayList<String> departamentoslist = new ArrayList<>();
        try{
            while(buffer.readLine() != null && seguir){
                try{
                    linea=buffer.readLine();
                    departamentoslist.add(linea);
                }catch(Exception e){
                    seguir=false;
                }
            }
        }catch (Exception e){}
        String[] departamentos_array=departamentoslist.toArray(new String[departamentoslist.size()]);
        return departamentos_array;
    }
    public void checkboxprint(){
        LinearLayout seccionIndustria = (LinearLayout) findViewById(R.id.seccion_departamentos);
        for (int i=0;i<departamentos.length;i++) {
            CheckBox opcion = new CheckBox(this);
            opcion.setText(departamentos[i]);
            Log.d("nuevo_inventario", String.valueOf(departamentos[i]));
            opcion.setLayoutParams(
                    new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            seccionIndustria.addView(opcion);
            checkbox_departamentos[i]=(opcion);
        }
    }
    public void obtenerDepa(View view){

        boolean bandera=true;
        int i=0;

        while(i<checkbox_departamentos.length && bandera==true){
            Log.d("nuevo_inventario",String.valueOf(i));
            if(checkbox_departamentos[i].isChecked()){
                Log.d("nuevo_inventario","es seleccionado");
                Intent opc=new Intent(this, inventario_departamento.class);
                opc.putExtra("departamento",checkbox_departamentos[i].getText());
                opc.putExtra("bandera",bandera);
                startActivity(opc);
            }else{
                Log.d("nuevo_inventario","no es seleccionado");
            }
            i++;
        }
    }

}