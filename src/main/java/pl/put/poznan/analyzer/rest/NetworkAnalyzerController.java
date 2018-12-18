package pl.put.poznan.analyzer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foo")
public class NetworkAnalyzerController {

    private static final Logger logger = LoggerFactory.getLogger(NetworkAnalyzerController.class);
    //TODO zaimplementowac jakies metody

    @RequestMapping(method = RequestMethod.GET)
    public String get(){
        return "Uhhhh halo";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void post(){
    }
}


