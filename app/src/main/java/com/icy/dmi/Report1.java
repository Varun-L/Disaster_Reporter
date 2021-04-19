package com.icy.dmi;

public class Report1 {

    public String Prev_Incom;
    public String Disaster,Severity,Time,Time_Stamp;
    public String lat,lon;
//    public String de="Earthquake",dl="Landslide" ,df="Flood",dt="Terror Attack",sl="Low",sm="Medium",sh="High";
//    public String tw="Week",tm="Month",ty="Year";

    public Report1(String pi,String D,String S,String T,String lat,String lon,String ts){
        this.Prev_Incom=pi;
        this.Disaster=D;
        this.Severity=S;
        this.Time=T;
        this.lat=lat;
        this.lon=lon;
        this.Time_Stamp=ts;
    }
    public Report1(String pi,String D,String S,String T,String ts){
        this.Prev_Incom=pi;
        this.Disaster=D;
        this.Severity=S;
        this.Time=T;
    }

}

class DBReport{

    public String UID;
    public int UColor,UTSize;

    public DBReport(String UID, int UTSize,int UColor) {
        this.UID = UID;
        this.UColor = UColor;
        this.UTSize = UTSize;
    }
    public DBReport(){

    }


}