package com.geofencedattendance

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofenceStatusCodes
import com.google.android.gms.location.GeofencingEvent

class GeofenceBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val geofencingEvent = intent?.let { GeofencingEvent.fromIntent(it) }
        if(geofencingEvent == null){
            return;
        }
        if (geofencingEvent.hasError()) {
            val errorMessage = GeofenceStatusCodes
                .getStatusCodeString(geofencingEvent.errorCode)
            return
        }
        // Get the transition type.
        val geofenceTransition = geofencingEvent.geofenceTransition
        if(geofenceTransition == Geofence.GEOFENCE_TRANSITION_DWELL){
            return // not interested init.
        }

        val notificationManager  = RNNotificationManager(context!!);
        notificationManager.createChannel()
        notificationManager.send(geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER);
    }
}
