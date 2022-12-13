package corbos.pokeapp.controllers;

import corbos.pokeapp.models.PokeType;
import corbos.pokeapp.service.PokeTypes;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PokeTypeController {

    private final PokeTypes pokeTypes;

    public PokeTypeController(PokeTypes pt) {
        pokeTypes = pt;
    }

    @GetMapping("/poketype")
    public String index(@ModelAttribute("poketype") PokeType pokeType, Model model) {
        model.addAttribute("poketypes", pokeTypes.all());
        return "poketype-index";
    }

    @PostMapping("/poketype")
    public String addPokeType(@ModelAttribute("poketype") @Valid PokeType pokeType,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("poketypes", pokeTypes.all());
            return "poketype-index";
        }

        pokeTypes.save(pokeType);

        return "redirect:/poketype";
    }

    @GetMapping("/poketype/delete/{id}")
    public String confirmDelete(@PathVariable int id, Model model) {

        PokeType pt = pokeTypes.findById(id);

        if (pt == null) {
            return "redirect:/poketype";
        }

        model.addAttribute("poketype", pt);

        return "poketype-delete";
    }

    @PostMapping("/poketype/delete/{id}")
    public String delete(@PathVariable int id) {
        pokeTypes.deleteById(id);
        return "redirect:/poketype";
    }

    @GetMapping("/poketype/edit/{id}")
    public String displayEdit(@PathVariable int id, Model model) {
        PokeType pt = pokeTypes.findById(id);

        if (pt == null) {
            return "redirect:/poketype";
        }

        model.addAttribute("poketype", pt);

        return "poketype-edit";
    }

    @PostMapping("/poketype/edit/{id}")
    public String edit(@ModelAttribute("poketype") @Valid PokeType pt,
            BindingResult result) {

        if (result.hasErrors()) {
            return "poketype-edit";
        }
        
        pokeTypes.save(pt);
        return "redirect:/poketype";
    }
}
