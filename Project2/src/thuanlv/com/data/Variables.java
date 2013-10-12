package thuanlv.com.data;

public class Variables {
	public static double latitude;
	public static double longitude;

	// url to make request
	public static String url = "http://api.openweathermap.org/data/2.5/weather?lat=35&lon=139";

	// JSON Node names
	public static final String TAG_WEATHER = "weather";
	public static final String TAG_WEATHER_DESCRIPTION = "description";
	public static final String TAG_WEATHER_ICON = "icon";
	public static final String TAG_MAIN = "main";
	public static final String TAG_MAIN_TEMP = "temp";
	public static final String TAG_MAIN_HUMIDITY = "humidity";
	public static final String TAG_WIND = "wind";
	public static final String TAG_NAME = "name";
	public static final String TAG_WIN_SPEED = "speed";
}
