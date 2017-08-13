package ru.karapetiandav.yamblzproject.data.job;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.annotation.NonNull;

import com.evernote.android.job.Job;
import com.evernote.android.job.JobRequest;

import java.util.concurrent.TimeUnit;

import ru.karapetiandav.yamblzproject.R;
import ru.karapetiandav.yamblzproject.data.network.NetworkHelper;
import ru.karapetiandav.yamblzproject.data.prefs.PreferenceHelper;


public class SyncWeatherJob extends Job {

    public static final String TAG = "Sync_Weather_Job";

    private PreferenceHelper preferenceHelper;
    private NetworkHelper networkHelper;
    private Resources resources;

    public SyncWeatherJob(PreferenceHelper preferenceHelper, NetworkHelper networkHelper,
                          Resources resources) {
        this.preferenceHelper = preferenceHelper;
        this.networkHelper = networkHelper;
        this.resources = resources;
    }

    public void schedulePeriodicJob(SharedPreferences sharedPreferences) {
        int minutes = Integer.parseInt(sharedPreferences.getString(
                resources.getString(R.string.pref_update_key),
                resources.getStringArray(R.array.pref_update_time_values)[1]));
        int jobId = new JobRequest.Builder(SyncWeatherJob.TAG)
                .setPeriodic(TimeUnit.MINUTES.toMillis(minutes), TimeUnit.MINUTES.toMillis(10))
                .setUpdateCurrent(true)
                .setRequiredNetworkType(JobRequest.NetworkType.CONNECTED)
                .setPersisted(true)
                .build()
                .schedule();
    }

    @NonNull
    @Override
    protected Result onRunJob(Params params) {
        //todo return
//        preferenceHelper.getCity()
//                .flatMap(city -> networkHelper.getCurrentWeather(city.getCityId()))
//                .map(WeatherDataModel::valueOf)
//                .doOnSuccess(preferenceHelper::saveWeather)
//                .subscribeOnCityChanges();
        return Result.SUCCESS;
    }
}