package thuanlv.com.location;

import thuanlv.com.data.Variables;
import android.widget.Toast;

public class GPS {
	public static void getLocation() {
		// GPSTracker class
		GPSTracker gps;

		// create class object
		gps = new GPSTracker(null);

		// check if GPS enabled
		if (gps.canGetLocation()) {
			Variables.latitude = gps.getLatitude();
			Variables.longitude = gps.getLongitude();
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
	}
}
