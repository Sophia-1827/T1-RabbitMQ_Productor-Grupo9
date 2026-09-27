package com.cibertec.t1rabbitmq_productor_grupo9.controller;

import com.cibertec.t1rabbitmq_productor_grupo9.config.RabbitMqConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/fibonacci")
public class FibonacciController {

    private final RabbitTemplate rabbitTemplate;

    // Flujo 1 y 2: recibe "1;2;15;8" y lo envía a RabbitMQ
    @GetMapping("/send")
    public ResponseEntity<String> enviarNumeros(@RequestParam String numbers) {
        rabbitTemplate.convertAndSend(
                RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTING_KEY,
                numbers
        );
        return ResponseEntity.ok("Lista enviada a RabbitMQ correctamente");
    }
}