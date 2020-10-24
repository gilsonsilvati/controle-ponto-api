package com.controleponto.api.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;

@Getter
public class RecursoCriadoEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Integer id;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Integer id) {
        super(source);

        this.response = response;
        this.id = id;
    }

}
