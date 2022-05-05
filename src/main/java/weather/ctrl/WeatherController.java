package weather.ctrl;

import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.DailyDataPoint;
import tk.plogitech.darksky.forecast.model.Forecast;

// https://github.com/lizardiam/prog2-exercise3

public class WeatherController {

    private final String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		Forecast data = getData(location);

        try {
            // highest temperature
            System.out.println("Highest temperature: " + getHighestTemperature(data));

            // average temperature
            System.out.println("Average temperature: " + getAverageTemperature(data));

            // count the daily values
            System.out.println("Daily values count: " + getDailyValues(data));
        } catch (Exception e) {
            e.printStackTrace();
        }

		// implement a Comparator for the Windspeed
        // Schreiben Sie eigenen Comparator um den Wind auf stündlicher Basis zu vergleichen
		
	}

    public double getHighestTemperature(Forecast data){
        return data.getDaily().getData().stream().mapToDouble(DailyDataPoint::getTemperatureHigh).max().orElseThrow();
    }

    public double getAverageTemperature(Forecast data){
        return data.getDaily().getData().stream().mapToDouble(DailyDataPoint::getTemperatureHigh).average().orElseThrow();
    }

    public long getDailyValues(Forecast data){
        return data.getDaily().getData().size();
    }

    /*public double compareWind(Forecast data){
        return data.getHourly().getData().stream().mapToDouble(DailyDataPoint::getTemperatureHigh).
    } */
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        Forecast forecast = new Forecast();
        try {
            forecast = client.forecast(request);
        } catch (ForecastException e) {
            e.printStackTrace();
        }

        return forecast;
    }
}
