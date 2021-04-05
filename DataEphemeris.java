package com.kadzalik;

public class DataEphemeris {

    /*  mengubah nilai Julian day pada pukul tertentu ke data ephemeris sesuai jam jd tersebut*/
    //Meeus Sun-Moon
    //Vsop87 Sun
    //Elpmpp2000 Moon
    //Kadzaliklab

    public static double [] Meeus (double JD) {
        double JD_UT=JD;

        double delta_T=0;
        //JDE waktu TD(Dynamical time)
        double jde=JD_UT+delta_T;

        double T_TD=(jde-2451545)/36525;
        double tau=T_TD/10;

        double deltaPsi= Nutasi.deltaPsiDanEpsilon(T_TD)[2];

        double epsilon= Nutasi.deltaPsiDanEpsilon(T_TD)[6];
        double epsilon_r=Math.toRadians(epsilon);


        //Bulan
        //l1= bujur rata-rata bulan
        double L1=(218.3164591+481267.88134236*T_TD-0.0013268*T_TD*T_TD+T_TD*T_TD*T_TD/538841-T_TD*T_TD*T_TD*T_TD/65194000)%360;

        //elongasi rata2 bulan
        double d= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[1];

        //Anomali rata2 Matahari
         double m= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[2];

        //Anomali rata2 bulan
        double ma= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[3];

        //Argumen bujur bulan
        double f= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[4];

        //Eksentrisitas orbit
        double e= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[5];

        //Koreksi bujur bulan
        double koreksibujurB= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[6];
        double bujurB=(L1+koreksibujurB)%360;
        double bujurB_nampak=(bujurB+deltaPsi)%360;
        if (bujurB_nampak<0)bujurB_nampak+=360;
        double bujurB_nampak_r=Math.toRadians(bujurB_nampak);

        //Koreksi lintang bulan
        double lintangB= TabelBulanMeeus.sukuPeriodik(T_TD,L1)[7];
        double lintangB_r=Math.toRadians(lintangB);

        //Koreksi jarak bumi-bulan
        double jarakBB=385000.56+ TabelBulanMeeus.sukuPeriodik(T_TD,L1)[8];

        double sudutParalaksB=Math.toDegrees(Math.asin(6378.14/jarakBB));
        double sudutJariB=358473400/(jarakBB*3600);

        double alphaBulan=(Math.toDegrees(Math.atan2(Math.sin(bujurB_nampak_r)*Math.cos(epsilon_r)-Math.tan(lintangB_r)*Math.sin(epsilon_r),Math.cos(bujurB_nampak_r))))%360;
        if (alphaBulan<0)alphaBulan=(alphaBulan+360)%360;
        double alphaBulanPukul=alphaBulan/15;

        double deltaBulan=Math.toDegrees(Math.asin(Math.sin(lintangB_r)*Math.cos(epsilon_r)+Math.cos(lintangB_r)*Math.sin(epsilon_r)*Math.sin(bujurB_nampak_r)));
        double deltaBulan_r=Math.toRadians(deltaBulan);



        //Matahari
        double L   = TabelMatahariMeeus.bujurEkliptik(tau,koreksibujurB)[1];
        double theta = TabelMatahariMeeus.bujurEkliptik(tau,koreksibujurB)[2];

        double lambdaM = TabelMatahariMeeus.bujurEkliptik(tau,koreksibujurB)[3];
        double lambdaM_r =Math.toRadians(lambdaM);
        double Delta_theta = TabelMatahariMeeus.bujurEkliptik(tau,koreksibujurB)[4];
        double theta_terkoreksi = TabelMatahariMeeus.bujurEkliptik(tau,koreksibujurB)[5];
        double jarakBumi_Matahari = TabelMatahariMeeus.jarakBumiMat(tau);
        double jarakBm_M=149598000*jarakBumi_Matahari;
        double lintangM = TabelMatahariMeeus.lintangEkliptikB(tau,lambdaM_r)[2];
        double beta_M_r =Math.toRadians(lintangM/3600);
        double koreksiAberasi=-20.4898/(3600*jarakBumi_Matahari);

        double bujurM_nampak=(theta_terkoreksi+deltaPsi+koreksiAberasi)%360;
        if (bujurM_nampak<0)bujurM_nampak+=360;
        double bujurM_nampak_r=Math.toRadians(bujurM_nampak);
        double sudutJariM=(959.63/3600)/jarakBumi_Matahari;

        double alphaMatahari=(Math.toDegrees(Math.atan2(Math.sin(bujurM_nampak_r)*Math.cos(epsilon_r)-Math.tan(beta_M_r)*Math.sin(epsilon_r),Math.cos(bujurM_nampak_r))))%360;
        if (alphaMatahari<0)alphaMatahari=(alphaMatahari+360)%360;
        double alphaM_pukul=alphaMatahari/15;
        double deltaMatahari=Math.toDegrees(Math.asin(Math.sin(beta_M_r)*Math.cos(epsilon_r)+Math.cos(beta_M_r)*Math.sin(epsilon_r)*Math.sin(bujurM_nampak_r)));
        double deltaM_r=Math.toRadians(deltaMatahari);


        double U=(JD-2451545)/36525;
        //bujur rata2 matahari
        double L0 =Math.toRadians((280.46607+36000.7698*U)%360);
        double EoT=(-1*(1789+237*U)*Math.sin(L0)-(7146-62*U)*Math.cos(L0)+(9934-14*U)*Math.sin(2*L0)-(29+5*U)*Math.cos(2*L0)+(74+10*U)*Math.sin(3*L0)+(320-4*U)*Math.cos(3*L0)-212*Math.sin(4*L0))/1000;

        EoT/=60; //jadikan menit

        double sudutFai=Math.acos(Math.sin(deltaBulan_r)*Math.sin(deltaM_r)+Math.cos(deltaBulan_r)*Math.cos(deltaM_r)*Math.cos(Math.toRadians(alphaBulan-alphaMatahari)));
        double sudutFase=Math.atan2(jarakBm_M*Math.sin(sudutFai),jarakBB-jarakBm_M*Math.cos(sudutFai));
        double sudutFase_d=Math.toDegrees(sudutFase);


        double iluminasiB=(1+Math.cos(sudutFase))/2;


        //menampilkan hasil
        //formatter jarak antara teks yang di print biar layoutnya rapi
//        String formatter ="%-8s%-15s%-15s%-15s%-15s%-15s%-15s%5s%n";
//        System.out.println("\n"+"Data Matahari");
//        System.out.printf(formatter,"Jam","Ecliptic","Ecliptic","Right","Apparent","Semi","Kemiringan","Equation");
//        System.out.printf(formatter,"Gmt","Longitude","Latitude","Ascension","Declination","Diameter","(Epsilon)","of time");
//        System.out.printf(formatter,(int)jam,desimal_ke_derajat(theta_terkoreksi)[1]+"\u00B0"+desimal_ke_derajat(theta_terkoreksi)[2]+"\u2032"+desimal_ke_derajat(theta_terkoreksi)[3]+"\u2033",(float)lintangM,desimal_ke_derajat(alphaMatahari)[1]+":"+desimal_ke_derajat(alphaMatahari)[2]+":"+desimal_ke_derajat(alphaMatahari)[3],desimal_ke_derajat(deltaMatahari)[1]+"\u00B0"+desimal_ke_derajat(deltaMatahari)[2]+"\u2032"+desimal_ke_derajat(deltaMatahari)[3]+"\u2033",desimal_ke_derajat(sudutJariM)[1]+"\u00B0"+desimal_ke_derajat(sudutJariM)[2]+"\u2032"+desimal_ke_derajat(sudutJariM)[3]+"\u2033",desimal_ke_derajat(epsilon)[1]+"\u00B0"+desimal_ke_derajat(epsilon)[2]+"\u2032"+desimal_ke_derajat(epsilon)[3]+"\u2033",desimal_ke_derajat(EoT)[1]+"\u00B0"+desimal_ke_derajat(EoT)[2]+"\u2032"+desimal_ke_derajat(EoT)[3]+"\u2033");

//
//        System.out.println("\n\n"+"Data Bulan");
//        System.out.printf(formatter,"Jam","Apparent","Apparent","Right","Apparent","Semi","Horizontal","Iluminasi");
//        System.out.printf(formatter,"Gmt","Longitude","Latitude","Ascension","Declination","Diameter","Parallax","Bulan");
//        System.out.printf(formatter,(int)jam,desimal_ke_derajat(bujurB_nampak)[1]+"\u00B0"+desimal_ke_derajat(bujurB_nampak)[2]+"\u2032"+desimal_ke_derajat(bujurB_nampak)[3]+"\u2033",desimal_ke_derajat(lintangB)[1]+"\u00B0"+desimal_ke_derajat(lintangB)[2]+"\u2032"+desimal_ke_derajat(lintangB)[3]+"\u2033",desimal_ke_derajat(alphaBulan)[1]+":"+desimal_ke_derajat(alphaBulan)[2]+":"+desimal_ke_derajat(alphaBulan)[3],desimal_ke_derajat(deltaBulan)[1]+"\u00B0"+desimal_ke_derajat(deltaBulan)[2]+"\u2032"+desimal_ke_derajat(deltaBulan)[3]+"\u2033",desimal_ke_derajat(sudutJariB)[1]+"\u00B0"+desimal_ke_derajat(sudutJariB)[2]+"\u2032"+desimal_ke_derajat(sudutJariB)[3]+"\u2033",desimal_ke_derajat(sudutParalaksB)[1]+"\u00B0"+desimal_ke_derajat(sudutParalaksB)[2]+"\u2032"+desimal_ke_derajat(sudutParalaksB)[3]+"\u2033",String.format("%.7f", iluminasiB));


     return  new double[]{0,theta_terkoreksi,lintangM,alphaMatahari,deltaMatahari,sudutJariM,epsilon,EoT,jarakBm_M,bujurB_nampak,lintangB,alphaBulan,deltaBulan,sudutJariB,sudutParalaksB,iluminasiB,sudutFase,jarakBB,bujurB_nampak};

        // indeks
        // 01 ekliptik long M
        // 02 ekliptik lat M
        // 03 Arekta M
        // 04 Deklinasi M
        // 05 sudut jariM
        // 06 kemiringanM
        // 07 Eot
        // 08 jarak mat-bumi

        // 09 ekliptik long B
        // 10 eklitik lat bulan
        // 11 A rekta B
        // 12 delinasi B
        // 13 sudutjari B
        // 14 sudut paralaks
        // 15 iluminasi B
        // 16 sudut fase
        // 17 jarak bumi-bulan

    }
}
