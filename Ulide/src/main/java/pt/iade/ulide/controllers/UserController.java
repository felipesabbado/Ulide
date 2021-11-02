package pt.iade.ulide.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.iade.ulide.models.User;
import pt.iade.ulide.models.exceptions.NotFoundException;
import pt.iade.ulide.models.exceptions.Response;
import pt.iade.ulide.models.repositories.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<User> getUsers() {
        logger.info("Sending all users !!!");
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable int id) {
        logger.info("Sending user with id " + id);
        Optional<User> _user = userRepository.findById(id);
        if (_user.isEmpty()) throw
                new NotFoundException("" + id, "User", "id");
        else
            return _user.get();
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(@RequestBody User user) {
        User savedUser = userRepository.save(user);
        logger.info("Saving user with id " + savedUser.getId());
        return savedUser;
    }

    @DeleteMapping(path = "/{id:[0-9]+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUser(@PathVariable int id) {
        logger.info("Deleting user with id " + id);
        userRepository.deleteById(id);
        return new Response("Deleted user with id " + id, null);
    }
}
