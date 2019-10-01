package com.octa.springapi.resource;

import com.octa.springapi.event.RecursoCriadoEvent;
import com.octa.springapi.model.Categoria;
import com.octa.springapi.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaResource {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public List<Categoria> lisarcategoria(){
        return categoriaRepository.findAll();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
    public ResponseEntity<Categoria> inserirCategoria(@Valid @RequestBody Categoria categoria, HttpServletResponse httpServletResponse) {
        Categoria save = categoriaRepository.save(categoria);
        publisher.publishEvent(new RecursoCriadoEvent(this,httpServletResponse,save.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(categoria);
}
    /*@GetMapping("/{id}")
    public Categoria pesquisaPorId(@PathVariable Long id){
        Optional<Categoria> byId = categoriaRepository.findById(id);
        return byId.get();
    }*/
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
    public ResponseEntity<?> pesquisaPorId(@PathVariable Long id){
        Categoria categoria = categoriaRepository.findOne(id);
        return categoria!=null?ResponseEntity.ok(categoria):ResponseEntity.notFound().build();
    }
}
