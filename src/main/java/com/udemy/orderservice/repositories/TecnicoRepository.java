package com.udemy.orderservice.repositories;

import com.udemy.orderservice.entity.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
    Tecnico findByCpf(String cpf);
}
