package corbos.pokeapp.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class PokeType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pokeTypeId;

    @NotBlank(message = "Name must not be blank.")
    @Column(unique = true)
    private String name;

    public int getPokeTypeId() {
        return pokeTypeId;
    }

    public void setPokeTypeId(int pokeTypeId) {
        this.pokeTypeId = pokeTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
