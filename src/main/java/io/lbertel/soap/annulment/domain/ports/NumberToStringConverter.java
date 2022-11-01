package io.lbertel.soap.annulment.domain.ports;

import reactor.core.publisher.Mono;

public interface NumberToStringConverter {
    Mono<String> converter();
}
