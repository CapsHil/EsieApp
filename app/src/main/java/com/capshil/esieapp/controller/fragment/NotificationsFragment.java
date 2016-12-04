package com.capshil.esieapp.controller.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.capshil.esieapp.R;
import com.capshil.esieapp.controller.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationsFragment extends Fragment {

    private List<String> mInboxList;

    public NotificationsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_notifications, container, false);
        final Button button = (Button) v.findViewById(R.id.button_simple_notifications);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotification("Title", "Content", "Ticker");
            }
        });
        final Button button2 = (Button) v.findViewById(R.id.button_toast_notifications);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "This is a Toast but please, don't eat me...", Toast.LENGTH_LONG).show();
            }
        });
        final Button button3 = (Button) v.findViewById(R.id.button_both_notifications);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "This is a Toast but please, don't eat me...", Toast.LENGTH_LONG).show();
                showNotification("Title", "Content", "Ticker");
            }
        });
        final Button button4 = (Button) v.findViewById(R.id.button_dialog_notifications);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("How are you ?")
                        .setMessage("Were you happy to discuss with me ?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "Thanks, I've enjoy our meeting", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "Sorry for that", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        return v;
    }

    private PendingIntent pendingIntentForNotification() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 1, intent, 0);
        return pendingIntent;
    }

    private void showNotification(String title, String content, String ticker) {
        Notification notification = new NotificationCompat.Builder(getActivity())
                .setContentTitle(title)
                .setContentText(content)
                .setTicker(ticker)
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_launcher))
                .setContentIntent(pendingIntentForNotification())
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager)
                getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(1, notification);
    }
}
