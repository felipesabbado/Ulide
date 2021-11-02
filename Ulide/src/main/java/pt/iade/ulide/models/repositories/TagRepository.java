package pt.iade.ulide.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.ulide.models.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer> {
}