package com.blogspot.blogsetyaaji.mastersiswa;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowStudentsFragment extends Fragment {


    public ShowStudentsFragment() {
        // Required empty public constructor
    }

    private ArrayList<HashMap<String, String>> studentsData;
    /* arraylist{
         hashmap {
             "id": "1",
             "name": "Abdullah",
             "address": "Depok",
             "class_id": "5",
            "status": "active"
         },
         hashmap {
             key : value,
             key : value
         },
         hashmap {
             key : value,
             key : value
         }
     }*/
    private String url;

    TextView txtDnama, txtDalamat, txtDjson;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        /*View view;
        view = inflater.inflate(R.layout.fragment_show_students, container, false);
        RecyclerView lvStudent = view.findViewById(R.id.lv_student);*/

//        return inflater.inflate(R.layout.fragment_show_students, container, false);
        return inflater.inflate(R.layout.student_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        RecyclerView lvStudent = view.findViewById(R.id.lv_student);
        txtDnama = view.findViewById(R.id.txt_dnama);
        txtDalamat = view.findViewById(R.id.txt_dalamat);
        txtDjson = view.findViewById(R.id.txt_djson);

        studentsData = new ArrayList<HashMap<String, String>>();
        url = "http://192.168.30.7/SMPIDN/webdatabase/apitampilsiswa.php";
        showData();
    }

    private void showData() {
        // menampilkan prses request berupa progress dialog
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();

        // membuat request
        JsonObjectRequest myRequest = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
//                Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                txtDjson.setText(response.toString());
                // pilah data per item
                try {
                    // mengambil json array dengan nama 'students'
                    JSONArray jsonArray = response.getJSONArray("students");
                    // untuk a = 0l a < 5; a ditambah terus sampai batas maksimal
                    for (int a = 0; a < jsonArray.length(); a++) {
                        // mengambil data di masing2 item berdasarkan index a/posisi ditem
                        JSONObject jsonObject = jsonArray.getJSONObject(a);
                        // memasukkan masing2 data ke dalam hashmap berdasarkan key name json
                        HashMap<String, String> rowData = new HashMap<String, String>();
                        rowData.put("nama", jsonObject.getString("name"));
                        rowData.put("alamat", jsonObject.getString("address"));
                        // memasukkan hashmap di arraylist
                        studentsData.add(rowData);
                    }
                    // menampilkan datanya di komponen
                    txtDnama.setText(studentsData.get(1).get("nama"));
                    txtDalamat.setText(studentsData.get(1).get("alamat"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        // menjalankan request
        RequestQueue myRequestQueue = Volley.newRequestQueue(getActivity());
        myRequestQueue.add(myRequest);
    }
}
