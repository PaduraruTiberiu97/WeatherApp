package ro.pentatraining.weatherapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import ro.pentatraining.weatherapp.RoomApp;
import ro.pentatraining.weatherapp.data.CityData;

@Database(entities = {CityData.class}, version = AppDatabase.DB_VERSION)
public abstract class AppDatabase extends RoomDatabase {
    public static final int DB_VERSION = 1;
    private static AppDatabase instance;
    private static final String DATABASE_NAME = "myDatabase";

    public abstract CityDao cityDao();

    public static AppDatabase getInstance() {
        if (instance == null) {
            instance = Room.databaseBuilder(RoomApp.getAppContext(), AppDatabase.class, DATABASE_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'City' ('id' INTEGER," +
                    "" + "'name' TEXT," +
                    "" + " 'desccription' TEXT," +
                    "" + "'temperature' TEXT,PRIMARY KEY('id'))");

        }
    };
}

