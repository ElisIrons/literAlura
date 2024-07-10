package br.com.alura.literAlura.service;

import br.com.alura.literAlura.model.Autor;
import br.com.alura.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(autor -> System.out.println("Nome: " + autor.getNome() + " | Data de nascimento: " + autor.getAnoNascimento() + " | Data Falecimento: " + autor.getAnoFalecimento()));
        return autores;
    }

    public List<Autor> listarAutoresVivos(int ano) {
        List<Autor> autores = autorRepository.autoresVivosNaqueleAno(ano);
        autores.forEach(a -> System.out.println("Nome: " + a.getNome() + " | Data de falecimento: " + a.getAnoFalecimento()));
        return autores;
    }
}
