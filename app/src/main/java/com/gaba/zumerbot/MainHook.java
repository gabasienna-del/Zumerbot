package com.gaba.zumerbot;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainHook extends NotificationListenerService {

    private final Set<String> allowedGroups = new HashSet<>(Arrays.asList(
            "Заказы Тараз", "Алматы Работа", "Доставка 08-02",
            "Курьеры Тараз", "Заказчики Алматы", "Работа Курьеры",
            "Express Тараз", "Taxi Заказы", "Грузы Алматы", "Подработка"
    ));

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null || sbn.getNotification() == null) return;

        Notification notification = sbn.getNotification();
        CharSequence title = notification.extras.getCharSequence(Notification.EXTRA_TITLE);
        CharSequence text = notification.extras.getCharSequence(Notification.EXTRA_TEXT);

        if (title != null && allowedGroups.contains(title.toString()) && text != null && text.toString().contains("заказ")) {
            sendAutoReply(title.toString(), "Да, я беру");
        }
    }

    private void sendAutoReply(String group, String message) {
        System.out.println("Автоответ для группы: " + group + " -> " + message);
    }
}
