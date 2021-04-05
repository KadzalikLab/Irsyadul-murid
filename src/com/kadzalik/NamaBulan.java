package com.kadzalik;

public class NamaBulan {
    public  static String hijri(int bulan){

        String namabulan="";

        switch (bulan) {
            case 1:
                namabulan = "Muharram";
                break;
            case 2:
                namabulan = "Safar";
                break;
            case 3:
                namabulan = "Rabiul Awal";
                break;
            case 4:
                namabulan = "Rabiu Tsani";
                break;
            case 5:
                namabulan = "Jumadil Awal";
                break;
            case 6:
                namabulan = "Jumadil Akhir";
                break;
            case 7:
                namabulan = "Rajab";
                break;
            case 8:
                namabulan = "Sya'ban";
                break;
            case 9:
                namabulan = "Ramadhan";
                break;
            case 10:
                namabulan = "Syawal";
                break;
            case 11:
                namabulan = "Dzulqo'dah";
                break;
            case 12:
            case 0:
                namabulan = "Dzulhijjah";
                break;
        }



        return namabulan;


    }

    public  static String masehi(int bulan){

        String namabulan="";

        switch (bulan) {
            case 1:
                namabulan = "Januari";
                break;
            case 2:
                namabulan = "Februari";
                break;
            case 3:
                namabulan = "Maret";
                break;
            case 4:
                namabulan = "April";
                break;
            case 5:
                namabulan = "Mei";
                break;
            case 6:
                namabulan = "Juni";
                break;
            case 7:
                namabulan = "Juli";
                break;
            case 8:
                namabulan = "Agustus";
                break;
            case 9:
                namabulan = "September";
                break;
            case 10:
                namabulan = "Oktober";
                break;
            case 11:
                namabulan = "November";
                break;
            case 12:
            case 0:
                namabulan = "Desember";
                break;
        }



        return namabulan;


    }


}
