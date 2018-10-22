package com.example.teamfoodie.epantry;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import com.example.teamfoodie.R;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;

public class NotificationClass extends AppCompatActivity{

//    List<PantryIngredient> pantryList = new ArrayList<>();
    List<PantryIngredient> lowStock = new ArrayList<>();

    public List<PantryIngredient> calculateLowStock(List<PantryIngredient> list) {

        for (PantryIngredient ing : list) {
            if(ing.getCurrentQuantity() <= ing.getTotalQuantity())
                System.out.println("ING CURRENT QUAN:"+ing.getCurrentQuantity()+"ING TOTAL QUAN:"+ing.getTotalQuantity());
            lowStock.add(ing);
        }

        return lowStock;
    }


    public void generateNotification(List<PantryIngredient> pantryList) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_logo)
                .setTicker("e-Pantry Ingredients Low Stock")
                .setContentTitle("Low-Stock Ingredients:")
                .setContentText("" + pantryList)
                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(lowStock.toString()));
                        .bigText("HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE " +
                                "HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE " +
                                "HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE " +
                                "HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE HELLO EVERYONE"));
        //.setContentInfo("Info");

        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
    }
}