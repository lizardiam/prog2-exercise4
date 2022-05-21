package weather.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import tk.plogitech.darksky.forecast.GeoCoordinates;
import tk.plogitech.darksky.forecast.model.Latitude;
import tk.plogitech.darksky.forecast.model.Longitude;
import weather.ctrl.WeatherController;

public class UserInterface {

    private final WeatherController ctrl = new WeatherController();

    public void getWeatherForVienna() {
        Longitude longitudeVienna = new Longitude(48.210033);
        Latitude latitudeVienna = new Latitude(16.363449);
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

    public void start() {
        Menu<Runnable> menu = new Menu<>("Weather Infos");
        menu.setTitel("Wählen Sie eine Stadt aus:");
        menu.insert("a", "Wien", this::getWeatherForVienna);
        menu.insert("b", "Koeln", this::getWeatherForCologne);
        menu.insert("c", "Florenz", this::getWeatherForFlorence);
        menu.insert("d", "City via Coordinates:", this::getWeatherByCoordinates);
        //ToDo: menu.insert("e", "Download Weather Parallel", this::)
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
