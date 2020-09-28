package com.cikarastudio.cikaramympasi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cikarastudio.cikaramympasi.Adapter.DataAdapter;
import com.cikarastudio.cikaramympasi.Dialog.LoadingDialog;
import com.cikarastudio.cikaramympasi.Model.ModelData;
import com.cikarastudio.cikaramympasi.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListResepActivity extends AppCompatActivity {

    private ArrayList<ModelData> dataList;
    RecyclerView recyclerView;
    DataAdapter dataAdapter;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_resep);

        loadingDialog = new LoadingDialog(ListResepActivity.this);
        loadingDialog.startLoading();
        dataList = new ArrayList<>();
        recyclerView = findViewById(R.id.rv_listData);
        recyclerView.setHasFixedSize(true);

        load_data();
    }

    private void load_data() {
        String URL_READBOOKING = "https://kingdom.cikarastudio.com/webservice/subkategori/aplikasi-android/125";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_READBOOKING,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id").trim();
                                    String nama = object.getString("nama_subkategori").trim();
                                    String keterangan = object.getString("sub_keterangan").trim();
                                    String icon = object.getString("icon_subkategori").trim();


                                    dataList.add(new ModelData(id, nama, keterangan, icon));
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    dataAdapter = new DataAdapter(getApplicationContext(), dataList);
                                    recyclerView.setAdapter(dataAdapter);
                                    dataAdapter.setOnItemClickCallback(new DataAdapter.OnItemClickCallback() {
                                        @Override
                                        public void onItemClicked(ModelData data) {
//                                            Toast.makeText(ListResepActivity.this, "Data Ada! " +data.getId(), Toast.LENGTH_SHORT).show();
                                            Intent detaildata = new Intent(ListResepActivity.this, DeskripsiActivity.class);
                                            detaildata.putExtra(DeskripsiActivity.EXTRA_DATA, data);
                                            startActivity(detaildata);
                                        }
                                    });
                                    loadingDialog.dissmissDialog();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            loadingDialog.dissmissDialog();
                            Toast.makeText(ListResepActivity.this, "Data Tidak Ada! Pesan Error: ", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingDialog.dissmissDialog();
                Toast.makeText(ListResepActivity.this, "Koneksi Gagal! Pesan Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
        };
        RequestQueue requestQueue = Volley.newRequestQueue(ListResepActivity.this);
        requestQueue.add(stringRequest);
    }
}