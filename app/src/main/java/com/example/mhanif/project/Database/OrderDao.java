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
public interface OrderDao {

    @Query("SELECT * FROM `Order`")
    List<Order> getall();

    @Insert
    void insertall(Order ...users);

    @Delete
    void deleteuser(Order user);
    @Query("SELECT * FROM `Order` where buyer_id like:user_id")
    List<Order> getuser(int user_id);

    @Query("Select COUNT(*) FROM `Order`")
    int countOrder();
}
