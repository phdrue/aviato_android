package com.example.hakkaton.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.hakkaton.Activity.LoginActivity;
import com.example.hakkaton.Activity.ScanActivity;
import com.example.hakkaton.MainActivity;
import com.example.hakkaton.R;
import com.example.hakkaton.helper.SharedPrefManager;

import org.w3c.dom.Text;

public class DashboardFragment extends Fragment {

    public TextView position;
    public TextView email;
    public TextView full_name;
public TextView snils;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);


        SharedPrefManager s = SharedPrefManager.getInstance(getContext());
        position = root.findViewById(R.id.status_queue);
        email = root.findViewById(R.id.EMAIL);
        email.setText(s.getUser_Email());
        position.setText(s.getMessage());
        full_name = root.findViewById(R.id.FIO);
        full_name.setText(s.getFirst_name() + " " + s.getSecond_name() + " " + s.getLast_name());
        snils = root.findViewById(R.id.SNILS);
        snils.setText(s.get_snils());

        return root;

    }


    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.status_queue);
        view.setText(item);
    }




}
