package pt.iade.ulide.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.ulide.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}