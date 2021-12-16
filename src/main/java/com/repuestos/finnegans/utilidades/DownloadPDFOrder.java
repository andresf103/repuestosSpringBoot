package com.repuestos.finnegans.utilidades;

import com.microsoft.playwright.*;
import com.repuestos.finnegans.dto.TrackingDTO;
import lombok.extern.slf4j.Slf4j;


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


    private void descargarOrdenDeCompra(Page currentPage, BrowserContext context, TrackingDTO orden) {
/**aca necesito sacar la informacion de la solicitud -> usuario ->email
 * orden de compra-> empresa->email
 * para esto del tracking de la orden de compra necesito conseguir el id-transaction de la solicitud
 * y con esa informacion conseguir la info de la solicitud para conseguir el usuario.
 */
        Page pageForDownload = context.newPage();
        String constructUrl = String.format(URL_PARTIAL, orden.getTransactionIdInicial()) + URL_PARTIAL_FINAL;
        try {
            pageForDownload.navigate(constructUrl);
        } catch (Exception e) {
            log.error("Error al descargar" + e.getMessage());
        }
        try {
            Thread.sleep(1000l);

        try {
            renameFileOrdenDeCompra(orden.getOrigen());
        } catch (IOException e) {
            log.info("error al renombrar el archivo" + e.getMessage());
        }
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
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

    public void downloadAllOrders(List<TrackingDTO> ordenesDeCompra) {
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
