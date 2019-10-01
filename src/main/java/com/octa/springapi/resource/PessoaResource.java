package com.octa.springapi.resource;

import com.octa.springapi.event.RecursoCriadoEvent;
import com.octa.springapi.model.Pessoa;
import com.octa.springapi.repository.PessoaRepository;
import com.octa.springapi.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
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
@RequestMapping("/pessoas")
public class PessoaResource {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private PessoaService pessoaService;
   @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public List<Pessoa> lisarcategoria(){
        return pessoaRepository.findAll();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> inserirPessoa(@Valid @RequestBody Pessoa pessoa, HttpServletResponse httpServletResponse) {
        Pessoa psave = pessoaRepository.save(pessoa);
        publisher.publishEvent(new RecursoCriadoEvent(this,httpServletResponse,psave.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
    public ResponseEntity<?> pesquisaPorIdPessoa(@PathVariable Long id){
        Pessoa pessoa = pessoaRepository.findOne(id);
        return pessoa!=null?ResponseEntity.ok(pessoa):ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id){
       pessoaRepository.delete(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public ResponseEntity<?> atualizar(@PathVariable Long id,@Valid @RequestBody Pessoa pessoa){
        Pessoa atualizar = pessoaService.atualizar(id, pessoa);
        return ResponseEntity.ok(atualizar);
    }

    @PutMapping("/{id}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
    public void atualizarParcialAtivo(@PathVariable Long id,@RequestBody Boolean ativo){
       pessoaService.atualizarParcialAtivo(id,ativo);
    }
}
