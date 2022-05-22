package weather.download;

import tk.plogitech.darksky.forecast.ForecastException;
import tk.plogitech.darksky.forecast.GeoCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// https://github.com/lizardiam/prog2-exercise4

public class ParallelDownloader extends Downloader {

    //ExecutorService: Dieses Interface ermöglicht einen Threadpool anzulegen - thread delegates a task to an ExecutorService for asynchronous execution

    //Future: Dieses Interface erlaubt Variablen für laufende Threads zu definieren und trotzdem im Ablauf des Programms vorzufahren.

    @Override
    public int process(List<GeoCoordinates> geoCoordinatesList) throws ForecastException {
        int count = 0;

        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Future<String>> futures = new ArrayList<>();

        for (GeoCoordinates coordinates : geoCoordinatesList) {

            futures.add(executor.submit(() -> save(coordinates)));
            count++;
        }

        executor.shutdown();
        return count;

    }
}