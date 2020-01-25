package com.arenterprise.pointofsale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {


    private Button DailyButton,OverallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        DailyButton= (Button) findViewById(R.id.btndailySale);
        OverallButton= (Button)findViewById(R.id.btnoverall);

        DailyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this,DailyActivity.class);
                startActivity(in);

            }
        });

        OverallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this,OverallActivity.class);
                startActivity(in);
            }
        });
    }
}
