package com.mercadolibre.ingreso.filter;

import com.mercadolibre.ingreso.commons.Logs;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Fabricio Cejas (fabrizzio.cejas.80@gmail.com)
 */
@Component
@Order(1)
public class TransactionFilter implements Filter {

    @Autowired
    private Logs log;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.system().info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put("traceid", UUID.randomUUID().toString());

        chain.doFilter(request, response);

        MDC.clear();
    }

    @Override
    public void destroy() {
        log.system().info("Destructing filter :{}", this);
    }
}
