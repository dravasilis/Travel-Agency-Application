package com.example.travelagency.AboutDatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Agency.class,Trip.class,Package.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract mydao mydaotemp();



}
