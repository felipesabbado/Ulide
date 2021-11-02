package pt.iade.ulide.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.iade.ulide.models.UserAchievement;
import pt.iade.ulide.models.exceptions.NotFoundException;
import pt.iade.ulide.models.exceptions.Response;
import pt.iade.ulide.models.repositories.UserAchievementRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/userAchievements")
public class UserAchievementController {

    private final Logger logger = LoggerFactory.getLogger(UserAchievementRepository.class);

    @Autowired
    private UserAchievementRepository userAcRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<UserAchievement> getUserAchievements() {
        logger.info("Sending all users achievements !!!");
        return userAcRepository.findAll();

    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAchievement getUserAchievement(@PathVariable int id) {
        logger.info("Sending users achievements with id " + id);
        Optional<UserAchievement> _userAchievement = userAcRepository.findById(id);
        if (_userAchievement.isEmpty()) throw
                new NotFoundException("" + id, "User achievement", "id");
        else
            return _userAchievement.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserAchievement saveUserAchievement(@RequestBody UserAchievement userAchievement) {
        UserAchievement savedUserAchievement = userAcRepository.save(userAchievement);
        logger.info("Saving user achievement with id " + savedUserAchievement.getId());
        return savedUserAchievement;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUserAchievement(@PathVariable int id) {
        logger.info("Deleting user achievement with id " + id);
        userAcRepository.deleteById(id);
        return new Response("Deleted user achievement with id " + id, null);
    }

}
