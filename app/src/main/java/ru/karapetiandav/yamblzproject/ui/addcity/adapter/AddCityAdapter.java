package ru.karapetiandav.yamblzproject.ui.addcity.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.addcity.model.CityViewModel;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.CitiesViewHolder> {

    private List<CityViewModel> cities = new ArrayList<>();
    private OnCityClickListener onCityClickListener;

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_row, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        CityViewModel viewModel = cities.get(position);
        //todo не разделять запятыми в маппере
        holder.cityTV.setText(viewModel.getCityName() + ", " + viewModel.getCountry());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void changeDataSet(List<CityViewModel> data) {
        cities = data;
        notifyDataSetChanged();
    }

    public void setOnCityClickListener(OnCityClickListener onCityClickListener) {
        this.onCityClickListener = onCityClickListener;
    }

    class CitiesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_row_textview) TextView cityTV;

        CitiesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view ->
                    onCityClickListener.onCityClick(cities.get(getAdapterPosition())));
        }
    }

    public interface OnCityClickListener {
        void onCityClick(@NonNull CityViewModel cityViewModel);
    }
}
