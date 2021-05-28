package com.example.mbap_project_exercise_tracker_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FacilitiesActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[];
    int facility_imgs[] = {R.drawable.gymmboxx_bedok, R.drawable.bedok_activesg, R.drawable.tampines_activesg};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facilities);

        s1 = getResources().getStringArray(R.array.facilities);
        s2 = getResources().getStringArray(R.array.opening_hours);
        s3 = getResources().getStringArray(R.array.address);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this, s1, s2, s3, facility_imgs);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    public void goToProfile2(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }
}

