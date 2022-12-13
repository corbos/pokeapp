package corbos.pokeapp.data;

import corbos.pokeapp.models.Collector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectorRepository extends JpaRepository<Collector, Integer> {

}
