package com.example.kongsun.schoolguide.accessor;

/**
 * Created by kongsun on 9/7/17.
 */

public class PublicUniversity {
    public int id;
    public String kh_Name;
    public String en_Name;
    public String logoUrl;
    public String photoUrl;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKh_Name() {
        return kh_Name;
    }

    public void setKh_Name(String kh_Name) {
        this.kh_Name = kh_Name;
    }

    public String getEn_Name() {
        return en_Name;
    }

    public void setEn_Name(String en_Name) {
        this.en_Name = en_Name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public PublicUniversity(int id, String kh_Name, String en_Name, String logoUrl, String photoUrl,String desc){
        this.id = id;
        this.kh_Name = kh_Name;
        this.en_Name = en_Name;
        this.logoUrl = logoUrl;
        this.photoUrl = photoUrl;
        this.desc =desc;
    }
}
