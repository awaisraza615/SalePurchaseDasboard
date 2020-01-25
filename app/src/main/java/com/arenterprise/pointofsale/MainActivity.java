package com.arenterprise.pointofsale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button Login;
    private EditText txtName,txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtName=(EditText) findViewById(R.id.txtname);
        txtPass =(EditText)findViewById(R.id.txtpass);
        Login= (Button) findViewById(R.id.btnlogin);

     Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = txtName.getText().toString();
                String pass = txtPass.getText().toString();

                if(name.equals("admin")&& pass.equals("123"))
                {
                    Intent in = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(in);
                    finish();
                }


            }
        });
    }
}
