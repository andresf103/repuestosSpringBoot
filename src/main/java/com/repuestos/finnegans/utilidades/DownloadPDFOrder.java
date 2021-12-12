package com.repuestos.finnegans.utilidades;

import com.microsoft.playwright.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.File;
import java.io.IOException;

import java.nio.file.Path;
import java.util.*;

/**
 * descarga los archivos pdfs a traves de PlayWrigt
 */
@Slf4j
public class DownloadPDFOrder {

    private static final String URL_PARTIAL = "https://2.teamplace.finneg.com/BSA/Sr2?XMLFILE=Factura.jrxml&DATASOURCE=F_BS_OP_0010&primaryKey=%s&MULTIREPORTPARAMS=Factura.jrxml";
    private static final String URL_PARTIAL_FINAL = "%7CF_BS_OP_0010%7COrden%20de%20Compra%7C1%7C&EMPRESAIDTRANSACCION=51";
    private static final String downloadPath="/home/andres/Descargas";


    private void descargarOrdenDeCompra(Page currentPage, BrowserContext context, String idOrden) {
/**aca necesito sacar la informacion de la solicitud -> usuario ->email
 * orden de compra-> empresa->email
 * para esto del tracking de la orden de compra necesito conseguir el id-transaction de la solicitud
 * y con esa informacion conseguir la info de la solicitud para conseguir el usuario.
 */
        Page pageForDownload = context.newPage();
        String constructUrl = String.format(URL_PARTIAL, idOrden) + URL_PARTIAL_FINAL;
        try {
            pageForDownload.navigate(constructUrl);
        } catch (Exception e) {
        }
        try {
            renameFileOrdenDeCompra(idOrden);
        } catch (IOException e) {
            log.info(e.getMessage());
        }
        pageForDownload.close();
    }

    private void renameFileOrdenDeCompra(String idOrden) throws IOException {
        File file = new File(downloadPath + "/Orden de Compra.pdf");
        if (file.exists()) {
            File file2 = new File(downloadPath + "/ordenes/" + idOrden + ".pdf");
            boolean ok = file.renameTo(file2);
            if (ok) {
                log.info("todo ok");
            }
        } else {
            log.error("no se encontro el archivo");
        }
    }

    public void downloadAllOrders(List<String> ordenesDeCompra) {
        List<String> argumentos = new ArrayList<>();
        argumentos.add("--profile-directory=\"Profile 1\"");

        try (Playwright playwright = Playwright.create()) {
            BrowserContext context = playwright.chromium().launchPersistentContext(Path.of("/home/andres/.config/google-chrome/Default"), new BrowserType.LaunchPersistentContextOptions()
                    .setExecutablePath(Path.of("/opt/google/chrome/google-chrome")).setDownloadsPath(Path.of(downloadPath)).setHeadless(false).setArgs(argumentos).setAcceptDownloads(true));
            Page page = context.newPage();
            page.navigate("https://services.finneg.com/login");
            page.fill("#loginname", "afernandez");
            page.press("#loginname", "Tab");
            page.fill("#loginpassword", "oic123");
            page.press("#loginpassword", "Tab");
            if (!page.inputValue("#logincompany").equals("oic")) {
                page.fill("#logincompany", "oic");
            }
            page.press("#logincompany", "Tab");
            page.waitForNavigation(() -> page.click("text=Ingresar"));
            /*for each idOrder do*/
            ordenesDeCompra.forEach(idOrder -> descargarOrdenDeCompra(page, context, idOrder));
        }
    }
}
