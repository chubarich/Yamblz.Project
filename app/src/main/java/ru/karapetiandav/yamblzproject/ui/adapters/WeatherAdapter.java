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
import ru.karapetiandav.yamblzproject.ui.entities.WeatherViewModel;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherViewModel> data = new ArrayList<>();

    private Context context;

    public WeatherAdapter(Context context) {
        this.context = context;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_day_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void updateData(List<WeatherViewModel> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.card_weather) CardView cardView;
        @BindView(R.id.date_textview) TextView dateTV;
        @BindView(R.id.temp_max_textview) TextView tempMaxTV;
        @BindView(R.id.icon_imageview) ImageView iconIV;
        @BindView(R.id.wind_value_textview) TextView windTV;
        @BindView(R.id.pressure_value_textview) TextView pressureTV;
        @BindView(R.id.humidity_value_textview) TextView humidityTV;
        @BindView(R.id.morning_temp_textview) TextView morningTV;
        @BindView(R.id.day_temp_textview) TextView dayTV;
        @BindView(R.id.evening_temp_textview) TextView eveningTV;
        @BindView(R.id.night_temp_textview) TextView nightTV;

        public WeatherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(WeatherViewModel weather) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, weather.getColorId()));
            dateTV.setText(weather.getDayOfWeek());
            tempMaxTV.setText(weather.getMaxTemp());
            iconIV.setImageResource(weather.getIconId());
            windTV.setText(weather.getWind());
            pressureTV.setText(weather.getPressure());
            humidityTV.setText(weather.getHumidity());
            morningTV.setText(weather.getMorningTemp());
            dayTV.setText(weather.getDayTemp());
            eveningTV.setText(weather.getEveningTemp());
            nightTV.setText(weather.getNightTemp());
        }
    }
}
