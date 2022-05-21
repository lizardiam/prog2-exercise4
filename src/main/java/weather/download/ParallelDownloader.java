package weather.download;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelDownloader {

    ExecutorService executor = Executors.newFixedThreadPool(10); //ExecutorService: Dieses Interface ermöglicht einen Threadpool anzulegen


}
