package corbos.pokeapp.service;

import corbos.pokeapp.data.CollectorRepository;
import corbos.pokeapp.models.Collector;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class Collectors {

    private CollectorRepository repo;

    public Collectors(CollectorRepository repo) {
        this.repo = repo;
    }

    public List<Collector> all() {
        return repo.findAll();
    }

    public Collector findById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Collector save(Collector c) {

        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Collector>> violations = validator.validate(c);
        if (violations.size() > 0) {
            throw new RuntimeException("INVALID DATA.");
        }

        return repo.save(c);
    }

    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
