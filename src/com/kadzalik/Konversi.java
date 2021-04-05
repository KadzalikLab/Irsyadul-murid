package com.kadzalik;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Konversi {

    //Fungsi pengubah tanggal ke julian day
    //parameter yang harus diisi: tahun, bulan, tanggal, jam, menit, detik
    public static double TanggalKeJulianDay(double tahun, double bulan, double tanggal, double jam, double menit, double detik){

        if (bulan<=2){bulan+=12; tahun-=1;}
        //bila bulan januari atau februari maka dianggap bulan ke 13/14 tahun sebelunmya

        //bila periode Julian
        //A dan B = 0
        int A;
        int B=0;

        //bila masuk periode Gregorian
        if ((tahun+bulan/100+tanggal/10000)>=1582.1015){
            A= (int) (tahun/100);
            B=2+(A/4)-A;

        }

        double julian_day=1720994.5+(int)(365.25*tahun)+(int)(30.60001*(bulan+1))+tanggal+B+(jam+(menit/60)+(detik/3600))/24;

        return julian_day;

    }

    //Fungsi pengubah  julian day  ke tanggal dan pukul
    //parameter yang harus diisi: julian day
    public static  int [] JulianDayKeTanggal(double julian_day){

        double jd1=julian_day+0.5;
        int Z= (int)jd1;
        double F= jd1-Z;
        double A;
        int AA;
        if (Z<2299161) A=Z;
        else {
            AA=(int)((Z-1867216.25)/36524.25);
            A=Z+1+AA-(int)(AA/4);
        }
        double B= A+1524;
        int C= (int)((B-122.1)/365.25);
        int D =(int)(365.25*C);
        int E = (int) ((B-D)/30.60001);

        double tanggal= (B-D-(int)(30.6001*E)+F);
        int bulan;
        if (E==14||E==15) bulan=E-13;
        else bulan=E-1;
        int tahun;
        if (bulan<=2) tahun=C-4715;
        else tahun=C-4716;

        //pecahan hari / pukul
        double pecahan_hari = tanggal-(int)tanggal;
        int jam=(int)(24*pecahan_hari);
        int menit=(int)(60*(pecahan_hari*24-jam));
        double detik=86400*pecahan_hari-3600*jam-60*menit;

        return  new int[]{0,(int)tanggal,bulan,tahun,jam,menit, (int) detik};
        //Daftar kembalian/return dalam bentuk Array yang berupa:
        //1:Tanggal
        //2:Bulan
        //3:Tahun
        //4:Jam
        //5:Menit
        //6:Detik

    }

    //Fungsi pengubah Masehi ke Hijri
    //parameter yang harus diisi: tahun, bulan, tanggal
    public static int [] MasehiKeHijri(double tahun, double bulan, double tanggal){

        //tanggal diatas dijadikan julian day dulu melalui fungsi Konversi
        double julian_day= Konversi.TanggalKeJulianDay(tahun,bulan,tanggal,0,0,0);

        double selisih_hari=julian_day-1948438.5; //selisih hari tanggal masehi dengan epoch/tahun nol kalender hijri
        int siklus= (int)((selisih_hari-1)/10631);//kelipatan 30 tahun atau berapakah siklus kabisat yang terjadi selama "selisih hari"
        if (selisih_hari<0)siklus=(int)Math.floor((selisih_hari-1)/10631);
        int tahun_siklus=siklus*30;//jumlah siklus dijadikan tahun
        double sisahari1= (selisih_hari-(siklus*10631));//  total selisih hari yang masih sisa setelah sebelumya dijadikan satuan siklus


        //rentang hari (satuan hari dari "sisahari1" dijadikan satuan tahun dan masih berapa sisa hari)
        int tambahan_tahun=0;
        int sisahari2=0;//ini nilai hari
        if (sisahari1>=1&&sisahari1<=354) { tambahan_tahun=1;sisahari2= (int) (sisahari1);}
        else if (sisahari1>=355&&sisahari1<=709) { tambahan_tahun=2;sisahari2= (int) (sisahari1-354);}
        else if (sisahari1>=710&&sisahari1<=1063) { tambahan_tahun=3;sisahari2= (int) (sisahari1-709);}
        else if (sisahari1>=1064&&sisahari1<=1417) { tambahan_tahun=4;sisahari2= (int) (sisahari1-1063);}
        else if (sisahari1>=1418&&sisahari1<=1772) { tambahan_tahun=5;sisahari2= (int) (sisahari1-1417);}
        else if (sisahari1>=1772&&sisahari1<=2126) { tambahan_tahun=6;sisahari2= (int) (sisahari1-1772);}
        else if (sisahari1>=2127&&sisahari1<=2481) { tambahan_tahun=7;sisahari2= (int) (sisahari1-2126);}
        else if (sisahari1>=2482&&sisahari1<=2835) { tambahan_tahun=8;sisahari2= (int) (sisahari1-2481);}
        else if (sisahari1>=2836&&sisahari1<=3189) { tambahan_tahun=9;sisahari2= (int) (sisahari1-2835);}
        else if (sisahari1>=3190&&sisahari1<=3544) { tambahan_tahun=10;sisahari2= (int) (sisahari1-3189);}
        else if (sisahari1>=3545&&sisahari1<=3898) { tambahan_tahun=11;sisahari2= (int) (sisahari1-3544);}
        else if (sisahari1>=3899&&sisahari1<=4252) { tambahan_tahun=12;sisahari2= (int) (sisahari1-3898);}
        else if (sisahari1>=4253&&sisahari1<=4607) { tambahan_tahun=13;sisahari2= (int) (sisahari1-4252);}
        else if (sisahari1>=4608&&sisahari1<=4961) { tambahan_tahun=14;sisahari2= (int) (sisahari1-4607);}
        else if (sisahari1>=4962&&sisahari1<=5315) { tambahan_tahun=15;sisahari2= (int) (sisahari1-4691);}
        else if (sisahari1>=5316&&sisahari1<=5670) { tambahan_tahun=16;sisahari2= (int) (sisahari1-5315);}
        else if (sisahari1>=5671&&sisahari1<=6024) { tambahan_tahun=17;sisahari2= (int) (sisahari1-5670);}
        else if (sisahari1>=6025&&sisahari1<=6379) { tambahan_tahun=18;sisahari2= (int) (sisahari1-6024);}
        else if (sisahari1>=6380&&sisahari1<=6733) { tambahan_tahun=19;sisahari2= (int) (sisahari1-6379);}
        else if (sisahari1>=6734&&sisahari1<=7087) { tambahan_tahun=20;sisahari2= (int) (sisahari1-6733);}
        else if (sisahari1>=7088&&sisahari1<=7442) { tambahan_tahun=21;sisahari2= (int) (sisahari1-7087);}
        else if (sisahari1>=7443&&sisahari1<=7796) { tambahan_tahun=22;sisahari2= (int) (sisahari1-7445);}
        else if (sisahari1>=7797&&sisahari1<=8150) { tambahan_tahun=23;sisahari2= (int) (sisahari1-7796);}
        else if (sisahari1>=8150&&sisahari1<=8505) { tambahan_tahun=24;sisahari2= (int) (sisahari1-8150);}
        else if (sisahari1>=8506&&sisahari1<=8859) { tambahan_tahun=25;sisahari2= (int) (sisahari1-8505);}
        else if (sisahari1>=8860&&sisahari1<=9214) { tambahan_tahun=26;sisahari2= (int) (sisahari1-8859);}
        else if (sisahari1>=9215&&sisahari1<=9568) { tambahan_tahun=27;sisahari2= (int) (sisahari1-9214);}
        else if (sisahari1>=9569&&sisahari1<=9922) { tambahan_tahun=28;sisahari2= (int) (sisahari1-9568);}
        else if (sisahari1>=9923&&sisahari1<=10277) { tambahan_tahun=29;sisahari2= (int) (sisahari1-9922);}
        else if (sisahari1>=10277&&sisahari1<=10631) { tambahan_tahun=30;sisahari2= (int) (sisahari1-10277);}

        int bulan_utuh_tamm;// proses menjadikan satuan hari ke satuan bulan
        if (sisahari2==355)bulan_utuh_tamm=11;
        else bulan_utuh_tamm=(int)((sisahari2-1)/29.5);

        double jumlah_hari_bulan_tamm;// jumlah hari yang terdapat pada bulan sebelumnya
        if ((bulan_utuh_tamm%2)==0) jumlah_hari_bulan_tamm=29.5*bulan_utuh_tamm;
        else jumlah_hari_bulan_tamm=29.5*(bulan_utuh_tamm-1)+30;

        int tanggal_hijri= (int) (sisahari2-jumlah_hari_bulan_tamm);//tnggal diambil dari jumlah total hari yang tersisa diambil umur bulan sebelumnya
        int bulan_hijri= bulan_utuh_tamm+1;// ditambah satu karna masih berupa bulan yang telah berlalu, bukan yang sedang dijalani
        int tahun_hijri= tahun_siklus+tambahan_tahun;



        return  new int []{0,  tanggal_hijri,  bulan_hijri,tahun_hijri};
    }

    //Fungsi pengubah Masehi ke Hijri
    //parameter yang harus diisi: tahun, bulan, tanggal
    public static int [] HijriKeMasehi(double tahun, double bulan, double tanggal){

        int siklus=(int)((tahun-1)/30);//berapa siklus kabisat yang terjadi selama "tahun" diatas
        int sisa_siklus= (int) (tahun-(siklus*30));//sisa siklus yang berupa tahun
        double banyaknya_hari=30*(bulan-1)-(int)((bulan-1)/2)+tanggal;// umur hari dihitung sejak 1 Muharram sampai bulan yang dicari. 1 Muharram juga ikut dihitung

        int jumlah_hari=0;//total hari yang terjadi dari epoch kalender hijri sampai ke tanggal yang dicari
        if (sisa_siklus<=2) jumlah_hari=354*(sisa_siklus-1);
        else if (sisa_siklus>2&&sisa_siklus<=5) jumlah_hari=354*(sisa_siklus-1)+1;
        else if (sisa_siklus>5&&sisa_siklus<=7) jumlah_hari=354*(sisa_siklus-1)+2;
        else if (sisa_siklus>7&&sisa_siklus<=10) jumlah_hari=354*(sisa_siklus-1)+3;
        else if (sisa_siklus>10&&sisa_siklus<=13) jumlah_hari=354*(sisa_siklus-1)+4;
        else if (sisa_siklus>13&&sisa_siklus<=16) jumlah_hari=354*(sisa_siklus-1)+5;
        else if (sisa_siklus>16&&sisa_siklus<=18) jumlah_hari=354*(sisa_siklus-1)+6;
        else if (sisa_siklus>18&&sisa_siklus<=21) jumlah_hari=354*(sisa_siklus-1)+7;
        else if (sisa_siklus>21&&sisa_siklus<=24) jumlah_hari=354*(sisa_siklus-1)+8;
        else if (sisa_siklus>24&&sisa_siklus<=26) jumlah_hari=354*(sisa_siklus-1)+9;
        else if (sisa_siklus>26&&sisa_siklus<=29) jumlah_hari=354*(sisa_siklus-1)+10;
        else if (sisa_siklus==30) jumlah_hari=354*(sisa_siklus-1)+11;

        double total_hari=siklus*10631+jumlah_hari+banyaknya_hari;
        double julian_day=1948438.5+total_hari;
        //nomor hari / hari ke
        double Z=julian_day+0.5;

        int AA= (int) ((Z-1867216.25)/36524.25);
        int A;
        if (Z<2299161) A= (int) Z;
        else A= (int) (Z+1+AA-(AA/4));

        int B=A+1524;
        int C=(int)((B-122.1)/365.25);
        int D=(int)(365.25*C);
        int E=(int)((B-D)/30.6001);
        int tanggal_masehi=B-D-(int)(30.6001*E);
        int bulan_masehi;
        if (E<14) bulan_masehi=E-1;
        else bulan_masehi=E-13;
        int tahun_masehi;
        if (bulan_masehi>2)tahun_masehi=C-4716;
        else tahun_masehi=C-4715;

        return  new int []{0,  tanggal_masehi,  bulan_masehi,tahun_masehi};

    }
    //kadzalik2020
    //Menentukan GST dari UT
    public  static int []UtKeGst(double julianday, int zonawaktu){
        /* Saya nggak tau alurnya gimana, tapi entah kenapa kodenya berhasil
        * jadi. kalau error silahkan dicari sendiri ya ...hehehe*/
        double pukuljd_0_UT=(julianday%1-0.5)*24;
        double UT=pukuljd_0_UT-zonawaktu;
        double julianday_0_UT=((int)julianday)+0.5;
        double T=(julianday_0_UT-2451545)/36525;
        double GST_0_UT=6.6973745583+2400.0513369072*T+0.0000258622*T*T;

        //Fungsi MODULO
        while (GST_0_UT>24){ GST_0_UT-=24; }//Bila nilai Positif lebih dari 24, akan dikurang 24 terus menerus sampai nilai dibawah 24
        while (GST_0_UT<0){ GST_0_UT+=24; }//Bila nilai negatif, akan ditambah 24 terus menerus sampai positif

        double GST_UT=GST_0_UT+1.0027379035*UT;//nilai sidereal time pada pukul UT
        if (GST_UT>=24) GST_UT-=24;

        int[] GST_sexagesimal= Konversi.DesimalKeDerajat(GST_UT);
        return new int []{ 0,GST_sexagesimal[1],GST_sexagesimal[2],GST_sexagesimal[3]};


    }

    public  static int []GstKeUt(double julianday,double jam_gst, double menit_gst , double detik_gst){
        double julianday_0_UT=((int)julianday)+0.5;
        double pukul_desimal=Konversi.DerajatKeDesimal(jam_gst,menit_gst,detik_gst);
        double T=(julianday_0_UT-2451545)/36525;
        double GST_0_UT=6.6973745583+2400.0513369072*T+0.0000258622*T*T;

        double UT_GST=pukul_desimal-GST_0_UT;
        //Fungsi MODULO
        while (UT_GST>24){ UT_GST-=24; }//Bila nilai Positif lebih dari 24, akan dikurang 24 terus menerus sampai nilai dibawah 24
        while (UT_GST<0){ UT_GST+=24; }//Bila nilai negatif, akan ditambah 24 terus menerus sampai positif
        UT_GST=UT_GST/1.00273790935;

        int[] UT_sexagesimal= Konversi.DesimalKeDerajat(UT_GST);
        return new int[]{ 0,UT_sexagesimal[1],UT_sexagesimal[2],UT_sexagesimal[3]};


    }

    public  static double GstKeLst(double bujur_tempat,double GST_desimal){

        //GST ke Local Sidereal Time
        double LST;
        if (bujur_tempat<0) LST=GST_desimal-(bujur_tempat/15);//ini bila Bujur Barat
        else  LST=GST_desimal+(bujur_tempat/15);//ini bila Bujur Timur

    return LST;



    }

    public  static double DerajatKeDesimal(double jah, double qoh, double ni){
        if (jah<0||qoh<0||ni<0) return jah-(Math.abs(qoh)/60)-(Math.abs(ni)/3600);//Bila salah satu nilainya negatif
        else return jah+(qoh/60)+(ni/3600);
    }

    public  static int []DesimalKeDerajat(double desimal){

        //cek nilai negatif atau bukan
        boolean negatif=false;
        if (desimal<0)negatif=true;

        //ini menghitungnya mengabaikan nilai negatif
        desimal=Math.abs(desimal);
        int jah=(int)desimal;
        double qoh=Math.abs((desimal%1)*60);
        double ni=Math.round((qoh%1)*60);
        //ini pembulatan
        if ((int)ni>59){ni-=60; qoh+=1;}
        if ((int)qoh>59){qoh-=60; jah+=1;}


        //bila negatif
        if (negatif) {
            if (jah==0)
            { if ((int)qoh==0)return new int[]{0, -jah,-(int)qoh,(int)-ni};
            else return new int[]{0, -jah,-(int)qoh,(int)ni};}

            else return new int[]{0, -jah,(int)qoh,(int)ni}; }

        //bila positif
        else return new int[]{0,jah,(int)qoh,(int)ni};
    }

    public  static double []DesimalKeDerajatMili(double decimal_degrees){

        decimal_degrees=Math.abs(decimal_degrees);
        int derajat=(int)(decimal_degrees);
        // set decimal_degrees value here
        double menit = (int)((decimal_degrees - derajat) * 60);
        double detik =  (decimal_degrees - derajat - menit/60) * 3600.00;
        double milidetik = (detik - Math.floor(detik)) * 10.0;

        // get rid of fractional part
        //menit = Math.floor(menit);
        //detik = Math.floor(detik);
        //milidetik = Math.floor(milidetik);

//        desimal=Math.abs(desimal);
//        int jah=(int)desimal;
//        int qoh= (int) ((desimal-jah)*60);
//        double ni=(desimal-qoh /60.0)*3600;


      return new double[]{0,derajat,menit,detik,milidetik};
    }
    public static String dms(double desimal){

        return Konversi.DesimalKeDerajat(desimal)[1]+"\u00B0"+Konversi.DesimalKeDerajat(desimal)[2]+"\u2032"+Konversi.DesimalKeDerajat(desimal)[3]+"\u2033";
    }
    public static String hms(double desimal){

        return Konversi.DesimalKeDerajat(desimal)[1]+":"+Konversi.DesimalKeDerajat(desimal)[2]+":"+Konversi.DesimalKeDerajat(desimal)[3];
    }

    //test
    public static String dmsMili(double desimal){

        if (desimal<0)      return "-"+(int)Konversi.DesimalKeDerajatMili(desimal)[1]+"\u00B0"+(int)Konversi.DesimalKeDerajatMili(desimal)[2]+"\u2032"+String.format("%.2f", Konversi.DesimalKeDerajatMili(desimal)[3])+"\u2033";
        else return (int)Konversi.DesimalKeDerajatMili(desimal)[1]+"\u00B0"+(int)Konversi.DesimalKeDerajatMili(desimal)[2]+"\u2032"+String.format("%.2f", Konversi.DesimalKeDerajatMili(desimal)[3])+"\u2033";
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public  static String namaHariPasaran(double jd){
        int nomor_hari= (int) ((jd+1.5 )%7+1);
        int nomor_pasaran= (int) ((jd+1.5 )%5);

    return  NamaHariPasaran.namahari(nomor_hari)+" "+NamaHariPasaran.namapasaran(nomor_pasaran);}

}
