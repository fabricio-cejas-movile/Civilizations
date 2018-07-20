package com.mercadolibre.ingreso.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Component
public class Logs {
    private Logger requestAnswered = LoggerFactory.getLogger("requests_answered");
    private Logger requestPerformed = LoggerFactory.getLogger("requests-performed");
    private Logger exceptions = LoggerFactory.getLogger("exceptions");
    private Logger system = LoggerFactory.getLogger("system");
    private Logger dao = LoggerFactory.getLogger("dao");

    public Logger requestAnswered() {
        return this.requestAnswered;
    }

    public Logger requestPerformed() {
        return this.requestPerformed;
    }

    public Logger exceptions() {
        return this.exceptions;
    }

    public Logger system() {
        return this.system;
    }

    public Logger dao() {
        return this.dao;
    }
}
