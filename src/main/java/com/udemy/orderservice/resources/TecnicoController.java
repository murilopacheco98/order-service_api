package com.udemy.orderservice.resources;

import com.udemy.orderservice.dtos.TecnicoDTO;
import com.udemy.orderservice.entity.Tecnico;
import com.udemy.orderservice.services.TecnicoService;
import jakarta.servlet.Servlet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tecnico")
public class TecnicoController {
    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) throws Exception {
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<TecnicoDTO> tecnicoDTOList = tecnicoService.findAll();
        return ResponseEntity.ok().body(tecnicoDTOList);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO tecnicoDTOCreated = tecnicoService.create(tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tecnicoDTOCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(tecnicoDTOCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@Valid @PathVariable Integer id, @RequestBody TecnicoDTO tecnicoDTO) {
        TecnicoDTO tecnicoDTOCreated = tecnicoService.update(id, tecnicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tecnicoDTOCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(tecnicoDTOCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
