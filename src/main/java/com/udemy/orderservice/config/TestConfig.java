package com.udemy.orderservice.config;

import com.udemy.orderservice.entity.Cliente;
import com.udemy.orderservice.entity.OrderService;
import com.udemy.orderservice.entity.Tecnico;
import com.udemy.orderservice.enums.Prioridade;
import com.udemy.orderservice.enums.Status;
import com.udemy.orderservice.repositories.ClienteRepository;
import com.udemy.orderservice.repositories.OrderServiceRepository;
import com.udemy.orderservice.repositories.TecnicoRepository;
import com.udemy.orderservice.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration()
@Profile("test")
public class TestConfig {
    @Autowired
    DBService dbService;

    @Bean
    public void instanciaDB() {
        this.dbService.instanciaDB();
    }
}

