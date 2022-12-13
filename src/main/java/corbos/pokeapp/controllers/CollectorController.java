package corbos.pokeapp.controllers;

import corbos.pokeapp.models.Collector;
import corbos.pokeapp.models.Pokemon;
import corbos.pokeapp.service.Collectors;
import corbos.pokeapp.service.PokeDex;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CollectorController {

    private final Collectors collectors;
    private final PokeDex dex;

    @Autowired
    public CollectorController(Collectors collectors, PokeDex dex) {
        this.collectors = collectors;
        this.dex = dex;
    }

    @GetMapping("/collector")
    public String home(Model model) {
        model.addAttribute("collectors", collectors.all());
        return "collector-index";
    }

    @GetMapping("/collector/add")
    public String displayAdd(Collector c, Model model) {
        return "collector-add";
    }

    @PostMapping("/collector/add")
    public String add(@Valid Collector c, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "collector-add";
        }
        collectors.save(c);
        return "redirect:/collector";
    }

    @GetMapping("/collector/edit/{id}")
    public String displayEdit(@PathVariable int id, Model model) {
        Collector c = collectors.findById(id);
        if (c == null) {
            return "redirect:/collector";
        }
        model.addAttribute("collector", c);
        model.addAttribute("pokemon", dex.all());
        return "collector-edit";
    }

    @PostMapping("/collector/edit/{id}")
    public String edit(@Valid Collector c,
            BindingResult result,
            int[] dexNumbers,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pokemon", dex.all());
            return "collector-edit";
        }

        List<Pokemon> pokemon = dex.all();
        List<Pokemon> selectedPokemon = new ArrayList<>();
        if (dexNumbers != null) {
            for (int dexVal : dexNumbers) {

                Pokemon poke = pokemon.stream()
                        .filter(p -> p.getDexNumber() == dexVal)
                        .findFirst()
                        .orElse(null);

                if (poke != null) {
                    selectedPokemon.add(poke);
                }
            }
        }

        c.setPokemon(selectedPokemon);

        collectors.save(c);
        return "redirect:/collector";
    }

    @GetMapping("/collector/delete/{id}")
    public String displayDelete(@PathVariable int id, Model model) {

        Collector c = collectors.findById(id);
        if (c == null) {
            return "redirect:/collector";
        }

        model.addAttribute("collector", c);

        return "collector-delete";
    }

    @PostMapping("/collector/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        collectors.deleteById(id);
        return "redirect:/collector";
    }
}
