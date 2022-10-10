package net.cmderobertis.studentroster.repositories;

import net.cmderobertis.studentroster.models.Dorm;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DormRepository extends CrudRepository<Dorm, Long> {
    List<Dorm> findAll();
}
