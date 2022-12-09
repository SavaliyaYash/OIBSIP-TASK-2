package com.example.todolistoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> itemAdapter;
    AbstractList<String> item;

    ListView listview;
    Button btnadd, btnlogout;

    LoginAct logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = findViewById(R.id.listview);
        btnadd = findViewById(R.id.btnadd);
        btnlogout = findViewById(R.id.btnlogout);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

        item = new ArrayList<>();
        itemAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, item);
        listview.setAdapter(itemAdapter);

        setUpListViewListner();

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putBoolean("flag", false);
                editor.apply();

                Intent inext = new Intent(MainActivity.this, LoginAct.class);

                startActivity(inext);
                finish();
            }
        });

    }

    private void setUpListViewListner() {
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_SHORT).show();

                item.remove(i);
                itemAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }

    private void addItem(View view) {
        EditText input = findViewById(R.id.edttask);
        String itemText = input.getText().toString();

        if(!(itemText.equals(""))){
            itemAdapter.add(itemText);
        }
        else {
            Toast.makeText(getApplicationContext(), "plzz... Enter Text", Toast.LENGTH_SHORT).show();
        }
    }
}