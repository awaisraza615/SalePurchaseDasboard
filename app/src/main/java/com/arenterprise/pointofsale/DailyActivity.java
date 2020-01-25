package com.arenterprise.pointofsale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;



import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DailyActivity extends AppCompatActivity {

    PieChart pieChart;
    BarChart barChart;
    DatabaseReference refDailSellCash,refDailyInvoice;
    String CashAmount;
    String Discount;
    String Invoice;

    String Id[],Amount[];

    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);


        getDailySellCash();





    }

    private void getDailyInvoice() {
        refDailyInvoice = FirebaseDatabase.getInstance().getReference("DailySellInvoice");
        refDailyInvoice.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                count = Integer.parseInt(dataSnapshot.child("Counter").child("count").getValue(String.class));


                Id= new String[count];
                Amount = new String[count];

                for (int i=0;i<count;i++) {

                    Id[i] = dataSnapshot.child(String.valueOf(i)).child("Invoices_Id").getValue(String.class);
                    Amount[i] = dataSnapshot.child(String.valueOf(i)).child("Amount").getValue(String.class);


                    Log.d("id3", "onDataChange: "+i);
                    Log.d("id", "onDataChange: "+Id[i]);
                    Log.d("id2", "onDataChange: "+Amount[i]);


                }
               /* Id = dataSnapshot.child("0").child("Invoices_Id").getValue(String.class);
                Amount = dataSnapshot.child("0").child("Amount").getValue(String.class);
                Id2 = dataSnapshot.child("1").child("Invoices_Id").getValue(String.class);
                Amount2 = dataSnapshot.child("1").child("Amount").getValue(String.class);
                Id3 = dataSnapshot.child("2").child("Invoices_Id").getValue(String.class);
                Amount3 = dataSnapshot.child("2").child("Amount").getValue(String.class);
                Id4 = dataSnapshot.child("3").child("Invoices_Id").getValue(String.class);
                Amount4 = dataSnapshot.child("3").child("Amount").getValue(String.class);*/


                selBarChart();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    private void getDailySellCash()
    {
        refDailSellCash = FirebaseDatabase.getInstance().getReference("DailySellCash");
        refDailSellCash.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                CashAmount = dataSnapshot.child("CashAmount").getValue(String.class);
                Invoice = dataSnapshot.child("Invoices").getValue(String.class);
                Discount = dataSnapshot.child("Dicount").getValue(String.class);
                Log.d("Cash", "onDataChange: "+CashAmount);





                sellPieChart();

                getDailyInvoice();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void selBarChart() {

        barChart =(BarChart) findViewById(R.id.barchart);

        BarDataSet barDataSet1 = new BarDataSet(dataValues1(),"Daily invoice Report");
        BarData barData = new BarData();
        barData.addDataSet(barDataSet1);

        barChart.setData(barData);
        barChart.invalidate();
        }


    private ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals = new ArrayList<>();
        for (int j=0;j<count;j++) {
            dataVals.add(new BarEntry(Float.parseFloat(Id[j]), Float.parseFloat(Amount[j])));
        }
        return dataVals;
    }


    private void sellPieChart() {




        pieChart =(PieChart)findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(Float.parseFloat(CashAmount),"Cash"));
       // yValues.add(new PieEntry(Float.parseFloat(Invoice),"Invoices"));
        yValues.add(new PieEntry(Float.parseFloat(Discount),"Discount"));

        PieDataSet dataSet = new PieDataSet(yValues,"Daily Cash Report");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

    }


}
