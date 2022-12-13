package corbos.pokeapp.models;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class Collector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int collectorId;

    @NotBlank(message = "First Name is required.")
    private String firstName;

    @NotBlank(message = "Last Name is required.")
    private String lastName;

    @ManyToMany
    @JoinTable(name = "CollectorPokemon",
            joinColumns = {
                @JoinColumn(name = "collectorId")},
            inverseJoinColumns = {
                @JoinColumn(name = "dexNumber")})
    private List<Pokemon> pokemon;

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public boolean hasPokemon(int dexNumber) {
        if (pokemon != null) {
            for (Pokemon p : pokemon) {
                if (p.getDexNumber() == dexNumber) {
                    return true;
                }
            }
        }
        return false;
    }

}
