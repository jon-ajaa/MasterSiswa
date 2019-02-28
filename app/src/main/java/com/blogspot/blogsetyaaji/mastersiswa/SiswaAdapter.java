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

class SiswaAdapter extends RecyclerView.Adapter<SiswaAdapter.StudentViewHolder> {

    // TODO: 2
    private ArrayList<HashMap<String, String>> listData;
    private Context context;

    // TODO: 1
    public SiswaAdapter(FragmentActivity activity,
                           ArrayList<HashMap<String, String>> studentsData) {
        // TODO: 3
        this.context = activity;
        this.listData = studentsData;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // memasukkan layout item student ke adapter
        // TODO: 4
        View view = LayoutInflater.from(context).inflate(R.layout.student_item, viewGroup,
                false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        // memanmpilkan data di kompoen layout item student
        // TODO: 8
        studentViewHolder.txtNama.setText(listData.get(i).get("nama"));
        studentViewHolder.txtAlamat.setText(listData.get(i).get("alamat"));
    }

    @Override
    public int getItemCount() {
        // menentukan jumlah data yang ingin ditampilkan
        // TODO: 5
        return listData.size();
    }

    // untuk mendeklarasi dan menginisialisasi komponen layout item student
    public class StudentViewHolder extends RecyclerView.ViewHolder {
        // deklarasi
        // TODO: 6
        TextView txtNama, txtAlamat;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            // inisialisasi
            // TODO: 7
            txtNama = itemView.findViewById(R.id.item_namasiswa);
            txtAlamat= itemView.findViewById(R.id.item_alamatsiswa);
        }
    }
}