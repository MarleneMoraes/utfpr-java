package br.edu.utfpr.cp.espjava.crudcidades.vision;

import java.util.HashSet;
import java.util.Set;

import javax.naming.Binding;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
public class CityController {

    private Set<City> cidades;

    public CityController() {
        cidades = new HashSet<>();
    }

    @GetMapping("/")
    public String list(Model memory) {

        memory.addAttribute("listaCidades", cidades);

        return "/crud";
    }

    @PostMapping("/criar")
    public String create(@Valid City city, BindingResult result, Model memory) {

        if (result.hasErrors()) {
            result.getFieldErrors().forEach(
                    error -> memory.addAttribute(
                                error.getField(), error.getDefaultMessage())

            );

            memory.addAttribute("nomeInformado", city.getName());
            memory.addAttribute("estadoInformado", city.getState());
            memory.addAttribute("listaCidades", cidades);
        } else {
            cidades.add(city);
        }

        return "redirect:/";
    }

    @GetMapping("/excluir")
    public String delete(
            @RequestParam String name,
            @RequestParam String state) {

        cidades.removeIf(cidade -> cidade.getName().equals(name) &&
                cidade.getState().equals(state));

        return "redirect:/";
    }

    @GetMapping("/preparaAlterar")
    public String prepareUpdate(
            @RequestParam String name,
            @RequestParam String state,
            Model memory) {
        var cidadeAtual = cidades
                .stream()
                .filter(cidade -> cidade.getName().equals(name) &&
                        cidade.getState().equals(state))
                .findAny();

        if (cidadeAtual.isPresent()) {
            memory.addAttribute("cidadeAtual", cidadeAtual.get());
            memory.addAttribute("listaCidades", cidades);

        }
        return "/crud";
    }

    @GetMapping("/alterar")
    public String update(
            @RequestParam String name,
            @RequestParam String state,
            City city,
            BindingResult result,
            Model memory) {
        cidades.removeIf(cidadeAtual -> cidadeAtual.getName().equals(name) &&
                cidadeAtual.getState().equals(state));

        create(city, result, memory);

        return "redirect:/";
    }

}
