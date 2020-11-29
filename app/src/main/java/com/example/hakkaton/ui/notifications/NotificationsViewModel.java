package com.example.hakkaton.ui.notifications;

import android.Manifest;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import github.nisrulz.qreader.QREader;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    // User Interface
    private TextView text;

    // QREader
    private SurfaceView mySurfaceView;
    private QREader qrEader;
    private boolean hasCameraPermission = false;
    private static final String cameraPerm = Manifest.permission.CAMERA;


    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }




    public LiveData<String> getText() {
        return mText;
    }
}