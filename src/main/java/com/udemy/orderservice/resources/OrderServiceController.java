package com.udemy.orderservice.resources;

import com.udemy.orderservice.dtos.OrderServiceDTO;
import com.udemy.orderservice.entity.OrderService;
import com.udemy.orderservice.services.OrderServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order-service")
public class OrderServiceController {
    @Autowired
    private OrderServiceService orderServiceService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderServiceDTO> findById(@PathVariable Integer id) throws Exception {
        OrderService orderService = orderServiceService.findById(id);
        return ResponseEntity.ok().body(new OrderServiceDTO(orderService));
    }

    @GetMapping
    public ResponseEntity<List<OrderServiceDTO>> findAll() {
        List<OrderServiceDTO> orderServiceDTOList = orderServiceService.findAll();
        return ResponseEntity.ok().body(orderServiceDTOList);
    }

    @PostMapping
    public ResponseEntity<OrderServiceDTO> create(@Valid @RequestBody OrderServiceDTO orderServiceDTO) {
        OrderServiceDTO orderServiceDTOCreated = orderServiceService.create(orderServiceDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderServiceDTOCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(orderServiceDTOCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderServiceDTO> update(@Valid @PathVariable Integer id, @RequestBody OrderServiceDTO orderServiceDTO) {
        OrderServiceDTO orderServiceDTOCreated = orderServiceService.update(id, orderServiceDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(orderServiceDTOCreated.getId()).toUri();
        return ResponseEntity.created(uri).body(orderServiceDTOCreated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        orderServiceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
