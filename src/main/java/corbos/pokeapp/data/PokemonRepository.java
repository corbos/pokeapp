package corbos.pokeapp.data;

import corbos.pokeapp.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {

    Pokemon findByName(String name);
}
