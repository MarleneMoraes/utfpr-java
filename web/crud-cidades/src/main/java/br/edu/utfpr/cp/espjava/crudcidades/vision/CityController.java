package br.edu.utfpr.cp.espjava.crudcidades.vision;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class CityController {

    private Set<City> cities;

    public CityController() {
        cities = new HashSet<>();
    }

    @GetMapping("/")
    public String list(Model memory) {
        memory.addAttribute("listCities", cities);
        return "/crud";
    }

    @PostMapping("/create")
    public String create(@Valid City city, BindingResult validation, Model memory) {
        if (validation.hasErrors()) {
            validation.getFieldErrors().forEach(error -> 
                memory.addAttribute(error.getField(), error.getDefaultMessage()));
            memory.addAttribute("nameProvided", city.getName());
            memory.addAttribute("stateProvided", city.getState());
            memory.addAttribute("listCities", cities);
            return ("/crud");
        } else {
            cities.add(city);
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam String name, @RequestParam String state) {
        cities.removeIf(currentCity -> 
            currentCity.getName().equals(name) && currentCity.getState().equals(state));
        return "redirect:/";
    }

    @GetMapping("/prepareModify")
    public String prepareModify(@RequestParam String name, @RequestParam String state, Model memory) {
        var currentCity = cities.stream()
            .filter(city -> city.getName().equals(name) && city.getState().equals(state))
            .findAny();
        if (currentCity.isPresent()) {
            memory.addAttribute("currentCity", currentCity.get());
            memory.addAttribute("listCities", cities);
        }
        return "/crud";
    }

    @PostMapping("/modify")
    public String modify(
        @RequestParam String currentName, 
        @RequestParam String currentState,
        @Valid City city,
        BindingResult validation,
        Model memory) {

        if (validation.hasErrors()) {
            validation.getFieldErrors().forEach(error -> 
                memory.addAttribute(error.getField(), error.getDefaultMessage()));
            memory.addAttribute("currentCity", city);
            memory.addAttribute("listCities", cities);
            return "/crud";
        }

        cities.removeIf(currentCity -> 
            currentCity.getName().equals(currentName) && 
            currentCity.getState().equals(currentState));

        cities.add(city);

        return "redirect:/";
    }
}