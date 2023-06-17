package com.appmobile.myapplication.fragmentos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appmobile.myapplication.R;


public class F_AcercaDe extends Fragment {

    ImageView Ir_facebook, Ir_instagram, Ir_youtube,Ir_twitter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_f__acerca_de, container, false);

        Ir_facebook = view.findViewById(R.id.Ir_facebook);
        Ir_instagram = view.findViewById(R.id.Ir_instagram);
        Ir_youtube = view.findViewById(R.id.Ir_youtube);
        Ir_twitter = view.findViewById(R.id.Ir_twitter);

        Ir_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ir_p_facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com"));
                startActivity(ir_p_facebook);
            }
        });

        Ir_instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ir_p_instagram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com"));
                startActivity(ir_p_instagram);
            }
        });

        Ir_youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ir_p_youtube = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=zv-wiRg7_lI"));
                startActivity(ir_p_youtube);
            }
        });

        Ir_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ir_p_twitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.twitter.com"));
                startActivity(ir_p_twitter);
            }
        });

        return view;
    }
}