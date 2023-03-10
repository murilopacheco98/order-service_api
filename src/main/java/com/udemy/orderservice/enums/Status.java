package com.udemy.orderservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {
    ABERTO(0, "ABERTO"), ANDAMENTO(1, "ANDAMENTO"), ENCERRADO(2, "ENCERRADO");

    private Integer cod;
    private String descricao;

    public static Prioridade toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for(Prioridade x : Prioridade.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + cod);
    }
}
