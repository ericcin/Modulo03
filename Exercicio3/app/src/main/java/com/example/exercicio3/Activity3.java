package com.example.exercicio3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.net.wifi.WifiManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exercicio3.databinding.Activity3Binding;

import java.util.List;

public class Activity3 extends AppCompatActivity {

    private Activity3Binding binding;
    private Activity1.MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getBaseContext(), Activity1.class);
                startActivity(in);
            }
        });

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        if (contacts.size() >= 3){
            Log.d("HSS", "ID: "+contacts.get(2).getId() + " Nome: " + contacts.get(2).getName());
        }

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","Activity 3");
        startService(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new Activity1.MyBroadcastReceiver();
        IntentFilter filter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);

        registerReceiver(receiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("HSS", "O Status do Wi-Fi Mudou");
        }
    }
}