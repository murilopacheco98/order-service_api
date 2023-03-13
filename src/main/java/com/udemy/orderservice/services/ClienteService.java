package com.udemy.orderservice.services;


import com.udemy.orderservice.dtos.ClienteDTO;
import com.udemy.orderservice.entity.Cliente;
import com.udemy.orderservice.entity.Tecnico;
import com.udemy.orderservice.exceptions.ObjectNotFoundException;
import com.udemy.orderservice.repositories.ClienteRepository;
import com.udemy.orderservice.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TecnicoRepository tecnicoRepository;
    public Cliente findById(Integer id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado: " + id + "\n Tipo: " + Cliente.class.getName()));
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> tecnicoList = clienteRepository.findAll();
        return tecnicoList.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public void findCpf(String cpf) {
        Cliente cliente = clienteRepository.findByCpf(cpf);
        Tecnico tecnico = tecnicoRepository.findByCpf(cpf);
        if (cliente != null || tecnico != null) {
            throw new DataIntegrityViolationException("Cpf já cadastrado: " + cpf);
        }
    }
    public ClienteDTO create(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente(clienteDTO.getNome(), clienteDTO.getCpf(), clienteDTO.getTelefone());
        findCpf(clienteDTO.getCpf());
        cliente = clienteRepository.save(cliente);
        return new ClienteDTO(cliente);
    }
    public ClienteDTO update(Integer id, ClienteDTO clienteDTO) {
        Cliente tecnicoFound = findById(id);
        if (!Objects.equals(tecnicoFound.getCpf(), clienteDTO.getCpf())) {
            findCpf(clienteDTO.getCpf());
        }

        tecnicoFound.setNome(clienteDTO.getNome());
        tecnicoFound.setCpf(clienteDTO.getCpf());
        tecnicoFound.setTelefone(clienteDTO.getTelefone());

        tecnicoFound = clienteRepository.save(tecnicoFound);
        return new ClienteDTO(tecnicoFound);
    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if (cliente.getOrderServiceList().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço, não pode ser deletado.");
        }
        clienteRepository.deleteById(id);
    }
}

