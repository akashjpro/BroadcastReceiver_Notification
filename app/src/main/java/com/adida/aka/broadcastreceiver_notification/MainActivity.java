package com.adida.aka.broadcastreceiver_notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnCreate, btnClose;
    int notificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addControls() {
        btnCreate = (Button) findViewById(R.id.buttonCreateNotification);
        btnClose  = (Button) findViewById(R.id.buttonCloseNotificattion);
    }

    private void addEvents() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();
            }
            
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeNotification();
            }
        });
    }

    private void closeNotification() {
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.cancel(notificationId);
    }

    private void createNotification() {
        android.support.v4.app.NotificationCompat.Builder mBuilder =
                 new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Có thông báo")
                .setContentText("Mời bạn nhấn để cập nhật version");

        Intent resultIntent = new Intent(this, UpdateActivity.class);

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);

//        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        mBuilder.setSound(uri);

        Uri newSound= Uri.parse("android.resource://"
                + getPackageName() + "/" + R.raw.adidaphat);
        mBuilder.setSound(newSound);


        notificationId = 113;

        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(notificationId, mBuilder.build());


    }
}
