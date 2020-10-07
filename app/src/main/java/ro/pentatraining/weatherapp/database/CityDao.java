package ro.pentatraining.weatherapp.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ro.pentatraining.weatherapp.data.CityData;

@Dao
public interface CityDao {
    @Query("SELECT * FROM CITIES")
    List<CityData> getAll();

    @Query("SELECT * FROM cities WHERE city_name= :cityName")

    CityData findByName(String cityName);

    //    @Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(CityData... cities);

    @Update
    public void updateUsers(CityData... cities);

    @Delete
    void delete(CityData cities);

    @Query("DELETE FROM cities")
    void deleteAll();

    @Query("SELECT * FROM cities WHERE city_name= :cityName")
    public CityData[] loadAllUsersOlderThan(String cityName);



}
