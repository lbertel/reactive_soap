package io.lbertel.soap.annulment.infrastructure.controllers;

import io.lbertel.soap.annulment.use_case.NumberToStringConverterUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/temperature-converter")
public class NumberToStringController {

    private final NumberToStringConverterUseCase numberToStringUseCase;

    public NumberToStringController(NumberToStringConverterUseCase numberToStringUseCase) {
        this.numberToStringUseCase = numberToStringUseCase;
    }

    @GetMapping("/")
    private Mono<String> converter(){
        return numberToStringUseCase.convert();
    }
}
