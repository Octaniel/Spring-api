package com.octa.springapi.resource;

import com.octa.springapi.event.RecursoCriadoEvent;
import com.octa.springapi.model.Lancamento;
import com.octa.springapi.model.Lancamento_;
import com.octa.springapi.model.Pessoa;
import com.octa.springapi.repository.LancamentoRepository;
import com.octa.springapi.repository.filter.LancamentoFilter;
import com.octa.springapi.repository.projection.LancamentoResumo;
import com.octa.springapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lancamento")
public class LancamentoResource {
    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private LancamentoService lancamentoService;
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<Lancamento> filtrar(LancamentoFilter lancamentofilter, Pageable pageable){
        return lancamentoRepository.filtrar(lancamentofilter,pageable);
    }@GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public Page<LancamentoResumo> rsumo(LancamentoFilter lancamentofilter, Pageable pageable){
        return lancamentoRepository.resumo(lancamentofilter,pageable);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<?> pesquisaPorIdPessoa(@PathVariable Long id){
        Lancamento lancamento = lancamentoRepository.findOne(id);
        return lancamento!=null?ResponseEntity.ok(lancamento):ResponseEntity.notFound().build();
    }
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Lancamento> inserirPessoa(@Valid @RequestBody Lancamento lancamento, HttpServletResponse httpServletResponse) {
        Lancamento save = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this,httpServletResponse,save.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamento);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id){
        lancamentoRepository.delete(id);
    }
}
