package com.elmansouri.ccna.customnotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AdilBroadcaster extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Boutton Play Cliqué",Toast.LENGTH_LONG).show();
    }
}
