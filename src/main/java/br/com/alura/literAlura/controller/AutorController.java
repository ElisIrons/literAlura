package br.com.alura.literAlura.controller;

import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listarAutores() {
        return autorService.listarAutores();
    }

    @GetMapping("/vivos/{ano}")
    public List<Autor> listarAutoresVivos(@PathVariable int ano) {
        return autorService.listarAutoresVivos(ano);
    }

}
