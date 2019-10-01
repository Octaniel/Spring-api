package com.octa.springapi.service;
import com.octa.springapi.model.Pessoa;
import com.octa.springapi.repository.PessoaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa atualizar(Long id,Pessoa pessoa){
        Pessoa pessoa1 = getPessoaid(id);
        BeanUtils.copyProperties(pessoa,pessoa1,"id");
        return pessoaRepository.save(pessoa1);
    }


    public void atualizarParcialAtivo(Long id, Boolean ativo) {
        Pessoa pessoaid = getPessoaid(id);
        pessoaid.setAtivo(ativo);
        pessoaRepository.save(pessoaid);
    }
    private Pessoa getPessoaid(Long id) {
        Pessoa pessoa1 = pessoaRepository.findOne(id);
        if(pessoa1==null){
            throw new EmptyResultDataAccessException(1);
        }
        return pessoa1;
    }
}
