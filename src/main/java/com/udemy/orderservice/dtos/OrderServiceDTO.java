package com.udemy.orderservice.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.udemy.orderservice.entity.OrderService;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderServiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataAbertura;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataFechamento;
    private Integer prioridadeId;
    @NotBlank(message = "O campo observações é obrigatório")
    private String observacoes;
    private Integer statusId;
    private TecnicoDTO tecnicoDTO;
    private ClienteDTO clienteDTO;

    public OrderServiceDTO(OrderService orderService) {
        super();
        id = orderService.getId();
        dataAbertura = orderService.getDataAbertura();
        dataFechamento = orderService.getDataFechamento();
        prioridadeId = orderService.getPrioridadeId();
        observacoes = orderService.getObservacoes();
        statusId = orderService.getStatusId();
        tecnicoDTO = new TecnicoDTO(orderService.getTecnico());
        clienteDTO = new ClienteDTO(orderService.getCliente());
    }
}
