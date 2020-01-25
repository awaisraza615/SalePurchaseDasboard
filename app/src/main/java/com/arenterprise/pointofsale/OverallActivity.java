package com.arenterprise.pointofsale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.KeyStore;
import java.util.ArrayList;

public class OverallActivity extends AppCompatActivity {

    private LineChart lineChart, lineChart2;
    DatabaseReference refDateSell, refDatePurchase;

    String Date, Amount,Date1, Amount1;
    int day ;
    int month;
    int year ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall);





        getDateWiseSell();


    }

    private void getDateWisePurchase() {
        refDatePurchase = FirebaseDatabase.getInstance().getReference("DatewisePurchase");
        refDatePurchase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Date1 = dataSnapshot.child("0").child("Date").getValue(String.class);
                Amount1 = dataSnapshot.child("0").child("Amount").getValue(String.class);

             String date = Date1;
                String[] values = date.split("/");
              day = Integer.parseInt(values[0]);
               month = Integer.parseInt(values[1]);
              year = Integer.parseInt(values[2]);

                Log.d("Cash1", "onDataChange: " + month);

                OverallLineChartpurchase();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getDateWiseSell() {
        refDateSell = FirebaseDatabase.getInstance().getReference("DatewiseSell");
        refDateSell.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                Date = dataSnapshot.child("0").child("Date").getValue(String.class);
                Amount = dataSnapshot.child("0").child("Amount").getValue(String.class);

                String date = Date;
                String[] values = date.split("/");
                day = Integer.parseInt(values[0]);
                month = Integer.parseInt(values[1]);
                year = Integer.parseInt(values[2]);

                Log.d("Cash", "onDataChange: " + Amount);

                OverallLineChartSell();
                getDateWisePurchase();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void OverallLineChartSell() {


        lineChart = (LineChart) findViewById(R.id.lineChart);

        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(), "DateWise Sell");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();
    }

    private ArrayList<Entry> dataValues1() {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(9, 2000));
        dataVals.add(new Entry(10, 3000));
        dataVals.add(new Entry(11, 1000));
        dataVals.add(new Entry(month, Float.parseFloat(Amount)));

        return dataVals;

    }

    private void OverallLineChartpurchase() {

        lineChart2 = (LineChart) findViewById(R.id.lineChart2);

        LineDataSet lineDataSet1 = new LineDataSet(dataValues2(), "DateWise Purchase");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData data = new LineData(dataSets);
        lineChart2.setData(data);

        lineChart2.invalidate();
    }

    private ArrayList<Entry> dataValues2() {
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(9, 3000));
        dataVals.add(new Entry(10, 2000));
        dataVals.add(new Entry(11, 1500));
        dataVals.add(new Entry(month, Float.parseFloat(Amount1)));

        return dataVals;

    }
}
