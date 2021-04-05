package com.kadzalik.irsyadulmurid;

import com.kadzalik.Konversi;

public class HabIrsyadMurid {
    public static void main(String[] args) {
        String format = "%-25s%5s%n"; //buat formatter biar layout print rapi
        // data-data yang dibutuhkan
        double derajat_bujur=112;
        double menit_bujur=44;
        double detik_bujur=57;
        double bujur= Konversi.DerajatKeDesimal(derajat_bujur,menit_bujur,detik_bujur);

        double derajat_lintang=-7;
        double menit_lintang=1;
        double detik_lintang=58;
        double lintang= Konversi.DerajatKeDesimal(derajat_lintang,menit_lintang,detik_lintang);

        double tinggi_tempat=10;
        int zona_waktu=7;

        double tanggal_hijri=29;
        double bulan_hijri=3;
        double tahun_hijri=1441;
        double bujur_waktu_daerah=105;

        double tgl01=29;
        double bln01=0;
        double thn01=0;
        if (tanggal_hijri<15){
            if(bulan_hijri==1)bln01=12;
            else bln01-=1;
        }else bln01=bulan_hijri;

        if (bln01==12&&tanggal_hijri<15)thn01=tahun_hijri-1;
        else thn01=tahun_hijri;




        double bln02=0;
        double thn02=0;
        if (tanggal_hijri<15){
            if(bulan_hijri==1)bln02=12;
            else bln02-=1;
        }else bln02=bulan_hijri;

        if (bln02==12&&tanggal_hijri<15)thn01=tahun_hijri-1;
        else thn01=tahun_hijri;






    }
}
