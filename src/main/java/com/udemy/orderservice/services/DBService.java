package com.udemy.orderservice.services;

import com.udemy.orderservice.entity.Cliente;
import com.udemy.orderservice.entity.OrderService;
import com.udemy.orderservice.entity.Tecnico;
import com.udemy.orderservice.enums.Prioridade;
import com.udemy.orderservice.enums.Status;
import com.udemy.orderservice.repositories.ClienteRepository;
import com.udemy.orderservice.repositories.OrderServiceRepository;
import com.udemy.orderservice.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OrderServiceRepository orderServiceRepository;

    public void instanciaDB() {
        Tecnico tecnico = new Tecnico("Valdir Cezar", "144.785.300-84", "(88) 98888-8888");
        Cliente cliente = new Cliente("Betina Campos", "598.508.200-80", "(88) 98888-7777");
        OrderService orderService = new OrderService(null, Prioridade.ALTA, "Trocar fonte do notebook", Status.ANDAMENTO, tecnico, cliente);

        tecnicoRepository.save(tecnico);
        clienteRepository.save(cliente);
        orderServiceRepository.save(orderService);
    }
}
