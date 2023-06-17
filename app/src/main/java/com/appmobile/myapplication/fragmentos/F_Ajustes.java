package com.appmobile.myapplication.fragmentos;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.appmobile.myapplication.MainActivity;
import com.appmobile.myapplication.R;
import com.appmobile.myapplication.basededatos.BDHelper;
import com.appmobile.myapplication.basededatos.Constants;
import com.appmobile.myapplication.loginusuario.Logeo_usuario;

public class F_Ajustes extends Fragment {

    TextView Eliminar_Todos_Registros, Cambiar_password;
    Dialog dialog, dialog_p_m;

    BDHelper bdHelper;

    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF = "mi_pref";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_C_PASSWORD = "c_password";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE , WindowManager.LayoutParams.FLAG_SECURE);
        View view = inflater.inflate(R.layout.fragment_f__ajustes, container, false);

        Eliminar_Todos_Registros = view.findViewById(R.id.Eliminar_Todos_Registros);
        Cambiar_password = view.findViewById(R.id.Cambiar_password);
        dialog = new Dialog(getActivity());
        dialog_p_m = new Dialog(getActivity());
        bdHelper = new BDHelper(getActivity());

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        Eliminar_Todos_Registros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Eliminar_Registros();
            }
        });

        Cambiar_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getActivity(), "Cambiar contraseña", Toast.LENGTH_SHORT).show();
                CuadroDialogoPassword();
            }
        });

        return view;
    }


    private void Dialog_Eliminar_Registros() {

        Button Btn_Si, Btn_Cancelar;

        dialog.setContentView(R.layout.cuadro_dialogo_eliminar_todos_registros);

        Btn_Si = dialog.findViewById(R.id.Btn_Si);
        Btn_Cancelar = dialog.findViewById(R.id.Btn_Cancelar);

        Btn_Si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bdHelper.EliminarTodosRegistros();
                startActivity(new Intent(getActivity(), MainActivity.class));
                Toast.makeText(getActivity(), "Se ha eliminado todos los registros", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Btn_Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setCancelable(false);
    }

    private void CuadroDialogoPassword() {
        //establecer las vistas
        EditText Password;
        EditText Et_nuevo_password, Et_C_nuevo_password;
        Button Btn_cambiar_password, Btn_cancelar_password;

        String password_recuperada = sharedPreferences.getString(KEY_PASSWORD, null);

        //conectamos con el cuadro del dialogo
        dialog_p_m.setContentView(R.layout.cuadro_dialogo_password);

        //Inicializar las vistas
        Password = dialog_p_m.findViewById(R.id.Password);
        Et_nuevo_password = dialog_p_m.findViewById(R.id.Et_nuevo_password);
        Et_C_nuevo_password = dialog_p_m.findViewById(R.id.Et_C_nuevo_password);
        Btn_cambiar_password = dialog_p_m.findViewById(R.id.Btn_cambiar_password);
        Btn_cancelar_password = dialog_p_m.findViewById(R.id.Btn_cancelar_password);

        Btn_cambiar_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obtener los datos de los edittext
                String S_nuevo_password = Et_nuevo_password.getText().toString().trim();
                String S_c_nuevo_password = Et_C_nuevo_password.getText().toString().trim();

                /*Validación de datos*/
                if (S_nuevo_password.equals("")){
                    Toast.makeText(getActivity(), "Ingrese nueva contraseña", Toast.LENGTH_SHORT).show();
                }
                else if (S_c_nuevo_password.equals("")){
                    Toast.makeText(getActivity(), "Confirme nueva contraseña", Toast.LENGTH_SHORT).show();
                }
                else if (S_nuevo_password.length()<6){
                    Toast.makeText(getActivity(), "La contraseña debe tener más de 6 caracteres", Toast.LENGTH_SHORT).show();
                }
                else if (!S_nuevo_password.equals(S_c_nuevo_password)){
                    Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    /*Pasar los nuevos datos a las llaves*/
                    editor.putString(KEY_PASSWORD, S_nuevo_password);
                    editor.putString(KEY_C_PASSWORD, S_c_nuevo_password);
                    editor.apply();
                    /*Salir de la aplicación, para iniciar sesión con la nueva contraseña*/
                    startActivity(new Intent(getActivity(), Logeo_usuario.class));
                    getActivity().finish();
                    Toast.makeText(getActivity(), "La contraseña maestra se ha cambiado", Toast.LENGTH_SHORT).show();
                    dialog_p_m.dismiss();
                }


            }
        });

        Btn_cancelar_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Cancelado por el usuario", Toast.LENGTH_SHORT).show();
                dialog_p_m.dismiss();
            }
        });

        Password.setText(password_recuperada);
        Password.setEnabled(false);
        Password.setBackgroundColor(Color.TRANSPARENT);
        Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        dialog_p_m.show();
        dialog_p_m.setCancelable(false);

    }
}