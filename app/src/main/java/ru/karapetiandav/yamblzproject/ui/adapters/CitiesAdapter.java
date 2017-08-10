package ru.karapetiandav.yamblzproject.ui.adapters;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.entities.CityWeatherViewModel;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.CitiesViewHolder> {

    private OnCityWeatherClickListener onCityWeatherClickListener;
    private List<CityWeatherViewModel> data = new ArrayList<>();
    private Context context;

    public CitiesAdapter(Context context) {
        this.context = context;
        setHasStableIds(true);
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_city, parent,false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        CityWeatherViewModel cityWeather = data.get(position);
        holder.cityIconIV.setImageResource(cityWeather.getIconId());
        holder.cityNameTV.setText(cityWeather.getCityViewModel().getCityName());
        holder.countryTV.setText(cityWeather.getCityViewModel().getCountry());
        holder.tempTV.setText(cityWeather.getTemp());
        holder.cardView.setCardBackgroundColor(ContextCompat.getColor(context, cityWeather.getColor()));

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<CityWeatherViewModel> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void addCity(CityWeatherViewModel city) {
        if (!data.contains(city)) {
            data.add(city);
            notifyItemInserted(data.size() - 1);
        }
    }

    public void setOnCityWeatherClickListener(
            OnCityWeatherClickListener onCityWeatherClickListener) {
        this.onCityWeatherClickListener = onCityWeatherClickListener;
    }

    class CitiesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_city) CardView cardView;
        @BindView(R.id.city_name_textview) TextView cityNameTV;
        @BindView(R.id.country_textview) TextView countryTV;
        @BindView(R.id.city_temp_textview) TextView tempTV;
        @BindView(R.id.city_icon_imageview)  ImageView cityIconIV;

        public CitiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> onCityWeatherClickListener
                    .onCityWeatherClick(data.get(getAdapterPosition())));
        }
    }

    public interface OnCityWeatherClickListener {
        void onCityWeatherClick(CityWeatherViewModel item);
    }
}
