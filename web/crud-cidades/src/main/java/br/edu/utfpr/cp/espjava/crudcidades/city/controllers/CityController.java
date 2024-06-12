package br.edu.utfpr.cp.espjava.crudcidades.city.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.utfpr.cp.espjava.crudcidades.city.City;
import br.edu.utfpr.cp.espjava.crudcidades.city.entities.CityEntity;
import br.edu.utfpr.cp.espjava.crudcidades.city.repositories.CityRepository;
import jakarta.validation.Valid;

@Controller
public class CityController {

    private final CityRepository repository;

    public CityController(final CityRepository repository) {
        this.repository = repository;
    }
    
    private List<City> cityConversion(List<CityEntity> cities) {
        return cities.stream()
                    .map(city -> new City(
                        city.getName(), 
                        city.getState()))
                    .collect(Collectors.toList());
    }
    
    @GetMapping("/")
    public String list(Model memory) {
        memory.addAttribute("listCities", 
                            this.cityConversion(repository.findAll()));

        return "/crud";
    }

    @PostMapping("/create")
    public String create(@Valid City city, BindingResult validation, Model memory) {

        if (validation.hasErrors()) {
            validation.getFieldErrors().forEach(

                    error -> memory.addAttribute(error.getField(), error.getDefaultMessage()));

            memory.addAttribute("nameProvided", city.getName());
            memory.addAttribute("stateProvided", city.getState());
            memory.addAttribute("listCities", this.cityConversion(repository.findAll()));

            return ("/crud");
        } else {
            repository.save(city.clone());
        }
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(
            @RequestParam String name,
            @RequestParam String state) {
        
        
        var foundCity = repository.findByNameAndState(name, state);

        foundCity.ifPresent(repository::delete);

        return "redirect:/";
    }

    @GetMapping("/prepareUpdate")
    public String prepareUpdate(
            @RequestParam String name,
            @RequestParam String state,
            Model memory) {

        var currentCity = repository.findByNameAndState(name, state);

        currentCity.ifPresent(foundCity -> {            
            memory.addAttribute("currentCity", foundCity);
            memory.addAttribute("listCities", this.cityConversion(repository.findAll()));
        });

        return "/crud";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam String currentName,
            @RequestParam String currentState,
            @Valid City city,
            BindingResult validation,
            Model memory) {

                var currentCity = repository.findByNameAndState(currentName, currentState);

                if(currentCity.isPresent()) {
                    
                    var foundCity = currentCity.get();
                    foundCity.setName(city.getName());
                    foundCity.setState(city.getState());

                    repository.saveAndFlush(foundCity);
                };

        return "redirect:/";
    }
}