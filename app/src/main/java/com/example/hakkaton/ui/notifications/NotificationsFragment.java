package com.example.hakkaton.ui.notifications;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.hakkaton.Activity.ScanActivity;
import com.example.hakkaton.R;

import github.nisrulz.qreader.QREader;

public class NotificationsFragment extends Fragment {


    // User Interface
    private TextView text;

    // QREader
    private SurfaceView mySurfaceView;
    private QREader qrEader;
    private boolean hasCameraPermission = false;
    private static final String cameraPerm = Manifest.permission.CAMERA;

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        startActivity(new Intent(getActivity(), ScanActivity.class));

        return root;
    }

    }

