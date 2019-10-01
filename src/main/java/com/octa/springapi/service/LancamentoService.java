package com.octa.springapi.service;

import com.octa.springapi.model.Lancamento;
import com.octa.springapi.model.Pessoa;
import com.octa.springapi.repository.LancamentoRepository;
import com.octa.springapi.repository.PessoaRepository;
import com.octa.springapi.service.exception.PesssoaInexistenteOuInativaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    public Lancamento salvar(Lancamento lancamento){
        Pessoa pessoa1 = pessoaRepository.findOne(lancamento.getPessoa().getId());
        if((pessoa1==null)||(!pessoa1.getAtivo())){
            throw  new PesssoaInexistenteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }
}
