package thuanlv.com;

import thuanlv.com.data.Variables;
import thuanlv.com.location.GPSTracker;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class WeatherActivity extends TabActivity {
	boolean isUpdate = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// new GetLocation().execute();
		getLocation();
		init_tabHosts();
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

	private int getLocation() {

		// GPSTracker class
		GPSTracker gps;

		// create class object
		gps = new GPSTracker(WeatherActivity.this);

		// check if GPS enabled
		if (gps.canGetLocation()) {

			Variables.latitude = gps.getLatitude();
			Variables.longitude = gps.getLongitude();
			Log.e("thuan", Variables.latitude + " " + Variables.longitude);
		} else {
			// can't get location
			// GPS or Network is not enabled
			// Ask user to enable GPS/network in settings
			gps.showSettingsAlert();
		}
		return 1;
	}

	// private class GetLocation extends AsyncTask<Void, Void, Void> {
	//
	// private ProgressDialog progress = null;
	//
	// @Override
	// protected Void doInBackground(Void... params) {
	// while (getLocation() == 0)
	// ;
	// return null;
	// }
	//
	// @Override
	// protected void onPreExecute() {
	// // TODO Auto-generated method stub
	// progress = ProgressDialog.show(WeatherActivity.this, "Cap nhat",
	// "Dang cho vi tri");
	// progress.setIcon(R.drawable.ic_launcher);
	//
	// super.onPreExecute();
	// }
	//
	// @Override
	// protected void onProgressUpdate(Void... values) {
	// // TODO Auto-generated method stub
	// // TODO Auto-generated method stub
	// // progress = ProgressDialog.show(WeatherActivity.this, "Cap nhat",
	// // "Dang cho vi tri");
	// // progress.setIcon(R.drawable.ic_launcher);
	// super.onProgressUpdate(values);
	// }
	//
	// @Override
	// protected void onPostExecute(Void result) {
	// // TODO Auto-generated method stub
	// // getLocation();
	// progress.dismiss();
	// Toast.makeText(
	// getApplicationContext(),
	// "Your Location is - \nLat: " + Variables.latitude
	// + "\nLong: " + Variables.longitude,
	// Toast.LENGTH_LONG).show();
	// init_tabHosts();
	// super.onPostExecute(result);
	// }
	// }

}
