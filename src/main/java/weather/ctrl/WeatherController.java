package weather.ctrl;


import tk.plogitech.darksky.api.jackson.DarkSkyJacksonClient;
import tk.plogitech.darksky.forecast.*;
import tk.plogitech.darksky.forecast.model.Forecast;

public class WeatherController {

    private String apiKey = "ab5c55091bfde0864c41b337f1c66af5";
    

    public void process(GeoCoordinates location) {
        System.out.println("process "+location); //$NON-NLS-1$
		Forecast data = getData(location);
		
		//TODO implement Error handling 
		
		//TODO implement methods for
		// highest temperature 
		// average temperature 
		// count the daily values
		
		// implement a Comparator for the Windspeed 
		
	}
    
    
    public Forecast getData(GeoCoordinates location) {
		ForecastRequest request = new ForecastRequestBuilder()
                .key(new APIKey(apiKey))
                .location(location)
                .build();

        DarkSkyJacksonClient client = new DarkSkyJacksonClient();

        try {
            Forecast forecast = client.forecast(request);
            System.out.println(forecast.getDaily());
        } catch (ForecastException e) {
            e.printStackTrace();
        }


        return null;
    }
}
