package br.com.alura.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultsDTO(
        int count,
        @JsonAlias("results") List<LivroDTO> livros) {
    public Map<Object, Object> getResultados() {
        return Map.of();
    }
}