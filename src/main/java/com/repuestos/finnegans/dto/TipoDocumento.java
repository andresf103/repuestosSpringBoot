package com.repuestos.finnegans.dto;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TipoDocumento {
    SOLICITUD("Solicitud"),
    SOLICITUD_REPUESTOS("Solicitud Repuestos"),
    ORDEN_DE_COMPRA("Orden de Compra"),
    ORDEN_DE_COMPRA_REPUESTOS("Orden de Compra Repuestos"),
    FACTURA_DE_COMPRA("Factura de Compra"),
    FACTURA_DE_COMPRA_REPUESTOS("Factura de Compra Repuestos");

    private String documento;

    TipoDocumento(String documento) {
        this.documento = documento;
    }

    public String getDocumento() {
        return documento;
    }

    public static TipoDocumento value(String documento) {
        return Arrays.stream(TipoDocumento.values())
                .filter(tipoDocumento -> tipoDocumento.getDocumento()
                        .equals(documento)).collect(Collectors.toList()).get(0);
    }
}
