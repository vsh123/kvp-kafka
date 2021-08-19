package com.kvp.kafka.step3;

import com.kvp.domain.step3.Receipt;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/step3")
public class Step3Controller {
    private final Step3Producer step3Producer;

    @GetMapping
    public ResponseEntity get(Receipt receipt) {
        step3Producer.send(receipt);
        return ResponseEntity.ok().build();
    }
}
