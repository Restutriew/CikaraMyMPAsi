package com.cikarastudio.cikaramympasi.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class ModelData implements Parcelable {
    String id;
    String nama;
    String keterangan;
    String icon;

    public ModelData(String id, String nama, String keterangan, String icon) {
        this.id = id;
        this.nama = nama;
        this.keterangan = keterangan;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static Creator<ModelData> getCREATOR() {
        return CREATOR;
    }

    protected ModelData(Parcel in) {
        id = in.readString();
        nama = in.readString();
        keterangan = in.readString();
        icon = in.readString();
    }

    public static final Creator<ModelData> CREATOR = new Creator<ModelData>() {
        @Override
        public ModelData createFromParcel(Parcel in) {
            return new ModelData(in);
        }

        @Override
        public ModelData[] newArray(int size) {
            return new ModelData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(nama);
        parcel.writeString(keterangan);
        parcel.writeString(icon);
    }
}
