package com.repuestos.finnegans;

public enum EndPoints {
    GET_TOKEN("https://api.teamplace.finneg.com/api/oauth/token?grant_type=client_credentials&client_id=%s&client_secret=%s"),
    TRACKING("https://api.teamplace.finneg.com/api/reports/tracking?domain=oic" +
            "&PARAMWEBREPORT_FechaDesde=%s" +
            "&PARAMWEBREPORT_FechaHasta=%s"),
    SOLICITUD("https://api.teamplace.finneg.com/api/reports/pedidosdecompra?"+
                    "domain=oic" +
                    "&PARAMWEBREPORT_FechaDesde=%s" +
                    "&PARAMWEBREPORT_FechaHasta=%s" +
                    "&PARAMEmpresa=OICSA1"),
    RENEW_TOKEN("https://api.teamplace.finneg.com/api/oauth/token?grant_type=refresh_token&client_id=%s&client_secret=%s&refresh_token=%s");

    private String url;

    EndPoints(String url){
        this.url=url;
    }

    public String getUrl() {
        return url;
    }
}
