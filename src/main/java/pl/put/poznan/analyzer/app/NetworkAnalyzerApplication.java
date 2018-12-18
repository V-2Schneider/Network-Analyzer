package pl.put.poznan.analyzer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class that run the application
 */
@SpringBootApplication(scanBasePackages = {"pl.put.poznan.analyzer.rest"})
public class NetworkAnalyzerApplication {
    
    /**
     * Main function that starts the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NetworkAnalyzerApplication.class, args);
    }
}
