package com.cikarastudio.cikaramympasi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cikarastudio.cikaramympasi.Model.ModelData;
import com.cikarastudio.cikaramympasi.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private Context mContext;
    private ArrayList<ModelData> mModelDataList;

    private OnItemClickCallback onItemClickCallback;



    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(ModelData data);
    }

    public DataAdapter(Context context, ArrayList<ModelData> bumilList) {
        mContext = context;
        mModelDataList = bumilList;
    }

    public DataAdapter(Context mContext, ArrayList<ModelData> mModelDataList, OnItemClickCallback onItemClickCallback) {
        this.mContext = mContext;
        this.mModelDataList = mModelDataList;
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_data, parent, false);
        return new DataAdapter.DataViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final DataViewHolder holder, int position) {
        ModelData currentItem = mModelDataList.get(position);

        String id = currentItem.getId();
        String nama = currentItem.getNama();
        String keterangan = currentItem.getKeterangan();
        String icon = currentItem.getIcon();

        holder.nama.setText(nama);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(mModelDataList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mModelDataList.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView nama;
        DataViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.listnama);
        }
    }
}
