package com.octa.springapi.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties("Spring")
public class SpringApiProperty {
    private Seguranca seguranca=new Seguranca();
    private String originPermitida="";

    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    public Seguranca getSeguranca() {
        return seguranca;
    }

    public static class Seguranca{
        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }
}
