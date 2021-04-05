package com.kadzalik.irsyadulmurid;
import com.kadzalik.DataEphemeris;
import com.kadzalik.Konversi;
import com.kadzalik.NamaBulan;
import com.kadzalik.NamaHariPasaran;

public class HisabAwalBulan {
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

        double bulan_hijri=6; // masukkan bulan naqish
        double tahun_hijri=1441;
        double bujur_waktu_daerah=zona_waktu*15;




        //menentukan saat ijtima'
        bulan_hijri--; //mencari nilaibulan tamm
        double hy = tahun_hijri+(bulan_hijri*29.53)/354.3671;  //ayyamut tarikh hijr/ hari tanggal hijri
        double k;// Al mahfudz (Al mabda' / epoch 1410)
        if (((hy-1410)*12)%1>=0.5) k=(int)((hy-1410)*12)+1;
        else k=(int)((hy-1410)*12);
        double t=k/1200;                                                //juzul ashli
        double jd=2447740.652+29.53058868*k+0.0001178*Math.pow(t,2); // tarikh juliyani ghoirul muaddal
        double ks=frac((207.9587074+29.10535608*k+-0.0000333*Math.pow(t,2))/360)*360; //Khossoh Syamsi
        double kq=frac((111.1791307+385.81691806*k+0.0107306*Math.pow(t,2))/360)*360; //Khossoh Qomar
        double ha=frac((164.2162296+390.67050646*k+-0.0016528*Math.pow(t,2))/360)*360; //Hissoh Ardli

        System.out.printf(format,"Ayyamu Tarikh Hijri",hy);
        System.out.printf(format,"Mabda' 1410",k);
        System.out.printf(format,"Juz Al Ahsli",(float)t);
        System.out.printf(format,"Juliyani G Muaddal",jd);
        System.out.printf(format,"Khossoh Syamsi",Konversi.dmsMili(ks));
        System.out.printf(format,"Khossoh Qomar",Konversi.dmsMili(kq));
        System.out.printf(format,"Hissoh 'Ardli",Konversi.dmsMili(ha));


        double t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13; // 13 takdil

        t1 = (0.1734 - 0.000393 * t) * Math.sin(Math.toRadians(ks));
        t2 = 0.0021 * Math.sin(Math.toRadians(2*ks));
        t3 = -0.4068 * Math.sin(Math.toRadians(kq));
        t4 = 0.0161 * Math.sin(Math.toRadians(2*kq));
        t5 = -0.0004 * Math.sin(Math.toRadians(3*kq));
        t6 = 0.0104 * Math.sin(Math.toRadians(2*ha));
        t7 = -0.0051 * Math.sin(Math.toRadians(ks+kq));
        t8 = -0.0074 * Math.sin(Math.toRadians(ks-kq));
        t9 = 0.0004 * Math.sin(Math.toRadians(2*ha+ks));
        t10 = -0.0004 * Math.sin(Math.toRadians(2*ha-ks));
        t11 = -0.0006 * Math.sin(Math.toRadians(2*ha+kq));
        t12 = 0.0010 * Math.sin(Math.toRadians(2*ha-kq));
        t13 = 0.0005 *Math.sin(Math.toRadians(ks+2*kq));

        double mt=t1+t2+t3+t4+t5+t6+t7+t8+t9+t10+t11+t12+t13; // majmuah takdiilati
        System.out.printf(format,"ta'dil 01",Konversi.dmsMili(t1));
        System.out.printf(format,"ta'dil 02",Konversi.dmsMili(t2));
        System.out.printf(format,"ta'dil 03",Konversi.dmsMili(t3));
        System.out.printf(format,"ta'dil 04",Konversi.dmsMili(t4));
        System.out.printf(format,"ta'dil 05",Konversi.dmsMili(t5));
        System.out.printf(format,"ta'dil 06",Konversi.dmsMili(t6));
        System.out.printf(format,"ta'dil 07",Konversi.dmsMili(t7));
        System.out.printf(format,"ta'dil 08",Konversi.dmsMili(t8));
        System.out.printf(format,"ta'dil 09",Konversi.dmsMili(t9));
        System.out.printf(format,"ta'dil 10",Konversi.dmsMili(t10));
        System.out.printf(format,"ta'dil 11",Konversi.dmsMili(t11));
        System.out.printf(format,"ta'dil 12",Konversi.dmsMili(t12));
        System.out.printf(format,"ta'dil 13",Konversi.dmsMili(t13));
        System.out.printf(format,"mt",Konversi.dmsMili(mt));


        double jd_ijtima_ut=jd+0.5+mt;
        double jd_ijtima_wd=(jd+0.5+mt)+(zona_waktu/24.0);
        double waktu_ijtima_ut=frac(jd_ijtima_ut)*24;
        double waktu_ijtima_wd;                     //waktu ijtima sesuai daerah Wib/wit /wita
        if ((waktu_ijtima_ut+zona_waktu)>24)waktu_ijtima_wd=waktu_ijtima_ut+zona_waktu-24;
        else waktu_ijtima_wd=waktu_ijtima_ut+zona_waktu;
        //System.out.println(Konversi.hms(waktu_ijtima_wd));
        int z =(int)jd_ijtima_ut;
        int aa=(int)((z-1867216.25)/36524.25);
        int a;
        if (z>=2299161)a=z+1+aa-(int)(aa/4);
        else a=z;

        int b = a+1524;
        int c =(int)((b-122.1)/365.25);
        int d = (int)(365.25*c);
        int e = (int)((b-d)/30.6001);
        int tanggal_ijtima_masehi=(int)(b-d-(int)(30.6001*e));
        int tanggal_ijtima_masehi_wd=ijtima_tanggal_masehi_wd(jd_ijtima_wd)[1];
        int bulan_ijtima_masehi;
        if (e>=13.5) bulan_ijtima_masehi=e-13;
        else bulan_ijtima_masehi=e-1;
        int bulan_ijtima_masehi_wd=ijtima_tanggal_masehi_wd(jd_ijtima_wd)[2];

        int tahun_ijtima_masehi;
        if (bulan_ijtima_masehi>=2.5)tahun_ijtima_masehi=c-4716;
        else tahun_ijtima_masehi=c-4715;
        int tahun_ijtima_masehi_wd=ijtima_tanggal_masehi_wd(jd_ijtima_wd)[3];
        int pa=z+2;
        int nomor_hari=pa-(int)(pa/7)*7;
        int nomor_pasaran=pa-(int)(pa/5)*5;

        String ijtima_tanggal_masehi_ut=tanggalMasehi(nomor_hari,nomor_pasaran,tanggal_ijtima_masehi,bulan_ijtima_masehi,tahun_ijtima_masehi);
        String ijtima_tanggal_masehi_wd= ijtima_tanggal_masehi_wd_str(jd_ijtima_wd);

        System.out.printf(format,"Juliyani M liijtma'",String.format("%.3f", jd_ijtima_ut));
        System.out.printf(format,"Waktu Ijtima' UT",Konversi.hms(waktu_ijtima_ut));
        System.out.printf(format,"Waktu Ijtima' WD",Konversi.dmsMili(waktu_ijtima_wd));
        System.out.printf(format,"(int)ijtima' UT",z);
        System.out.printf(format,"Mutammim Mahfudz",aa);
        System.out.printf(format,"Mahfudz 01",a);
        System.out.printf(format,"Mahfudz 02",b);
        System.out.printf(format,"Mahfudz 03",c);
        System.out.printf(format,"Mahfudz 04",d);
        System.out.printf(format,"Mahfudz 05",e);
        System.out.printf(format,"Tanggal M Ijtima UT",tanggal_ijtima_masehi);
        System.out.printf(format,"Bulan M Ijtima",bulan_ijtima_masehi);
        System.out.printf(format,"Tahun M Ijtima",tahun_ijtima_masehi);
        System.out.printf(format,"Tanggal Ijtima' UT",ijtima_tanggal_masehi_ut);
        System.out.printf(format,"Tanggal Ijtima' WD",ijtima_tanggal_masehi_wd);





        double ghurub_syamsi_wd=waktuMaghrib(tanggal_ijtima_masehi_wd,bulan_ijtima_masehi_wd,tahun_ijtima_masehi_wd,lintang,bujur,tinggi_tempat,zona_waktu);
        double ghurub_syamsi_ut=ghurub_syamsi_wd-zona_waktu;

        //data tanggal baru guna menghirung data matahari-bulan
        double m,y;
        if (bulan_ijtima_masehi<=2) {
            m = bulan_ijtima_masehi + 12;
            y=tahun_ijtima_masehi-1;
        }
        else {
            m = bulan_ijtima_masehi;
            y=tahun_ijtima_masehi;
        }


        int A;
        int B=0;

        //bila masuk periode Gregorian
        if ((tahun_ijtima_masehi+bulan_ijtima_masehi/100.0+tanggal_ijtima_masehi/10000.0)>=1582.1015){
            A= (int) (tahun_ijtima_masehi/100);
            B=2+(A/4)-A;
        }

        double julian_day=(int)(365.25*(y+4716))+(int)(30.6001*(m+1))+tanggal_ijtima_masehi_wd+(ghurub_syamsi_ut/24)+B-1524.5; // aslul miladi
        //double rjulian_day=Math.round(julian_day * 1000.0) / 1000.0;
        double T=(julian_day-2451545)/36525; //juz al asli milady


        // System.out.printf(format,"",Konversi.dmsMili());
        System.out.printf(format,"Ghurub Syamsi ",Konversi.dmsMili(ghurub_syamsi_wd));
        System.out.printf(format,"Al Ashlu Miladi",julian_day);
         System.out.printf(format,"Juz Ashlil M",T);
        System.out.printf(format,"T ",T);


        // Data Matahari
        double ws=frac((280.46645+36000.76983*T)/360)*360; //wasat syamsi
        double khs=frac((357.5291+35999.05030*T)/360)*360;//khossoh syamsi
        double N=frac((125.04-1934.136*T)/360)*360;//uqdah syamsi
        System.out.println(System.lineSeparator());
        System.out.println("Data Matahari: ");
        System.out.printf(format,"Wasat Syamsi ",Konversi.dmsMili(ws));
        System.out.printf(format,"Khossoh Syamsi ",Konversi.dmsMili(khs));
        System.out.printf(format,"Uqdah Syamsi ",Konversi.dmsMili(N));

        //Tashihatu li uqdah syamsi wal qomar

        double k1=(17.264/3600)*Math.sin(Math.toRadians(N))+(0.206/3600)*Math.sin(Math.toRadians(2*N));
        double k2=(-1.264/3600)*Math.sin(Math.toRadians(2*ws));
        double r1=(9.23/3600)*Math.cos(Math.toRadians(N))-(0.090/3600)*Math.cos(Math.toRadians(2*N));
        double r2=(0.548/3600)*Math.cos(Math.toRadians(2*ws));
        double q1=23.43929111+r1+r2-(46.8150/3600)*T;//mailkully
        double ts=(6898.06/3600)*Math.sin(Math.toRadians(ks))+(72.095/3600)*Math.sin(Math.toRadians(2*ks))+(0.966/3600)*Math.sin(Math.toRadians(3*ks));//ta'dil syamsi
        double s1=ws+ts+k1+k2-(20.47/3600);
        double deklinasi_matahari=Math.toDegrees(Math.asin(Math.sin(Math.toRadians(s1))*Math.sin(Math.toRadians(q1))));
        double pt=Math.toDegrees(Math.atan(Math.tan(Math.toRadians(s1))*Math.cos(Math.toRadians(q1))));
        if (s1>=0&&s1<=90)pt+=0;
        else if (s1>90&&s1<=270)pt+=180;
        else if (s1>270&&s1<=360)pt+=360;

        System.out.printf(format,"Tashih 01",Konversi.dmsMili(k1));
        System.out.printf(format,"Tashih 02",Konversi.dmsMili(k2));
        System.out.printf(format,"Tashih 03",Konversi.dmsMili(r1));
        System.out.printf(format,"Tashih 04",Konversi.dmsMili(r2));
        System.out.printf(format,"Mail Kully",Konversi.dmsMili(q1));
        System.out.printf(format,"Ta'dilu Syamsi",Konversi.dmsMili(ts));
        System.out.printf(format,"Thul Syamsi",Konversi.dmsMili(s1));
        System.out.printf(format,"Mail Syamsi",Konversi.dmsMili(deklinasi_matahari));
        System.out.printf(format,"Matholi' M Syamsi",Konversi.dmsMili(pt));


        double ea=-1.915*Math.sin(Math.toRadians(ks));
        double eb=-0.02*Math.sin(Math.toRadians(2*ks));
        double ec=2.466*Math.sin(Math.toRadians(2*s1));
        double ed=-0.053*Math.sin(Math.toRadians(4*s1));
        double dt=(ea+eb+ec+ed)/15; //daqoiq tafawut

        double sd=0.267/(1-0.017*Math.cos(Math.toRadians(ks)));
        double dip=(1.76/60)*Math.sqrt(tinggi_tempat);
        double h=-(sd+(34.5/60)+dip);//irtifa syamsi waktal ghurub
        double nishfu_qous_nSyamsi=Math.toDegrees(Math.acos(-Math.tan(Math.toRadians(lintang))*Math.tan(Math.toRadians(deklinasi_matahari))+Math.sin(Math.toRadians(h))/Math.cos(Math.toRadians(lintang))/Math.cos(Math.toRadians(deklinasi_matahari))));
        double gr_lmt=nishfu_qous_nSyamsi/15+(12-dt);
        double gr_wd=gr_lmt+(bujur_waktu_daerah-bujur)/15;

        System.out.printf(format,"Mahfudz Dt 01",Konversi.dmsMili(ea));
        System.out.printf(format,"Mahfudz Dt 02",Konversi.dmsMili(eb));
        System.out.printf(format,"Mahfudz Dt 03",Konversi.dmsMili(ec));
        System.out.printf(format,"Mahfudz Dt 04",Konversi.dmsMili(ed));
        System.out.printf(format,"Daqoiq Tafawut",Konversi.dmsMili(dt));
        System.out.printf(format,"Nishfu Qutri Syamsi",Konversi.dmsMili(sd));
        System.out.printf(format,"Inkhifadhul ufuq",Konversi.dmsMili(dip));
        System.out.printf(format,"Nisfu Qous N Syamsi",Konversi.dmsMili(nishfu_qous_nSyamsi));
        System.out.printf(format,"Ghurub Syamsi Wasatiyyah",Konversi.dmsMili(gr_lmt));
        System.out.printf(format,"Ghurub Syamsi Dairiyyah",Konversi.dmsMili(gr_wd));
        System.out.println(System.lineSeparator());




        //Data bulan
        System.out.println("Data Bulan:");
        double wq=frac((218.31617+481267.88088*T)/360)*360; //wasat qomar
        System.out.printf(format,"Wasatul Qomar ",Konversi.dmsMili(wq));
        double khq=frac((134.96292+477198.86753*T)/360)*360;
        System.out.printf(format,"Khossotul Qomar",Konversi.dmsMili(khq));
        double f=frac((93.27283+483202.01873*T)/360)*360;
        System.out.printf(format,"Hissoh Ardli",Konversi.dmsMili(f));
        double fw=frac((297.85027+445267.11135*T)/360)*360;
        System.out.printf(format,"Fadlul Wasat",Konversi.dmsMili(fw));

        // 14 takdil qomar
        double tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8,tq9,tq10,tq11,tq12,tq13,tq14;
        tq1=(22640/3600.0)*Math.sin(Math.toRadians(khq));
        tq2=(-4586/3600.0)*Math.sin(Math.toRadians(khq-2*fw));
        tq3=(2370/3600.0)*Math.sin(Math.toRadians(2*fw));//
        tq4=(769/3600.0)*Math.sin(Math.toRadians(2*khq));//
        tq5=(-668/3600.0)*Math.sin(Math.toRadians(khs));
        tq6=(-412/3600.0)*Math.sin(Math.toRadians(2*f));//
        tq7=(-212/3600.0)*Math.sin(Math.toRadians(2*khq-2*fw));//
        tq8=(-206/3600.0)*Math.sin(Math.toRadians(khq+khs-2*fw));
        tq9=(192/3600.0)*Math.sin(Math.toRadians(khq+2*fw));//
        tq10=(-165/3600.0)*Math.sin(Math.toRadians(khs-2*fw));
        tq11=(148/3600.0)*Math.sin(Math.toRadians(khq-khs));
        tq12=(-125/3600.0)*Math.sin(Math.toRadians(fw));//
        tq13=(-110/3600.0)*Math.sin(Math.toRadians(khq+khs));
        tq14=(-55/3600.0)*Math.sin(Math.toRadians(2*f-2*fw));//
        double mtq=tq1+tq2+tq3+tq4+tq5+tq6+tq7+tq8+tq9+tq10+tq11+tq12+tq13+tq14;

        System.out.printf(format,"Ta'dil Qomar 01",Konversi.dmsMili(tq1));
        System.out.printf(format,"Ta'dil Qomar 02",Konversi.dmsMili(tq2));
        System.out.printf(format,"Ta'dil Qomar 03",Konversi.dmsMili(tq3));
        System.out.printf(format,"Ta'dil Qomar 04",Konversi.dmsMili(tq4));
        System.out.printf(format,"Ta'dil Qomar 05",Konversi.dmsMili(tq5));
        System.out.printf(format,"Ta'dil Qomar 06",Konversi.dmsMili(tq6));
        System.out.printf(format,"Ta'dil Qomar 07",Konversi.dmsMili(tq7));
        System.out.printf(format,"Ta'dil Qomar 08",Konversi.dmsMili(tq8));
        System.out.printf(format,"Ta'dil Qomar 09",Konversi.dmsMili(tq9));
        System.out.printf(format,"Ta'dil Qomar 10",Konversi.dmsMili(tq10));
        System.out.printf(format,"Ta'dil Qomar 11",Konversi.dmsMili(tq11));
        System.out.printf(format,"Ta'dil Qomar 12",Konversi.dmsMili(tq12));
        System.out.printf(format,"Ta'dil Qomar 13",Konversi.dmsMili(tq13));
        System.out.printf(format,"Ta'dil Qomar 14",Konversi.dmsMili(tq14));
        System.out.printf(format,"Majmuah Ta'dil Qomar",Konversi.dmsMili(mtq));

        double mo=(wq+mtq+k1+k2-(20.47/3600));
        double a1=khq+tq2+tq3+tq5;
        double l1=(18461/3600.0)*Math.sin(Math.toRadians(f))+(1010/3600.0)*Math.sin(Math.toRadians(khq+f))+(1000/3600.0)*Math.sin(Math.toRadians(khq-f))-(624/3600.0)*Math.sin(Math.toRadians(f-2*fw))-(199/3600.0)*Math.sin(Math.toRadians(khq-f-2*fw))-(167/3600.0)*Math.sin(Math.toRadians(khq+f-2*fw));
        double x = Math.toDegrees(Math.atan(Math.sin(Math.toRadians(mo))*Math.tan(Math.toRadians(q1))));
        double hb=(l1+x);

        System.out.printf(format,"Thulul Qomar",Konversi.dmsMili(mo));
        System.out.printf(format,"Khossoh Mu'addalah",Konversi.dmsMili(a1));
        System.out.printf(format,"Ardlu Qomar",Konversi.dmsMili(l1));
        System.out.printf(format,"Mail Tsani Qomar",Konversi.dmsMili(x));
        System.out.printf(format,"Hissoh Bu'du",Konversi.dmsMili(hb));

        double dc=Math.toDegrees(Math.asin(Math.sin(Math.toRadians(mo))*Math.sin(Math.toRadians(q1))*Math.sin(Math.toRadians(hb))/Math.sin(Math.toRadians(x))));
        double ptc=Math.toDegrees(Math.acos(Math.cos(Math.toRadians(mo))*Math.cos(Math.toRadians(l1))/Math.cos(Math.toRadians(dc))));
        if (mo>180&&mo<=360)ptc=(360-ptc);
        double tc = (pt-ptc)+nishfu_qous_nSyamsi;
        double hc=Math.toDegrees(Math.asin(Math.sin(Math.toRadians(lintang))*Math.sin(Math.toRadians(dc))+Math.cos(Math.toRadians(lintang))*Math.cos(Math.toRadians(dc))*Math.cos(Math.toRadians(tc))));


        System.out.printf(format,"Deklinasi Bulan",Konversi.dmsMili(dc));
        System.out.printf(format,"Matholi' Qomar",Konversi.dmsMili(ptc));
        System.out.printf(format,"Fadlud dair",Konversi.dmsMili(tc));
        System.out.printf(format,"Irtifa' hilal hakiki",Konversi.dmsMili(hc));

        double jarak_bm_bln=(384401*(1-0.0549 * 0.0549))/(1+0.0549*(Math.cos(Math.toRadians(a1+tq1))));
        double hp1=0.9507/(jarak_bm_bln/384401);
        double sdc=(0.5181/(jarak_bm_bln/384401))/2;
        double hp2=hp1*(Math.cos(Math.toRadians(hc)));
        double ref=0.0167/Math.tan(Math.toRadians(hc+7.31/(hc+4.4)));
        double hcm;
        if ((hc-hp2)>0)hcm=hc-hp2+sdc+ref+dip;
        else if ((hc-hp2)<=0)hcm=hc-hp2;
        else hcm=0;

        System.out.printf(format,"Jarak Bumi-Bulan",(float)jarak_bm_bln+" Km");
        System.out.printf(format,"Ikhtilaf Mandzor 1",Konversi.dmsMili(hp1));
        System.out.printf(format,"Nishfu qutri Qomar",Konversi.dmsMili(sdc));
        System.out.printf(format,"Ikhtilaf Mandzor 2",Konversi.dmsMili(hp2));
        System.out.printf(format,"Refraksi",Konversi.dmsMili(ref));
        System.out.printf(format,"Irtifa' hilal Mar'i",Konversi.dmsMili(hcm));

        double azm_b=Math.toDegrees(Math.atan(-Math.sin(Math.toRadians(lintang))/Math.tan(Math.toRadians(nishfu_qous_nSyamsi))+Math.cos(Math.toRadians(lintang))*Math.tan(Math.toRadians(deklinasi_matahari))/Math.sin(Math.toRadians(nishfu_qous_nSyamsi))));
        double azm_u;
        if (t<180)azm_u=azm_b+270;
        else azm_u=azm_b+90;

        double azc_b=Math.toDegrees(Math.atan(-Math.sin(Math.toRadians(lintang))/Math.tan(Math.toRadians(tc))+Math.cos(Math.toRadians(lintang))*Math.tan(Math.toRadians(dc))/Math.sin(Math.toRadians(tc))));
        double azc_u;
        if (tc<180)azc_u=azc_b+270;
        else azc_u=azc_b+90;

        System.out.printf(format,"Azimut Matahri B",Konversi.dmsMili(azm_b));
        System.out.printf(format,"Azimut Matahri U",Konversi.dmsMili(azm_u));
        System.out.printf(format,"Azimut Hilal B",Konversi.dmsMili(azc_b));
        System.out.printf(format,"Azimut Hilal U",Konversi.dmsMili(azc_u));

        double sudut_hilal_matahari=azc_u-azm_u;
        double muktsu_hilal=(ptc-pt)/15;
        double mahfudz_samkulHilal=Math.toDegrees(Math.acos(Math.cos(Math.toRadians(Math.abs(hcm-h)))*Math.cos(Math.toRadians(Math.abs(azc_u-azm_u)))));
        double samkul_hilal=(1-(Math.cos(Math.toRadians(mahfudz_samkulHilal))))*sdc*60;

        System.out.printf(format,"Hilal dari Matahari",Konversi.dmsMili(sudut_hilal_matahari));
        System.out.printf(format,"Muktsu Hilal taqribi",Konversi.dmsMili(muktsu_hilal));
        System.out.printf(format,"Mahfudz Samkul Hilal",Konversi.dmsMili(mahfudz_samkulHilal));
        System.out.printf(format,"Samkul hilal",String.format("%.3f", samkul_hilal)+" M ");

        double elongasi=Math.toDegrees(Math.acos(Math.cos(Math.toRadians(mo-s1))*Math.cos(Math.toRadians(l1))));
        double mahfudz_nh=Math.toDegrees(Math.acos(-Math.cos(Math.toRadians(elongasi))));
        double nurul_hilal=((1+Math.cos(Math.toRadians(mahfudz_nh)))/2)*100;
        double ghurub_hilal_taqrib=ghurub_syamsi_wd+muktsu_hilal;
        double jarak_bumi_matahari=(1.000140-0.01671*  Math.cos(Math.toRadians(khs)) -0.00014*Math.cos(Math.toRadians(2*khs)))*149597870;

        System.out.printf(format,"Elongasi",Konversi.dmsMili(elongasi));
        System.out.printf(format,"Mahfudz Nh",Konversi.dmsMili(mahfudz_nh));
        System.out.printf(format,"Nurul hilal",String.format("%.3f", nurul_hilal)+" % ");
        System.out.printf(format,"Ghurub hilal",Konversi.dmsMili(ghurub_hilal_taqrib));
        System.out.printf(format,"Jarak Bumi-Mat",(float)jarak_bumi_matahari);
        String tess=(String.format(format,"Elongasi",Konversi.dmsMili(elongasi)));

        System.out.println(System.lineSeparator());





        String format2 = "%-35s%5s%n";

        double jd_ijtima_final=Konversi.TanggalKeJulianDay(tahun_ijtima_masehi_wd,bulan_ijtima_masehi_wd,tanggal_ijtima_masehi_wd,waktu_ijtima_wd,0,0);
        double jd_ghurub_ijtima=Konversi.TanggalKeJulianDay(tahun_ijtima_masehi_wd,bulan_ijtima_masehi_wd,tanggal_ijtima_masehi_wd,gr_wd,0,0);
        boolean iktibar=hc>=2;
        String ghurub_bakda_ijtima;
        if (jd_ijtima_final>jd_ghurub_ijtima) ghurub_bakda_ijtima=tanggal_ghurubv2(jd_ijtima_wd);
        else ghurub_bakda_ijtima=tanggal_ghurub(jd_ijtima_wd);
        double umur_hilal=(jd_ghurub_ijtima-jd_ijtima_final)*24;
        int tahun_hijru_display=(int) tahun_hijri;
        if ((int) bulan_hijri==0)tahun_hijru_display--;


        String awal_bulan= kesimpulanHAB(jd_ijtima_final,iktibar);
        System.out.printf(format2,"1 "+NamaBulan.hijri((int) bulan_hijri+1)+" "+(int) tahun_hijri,awal_bulan);
        System.out.printf(format2,"Ijtima' Akhir "+NamaBulan.hijri((int) bulan_hijri)+" "+(int) tahun_hijru_display,ijtima_tanggal_masehi_wd+" - Pukul: "+Konversi.hms(waktu_ijtima_wd));
        System.out.printf(format2,"Ghurub Setelah Ijtima' ",ghurub_bakda_ijtima+" - Pukul: "+Konversi.hms(gr_wd));
        System.out.println("\nData Hilal "+ijtima_tanggal_masehi_wd+" "+Konversi.hms(gr_wd));
        System.out.printf(format2,"Umur bulan ke Ghurub",Konversi.hms(umur_hilal));
        System.out.printf(format2,"Tinggi Hilal Hakiki",Konversi.dmsMili(hc));
        System.out.printf(format2,"Tinggi Hilal Mar'i",Konversi.dmsMili(hcm));
        System.out.printf(format2,"Muktsul Hilal",Konversi.dmsMili(muktsu_hilal));
        System.out.printf(format2,"Hilal Terbenam Pukul",Konversi.dmsMili(ghurub_hilal_taqrib));

        String arah_azimut_m;
        if (azm_b<0)arah_azimut_m="Selatan Titik Barat";
        else arah_azimut_m="Utara Titik Barat";

        String arah_azimut_b;
        if (azc_b<0)arah_azimut_b="Selatan Titik Barat";
        else arah_azimut_b="Utara Titik Barat";


        System.out.printf(format2,"Azimut Matahari",Konversi.dmsMili(azm_b)+"  "+arah_azimut_m);
        System.out.printf(format2,"Azimut Hilal",Konversi.dmsMili(azc_b)+"  "+arah_azimut_b);
        String arah_hilal="";
        String keadaan_hilal="";
        if (sudut_hilal_matahari==0){
            keadaan_hilal="Terlentang";
        }else if (sudut_hilal_matahari>0){
            keadaan_hilal="Miring ke Utara";
            arah_hilal="Utara Matahari";
        }else if (sudut_hilal_matahari<0){
            keadaan_hilal="Miring ke Selatan";
            arah_hilal="Selatan Matahari";
        }

        System.out.printf(format2,"Arah Hilal dari Matahari",arah_hilal);
        System.out.printf(format2,"Keadaan Hilal",keadaan_hilal);
        System.out.printf(format2,"Cahaya Hilal",String.format("%.3f", nurul_hilal)+"% ");
        System.out.printf(format2,"Samkul Hilal",String.format("%.3f", samkul_hilal)+"M ");

       if(umur_hilal<0)hisabUlang(tahun_hijri,bulan_hijri,lintang,bujur,zona_waktu,tinggi_tempat,bujur_waktu_daerah);




    }
    public  static void hisabUlang(double tahun_hijri,double bulan_hijri,double lintang,double bujur,int zona_waktu,double tinggi_tempat, double bujur_waktu_daerah){
       //fungsi ini sama persis dengan yang atas, namun julian day-nya ditambah satu hari



        double hy = tahun_hijri+(bulan_hijri*29.53)/354.3671;            //ayyamut tarikh hijri
        double k;                                                       // Al mahfudz (Al mabda' / epoch 1410)
        if (((hy-1410)*12)%1>=0.5) k=(int)((hy-1410)*12)+1; else k=(int)((hy-1410)*12);
        double t=k/1200;                                                //juzul ashli
        double jd=2447740.652+29.53058868*k+0.0001178*Math.pow(t,2); // tarikh juliyani ghoirul muaddal
        double ks=frac((207.9587074+29.10535608*k+-0.0000333*Math.pow(t,2))/360)*360; //Khossoh Syamsi
        double kq=frac((111.1791307+385.81691806*k+0.0107306*Math.pow(t,2))/360)*360; //Khossoh Qomar
        double ha=frac((164.2162296+390.67050646*k+-0.0016528*Math.pow(t,2))/360)*360; //Hissoh Ardli




        double t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13; // 13 takdil

        t1 = (0.1734 - 0.000393 * t) * Math.sin(Math.toRadians(ks));
        t2 = 0.0021 * Math.sin(Math.toRadians(2*ks));
        t3 = -0.4068 * Math.sin(Math.toRadians(kq));
        t4 = 0.0161 * Math.sin(Math.toRadians(2*kq));
        t5 = -0.0004 * Math.sin(Math.toRadians(3*kq));
        t6 = 0.0104 * Math.sin(Math.toRadians(2*ha));
        t7 = -0.0051 * Math.sin(Math.toRadians(ks+kq));
        t8 = -0.0074 * Math.sin(Math.toRadians(ks-kq));
        t9 = 0.0004 * Math.sin(Math.toRadians(2*ha+ks));
        t10 = -0.0004 * Math.sin(Math.toRadians(2*ha-ks));
        t11 = -0.0006 * Math.sin(Math.toRadians(2*ha+kq));
        t12 = 0.0010 * Math.sin(Math.toRadians(2*ha-kq));
        t13 = 0.0005 *Math.sin(Math.toRadians(ks+2*kq));

        double mt=t1+t2+t3+t4+t5+t6+t7+t8+t9+t10+t11+t12+t13; // majmuah takdiilati



        double jd_ijtima_ut=jd+0.5+mt;
        double jd_ijtima_wd=(jd+0.5+mt)+(zona_waktu/24.0);
        double waktu_ijtima_ut=frac(jd_ijtima_ut)*24;
        double waktu_ijtima_wd;                     //waktu ijtima sesuai daerah Wib/wit /wita
        if ((waktu_ijtima_ut+zona_waktu)>24)waktu_ijtima_wd=waktu_ijtima_ut+zona_waktu-24;
        else waktu_ijtima_wd=waktu_ijtima_ut+zona_waktu;
        //System.out.println(Konversi.hms(waktu_ijtima_wd));
        int z =(int)jd_ijtima_ut;
        int aa=(int)((z-1867216.25)/36524.25);
        int a;
        if (z>=2299161)a=z+1+aa-(int)(aa/4);
        else a=z;

        int b = a+1524;
        int c =(int)((b-122.1)/365.25);
        int d = (int)(365.25*c);
        int e = (int)((b-d)/30.6001);
        int tanggal_ijtima_masehi=(int)(b-d-(int)(30.6001*e));
        int tanggal_ijtima_masehi_wd=ijtima_tanggal_masehi_wd(jd_ijtima_wd)[1];
        int bulan_ijtima_masehi;
        if (e>=13.5) bulan_ijtima_masehi=e-13;
        else bulan_ijtima_masehi=e-1;
        int bulan_ijtima_masehi_wd=ijtima_tanggal_masehi_wd(jd_ijtima_wd)[2];

        int tahun_ijtima_masehi;
        if (bulan_ijtima_masehi>=2.5)tahun_ijtima_masehi=c-4716;
        else tahun_ijtima_masehi=c-4715;
        int tahun_ijtima_masehi_wd=ijtima_tanggal_masehi_wd(jd_ijtima_wd)[3];
        int pa=z+2;
        int nomor_hari=pa-(int)(pa/7)*7;
        int nomor_pasaran=pa-(int)(pa/5)*5;

        double m,y;
        if (bulan_ijtima_masehi<=2) {
            m = bulan_ijtima_masehi + 12;
            y=tahun_ijtima_masehi-1;
        }
        else {
            m = bulan_ijtima_masehi;
            y=tahun_ijtima_masehi;
        }
        double ghurub_syamsi_wd=waktuMaghrib(tanggal_ijtima_masehi_wd,bulan_ijtima_masehi_wd,tahun_ijtima_masehi_wd,lintang,bujur,tinggi_tempat,zona_waktu);
        double ghurub_syamsi_ut=ghurub_syamsi_wd-zona_waktu;


        int A;
        int B=0;

        //bila masuk periode Gregorian
        if ((tahun_ijtima_masehi+bulan_ijtima_masehi/100.0+tanggal_ijtima_masehi/10000.0)>=1582.1015){
            A= (int) (tahun_ijtima_masehi/100);
            B=2+(A/4)-A;
        }

        double julian_day=(int)(365.25*(y+4716))+(int)(30.6001*(m+1))+tanggal_ijtima_masehi_wd+1+(ghurub_syamsi_ut/24)+B-1524.5; // aslul miladi
        //double rjulian_day=Math.round(julian_day * 1000.0) / 1000.0;
        double T=(julian_day-2451545)/36525; //juz al asli milady


        // System.out.printf(format,"",Konversi.dmsMili());



        // Data Matahari
        double ws=frac((280.46645+36000.76983*T)/360)*360; //wasat syamsi
        double khs=frac((357.5291+35999.05030*T)/360)*360;//khossoh syamsi
        double N=frac((125.04-1934.136*T)/360)*360;//uqdah syamsi


        //Tashihatu li uqdah syamsi wal qomar

        double k1=(17.264/3600)*Math.sin(Math.toRadians(N))+(0.206/3600)*Math.sin(Math.toRadians(2*N));
        double k2=(-1.264/3600)*Math.sin(Math.toRadians(2*ws));
        double r1=(9.23/3600)*Math.cos(Math.toRadians(N))-(0.090/3600)*Math.cos(Math.toRadians(2*N));
        double r2=(0.548/3600)*Math.cos(Math.toRadians(2*ws));
        double q1=23.43929111+r1+r2-(46.8150/3600)*T;//mailkully
        double ts=(6898.06/3600)*Math.sin(Math.toRadians(ks))+(72.095/3600)*Math.sin(Math.toRadians(2*ks))+(0.966/3600)*Math.sin(Math.toRadians(3*ks));//ta'dil syamsi
        double s1=ws+ts+k1+k2-(20.47/3600);
        double deklinasi_matahari=Math.toDegrees(Math.asin(Math.sin(Math.toRadians(s1))*Math.sin(Math.toRadians(q1))));
        double pt=Math.toDegrees(Math.atan(Math.tan(Math.toRadians(s1))*Math.cos(Math.toRadians(q1))));
        if (s1>=0&&s1<=90)pt+=0;
        else if (s1>90&&s1<=270)pt+=180;
        else if (s1>270&&s1<=360)pt+=360;


        double ea=-1.915*Math.sin(Math.toRadians(ks));
        double eb=-0.02*Math.sin(Math.toRadians(2*ks));
        double ec=2.466*Math.sin(Math.toRadians(2*s1));
        double ed=-0.053*Math.sin(Math.toRadians(4*s1));
        double dt=(ea+eb+ec+ed)/15; //daqoiq tafawut

        double sd=0.267/(1-0.017*Math.cos(Math.toRadians(ks)));
        double dip=(1.76/60)*Math.sqrt(tinggi_tempat);
        double h=-(sd+(34.5/60)+dip);//irtifa syamsi waktal ghurub
        double nishfu_qous_nSyamsi=Math.toDegrees(Math.acos(-Math.tan(Math.toRadians(lintang))*Math.tan(Math.toRadians(deklinasi_matahari))+Math.sin(Math.toRadians(h))/Math.cos(Math.toRadians(lintang))/Math.cos(Math.toRadians(deklinasi_matahari))));
        double gr_lmt=nishfu_qous_nSyamsi/15+(12-dt);
        double gr_wd=gr_lmt+(bujur_waktu_daerah-bujur)/15;

        //Data bulan

        double wq=frac((218.31617+481267.88088*T)/360)*360; //wasat qomar
        double khq=frac((134.96292+477198.86753*T)/360)*360;
        double f=frac((93.27283+483202.01873*T)/360)*360;
        double fw=frac((297.85027+445267.11135*T)/360)*360;


        // 14 takdil qomar
        double tq1,tq2,tq3,tq4,tq5,tq6,tq7,tq8,tq9,tq10,tq11,tq12,tq13,tq14;
        tq1=(22640/3600.0)*Math.sin(Math.toRadians(khq));
        tq2=(-4586/3600.0)*Math.sin(Math.toRadians(khq-2*fw));
        tq3=(2370/3600.0)*Math.sin(Math.toRadians(2*fw));//
        tq4=(769/3600.0)*Math.sin(Math.toRadians(2*khq));//
        tq5=(-668/3600.0)*Math.sin(Math.toRadians(khs));
        tq6=(-412/3600.0)*Math.sin(Math.toRadians(2*f));//
        tq7=(-212/3600.0)*Math.sin(Math.toRadians(2*khq-2*fw));//
        tq8=(-206/3600.0)*Math.sin(Math.toRadians(khq+khs-2*fw));
        tq9=(192/3600.0)*Math.sin(Math.toRadians(khq+2*fw));//
        tq10=(-165/3600.0)*Math.sin(Math.toRadians(khs-2*fw));
        tq11=(148/3600.0)*Math.sin(Math.toRadians(khq-khs));
        tq12=(-125/3600.0)*Math.sin(Math.toRadians(fw));//
        tq13=(-110/3600.0)*Math.sin(Math.toRadians(khq+khs));
        tq14=(-55/3600.0)*Math.sin(Math.toRadians(2*f-2*fw));//
        double mtq=tq1+tq2+tq3+tq4+tq5+tq6+tq7+tq8+tq9+tq10+tq11+tq12+tq13+tq14;

        double mo=(wq+mtq+k1+k2-(20.47/3600));
        double a1=khq+tq2+tq3+tq5;
        double l1=(18461/3600.0)*Math.sin(Math.toRadians(f))+(1010/3600.0)*Math.sin(Math.toRadians(khq+f))+(1000/3600.0)*Math.sin(Math.toRadians(khq-f))-(624/3600.0)*Math.sin(Math.toRadians(f-2*fw))-(199/3600.0)*Math.sin(Math.toRadians(khq-f-2*fw))-(167/3600.0)*Math.sin(Math.toRadians(khq+f-2*fw));
        double x = Math.toDegrees(Math.atan(Math.sin(Math.toRadians(mo))*Math.tan(Math.toRadians(q1))));
        double hb=(l1+x);

        double dc=Math.toDegrees(Math.asin(Math.sin(Math.toRadians(mo))*Math.sin(Math.toRadians(q1))*Math.sin(Math.toRadians(hb))/Math.sin(Math.toRadians(x))));
        double ptc=Math.toDegrees(Math.acos(Math.cos(Math.toRadians(mo))*Math.cos(Math.toRadians(l1))/Math.cos(Math.toRadians(dc))));
        if (mo>180&&mo<=360)ptc=(360-ptc);
        double tc = (pt-ptc)+nishfu_qous_nSyamsi;
        double hc=Math.toDegrees(Math.asin(Math.sin(Math.toRadians(lintang))*Math.sin(Math.toRadians(dc))+Math.cos(Math.toRadians(lintang))*Math.cos(Math.toRadians(dc))*Math.cos(Math.toRadians(tc))));

        double jarak_bm_bln=(384401*(1-0.0549 * 0.0549))/(1+0.0549*(Math.cos(Math.toRadians(a1+tq1))));
        double hp1=0.9507/(jarak_bm_bln/384401);
        double sdc=(0.5181/(jarak_bm_bln/384401))/2;
        double hp2=hp1*(Math.cos(Math.toRadians(hc)));
        double ref=0.0167/Math.tan(Math.toRadians(hc+7.31/(hc+4.4)));
        double hcm;
        if ((hc-hp2)>0)hcm=hc-hp2+sdc+ref+dip;
        else if ((hc-hp2)<=0)hcm=hc-hp2;
        else hcm=0;

        double azm_b=Math.toDegrees(Math.atan(-Math.sin(Math.toRadians(lintang))/Math.tan(Math.toRadians(nishfu_qous_nSyamsi))+Math.cos(Math.toRadians(lintang))*Math.tan(Math.toRadians(deklinasi_matahari))/Math.sin(Math.toRadians(nishfu_qous_nSyamsi))));
        double azm_u;
        if (t<180)azm_u=azm_b+270;
        else azm_u=azm_b+90;

        double azc_b=Math.toDegrees(Math.atan(-Math.sin(Math.toRadians(lintang))/Math.tan(Math.toRadians(tc))+Math.cos(Math.toRadians(lintang))*Math.tan(Math.toRadians(dc))/Math.sin(Math.toRadians(tc))));
        double azc_u;
        if (tc<180)azc_u=azc_b+270;
        else azc_u=azc_b+90;

        double sudut_hilal_matahari=azc_u-azm_u;
        double muktsu_hilal=(ptc-pt)/15;
        double mahfudz_samkulHilal=Math.toDegrees(Math.acos(Math.cos(Math.toRadians(Math.abs(hcm-h)))*Math.cos(Math.toRadians(Math.abs(azc_u-azm_u)))));
        double samkul_hilal=(1-(Math.cos(Math.toRadians(mahfudz_samkulHilal))))*sdc*60;

        double elongasi=Math.toDegrees(Math.acos(Math.cos(Math.toRadians(mo-s1))*Math.cos(Math.toRadians(l1))));
        double mahfudz_nh=Math.toDegrees(Math.acos(-Math.cos(Math.toRadians(elongasi))));
        double nurul_hilal=((1+Math.cos(Math.toRadians(mahfudz_nh)))/2)*100;
        double ghurub_hilal_taqrib=ghurub_syamsi_wd+muktsu_hilal;
        double jarak_bumi_matahari=(1.000140-0.01671*  Math.cos(Math.toRadians(khs)) -0.00014*Math.cos(Math.toRadians(2*khs)))*149597870;

        String format2 = "%-35s%5s%n";

        System.out.println("\nData Hilal "+tanggal_ghurubv2(jd_ijtima_wd)+" "+Konversi.hms(gr_wd));
        System.out.printf(format2,"Tinggi Hilal Hakiki",Konversi.dmsMili(hc));
        System.out.printf(format2,"Tinggi Hilal Mar'i",Konversi.dmsMili(hcm));
        System.out.printf(format2,"Muktsul Hilal",Konversi.dmsMili(muktsu_hilal));
        System.out.printf(format2,"Hilal Terbenam Pukul",Konversi.dmsMili(ghurub_hilal_taqrib));

        String arah_azimut_m;
        if (azm_b<0)arah_azimut_m="Selatan Titik Barat";
        else arah_azimut_m="Utara Titik Barat";

        String arah_azimut_b;
        if (azc_b<0)arah_azimut_b="Selatan Titik Barat";
        else arah_azimut_b="Utara Titik Barat";


        System.out.printf(format2,"Azimut Matahari",Konversi.dmsMili(azm_b)+"  "+arah_azimut_m);
        System.out.printf(format2,"Azimut Hilal",Konversi.dmsMili(azc_b)+"  "+arah_azimut_b);
        String arah_hilal="";
        String keadaan_hilal="";
        if (sudut_hilal_matahari==0){
            keadaan_hilal="Terlentang";
        }else if (sudut_hilal_matahari>0){
            keadaan_hilal="Miring ke Utara";
            arah_hilal="Utara Matahari";
        }else if (sudut_hilal_matahari<0){
            keadaan_hilal="Miring ke Selatan";
            arah_hilal="Selatan Matahari";
        }

        System.out.printf(format2,"Arah Hilal dari Matahari",arah_hilal);
        System.out.printf(format2,"Keadaan Hilal",keadaan_hilal);
        System.out.printf(format2,"Cahaya Hilal",String.format("%.3f", nurul_hilal)+"% ");
        System.out.printf(format2,"Samkul Hilal",String.format("%.3f", samkul_hilal)+"M ");


    }




    public static double waktuMaghrib(int tgl, int bulan, int tahun, double lintang, double bujur, double tt, int zw){
        double  PerkiraanDhuhur=0, PerkiraanMaghrib=0, h, f, G, Dz, EoT, Dip;
        double DekMat, sd, MaghribPerkiraan, MaghribSebenarnya;
        switch (zw) {
            case 7: PerkiraanDhuhur = 11;PerkiraanMaghrib=17;break;
            case 8: PerkiraanDhuhur = 10;PerkiraanMaghrib=16;break;
            case 9: PerkiraanDhuhur = 9; PerkiraanMaghrib=15; break;
        }

        EoT= DataEphemeris.Meeus(Konversi.TanggalKeJulianDay(tahun,bulan,tgl,PerkiraanDhuhur-zw,0,0))[7];
        Dz=12-EoT+((zw*15)-bujur)/15; //dzuhur wd perkiraan
        EoT= DataEphemeris.Meeus(Konversi.TanggalKeJulianDay(tahun,bulan,tgl,Dz-zw,0,0))[7];
        Dz=12-EoT+((zw*15)-bujur)/15; //dzuhur wd

        DekMat=DataEphemeris.Meeus(Konversi.TanggalKeJulianDay(tahun,bulan,tgl,PerkiraanMaghrib-zw,0,0))[4];
        sd=DataEphemeris.Meeus(Konversi.TanggalKeJulianDay(tahun,bulan,tgl,PerkiraanMaghrib-zw,0,0))[5];

        f=-Math.tan(Math.toRadians(lintang))*Math.tan(Math.toRadians(DekMat));
        G=Math.cos(Math.toRadians(lintang))*Math.cos(Math.toRadians(lintang));
        Dip=1.76/60*Math.sqrt(tt);
        h=-(sd+(34.5/60)+Dip)-0.0024;
        MaghribPerkiraan=Dz+Math.toDegrees(Math.acos(f+Math.sin(Math.toRadians(h))/G))/15;


        //menghitung ulang maghrib sebenarnya
        DekMat=DataEphemeris.Meeus(Konversi.TanggalKeJulianDay(tahun,bulan,tgl,MaghribPerkiraan-zw,0,0))[4];
        sd=DataEphemeris.Meeus(Konversi.TanggalKeJulianDay(tahun,bulan,tgl,MaghribPerkiraan-zw,0,0))[5];

        f=-Math.tan(Math.toRadians(lintang))*Math.tan(Math.toRadians(DekMat));
        G=Math.cos(Math.toRadians(lintang))*Math.cos(Math.toRadians(lintang));
        Dip=1.76/60*Math.sqrt(tt);
        h=-(sd+(34.5/60)+Dip)-0.0024;
        MaghribSebenarnya=Dz+Math.toDegrees(Math.acos(f+Math.sin(Math.toRadians(h))/G))/15;



        return MaghribSebenarnya;
    }



    public static String tanggal_ghurub(double jd){

        String tanggal_hasil;
        int z =(int)jd; //ziyadah litawtih ------ijtimak wd
        int aa=(int)((z-1867216.25)/36524.25);
        int a;
        if (z>=2299161)a=z+1+aa-(int)(aa/4);
        else a=z;

        int b = a+1524;
        int c =(int)((b-122.1)/365.25);
        int d = (int)(365.25*c);
        int e = (int)((b-d)/30.6001);
        int tanggal_masehi=(int)(b-d-(int)(30.6001*e));
        int bulan_masehi;
        if (e>=13.5) bulan_masehi=e-13;
        else bulan_masehi=e-1;

        int tahun_masehi;
        if (bulan_masehi>=2.5)tahun_masehi=c-4716;
        else tahun_masehi=c-4715;
        int pa=z+2;
        int nomor_hari=pa-(int)(pa/7)*7;
        int nomor_pasaran=pa-(int)(pa/5)*5;

        tanggal_hasil=tanggalMasehi(nomor_hari,nomor_pasaran,tanggal_masehi,bulan_masehi,tahun_masehi);
        return tanggal_hasil;

    }

    public static String tanggal_ghurubv2(double jd){

        String tanggal_hasil;
        int tanggal_masehi=Konversi.JulianDayKeTanggal(jd)[1];
        int bulan_masehi=Konversi.JulianDayKeTanggal(jd)[2];
        int tahun_masehi=Konversi.JulianDayKeTanggal(jd)[3];

        int nomor_hari= (int) ((jd+1.5 )%7+1);
        int nomor_pasaran= (int) ((jd+1.5 )%5);

        tanggal_hasil=NamaHariPasaran.namahari(nomor_hari)+" "+NamaHariPasaran.namapasaran(nomor_pasaran)+", "+tanggal_masehi+" "+NamaBulan.masehi(bulan_masehi)+" "+tahun_masehi;
        return tanggal_hasil;

    }

    public static  String kesimpulanHAB(double jd, boolean iktibar_bool){

        String iktibar;
        if (iktibar_bool){
            iktibar="Imkan Ru'yah";
            jd++;
        }else {
            iktibar="Istikmal";
            jd+=2;
        }
        String tanggal_hasil;
        int tanggal_masehi=Konversi.JulianDayKeTanggal(jd)[1];
        int bulan_masehi=Konversi.JulianDayKeTanggal(jd)[2];
        int tahun_masehi=Konversi.JulianDayKeTanggal(jd)[3];

        int nomor_hari= (int) ((jd+1.5 )%7+1);
        int nomor_pasaran= (int) ((jd+1.5 )%5);

        tanggal_hasil=NamaHariPasaran.namahari(nomor_hari)+" "+NamaHariPasaran.namapasaran(nomor_pasaran)+", "+tanggal_masehi+" "+NamaBulan.masehi(bulan_masehi)+" "+tahun_masehi +" - "+iktibar;

        return tanggal_hasil;

    }



    public  static  String ijtima_tanggal_masehi_wd_str(double jd){


        int tanggal_ijtima_masehi=ijtima_tanggal_masehi_wd(jd)[1];
        int bulan_ijtima_masehi=ijtima_tanggal_masehi_wd(jd)[2];


        int tahun_ijtima_masehi=ijtima_tanggal_masehi_wd(jd)[3];

        int nomor_hari=ijtima_tanggal_masehi_wd(jd)[4];
        int nomor_pasaran=ijtima_tanggal_masehi_wd(jd)[5];


        return tanggalMasehi(nomor_hari,nomor_pasaran,tanggal_ijtima_masehi,bulan_ijtima_masehi,tahun_ijtima_masehi);

    }

    public  static  int[] ijtima_tanggal_masehi_wd(double jd){

        int z =(int)jd; //ziyadah litawtih ------ijtimak wd
        int aa=(int)((z-1867216.25)/36524.25);
        int a;
        if (z>=2299161)a=z+1+aa-(int)(aa/4);
        else a=z;

        int b = a+1524;
        int c =(int)((b-122.1)/365.25);
        int d = (int)(365.25*c);
        int e = (int)((b-d)/30.6001);
        int tanggal_ijtima_masehi=(int)(b-d-(int)(30.6001*e));
        int bulan_ijtima_masehi;
        if (e>=13.5) bulan_ijtima_masehi=e-13;
        else bulan_ijtima_masehi=e-1;

        int tahun_ijtima_masehi;
        if (bulan_ijtima_masehi>=2.5)tahun_ijtima_masehi=c-4716;
        else tahun_ijtima_masehi=c-4715;
        int pa=z+2;
        int nomor_hari=pa-(int)(pa/7)*7;
        int nomor_pasaran=pa-(int)(pa/5)*5;


        // tanggalMasehi(nomor_hari,nomor_pasaran,tanggal_ijtima_masehi,bulan_ijtima_masehi,tahun_ijtima_masehi);
        return new int[]{0,tanggal_ijtima_masehi,bulan_ijtima_masehi,tahun_ijtima_masehi,nomor_hari,nomor_pasaran};
    }

    public  static  String tanggalMasehi(int nomor_hari,int nomor_pasaran,int tanggal, int bulan, int tahun){
        String namahari="";
        switch (nomor_hari) {
            case 1: namahari = "Ahad";break;
            case 2: namahari = "Senin";break;
            case 3: namahari = "Selasa";break;
            case 4: namahari = "Rabu";break;
            case 5: namahari = "Kamis";break;
            case 6: namahari = "Jum´at";break;
            case 7:
            case 0: namahari = "Sabtu";break;
        }

        String namapasaran="";

        switch (nomor_pasaran) {
            case 1: namapasaran = "Kliwon"; break;
            case 2: namapasaran = "Legi"; break;
            case 3: namapasaran = "Pahing";break;
            case 4: namapasaran = "Pon";break;
            case 5:
            case 0: namapasaran = "Wage";break;

        }

        return  namahari+" "+namapasaran+", "+tanggal+" "+NamaBulan.masehi(bulan)+" "+tahun;

    }

    public static  double frac(double desimal){
        desimal%=1;

        return  desimal;
    }

}
