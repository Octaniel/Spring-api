package com.octa.springapi.event.listener;

import com.octa.springapi.event.RecursoCriadoEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse httpServletResponse=recursoCriadoEvent.getHttpServletResponse();
        Long id=recursoCriadoEvent.getId();
    }
    private void adicionarHeaderLocation(HttpServletResponse httpServletResponse,Long id){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
        httpServletResponse.setHeader("Location",uri.toASCIIString());
    }
}
