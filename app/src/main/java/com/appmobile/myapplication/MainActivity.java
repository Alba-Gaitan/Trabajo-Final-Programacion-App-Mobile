package com.appmobile.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Toast;

import com.appmobile.myapplication.fragmentos.F_AcercaDe;
import com.appmobile.myapplication.fragmentos.F_Ajustes;
import com.appmobile.myapplication.fragmentos.F_Todas;
import com.appmobile.myapplication.loginusuario.Logeo_usuario;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    boolean DobleToqueParaSalir = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this , drawer , toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        /*Fragmento al ejecutar la aplicación*/
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new F_Todas()).commit();
            navigationView.setCheckedItem(R.id.Opcion_Todas);
        }

        //VIDEO

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Opcion_Todas){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new F_Todas()).commit();
        }
        if (id == R.id.Opcion_Ajustes){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new F_Ajustes()).commit();
        }
        if (id == R.id.Opcion_AcercaDe){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new F_AcercaDe()).commit();
        }
        if (id == R.id.Opcion_Salir){
            CerrarSesion();

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void CerrarSesion() {
        startActivity(new Intent(MainActivity.this, Logeo_usuario.class));
        Toast.makeText(this, "Cerraste sesión", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (DobleToqueParaSalir){
            super.onBackPressed();
            Toast.makeText(this, "Saliste de la aplicación", Toast.LENGTH_SHORT).show();
            return;
        }

        /*Al presionar una vez en el botón de retroceso*/
        this.DobleToqueParaSalir = true;
        Toast.makeText(this, "Presione 2 veces para salir", Toast.LENGTH_SHORT).show();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                DobleToqueParaSalir = false;
            }
        }, 2000);


    }
//    VIDEO CODIGO


}