package pt.iade.ulide.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.ulide.models.UserAchievement;

public interface UserAchievementRepository extends CrudRepository<UserAchievement, Integer> {
}