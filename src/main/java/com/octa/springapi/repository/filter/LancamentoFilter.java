package com.octa.springapi.repository.filter;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class LancamentoFilter {
    private String descricao;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datavencimentode;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datavencimentoate;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDatavencimentode() {
        return datavencimentode;
    }

    public void setDatavencimentode(LocalDate datavencimentode) {
        this.datavencimentode = datavencimentode;
    }

    public LocalDate getDatavencimentoate() {
        return datavencimentoate;
    }

    public void setDatavencimentoate(LocalDate datavencimentoate) {
        this.datavencimentoate = datavencimentoate;
    }
}
