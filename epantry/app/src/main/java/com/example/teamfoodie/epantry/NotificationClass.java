package com.example.teamfoodie.epantry;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.example.teamfoodie.R;
import com.example.teamfoodie.models.PantryIngredient;

import java.util.ArrayList;
import java.util.List;


public class NotificationClass {

    private NotificationManager notificationManager;
    private List<Integer> thresholds;

    public void calculateThreshold(Context context, NotificationManager notify, int userID, PantryIngredient ingredient, List<Integer> thresholdArray) {
        this.thresholds = thresholdArray;
        List<String> ingredientsLow = new ArrayList<String>();


        if (thresholds != null) {
            double threshold = (thresholds.get(ingredient.getIntegerFoodGroup()) / 100) * ingredient.getTotalQuantity();
            if (ingredient.getCurrentQuantity() < threshold) {
                ingredientsLow.add(ingredient.getIngredientName());
            }
        } else {
            System.out.println("thresholdArray is empty");
        }

        if (!ingredientsLow.isEmpty()) {
            generateNotification(context, notify, ingredient.getIngredientName());
        }


    }


    public void generateNotification(Context context, NotificationManager notifiy, String name) {

        notificationManager = notifiy;
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";
        System.out.println("INSIDE THE GENERATE NOTIFICATION");

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_logo)
                .setTicker("e-Pantry Ingredients Low Stock")
                .setContentTitle("ePantry has detected you are low on:")
                .setContentText(name.toUpperCase())
                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText(lowStock.toString()));
                        .bigText("YOU ARE RUNNING LOW ON " + name));

        notificationManager.notify(/*notification id*/1, notificationBuilder.build());
    }
}