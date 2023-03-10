package com.udemy.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.udemy.orderservice.enums.Prioridade;
import com.udemy.orderservice.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class OrderService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridadeId;
    private String observacoes;
    private Integer statusId;
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public OrderService() {
        super();
        this.dataAbertura = LocalDateTime.now();
        this.prioridadeId = 0;
        this.statusId = 0;
    }

    public OrderService(Integer id, Prioridade prioridade, String observacoes, Status status, Tecnico tecnico, Cliente cliente) {
        super();
        this.id = id;
        this.dataAbertura = LocalDateTime.now();
        this.prioridadeId = (prioridade == null) ? 0 : Prioridade.toEnum(prioridade.getCod()).getCod();
        this.observacoes = observacoes;
        this.statusId = (status == null) ? 0 : Status.toEnum(status.getCod()).getCod();
        this.tecnico = tecnico;
        this.cliente = cliente;
    }
}
