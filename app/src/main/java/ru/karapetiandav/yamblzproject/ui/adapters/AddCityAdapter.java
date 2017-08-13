package ru.karapetiandav.yamblzproject.ui.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.ui.entities.CityViewModel;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.CitiesViewHolder> {

    private List<CityViewModel> cities = new ArrayList<>();
    private OnCityClickListener onCityClickListener;
    private Context context;

    public AddCityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public CitiesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_row, parent, false);
        return new CitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CitiesViewHolder holder, int position) {
        CityViewModel viewModel = cities.get(position);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        String cityName = viewModel.getCityName();
        SpannableString redSpannable= new SpannableString(cityName);
        redSpannable.setSpan(new ForegroundColorSpan(
                ContextCompat.getColor(context, R.color.red)), 0, 1, 0);
        builder.append(redSpannable);

        holder.cityTV.setText(builder, TextView.BufferType.SPANNABLE);

//        holder.cityTV.setText(viewModel.getCityName());
        holder.countryTV.setText(viewModel.getCountry());
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
        @BindView(R.id.country_row_textview) TextView countryTV;

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
