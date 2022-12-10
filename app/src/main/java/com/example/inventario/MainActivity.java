package com.example.inventario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //cambio de activity
    public void EscogerOpcion(View v){
        Intent opc;
        switch(v.getId()){
            case R.id.opc_buscarProduct:
                 opc=new Intent(this, buscar_product.class);
            break;
            case R.id.opc_nuevoInventario:
                 opc=new Intent(this, nuevo_inventario.class);
            break;
            default:
                throw new IllegalStateException("Unexpected value: ");
        }
        startActivity(opc);
    }

}