package pl.put.poznan.analyzer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.analyzer.logic.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that controls the API of the application
 */

@RestController
@RequestMapping("")
public class NetworkAnalyzerController {

    private static final Logger logger = LoggerFactory.getLogger(NetworkAnalyzerController.class);
    private NetworkAnalyzer networkAnalyzer;
    Graph graph = new Graph();

    @Autowired
    public NetworkAnalyzerController(NetworkAnalyzer networkAnalyzer) {
        this.networkAnalyzer = networkAnalyzer;
    }

    @RequestMapping(path = "/bfs/nodes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PathResult findTheBestPathByBFS(@RequestBody int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        return networkAnalyzer.findTheBestPathByBFS(_entryNode, _exitNode,_mapOfNode);
    }

    @RequestMapping(path = "/dfs/nodes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PathResult findTheBestPathByDFS(@RequestBody int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        return networkAnalyzer.findTheBestPathByBFS(_entryNode, _exitNode,_mapOfNode);
    }

    @RequestMapping(path = "/greedy/nodes", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public PathResult findTheBestPathByGreedy(@RequestBody int _entryNode, int _exitNode, HashMap<Integer,Node> _mapOfNode) {
        return networkAnalyzer.findTheBestPathByBFS(_entryNode, _exitNode,_mapOfNode);
    }


   /* @RequestMapping(method = RequestMethod.GET)
    public String get(){
        return "Uhhhh halo";
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public void post(){
    }*/
}


