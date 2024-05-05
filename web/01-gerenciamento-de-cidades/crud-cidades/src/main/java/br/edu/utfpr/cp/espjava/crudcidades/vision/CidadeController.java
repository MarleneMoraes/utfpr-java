package br.edu.utfpr.cp.espjava.crudcidades.vision;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CidadeController {

    @GetMapping("/")
    public String list() {
        return "crud.html";
    }
}
