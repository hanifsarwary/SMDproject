package com.example.mhanif.project.Database;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;

/**
 * Created by M.hanif on 4/16/2018.
 */
@Entity
public class User {


    public String password;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @PrimaryKey
  @NonNull  public String Email;
    @ColumnInfo(name ="user_name")
    public String user_name;
    public String phone;
    public String address;
   public String key;


    public User() {

    }
    public User( String key,String Email){
        this.key=key;
        this.Email=Email;
    }


    public User( String firstName, String lastName, @NonNull String email, String user_name, String phone, String address) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
        this.user_name = user_name;
        this.phone = phone;
        this.address = address;
    }

    public User(String firstName, String lastName, @NonNull String email, String user_name, String phone, String address, String key) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
        this.user_name = user_name;
        this.phone = phone;
        this.address = address;
        this.key = key;
    }
}
