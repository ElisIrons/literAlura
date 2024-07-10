package br.com.alura.literAlura.model;

import br.com.alura.literAlura.dto.AutorDTO;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int anoNascimento;
    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {}

    public Autor(AutorDTO autorDTO) {
        this.nome = autorDTO.nome();
        this.anoNascimento = autorDTO.anoNascimento();
        this.anoFalecimento = autorDTO.anoFalecimento() == 0 ? null : autorDTO.anoFalecimento();
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public Integer getAnoFalecimento() {
        return anoFalecimento;
    }

    @Override
    public String toString() {
        return "********** Autor **********" +
                "\nNome = " + nome  +
                "\nAno de nascimento = " + anoNascimento +
                "\nAno de falecimento = " + anoFalecimento +
                "\nLivros = " + livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.toList());
    }
}
