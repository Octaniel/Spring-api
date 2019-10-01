package com.octa.springapi.repository.lancamento;

import com.octa.springapi.model.Lancamento;
import com.octa.springapi.repository.filter.LancamentoFilter;
import com.octa.springapi.repository.projection.LancamentoResumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LancamentoRepositoryQuery {
    Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    Page<LancamentoResumo> resumo(LancamentoFilter lancamentoFilter, Pageable pageable);

}
