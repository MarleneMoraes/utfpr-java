package br.edu.utfpr.cp.espjava.crudcidades.vision;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityController {

    @GetMapping("/")
    public String list(Model memory) {
        var cidades = Set.of(
            new City("Cornélio Procópio", "PR"),
            new City("Assis", "SP"),
            new City("Itajaí", "SC")
        );

        memory.addAttribute("listaCidades", cidades);
        return "crud.html";
    }
}
