package ro.pentatraining.weatherapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ro.pentatraining.weatherapp.R;
import ro.pentatraining.weatherapp.adapters.MyPlacesAdapter;
import ro.pentatraining.weatherapp.holders.Prognosis;
//import java.util.ArrayList;

public class MyPlacesActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Prognosis> prognosisList;
    //private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.myplaces_activity);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String fileContent = readFromTxt();
        prognosisList = convertJsonToObject(fileContent);

        adapter = new MyPlacesAdapter(prognosisList, this);
        recyclerView.setAdapter(adapter);
    }

    private String readFromTxt() {
        StringBuilder stringBuilder = new StringBuilder();
        InputStream json;
        try {
            json = getAssets().open("dataSet.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(json, StandardCharsets.UTF_8));
            String str;

            while ((str = bufferedReader.readLine()) != null) {
                stringBuilder.append(str);
            }

            bufferedReader.close();
        } catch (IOException e) {
            // Log.e(TAG, e.getMessage());
        }
        //  Log.d(TAG, "stringBuilder = " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    private List<Prognosis> convertJsonToObject(String dataSet) {
        Gson gson = new Gson();

        List<Prognosis> prognosis;
        prognosis = gson.fromJson(dataSet, new TypeToken<List<Prognosis>>() {
        }.getType());

        //Log.d(TAG, "prognosisList= " + prognosisList.toString());

        return prognosis;
    }

    private void sortRecyclerViewByAlphabeticalOrder() {
        Collections.sort(prognosisList, new Comparator<Prognosis>() {
            @Override
            public int compare(Prognosis o1, Prognosis o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void sortRecyclerViewByTemperature() {
        Collections.sort(prognosisList, new Comparator<Prognosis>() {
            @Override
            public int compare(Prognosis o1, Prognosis o2) {
                return o1.getTemp().compareTo(o2.getTemp());
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_alphabetical_sort:
                sortRecyclerViewByAlphabeticalOrder();
                return true;

            case R.id.btn_temperature_sort:
                sortRecyclerViewByTemperature();
                return true;
            default:
                return false;
        }
    }
}

