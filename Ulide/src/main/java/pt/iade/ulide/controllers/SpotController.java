package pt.iade.ulide.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pt.iade.ulide.models.Spot;
import pt.iade.ulide.models.exceptions.NotFoundException;
import pt.iade.ulide.models.exceptions.Response;
import pt.iade.ulide.models.repositories.SpotRepository;

import java.util.Optional;


@RestController
@RequestMapping(path = "/api/spots")
public class SpotController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SpotRepository spotRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Spot> getSpots() {
        logger.info("Sending all spots !!!");
        return spotRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Spot getSpot(@PathVariable int id) {
        logger.info("Sending spot with id " + id);
        Optional<Spot> _spot = spotRepository.findById(id);
        if (_spot.isEmpty()) throw
                new NotFoundException("" + id, "Spot", "id");
        else
            return _spot.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Spot saveSpot(@RequestBody Spot spot) {
        Spot savedSpot = spotRepository.save(spot);
        logger.info("Saving spot with id " + savedSpot.getId());
        return savedSpot;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteSpot(@PathVariable int id) {
        logger.info("Deleting spot with id " + id);
        spotRepository.deleteById(id);
        return new Response("Deleted spot with id " + id, null);
    }

}
