package com.example.todolistoasis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class LoginAct extends AppCompatActivity {

    EditText edtname, edtnumber;
    Button btnlogin;

    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnlogin = findViewById(R.id.btnlogin);
        edtname = findViewById(R.id.edtname);
        edtnumber = findViewById(R.id.edtnumber);

        String name = edtname.getText().toString();
        String number = edtnumber.getText().toString();

        db = new DBHelper(this);
        Model model = new Model();
        model.setName(name);
        model.setNumber(number);
        db.addUserDetail(model);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<Model> detail = db.getAllDetails();
                for (Model udetail : detail){
                    Toast.makeText(getApplicationContext(), "Name: " + udetail.getName() + "Number: " + udetail.getNumber(), Toast.LENGTH_SHORT).show();
                }

                SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putBoolean("flag", true);
                editor.apply();

                Intent inext = new Intent(LoginAct.this, MainActivity.class);
                inext.putExtra("name", name);
                startActivity(inext);
                finish();
            }
        });
    }

}