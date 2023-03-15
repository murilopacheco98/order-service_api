package com.udemy.orderservice.services;

import com.udemy.orderservice.dtos.OrderServiceDTO;
import com.udemy.orderservice.entity.*;
import com.udemy.orderservice.entity.OrderService;
import com.udemy.orderservice.enums.Prioridade;
import com.udemy.orderservice.enums.Status;
import com.udemy.orderservice.exceptions.ObjectNotFoundException;
import com.udemy.orderservice.repositories.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceService {
    @Autowired
    private OrderServiceRepository orderServiceRepository;
    @Autowired
    private TecnicoService tecnicoService;
    @Autowired
    private ClienteService clienteService;
    public OrderService findById(Integer id) {
        return orderServiceRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado: " + id + "\n Tipo: " + OrderService.class.getName()));
    }

    public List<OrderServiceDTO> findAll() {
        List<OrderService> orderServiceList = orderServiceRepository.findAll();
        return orderServiceList.stream().map(OrderServiceDTO::new).collect(Collectors.toList());
    }

    public OrderServiceDTO create(OrderServiceDTO orderServiceDTO) {
        OrderService orderService = new OrderService();

        orderService.setObservacoes(orderServiceDTO.getObservacoes());
        orderService.setPrioridadeId(Prioridade.toEnum(orderServiceDTO.getPrioridadeId()).getCod());
        orderService.setStatusId(Status.toEnum(orderServiceDTO.getStatusId()).getCod());

        Tecnico tecnico = tecnicoService.findById(orderServiceDTO.getTecnicoDTO().getId());
        Cliente cliente = clienteService.findById(orderServiceDTO.getClienteDTO().getId());

        orderService.setTecnico(tecnico);
        orderService.setCliente(cliente);
        orderService = orderServiceRepository.save(orderService);
        return new OrderServiceDTO(orderService);
    }
    public OrderServiceDTO update(Integer id, OrderServiceDTO orderServiceDTO) {
        OrderService orderServiceFound = findById(id);

        orderServiceFound.setObservacoes(orderServiceDTO.getObservacoes());
        orderServiceFound.setPrioridadeId(Prioridade.toEnum(orderServiceDTO.getPrioridadeId()).getCod());
        orderServiceFound.setStatusId(Status.toEnum(orderServiceDTO.getStatusId()).getCod());

        Tecnico tecnico = tecnicoService.findById(orderServiceDTO.getTecnicoDTO().getId());
        Cliente cliente = clienteService.findById(orderServiceDTO.getClienteDTO().getId());

        orderServiceFound.setTecnico(tecnico);
        orderServiceFound.setCliente(cliente);
        if (orderServiceFound.getStatusId().equals(2)) {
            orderServiceFound.setDataFechamento(LocalDateTime.now());
        }
        orderServiceFound = orderServiceRepository.save(orderServiceFound);
        return new OrderServiceDTO(orderServiceFound);
    }

    public void delete(Integer id) {
//        OrderService orderService = findById(id);
//        if (orderService.getOrderServiceList().size() > 0) {
//            throw new DataIntegrityViolationException("Técnico possui ordens de serviço, não pode ser deletado.");
//        }
        orderServiceRepository.deleteById(id);
    }
}
