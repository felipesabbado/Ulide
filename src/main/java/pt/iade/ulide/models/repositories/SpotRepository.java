package pt.iade.ulide.models.repositories;

import org.springframework.data.repository.CrudRepository;
import pt.iade.ulide.models.Spot;

public interface SpotRepository extends CrudRepository<Spot, Integer> {
}