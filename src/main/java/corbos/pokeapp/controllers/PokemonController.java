package corbos.pokeapp.controllers;

import corbos.pokeapp.models.Pokemon;
import corbos.pokeapp.service.PokeDex;
import corbos.pokeapp.service.PokeTypes;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PokemonController {

    private final PokeDex dex;
    private final PokeTypes pokeTypes;

    @Autowired
    public PokemonController(PokeDex dex, PokeTypes pokeTypes) {
        this.dex = dex;
        this.pokeTypes = pokeTypes;
    }

    @GetMapping("/")
    public String getAll(Model model) {
        model.addAttribute("pokemon", dex.all());
        return "home";
    }

    @GetMapping("/pokemon/add")
    public String addPokemon(Pokemon pokemon, Model model) {
        model.addAttribute("poketypes", pokeTypes.all());
        return "pokemon-add";
    }

    @PostMapping("/pokemon/add")
    public String addPokemon(@Valid Pokemon pokemon, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("poketypes", pokeTypes.all());
            return "pokemon-add";
        }

        Pokemon existing = dex.findByDexNumber(pokemon.getDexNumber());
        if (existing != null) {
            model.addAttribute("poketypes", pokeTypes.all());
            result.addError(new FieldError("pokemon", "dexNumber", "Duplicate Dex #"));
            return "pokemon-add";
        }

        existing = dex.findByName(pokemon.getName());
        if (existing != null) {
            model.addAttribute("poketypes", pokeTypes.all());
            result.addError(new FieldError("pokemon", "name", "Duplicate Name"));
            return "pokemon-add";
        }

        dex.save(pokemon);

        return "redirect:/";
    }

    @GetMapping("/pokemon/edit/{dexNumber}")
    public String editPokemon(@PathVariable int dexNumber, Model model) {

        Pokemon p = dex.findByDexNumber(dexNumber);
        if (p == null) {
            return "redirect:/"; // not found, forget it.
        }

        model.addAttribute("pokemon", p);
        model.addAttribute("poketypes", pokeTypes.all());

        return "pokemon-edit";
    }

    @PostMapping("/pokemon/edit/{dexNumber}")
    public String editPokemon(@Valid Pokemon pokemon, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("poketypes", pokeTypes.all());
            return "pokemon-edit";
        }

        Pokemon existing = dex.findByName(pokemon.getName());
        if (existing != null && existing.getDexNumber() != pokemon.getDexNumber()) {

            result.addError(new FieldError("pokemon", "name", "Duplicate Name"));

            existing = dex.findByDexNumber(pokemon.getDexNumber());
            pokemon.setName(existing.getName()); // reset back to original name

            model.addAttribute("poketypes", pokeTypes.all());

            return "pokemon-edit";
        }

        dex.save(pokemon);

        return "redirect:/";
    }

    @GetMapping("/pokemon/delete/{dexNumber}")
    public String deletePokemon(@PathVariable int dexNumber, Model model) {

        Pokemon p = dex.findByDexNumber(dexNumber);
        if (p == null) {
            return "redirect:/"; // not found, forget it.
        }

        model.addAttribute("pokemon", p);

        return "pokemon-delete";
    }

    @PostMapping("/pokemon/delete/{dexNumber}")
    public String deletePokemon(@PathVariable int dexNumber) {

        dex.deleteByDexNumber(dexNumber);

        return "redirect:/";
    }
}
