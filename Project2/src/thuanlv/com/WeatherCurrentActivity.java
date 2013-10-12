package thuanlv.com;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import thuanlv.com.data.Variables;
import thuanlv.com.jsonparsing.JSONParser;
import android.R.integer;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherCurrentActivity extends Activity {
	private String name;
	private String temp;
	private String icon;
	String speed;
	String description;
	String humidity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.current_layout);

		// getCurentWeather(Variables.latitude, Variables.longitude);
		getCurentWeather(21, 105);

		ImageView iv_current_icon = (ImageView) findViewById(R.id.iv_current_icon);
		iv_current_icon.setImageResource(R.drawable.current_02d);

		TextView tv_currnet_name = (TextView) findViewById(R.id.tv_current_name);
		tv_currnet_name.setText(name);

		TextView tv_currnet_temp = (TextView) findViewById(R.id.tv_current_temp);
		double t = Double.parseDouble(temp);
		t = (t - 32) / 1.8;
		tv_currnet_temp.setText(String.valueOf((int)t));

		TextView tv_currnet_speed = (TextView) findViewById(R.id.tv_current_speed);
		tv_currnet_speed.setText(speed + " speed");

		TextView tv_currnet_description = (TextView) findViewById(R.id.tv_current_description);
		tv_currnet_description.setText(description);

		TextView tv_currnet_humidity = (TextView) findViewById(R.id.tv_current_humidity);
		tv_currnet_humidity.setText(humidity + " %");
	}

	void getCurentWeather(double latitude, double longitude) {
		// url to make request
		String url = "http://api.openweathermap.org/data/2.5/weather?lat="
				+ latitude + "&lon=" + longitude;
		Log.d("log", url);

		// Creating JSON Parser instance
		JSONParser jParser = new JSONParser();

		// getting JSON string from URL
		JSONObject json = jParser.getJSONFromUrl(url);

		try {
			JSONArray w = json.getJSONArray(Variables.TAG_WEATHER);

			JSONObject weather = w.getJSONObject(0);

			description = weather.getString(Variables.TAG_WEATHER_DESCRIPTION);
			icon = weather.getString(Variables.TAG_WEATHER_ICON);

			JSONObject main = json.getJSONObject(Variables.TAG_MAIN);
			temp = main.getString(Variables.TAG_MAIN_TEMP);
			humidity = main.getString(Variables.TAG_MAIN_HUMIDITY);

			JSONObject wind = json.getJSONObject(Variables.TAG_WIND);
			speed = wind.getString(Variables.TAG_WIN_SPEED);

			name = json.getString(Variables.TAG_NAME);
			Log.d("thuanlog", "thuanlog " + name + url);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.d("thuanlog", "thuanlog loi o day");
			e.printStackTrace();
		}

	}

}
