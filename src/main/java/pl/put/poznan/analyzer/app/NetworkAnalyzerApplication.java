package pl.put.poznan.analyzer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = {"pl.put.poznan.analyzer.rest"})
public class NetworkAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetworkAnalyzerApplication.class, args);
    }
}
