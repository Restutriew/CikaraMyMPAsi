package com.cikarastudio.cikaramympasi.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cikarastudio.cikaramympasi.Model.ModelData;
import com.cikarastudio.cikaramympasi.R;
import com.squareup.picasso.Picasso;

public class DeskripsiActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deskripsi);

        TextView tv_name = findViewById(R.id.tv_nama);
        TextView tv_keterangan = findViewById(R.id.tv_keterangan);
        ImageView img_icon = findViewById(R.id.img_icon);


        ModelData modelData = getIntent().getParcelableExtra(EXTRA_DATA);
        String title = modelData.getNama();
        String deskripsi = modelData.getKeterangan();
        String photo = modelData.getIcon();
//        String icon = "https://kingdom.cikarastudio.com/img/kategori/" + photo;

        tv_name.setText(title);
        tv_keterangan.setText(deskripsi);
        Picasso.with(this)
                .load("https://kingdom.cikarastudio.com/img/kategori/"+photo)
                .into(img_icon);

    }
}