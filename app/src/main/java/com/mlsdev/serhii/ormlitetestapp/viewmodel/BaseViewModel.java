package com.mlsdev.serhii.ormlitetestapp.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.mlsdev.serhii.ormlitetestapp.data.DatabaseHelper;

/**
 * Created by serhii on 19.04.16.
 */
public abstract class BaseViewModel extends BaseObservable implements ViewModel {
    public final static String EXTRA_NOTE = "extra_note";
    protected Context context;
    protected DatabaseHelper databaseHelper;

    public BaseViewModel(Context context) {
        this.context = context;
    }

    protected DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null)
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

        return databaseHelper;
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {
    }
}
