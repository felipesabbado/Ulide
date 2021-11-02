package pt.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.iade.ulide.models.Route;
import pt.iade.ulide.models.exceptions.NotFoundException;
import pt.iade.ulide.models.repositories.RouteRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/routes")
public class RouteController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RouteRepository routeRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Route> getRoutes() {
        logger.info("Sending all users !!!");
        return routeRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Route getRoute(@PathVariable int id) {
        logger.info("Sending route with id " + id);
        Optional<Route> _route = routeRepository.findById(id);
        if (_route.isEmpty()) throw
                new NotFoundException("" + id, "Route", "id");
        else
            return _route.get();
    }
}
