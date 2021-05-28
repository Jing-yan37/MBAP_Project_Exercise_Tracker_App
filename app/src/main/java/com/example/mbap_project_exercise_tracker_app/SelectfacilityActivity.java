package com.example.mbap_project_exercise_tracker_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectfacilityActivity extends AppCompatActivity {

    ImageView facilityImage2;
    TextView facilityName2, facilityOH2, facilityAddress2;
    String data1, data2, data3;
    int facilityImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfacility);

        facilityImage2 = findViewById(R.id.facilityImage2);
        facilityName2 = findViewById(R.id.facilityName2);
        facilityOH2 = findViewById(R.id.facility_OH2);
        facilityAddress2 = findViewById(R.id.facility_Address2);

        getData();
        setData();
    }
    private void getData() {
        if (getIntent().hasExtra("facilityImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2") && getIntent().hasExtra("data3")) {
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            facilityImage = getIntent().getIntExtra("facilityImage", 1);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    private void setData(){
        facilityName2.setText(data1);
        facilityOH2.setText(data2);
        facilityAddress2.setText(data3);
        facilityImage2.setImageResource(facilityImage);

    }
}
