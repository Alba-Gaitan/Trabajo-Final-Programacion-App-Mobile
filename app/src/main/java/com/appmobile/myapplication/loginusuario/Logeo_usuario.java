package com.appmobile.myapplication.loginusuario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.appmobile.myapplication.MainActivity;
import com.appmobile.myapplication.R;

public class Logeo_usuario extends AppCompatActivity {

    EditText EtPasswordU;
    Button BtnIngresar;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF = "mi_pref";
    private static final String KEY_PASSWORD = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE , WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_logeo_usuario);
        InicializarVariables();

        BtnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String S_password = EtPasswordU.getText().toString().trim();
                //Obtener la contraseña maestra almacenada en SharePreference
                String password_SP = sharedPreferences.getString(KEY_PASSWORD, null);

                if (S_password.equals("")){
                    Toast.makeText(Logeo_usuario.this, "Campo es obligatorio", Toast.LENGTH_SHORT).show();
                }
                else if (!S_password.equals(password_SP)){
                    Toast.makeText(Logeo_usuario.this, "La contraseña no es la correcta", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(Logeo_usuario.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void InicializarVariables(){
        EtPasswordU = findViewById(R.id.EtPasswordU);
        BtnIngresar = findViewById(R.id.BtnIngresar);
        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

    }


}