package com.repuestos.finnegans;

public enum EndPoints {
    GET_TOKEN("https://api.teamplace.finneg.com/api/oauth/token?grant_type=client_credentials&client_id=%s&client_secret=%s"),
    TRACKING("https://api.teamplace.finneg.com/api/reports/tracking?" +
            "domain=oic" +
            "&PARAMWEBREPORT_FechaDesde=%s" +
            "&PARAMWEBREPORT_FechaHasta=%s" +
            "&PARAMEmpresa=OICSA1"),
    SOLICITUD("https://api.teamplace.finneg.com/api/reports/pedidosdecompra?" +
            "domain=oic" +
            "&PARAMWEBREPORT_FechaDesde=%s" +
            "&PARAMWEBREPORT_FechaHasta=%s" +
            "&PARAMEmpresa=OICSA1"),
    USUARIOS("https://api.teamplace.finneg.com/api/reports/UsuarioAndres?domain=oic"),
    ORDEN("https://api.teamplace.finneg.com/api/reports/ordenesdecompra?" +
            "domain=oic"+
            "&PARAMWEBREPORT_FechaDesde=%s" +
            "&PARAMWEBREPORT_FechaHasta=%s" +
            "&PARAMEmpresa=OICSA1"),
    PROVEEDORES("https://api.teamplace.finneg.com/api/reports/proveedoresandres?domain=oic"),
    RENEW_TOKEN("https://api.teamplace.finneg.com/api/oauth/token?grant_type=refresh_token&client_id=%s&client_secret=%s&refresh_token=%s"),
    ORDENDETAIL("https://api.teamplace.finneg.com/api/reports/analisisdeordenes?" +
            "domain=oic" +
            "&PARAMWEBREPORT_FechaDesde=%s" +
            "&PARAMWEBREPORT_IncluirConceptosCalculados=true" +
            "&PARAMWEBREPORT_FechaHasta=%s" +
            "&PARAMWEBREPORT_Empresa=OICSA1");

    private String url;

    EndPoints(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
