package com.udemy.orderservice.dtos;

import com.udemy.orderservice.entity.Tecnico;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TecnicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;
    @CPF
    @NotBlank(message = "O campo cpf é obrigatório")
    private String cpf;
    @NotBlank(message = "O campo telefone é obrigatório")
    private String telefone;
    public TecnicoDTO(Tecnico tecnico) {
        this.id = tecnico.getId();
        this.nome = tecnico.getNome();
        this.cpf = tecnico.getCpf();
        this.telefone = tecnico.getTelefone();
    }
}
