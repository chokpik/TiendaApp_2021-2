package com.example.tiendaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    private static final int CODIGO_AGREGAR_PRODUCTO = 100;
    private static final int CODIGO_DETALLE_PRODUCTO = 110;
    private Button btFormulario;
    private ProductoAdapter adapter;

    private ArrayList<Producto> productos = new ArrayList<Producto>();
    private RecyclerView rvProducto;

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

            Intent intent = new Intent(ListadoActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else {
            System.out.println("Joder error");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        String correo = getIntent().getStringExtra("correo");
        setTitle(correo);

        rvProducto = findViewById(R.id.rv_productos_listado);
        btFormulario = findViewById(R.id.button);

        btFormulario.setOnClickListener (view -> {

            Intent miItencion = new Intent(ListadoActivity.this, FormularioActivity.class);
            startActivityForResult(miItencion, CODIGO_AGREGAR_PRODUCTO);

        });




        productos.add(new Producto("Razer DeathAdder Gaming Ratón", 10000.0, "https://m.media-amazon.com/images/I/61X0kOEm24L._AC_SL1500_.jpg"));
        productos.get(0).setDescripcion("Raton gaming");
        productos.add(new Producto("NPET teclado profesional retroiluminado", 10000.0, "https://m.media-amazon.com/images/I/61w0BypBzrL._AC_SL1500_.jpg"));
        productos.get(1).setDescripcion("Teclado gaming");
        productos.add(new Producto("SAMSUNG CR50 - 60Hz Actualizado", 10000.0, "https://m.media-amazon.com/images/I/61LmgKYPiJL._AC_SL1024_.jpg"));
        productos.get(2).setDescripcion("Monitor gaming");
        productos.add(new Producto("Corsair 4000D Caja de PC ATX", 10000.0, "https://m.media-amazon.com/images/I/71rWTjzZWBL._AC_SL1500_.jpg"));
        productos.get(3).setDescripcion("Caja pc gaming");
        productos.add(new Producto("Alfombrilla de ratón para ordenador", 10000.0, "https://m.media-amazon.com/images/I/51b+kKkWkwS._AC_SL1280_.jpg"));
        productos.get(4).setDescripcion("Alfombrilla gaming");

        adapter = new ProductoAdapter(productos);
        rvProducto.setAdapter(adapter);
        rvProducto.setLayoutManager(new LinearLayoutManager(ListadoActivity.this));
        rvProducto.setHasFixedSize(true);

        adapter.setOnItemClickListener(new ProductoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Producto producto) {
                Intent intent = new Intent(ListadoActivity.this, DetalleActivity.class);

                intent.putExtra("producto", producto);
                startActivityForResult(intent, CODIGO_DETALLE_PRODUCTO);
            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CODIGO_AGREGAR_PRODUCTO && resultCode == RESULT_OK) {

            if(data != null){
                Producto miProducto = (Producto) data.getSerializableExtra("producto");
                Producto ultimo = productos.get(productos.size()-1);
                int id = ultimo.getId()+1;
                miProducto.setId(id);
                productos.add(miProducto);

                adapter.setListado(productos);

                Toast.makeText(ListadoActivity.this, "Regresando al formulario", Toast.LENGTH_SHORT).show();
            }
        }
        if(requestCode == CODIGO_DETALLE_PRODUCTO && resultCode == RESULT_OK){
            if(data != null){
                Producto miProducto = (Producto) data.getSerializableExtra("producto");
                Boolean editar = data.getBooleanExtra("editar", false);

                for (Producto elemento: productos) {
                    Log.d("ELIMINAR", "Lista:" + elemento.getId() + " eliminar: " + miProducto.getId());
                    if (elemento.getId() == miProducto.getId()){
                        int posicion = productos.indexOf(elemento);
                        productos.remove(elemento);
                        if(editar){
                            productos.set(posicion, miProducto);
                        }else{
                            productos.remove(elemento);
                        }
                        break;
                    }
                }
                adapter.setListado(productos);
            }
        }
    }
}