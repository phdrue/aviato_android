package com.example.hakkaton.Activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hakkaton.MainActivity;
import com.example.hakkaton.R;
import com.example.hakkaton.RPResultListener;
import com.example.hakkaton.RuntimePermissionUtil;
import com.example.hakkaton.api.Retrofit;
import com.example.hakkaton.api.RetrofitClient;
import com.example.hakkaton.helper.SharedPrefManager;
import com.example.hakkaton.models.Data;
import com.example.hakkaton.ui.home.HomeFragment;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanActivity extends AppCompatActivity {

   // User Interface
    public TextView text;

    // QREader
    private SurfaceView mySurfaceView;
    private QREader qrEader;
    private boolean hasCameraPermission = false;
    private static final String cameraPerm = Manifest.permission.CAMERA;
    public TextView textAccess;
    public Button restartButton;
    public Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scaner);
        text = findViewById(R.id.code_info);
        textAccess = findViewById(R.id.textAccess);


        hasCameraPermission = RuntimePermissionUtil.checkPermissonGranted(this, cameraPerm);

        restartButton = findViewById(R.id.btn_restart_activity);
        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScanActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        // Setup SurfaceView
        // -----------------
        mySurfaceView = findViewById(R.id.camera_view);

        if (hasCameraPermission) {
            // Setup QREader
            setupQREader();
            mySurfaceView.setVisibility(View.VISIBLE);
        } else {
            RuntimePermissionUtil.requestPermission(
                    ScanActivity.this,
                    cameraPerm,
                    100
            );
        }
    }

    void setupQREader() {
        // Init QREader
        // ------------
        qrEader = new QREader.Builder(this, mySurfaceView, new QRDataListener() {
            @Override
            public void onDetected(final String data) {
                if(counter == 0 )
                {
                    counter++;
                Log.d("QREader", "Value : " + data);

                text.post(new Runnable() {
                    @Override
                    public void run() {
                        text.setText(data);
                       // onPause();

                       /*Intent intent = new Intent(ScanActivity.this, MainActivity.class);
                        startActivity(intent);*/
                        //Toast.makeText(ScanActivity.this, text.getText(), Toast.LENGTH_LONG).show();

                        ///////1!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        //Integer ID = 1;
                        String[] IDs = data.split("/");
                        Integer ID = Integer.parseInt(IDs[IDs.length - 1]);
                        ///////////////////////////////////////
                        SharedPrefManager s = SharedPrefManager.getInstance(ScanActivity.this);
                        String auth = "Bearer" + " " + s.getToken();
                        /*Toast.makeText(ScanActivity.this,
                                auth, Toast.LENGTH_SHORT).show();*/

                        Call<Data> calll = RetrofitClient.getInstance().getApi().getQueue( ID, auth);

                        calll.enqueue(new Callback<Data>() {
                            @Override
                            public void onResponse(Call<Data> call, Response<Data> response) {
                                Data data = response.body();
                                if(!response.isSuccessful()){
                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Ошибка!", Toast.LENGTH_SHORT);
                                    toast.show();}
                                else {

                                    Toast toast = Toast.makeText(getApplicationContext(),
                                            "Норм", Toast.LENGTH_SHORT);
                                    toast.show();
                                    s.saveMessage(data.getMessage());
                                    s.saveStatId(ID);

                    /*SharedPrefManager.getInstance(LoginActivity.this)
                            .saveToken(data.getResponse());*/
                                    Intent intent = new Intent(ScanActivity.this, MainActivity.class);
                                   ScanActivity.this.finish();
                                    startActivity(intent);

                                        Thread.interrupted();

                                    Toast.makeText(ScanActivity.this,
                                            s.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }


                            @Override
                            public void onFailure(Call<Data> call, Throwable t) {

                            }

                        });

                    }
                });

            }



            }
        }).facing(QREader.BACK_CAM)
                .enableAutofocus(true)
                .height(mySurfaceView.getHeight())
                .width(mySurfaceView.getWidth())
                .build();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (hasCameraPermission) {

            // Cleanup in onPause()
            // --------------------
            qrEader.releaseAndCleanup();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (hasCameraPermission) {

            // Init and Start with SurfaceView
            // -------------------------------
            qrEader.initAndStart(mySurfaceView);
        }
    }

    // Method that handles the result of the permission request made at the beginning of the application
    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull final String[] permissions,
            @NonNull final int[] grantResults
    ) {
        if (requestCode == 100) {
            RuntimePermissionUtil.onRequestPermissionsResult(grantResults, new RPResultListener() {
                @Override
                public void onPermissionGranted() {
                    /*if ( RuntimePermissionUtil.checkPermissonGranted(ScanActivity.this, cameraPerm)) {
                        Intent intent = new Intent(ScanActivity.this, ScanActivity.class);
                        startActivity(intent);
                    }*/
                }

                @Override
                public void onPermissionDenied() {
                    // Do nothing
                    textAccess.setVisibility(View.VISIBLE);
                    restartButton.setVisibility(View.VISIBLE);
                }
            });
        }
    }





}
