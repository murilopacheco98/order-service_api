package com.udemy.orderservice.services;

import com.udemy.orderservice.dtos.TecnicoDTO;
import com.udemy.orderservice.entity.Tecnico;
import com.udemy.orderservice.exceptions.ObjectNotFoundException;
import com.udemy.orderservice.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TecnicoService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteService clienteService;

    public Tecnico findById(Integer id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado: " + id + "\n Tipo: " + Tecnico.class.getName()));
    }

    public List<TecnicoDTO> findAll() {
        List<Tecnico> tecnicoList = tecnicoRepository.findAll();
        return tecnicoList.stream().map(TecnicoDTO::new).collect(Collectors.toList());
    }

    public TecnicoDTO create(TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = new Tecnico(tecnicoDTO.getNome(), tecnicoDTO.getCpf(), tecnicoDTO.getTelefone());
        clienteService.findCpf(tecnicoDTO.getCpf());
        try {
            tecnico = tecnicoRepository.save(tecnico);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível salvar este técnico");
        }
        return new TecnicoDTO(tecnico);
    }

    public TecnicoDTO update(Integer id, TecnicoDTO tecnicoDTO) {
        Tecnico tecnicoFound = findById(id);
        if (!Objects.equals(tecnicoFound.getCpf(), tecnicoDTO.getCpf())) {
            clienteService.findCpf(tecnicoDTO.getCpf());
        }

        tecnicoFound.setNome(tecnicoDTO.getNome());
        tecnicoFound.setCpf(tecnicoDTO.getCpf());
        tecnicoFound.setTelefone(tecnicoDTO.getTelefone());
        try {
            tecnicoFound = tecnicoRepository.save(tecnicoFound);
        } catch (Exception e) {
            throw new DataIntegrityViolationException("Não foi possível salvar este técnico");
        }
        return new TecnicoDTO(tecnicoFound);
    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (tecnico.getOrderServiceList().size() > 0) {
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço, não pode ser deletado.");
        }
        tecnicoRepository.deleteById(id);
    }
}
