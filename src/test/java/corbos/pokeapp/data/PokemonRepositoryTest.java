package corbos.pokeapp.data;

import corbos.pokeapp.models.PokeType;
import corbos.pokeapp.models.Pokemon;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class PokemonRepositoryTest {

    @Autowired
    private PokemonRepository repo;

    public PokemonRepositoryTest() {
    }

    @Test
    public void testAll() {
        List<Pokemon> all = repo.findAll();
        assertTrue(all != null && all.size() > 0);
    }

    @Test
    public void testSave() {

        // Each Pokemon gets a type.
        PokeType pt = new PokeType();
        pt.setPokeTypeId(1);
        pt.setName("Normal");

        Pokemon p = new Pokemon();
        p.setDexNumber(1);
        p.setName("Bulbasaur");
        p.setPokeType(pt);

        p = repo.save(p);

        assertTrue(p != null);
    }

}
