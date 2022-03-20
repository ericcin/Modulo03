package com.example.exercicio3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercicio3.databinding.Activity1Binding;
import com.example.exercicio3.databinding.Activity2Binding;

import java.util.List;

public class Activity2 extends AppCompatActivity {

    private Activity2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Activity2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), Activity3.class);
                startActivity(in);
            }
        });

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        if (contacts.size() >= 2){
            Log.d("HSS", "ID: "+contacts.get(1).getId() + " Nome: " + contacts.get(1).getName());
        }

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","Activity 2");
        startService(intent);
    }
}