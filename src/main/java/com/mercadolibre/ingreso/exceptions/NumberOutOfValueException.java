package com.mercadolibre.ingreso.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NumberOutOfValueException extends RuntimeException {

    public NumberOutOfValueException(String message) {
        super(message);
    }
}
