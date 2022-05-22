package weather.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;
import weather.ctrl.WeatherController;
import weather.download.ParallelDownloader;
import weather.download.SequentialDownloader;

public class UserInterface {

    private final WeatherController ctrl = new WeatherController();
    private final SequentialDownloader sd = new SequentialDownloader();
    private final ParallelDownloader pd = new ParallelDownloader();

    public void getWeatherForVienna() {
        Longitude longitudeVienna = new Longitude(48.208704);
        Latitude latitudeVienna = new Latitude(16.371234);
        GeoCoordinates vienna = new GeoCoordinates(longitudeVienna, latitudeVienna);

        ctrl.process(vienna);

    }

    public void getWeatherForCologne() {
        Longitude longitudeCologne = new Longitude(50.935173);
        Latitude latitudeCologne = new Latitude(6.953101);
        GeoCoordinates cologne = new GeoCoordinates(longitudeCologne, latitudeCologne);
        ctrl.process(cologne);

    }

    public void getWeatherForFlorence() {
        Longitude longitudeFlorence = new Longitude(43.769562);
        Latitude latitudeFlorence = new Latitude(11.255814);
        GeoCoordinates florence = new GeoCoordinates(longitudeFlorence, latitudeFlorence);
        ctrl.process(florence);

    }

    public void getWeatherByCoordinates() {
        Longitude longitude = new Longitude(readDouble(-180, 180));
        Latitude latitude = new Latitude(readDouble(-90, 90));
        GeoCoordinates location = new GeoCoordinates(longitude, latitude);
        ctrl.process(location);
    }

    public void parallelDownload() throws ForecastException {
        List<GeoCoordinates> geoCoordinatesList = new ArrayList<>();
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(16.371234), new Latitude(48.208704))); //Wien
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(4.3676), new Latitude(50.8371))); // Brüssel
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(-3.7033), new Latitude(40.4167))); // Madrid
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(10.7387), new Latitude(59.9138))); // Oslo
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(18.0645), new Latitude(59.3328))); //Stockholm
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(26.0979), new Latitude(44.4479))); //Bukarest
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(17.1547), new Latitude(48.2116))); //Bratislava
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(4.8910), new Latitude(52.3738))); //Amsterdam
        sd.process(geoCoordinatesList);
    }

    public void sequentialDownload() throws ForecastException {
        List<GeoCoordinates> geoCoordinatesList = new ArrayList<>();
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(16.371234), new Latitude(48.208704))); //Wien
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(4.3676), new Latitude(50.8371))); // Brüssel
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(-3.7033), new Latitude(40.4167))); // Madrid
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(10.7387), new Latitude(59.9138))); // Oslo
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(18.0645), new Latitude(59.3328))); //Stockholm
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(26.0979), new Latitude(44.4479))); //Bukarest
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(17.1547), new Latitude(48.2116))); //Bratislava
        geoCoordinatesList.add(new GeoCoordinates(new Longitude(4.8910), new Latitude(52.3738))); //Amsterdam
        pd.process(geoCoordinatesList);
    }

    public void start() {
        Menu<Runnable> menu = new Menu<>("Weather Infos");
        menu.setTitel("Wählen Sie eine Stadt aus:");
        menu.insert("a", "Wien", this::getWeatherForVienna);
        menu.insert("b", "Koeln", this::getWeatherForCologne);
        menu.insert("c", "Florenz", this::getWeatherForFlorence);
        menu.insert("d", "City via Coordinates:", this::getWeatherByCoordinates);
        menu.insert("e", "Download Weather Parallel", () -> {
            try {
                parallelDownload();
            } catch (ForecastException e) {
                e.printStackTrace();
            }
        });
        menu.insert("f", "Download Weather Sequential", () -> {
            try {
                sequentialDownload();
            } catch (ForecastException e) {
                e.printStackTrace();
            }
        });
        menu.insert("q", "Quit", null);
        Runnable choice;
        while ((choice = menu.exec()) != null) {
            choice.run();
        }
        System.out.println("Program finished");
    }


    protected String readLine() {
        String value = "\0";
        BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            value = inReader.readLine();
        } catch (IOException e) {
        }
        return value.trim();
    }

    protected Double readDouble(int lowerlimit, int upperlimit) {
        Double number = null;
        while (number == null) {
            String str = this.readLine();
            try {
                number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
                System.out.println("Please enter a valid number:");
                continue;
            }
            if (number < lowerlimit) {
                System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
                System.out.println("Please enter a lower number:");
                number = null;
            }
        }
        return number;
    }
}
