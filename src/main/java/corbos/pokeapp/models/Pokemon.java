package corbos.pokeapp.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
public class Pokemon implements Serializable {

    @Id
    @Positive(message = "Dex number must be positive.")
    private int dexNumber;

    @NotBlank(message = "Poke name must not be blank.")
    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "PokeTypeId")
    private PokeType pokeType;

    public int getDexNumber() {
        return dexNumber;
    }

    public void setDexNumber(int dexNumber) {
        this.dexNumber = dexNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokeType getPokeType() {
        return pokeType;
    }

    public void setPokeType(PokeType pokeType) {
        this.pokeType = pokeType;
    }

}
