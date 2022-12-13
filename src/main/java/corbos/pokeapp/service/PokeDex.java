package corbos.pokeapp.service;

import corbos.pokeapp.data.PokemonRepository;
import corbos.pokeapp.models.Pokemon;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class PokeDex {

    private final PokemonRepository repo;

    public PokeDex(PokemonRepository repo) {
        this.repo = repo;
    }

    public List<Pokemon> all() {
       
        return repo.findAll();
    }

    public Pokemon findByDexNumber(int dexNumber) {
        return repo.findById(dexNumber).orElse(null);
    }

    public Pokemon findByName(String name) {
        return repo.findByName(name);
    }

    public Pokemon save(Pokemon p) {

        Validator validator = Validation.buildDefaultValidatorFactory()
                .getValidator();

        Set<ConstraintViolation<Pokemon>> violations = validator.validate(p);
        if (violations.size() > 0) {
            throw new RuntimeException("invalid pokemon");
        }

        return repo.save(p);
    }
    
    public void deleteByDexNumber(int dexNumber){
        repo.deleteById(dexNumber);
    }

}
