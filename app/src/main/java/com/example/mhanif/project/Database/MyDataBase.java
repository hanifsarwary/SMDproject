package com.example.mhanif.project.Database;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by M.hanif on 4/19/2018.
 */
@Database(entities = {User.class,Item.class,Order.class,Shop.class},version = 1)
public abstract class MyDataBase extends RoomDatabase {


    public abstract UserDao userDao();

    public abstract ItemDao itemDao();

    public abstract OrderDao orderDao();

    public abstract ShopDao shopDao();

    public static MyDataBase INSTANCE;

    public static MyDataBase getAppDataBase(Context context){
        if(INSTANCE==null)
            INSTANCE= Room.databaseBuilder(context,MyDataBase.class,"GlobalMart").build();

        return INSTANCE;

    }



















    /**
     * Creates the open helper to access the database. Generated class already implements this
     * method.
     * Note that this method is called when the RoomDatabase is initialized.
     *
     * @param config The configuration of the Room database.
     * @return A new SupportSQLiteOpenHelper to be used while connecting to the database.
     */
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    /**
     * Called when the RoomDatabase is created.
     * <p>
     * This is already implemented by the generated code.
     *
     * @return Creates a new InvalidationTracker.
     */
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
