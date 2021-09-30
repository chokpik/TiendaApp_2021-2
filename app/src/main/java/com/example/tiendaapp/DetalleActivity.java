package com.example.tiendaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetalleActivity extends AppCompatActivity {

   private static final int CODIGO_EDITAR_PRODUCTO = 110;

    private TextView tvTitulo;
    private ImageView ivProducto;
    private TextView tvPrecio;
    private TextView tvDescripcion;
    private Button btEditar;
    private Button btEliminar;
    private Producto producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        producto = (Producto) getIntent().getSerializableExtra("producto");

        tvTitulo = findViewById(R.id.tv_nombre_producto_detalle);
        ivProducto = findViewById(R.id.imgv_producto_detalle);
        tvPrecio = findViewById(R.id.tv_precio_detalle);
        tvDescripcion = findViewById(R.id.tv_descrp_detalle);
        btEditar= findViewById(R.id.bt_editar_producto);
        btEliminar = findViewById(R.id.bt_eliminar_producto);

        tvTitulo.setText(getString(R.string.txt_titulo_detalle, producto.getNombre()));
        tvPrecio.setText(String.valueOf(producto.getPrecio()));
        tvDescripcion.setText(producto.getDescripcion());
        Glide.with(DetalleActivity.this).load(producto.getUrlImagen()).into(ivProducto);

        btEditar.setOnClickListener(view -> {
            Intent i = new Intent(DetalleActivity.this, FormularioActivity.class);
            i.putExtra("producto", producto);
            startActivityForResult(i, CODIGO_EDITAR_PRODUCTO);
        });

        btEliminar.setOnClickListener( view -> {
            Intent iData = new Intent();
            iData.putExtra("producto", producto);
            setResult(RESULT_OK, iData);
            finish();
        });
    }

    private void cargarDatosProducto() {
        tvTitulo.setText(getString(R.string.txt_titulo_detalle, producto.getNombre()));

        tvPrecio.setText(String.valueOf(producto.getPrecio()));

        tvDescripcion.setText(producto.getDescripcion());

        Glide.with(DetalleActivity.this).load(producto.getUrlImagen()).into(ivProducto);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODIGO_EDITAR_PRODUCTO && resultCode == RESULT_OK){
            if(data != null){

                producto = (Producto) data.getSerializableExtra("producto");
                cargarDatosProducto();

            }
        }
    }

    @Override
    public void onBackPressed(){
        Intent iData = new Intent();
        iData.putExtra("producto", producto);
        iData.putExtra("editar", true);
        setResult(RESULT_OK, iData);

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_appbar, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.mi_cerrar_sesion) {
            SharedPreferences preferences = getSharedPreferences(getString(R.string.txt_nombre_preferencias), MODE_PRIVATE);
            SharedPreferences.Editor editable = preferences.edit();
            editable.clear();
            editable.apply();

            Intent intent = new Intent(DetalleActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(intent);
            finish();
        } else {
            System.out.println("Joder error");
        }

        return super.onOptionsItemSelected(item);
    }
}