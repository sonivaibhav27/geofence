import android.content.Context
import com.facebook.react.bridge.ReactApplicationContext
import com.geofencedattendance.GeofenceDto
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.GeofencingRequest
import com.google.android.gms.location.LocationServices
import java.util.UUID

class GeofencedModule(context: ReactApplicationContext) {
    lateinit var geoFencingClient: GeofencingClient
    val _context: ReactApplicationContext = context;
    var geoFencedList : MutableList<Geofence> = mutableListOf()
    var geofenceListToAdd = mutableListOf<GeofenceDto>()
    val sharedPref = context.getSharedPreferences("locationSharedPref", Context.MODE_PRIVATE)
    fun initialize(){
        val isInitialized = sharedPref.getBoolean("initialized",false);
        if(isInitialized){
            //initialized geofencing once only.
            return;
        }

        geofenceListToAdd.add(GeofenceDto(11.1015774, 77.3873048, 100))
        geofenceListToAdd.add(GeofenceDto( 11.1120701, 77.2746059, 70))
        geofenceListToAdd.add(GeofenceDto( 11.1078938, 77.32728, 20))
        geofenceListToAdd.add(GeofenceDto(11.0963805, 77.3750278, 20))

        geoFencingClient = LocationServices.getGeofencingClient(_context);
        subscribeToLocation()
    }
    fun subscribeToLocation(){
        val requestId = UUID.randomUUID().toString();
        geofenceListToAdd.forEach { geoFence -> {

        } }
      val geoFencedData =   Geofence.Builder()
            .setRequestId(requestId)
            .setCircularRegion(geofenceListToAdd[0].latitude,geofenceListToAdd[0].longitude,geofenceListToAdd[0].radius.toFloat())
          .setCircularRegion(geofenceListToAdd[0].latitude,geofenceListToAdd[0].longitude,geofenceListToAdd[0].radius.toFloat())
          .setCircularRegion(geofenceListToAdd[0].latitude,geofenceListToAdd[0].longitude,geofenceListToAdd[0].radius.toFloat())
          .setCircularRegion(geofenceListToAdd[0].latitude,geofenceListToAdd[0].longitude,geofenceListToAdd[0].radius.toFloat())
          .build()
       geoFencedList.add(geoFencedData);
        sharedPref.edit().putString("Location1",requestId)
            .putBoolean("initialized",true)
            .apply();
    }

    private fun getGeofencingRequest(): GeofencingRequest {
        return GeofencingRequest.Builder().apply {
            setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
            addGeofences(geoFencedList)
        }.build()
    }
}