package io.lbertel.soap.annulment.use_case;

import io.lbertel.soap.annulment.domain.ports.NumberToStringConverter;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class NumberToStringConverterUseCase {

    private final NumberToStringConverter repository;

    public NumberToStringConverterUseCase(NumberToStringConverter repository) {
        this.repository = repository;
    }

    public Mono<String> convert() {
        return repository.converter();
    }

}
