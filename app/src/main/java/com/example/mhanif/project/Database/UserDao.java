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
public interface  UserDao {

    @Query("SELECT * FROM User")
    List<User>getall();

    @Insert
    void insertall(User ...users);

    @Delete
    void deleteuser(User user);
    @Query("SELECT * FROM User where Email like:user_id")
    User getuser(String user_id);

    @Query("Select COUNT(*) FROM User")
    int countuser();
}
