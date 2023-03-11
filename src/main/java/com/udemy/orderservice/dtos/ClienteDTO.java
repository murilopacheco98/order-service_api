package com.udemy.orderservice.dtos;

import com.udemy.orderservice.entity.Cliente;
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
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;
    @CPF
    @NotBlank(message = "O campo cpf é obrigatório")
    private String cpf;
    @NotBlank(message = "O campo telefone é obrigatório")
    private String telefone;
    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.telefone = cliente.getTelefone();
    }
}
