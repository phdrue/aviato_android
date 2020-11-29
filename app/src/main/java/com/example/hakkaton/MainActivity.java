package com.example.hakkaton;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hakkaton.Activity.ScanActivity;
import com.example.hakkaton.api.Retrofit;
import com.example.hakkaton.api.RetrofitClient;
import com.example.hakkaton.helper.SharedPrefManager;
import com.example.hakkaton.models.Data;
import com.example.hakkaton.ui.dashboard.DashboardFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public TextView position;

    private NotificationManager notificationManager;
    private static final int NOTIFY_ID = 1;
    private static final String CHANNEL_ID = "CHANNEL_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        position = findViewById(R.id.status_queue);
        notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setSelectedItemId(R.id.navigation_dashboard);


        SharedPrefManager s = SharedPrefManager.getInstance(MainActivity.this);


        new Thread(new Runnable() {
            public void run() {
                while(true) { //бесконечно крутим
                    try {
                        Thread.sleep(10000); //





                        SharedPrefManager s = SharedPrefManager.getInstance(MainActivity.this);
                        String auth = "Bearer" + " " + s.getToken();
                        //Snackbar.make(navView,auth, Snackbar.LENGTH_SHORT).show();
                        /*Toast.makeText(MainActivity.this,
                                auth, Toast.LENGTH_SHORT).show();*/
                        //s.saveStatId(1);

                       Call<Data> call = RetrofitClient.getInstance().getApi().getStat( s.getStatId(), auth);

                        call.enqueue(new Callback<Data>() {
                            @Override
                            public void onResponse(Call<Data> call, Response<Data> response) {
                                Data data = response.body();
                                if(!response.isSuccessful()) {
                                }
                                else { if(data.getMessage() != s.getMessage()){


                                    s.saveMessage(data.getMessage());

// подключаем FragmentManager
                                    FragmentManager fragmentManager = getSupportFragmentManager();

                                    // Получаем ссылку на фрагмент по ID
                                    DashboardFragment fragment = (DashboardFragment) fragmentManager
                                            .findFragmentById(R.id.navigation_dashboard);

// Сетим нужную информацию
                                    if (fragment != null)
                                        fragment.setText(data.getMessage());

                                      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        NotificationCompat.Builder notificationBuilder =
                                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                        .setAutoCancel(false)
                                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                                        .setWhen(System.currentTimeMillis())
                                        .setContentIntent(pendingIntent)
                                        .setContentTitle("Ваша очередь изменена!")
                                        .setContentText(data.getMessage()); //Вывод пуш уведомления об изменении очереди!!!
                        createChannelIfNeeded(notificationManager);
                        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());

                                }

                                }
                            }

                            @Override
                            public void onFailure(Call<Data> call, Throwable t) {

                            }

                        });

                        //Snackbar.make(navView,, Snackbar.LENGTH_SHORT).show(); воткнуть переменую
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }

    public static void  createChannelIfNeeded(NotificationManager manager){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(notificationChannel);
        }
    }

}
