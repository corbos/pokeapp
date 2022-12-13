package corbos.pokeapp.service;

import corbos.pokeapp.data.PokeTypeRepository;
import corbos.pokeapp.models.PokeType;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PokeTypes {
    
    private PokeTypeRepository repo;
    
    @Autowired
    public PokeTypes(PokeTypeRepository repo) {
        this.repo = repo;
    }
    
    public List<PokeType> all() {
        return repo.findAll();
    }
    
    public PokeType findById(int id) {
        Optional<PokeType> result = repo.findById(id);
        return result.orElse(null);
    }
    
    public PokeType save(PokeType pt) {
        return repo.save(pt);
    }
    
    public void deleteById(int id) {
        repo.deleteById(id);
    }
}
