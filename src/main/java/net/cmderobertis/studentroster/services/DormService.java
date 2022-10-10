package net.cmderobertis.studentroster.services;

import net.cmderobertis.studentroster.models.Dorm;
import net.cmderobertis.studentroster.repositories.DormRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DormService {
    private final DormRepository repo;
    public DormService(DormRepository dormRepository) {
        this.repo = dormRepository;
    }
    // CREATE
    public Dorm create(Dorm dorm) {
        return repo.save(dorm);
    }
    // READ
    public List<Dorm> getAll() {
        return repo.findAll();
    }
    public Dorm getOne(Long id) {
        Optional<Dorm> dorm = repo.findById(id);
        return dorm.orElse(null);
    }
    // UPDATE
    public void update(Dorm dorm) { repo.save(dorm); }
    //DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
