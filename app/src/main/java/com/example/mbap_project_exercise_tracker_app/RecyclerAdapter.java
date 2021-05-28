package com.example.mbap_project_exercise_tracker_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    String data1[], data2[], data3[];
    int facility_imgs[];

    public RecyclerAdapter(Context ct, String s1[], String s2[], String s3[], int img[]){
        context = ct;
        data1 = s1;
        data2 = s2;
        data3 = s3;
        facility_imgs = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.facility_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        holder.facilityName.setText(data1[position]);
        holder.facilityOH.setText(data2[position]);
        holder.facilityAddress.setText(data3[position]);
        holder.facilityImage.setImageResource(facility_imgs[position]);

        holder.facilityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SelectfacilityActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("data3", data3[position]);
                intent.putExtra("facility_imgs", facility_imgs[position]);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data2.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView facilityName, facilityOH, facilityAddress;
        ImageView facilityImage;
        ConstraintLayout facilityLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            facilityName = itemView.findViewById(R.id.facilityName);
            facilityOH = itemView.findViewById(R.id.facility_OH);
            facilityAddress = itemView.findViewById(R.id.facility_Address);
            facilityImage = itemView.findViewById(R.id.facilityImage);
            facilityLayout = itemView.findViewById(R.id.facilityLayout);
        }
    }
}
