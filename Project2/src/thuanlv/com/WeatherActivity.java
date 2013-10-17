package thuanlv.com;
// commit 1
// commit 2
import thuanlv.com.data.Variables;
import thuanlv.com.location.GPSTracker;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class WeatherActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		init_tabHosts();
		getLocation();
	}

	void init_tabHosts() {
		TabHost tabHost = getTabHost();

		// Tab for Current
		TabSpec current = tabHost.newTabSpec("Current");
		current.setIndicator("Current",
				getResources().getDrawable(R.drawable.ic_launcher));
		Intent photosIntent = new Intent(this, WeatherCurrentActivity.class);
		current.setContent(photosIntent);

		// Tab for Forecast
		TabSpec forecast = tabHost.newTabSpec("Forecast");
		forecast.setIndicator("Forecast",
				getResources().getDrawable(R.drawable.ic_launcher));
		Intent songsIntent = new Intent(this, WeatherForecastActivity.class);
		forecast.setContent(songsIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(current); // Adding current tab
		tabHost.addTab(forecast); // Adding forecast tab
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	void getLocation() {
		// GPSTracker class
		GPSTracker gps;

		// create class object
		gps = new GPSTracker(WeatherActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			Variables.latitude = gps.getLatitude();
			Variables.longitude = gps.getLongitude();

			// \n is for new line
			Toast.makeText(
					getApplicationContext(),
					"Your Location is - \nLat: " + Variables.latitude
							+ "\nLong: " + Variables.longitude,
					Toast.LENGTH_LONG).show();
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
	}

}
