package com.example.tiendaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTitulo;
    private EditText etCorreo;
    private EditText etPasswd;
    private Button btIniciar;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitulo = findViewById(R.id.tv_titulo_inicio);
        etCorreo = findViewById(R.id.et_correo_inicio);
        etPasswd = findViewById(R.id.et_passwd_main);
        btIniciar = findViewById(R.id.bt_iniciar_inicio);

        tvTitulo.setText("Por codigo");
        preferences = getSharedPreferences(getString(R.string.txt_nombre_preferencias), MODE_PRIVATE);

        boolean logeado = preferences.getBoolean(getString(R.string.txt_preferencia_login), false);

        if(logeado)
        {
            Intent intent = new Intent(MainActivity.this, ListadoActivity.class);
            startActivity(intent);

            finish();
        }

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usu = "camilo@correo.com";
                String pass = "camilo123";
                String correo = etCorreo.getText().toString();
                String passwd = etPasswd.getText().toString();

                if(correo.equals(usu) && passwd.equals(pass)){
                    Toast.makeText(MainActivity.this, "Bienvenido " + correo, Toast.LENGTH_LONG).show();

                    SharedPreferences.Editor editable = preferences.edit();
                    editable.putBoolean(getString(R.string.txt_preferencia_login), true);
                    editable.putString("email", correo);
                    editable.apply();


                    Intent miIntencion = new Intent(MainActivity.this, ListadoActivity.class);
                    miIntencion.putExtra("correo",correo);
                    startActivity(miIntencion);
                    finish();
                } else{
                    Toast.makeText(MainActivity.this, "Datos errados", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}