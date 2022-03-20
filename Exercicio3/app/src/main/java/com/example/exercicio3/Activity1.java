package com.example.exercicio3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.exercicio3.databinding.Activity1Binding;

import java.util.List;

public class Activity1 extends AppCompatActivity {

    private Activity1Binding binding;
    private MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = Activity1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent in = new Intent(getBaseContext(), Activity2.class);
                startActivity(in);
            }
        });

        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("TELA","Activity 1");
        startService(intent);

        List<MyContact> contacts = ContactsHelper.getContacts(this);

        if (contacts.size() >= 1){
            Log.d("HSS", "ID: "+contacts.get(0).getId() + " Nome: " + contacts.get(0).getName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        receiver = new MyBroadcastReceiver();
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

    static class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("HSS", "O Status do Wi-Fi Mudou");
        }
    }
}