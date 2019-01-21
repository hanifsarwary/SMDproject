package com.example.mhanif.project.Database;

import android.arch.persistence.room.Entity;

/**
 * Created by M.hanif on 4/22/2018.
 */
@Entity
public class Category {
   public long cid;
   public String c_name;
   public String c_desc;
   public int picture;

   public Category(int c,String n,String d,int picture){
       cid=c;c_name=n;c_desc=d;this.picture=picture;
   }

    public Category() {
    }

    public Category(long cid, String c_name, String c_desc, int picture) {
      this.cid = cid;
      this.c_name = c_name;
      this.c_desc = c_desc;
      this.picture = picture;
   }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_desc() {
        return c_desc;
    }

    public void setC_desc(String c_desc) {
        this.c_desc = c_desc;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }
}
