package corbos.pokeapp.data;

import corbos.pokeapp.models.PokeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokeTypeRepository extends JpaRepository<PokeType, Integer> {

}
