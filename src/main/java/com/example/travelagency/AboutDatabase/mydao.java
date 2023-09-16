package com.example.travelagency.AboutDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface mydao {
    @Insert
    public void addAgency(Agency agency);
    @Insert
    public void addTrip(Trip trip);
    @Insert
    public void addPackage(Package packag);

    @Query("select * from Agency")
    public List<Agency> getAgency();
    @Query("select * from Trip")
    public List<Trip> getTrip();
    @Query("select * from Package")
    public List<Package> getPackage();

    //Q1
    @Query("select p.id  "+
            "from Package p,trip t "+
                "where t.city like 'L%' and p.tid=t.id")
    public List<Integer> getQuery1();

    //Q2
    @Query("select p.id " +
                "from Package p,agency g"+
                    " where g.name='Ryanair' and p.aid=g.id and p.price=350 "+
            "UNION "+
                "select p.id "+
                    "from Package p,agency g "+
                        " where g.name='Aegean' and p.aid=g.id and p.price=550 ")
    public List<Integer> getQuery2();

    //Q3
    @Query("select distinct p.id "+
                "from Package p,agency g "+
                    "where g.name like '_______%' and p.aid=g.id "+
                       "and p.id in( " +
                            "select distinct p.id "+
                                "from Package p,trip t "+
                                    "where t.country='United Kingdom' and p.tid=t.id)")
    public List<Integer> getQuery3();


    @Delete
    public void deleteAgency(Agency agency);
    @Delete
    public void deleteTrip(Trip trip);
    @Delete
    public void deletePackage(Package packag);

    @Update
    public void updateAgency(Agency agency);
    @Update
    public void updateTrip(Trip trip);
    @Update
    public void updatePackage(Package packag);

}
