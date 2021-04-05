package com.kadzalik;

public class NamaHariPasaran {
    public  static String namahari(int hari){

        String namahari="";

        switch (hari) {
            case 1:
                namahari = "Ahad";
                break;
            case 2:
                namahari = "Senin";
                break;
            case 3:
                namahari = "Selasa";
                break;
            case 4:
                namahari = "Rabu";
                break;
            case 5:
                namahari = "Kamis";
                break;
            case 6:
                namahari = "Jum´at";
                break;
            case 7:
            case 0:
                namahari = "Sabtu";
                break;
        }



        return namahari;


    }

    public  static String namapasaran(int nomor_pasaran){

        String namapasaran="";

        switch (nomor_pasaran) {
            case 1:
                namapasaran = "Legi";
                break;
            case 2:
                namapasaran = "Pahing";
                break;
            case 3:
                namapasaran = "Pon";
                break;
            case 4:
                namapasaran = "Wage";
                break;
            case 5:
            case 0:
                namapasaran = "Kliwon";
                break;
        }



        return namapasaran;


    }

}
