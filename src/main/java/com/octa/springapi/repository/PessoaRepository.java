package com.octa.springapi.repository;

import com.octa.springapi.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa,Long> {
    @Override
    <S extends Pessoa> S save(S s);
}
