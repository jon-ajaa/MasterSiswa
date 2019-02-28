package com.blogspot.blogsetyaaji.mastersiswa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {
    private ArrayList<HashMap<String, String>> listData;
    private Context context;

    public ClassAdapter(FragmentActivity activity,
                        ArrayList<HashMap<String, String>> classdata) {

        this.context = activity;
        this.listData = classdata;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.class_item, viewGroup, false);
        return new ClassViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder classViewHolder, int i) {
        classViewHolder.txtNamaC.setText(listData.get(i).get("classes_name"));
        classViewHolder.txtNo.setText(listData.get(i).get("id"));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ClassViewHolder extends RecyclerView.ViewHolder {
        TextView txtNo, txtNamaC;

        public ClassViewHolder(View itemView) {
            super(itemView);
            txtNamaC = itemView.findViewById(R.id.item_namakelas);
            txtNo = itemView.findViewById(R.id.item_idclass);
        }
    }
}
