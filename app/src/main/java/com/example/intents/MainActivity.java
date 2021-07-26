package com.example.intents;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    ImageView ivMood, ivPhone, ivWeb, ivLocation;
    Button btnCreate;

    final int CREATE_CONTACT=1;
    String name= "", phone= "", web="", map="", mood="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivMood= findViewById(R.id.ivMood);
        ivPhone = findViewById(R.id.ivPhone);
        ivLocation= findViewById(R.id.ivLocation);
        ivWeb = findViewById(R.id.ivWeb);

        ivLocation.setVisibility(View.GONE);
        ivWeb.setVisibility(View.GONE);
        ivPhone.setVisibility(View.GONE);
        ivMood.setVisibility(View.GONE);

        btnCreate= findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this, com.example.intents.CreateContact.class);

                startActivityForResult(intent, CREATE_CONTACT);

            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+ phone));
                startActivity(intent);
            }
        });

        ivWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+ web));
                startActivity(intent);
            }
        });

        ivLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+ map));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CREATE_CONTACT){

            if(resultCode == RESULT_OK){
                ivLocation.setVisibility(View.VISIBLE);
                ivWeb.setVisibility(View.VISIBLE);
                ivPhone.setVisibility(View.VISIBLE);
                ivMood.setVisibility(View.VISIBLE);

                name= data.getStringExtra("name");
                phone= data.getStringExtra("phone");
                map= data.getStringExtra("map");
                web= data.getStringExtra("web");
                mood= data.getStringExtra("mood");

                if(mood.equals("happy")){
                    ivMood.setImageResource(R.drawable.happy);
                }else if(mood.equals("sad")){
                    ivMood.setImageResource(R.drawable.sad);
                }else{
                    ivMood.setImageResource(R.drawable.ok);
                }

            }else{
                Toast.makeText(this, "No se ha ingresado toda la información", Toast.LENGTH_SHORT).show();
            }
        }
    }
}