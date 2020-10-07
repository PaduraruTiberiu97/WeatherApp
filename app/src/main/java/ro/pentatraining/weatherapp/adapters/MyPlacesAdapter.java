package ro.pentatraining.weatherapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ro.pentatraining.weatherapp.R;
import ro.pentatraining.weatherapp.holders.Prognosis;

public class MyPlacesAdapter extends RecyclerView.Adapter<MyPlacesAdapter.ViewHolder> {
    List<Prognosis> prognosisList;
    Context context;

    public MyPlacesAdapter(List<Prognosis> prognosis, Context context) {
        this.prognosisList = prognosis;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myplaces_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Prognosis prognisis = prognosisList.get(position);
        holder.txtCity.setText(prognisis.getName());
        holder.txtWeatherDescription.setText(prognisis.getDescription());
        holder.txtTemp.setText(prognisis.getTemp());
        holder.txtMaxTemp.setText(prognisis.getMaxTemp());
        holder.txtMinTemp.setText(prognisis.getMinTemp());

    }

    @Override
    public int getItemCount() {
        return prognosisList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCity;
        private TextView txtTemp;
        private TextView txtWeatherDescription;
        private TextView txtMaxTemp;
        private TextView txtMinTemp;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.txtCity = itemView.findViewById(R.id.txt_city);
            this.txtTemp = itemView.findViewById(R.id.txt_temp);
            this.txtWeatherDescription = itemView.findViewById(R.id.txt_weather_description);
            this.txtMaxTemp = itemView.findViewById(R.id.txt_max_temp);
            this.txtMinTemp = itemView.findViewById(R.id.txt_min_temp);
        }
    }
}

