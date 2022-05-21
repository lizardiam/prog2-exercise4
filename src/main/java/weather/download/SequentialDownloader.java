package weather.download;

import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.GeoCoordinates;

import java.util.List;

public class SequentialDownloader extends Downloader {

    @Override
    public int process(List<GeoCoordinates> geoCoordinatesList) throws ForecastException {
        int count = 0;
        for (GeoCoordinates coordinates : geoCoordinatesList) {
            String fileName = save(coordinates);
            if(fileName != null)
                count++;
        }
        return count;
    }
}
