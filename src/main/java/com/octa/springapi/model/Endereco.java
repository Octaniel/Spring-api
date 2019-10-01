package com.octa.springapi.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class Endereco {
    @Size(max = 20)
    @Column(name = "zona")
    private String zona;
    @Size(max = 20)
    @Column(name = "bairo")
    private String bairo;
    @Size(max = 20)
    @Column(name = "distrito")
    private String distrito;
    @Size(max = 20)
    @Column(name = "numero")
    private String numero;

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getBairro() {
        return bairo;
    }

    public void setBairro(String bairro) {
        this.bairo = bairro;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
