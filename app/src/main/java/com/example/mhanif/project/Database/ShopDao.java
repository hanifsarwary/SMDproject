package com.example.mhanif.project.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by M.hanif on 4/17/2018.
 */
@Dao
public interface ShopDao {
    @Query("SELECT * FROM Shop")
    List<Shop> getall();

    @Insert
    void insertall(Shop ...shops);

    @Delete
    void deleteshop(Shop shop);
    @Query("SELECT * FROM Shop where ownerid like:user_id")
    List<Shop> getuser(int user_id);

    @Query("Select COUNT(*) FROM Shop")
    int countshop();
}
