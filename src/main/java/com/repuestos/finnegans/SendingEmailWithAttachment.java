package com.repuestos.finnegans;

import com.repuestos.finnegans.dto.OrdenDTO;
import com.repuestos.finnegans.entity.*;
import com.repuestos.finnegans.service.*;
import com.repuestos.finnegans.utilidades.DownloadPDFOrder;
import com.repuestos.finnegans.utilidades.SendEmail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class SendingEmailWithAttachment {

    private final SolicitudEntityService solicitudEntityService;
    private final UserFinneganEntityService userFinneganEntityService;
    private final ProveedorEntityService proveedorEntityService;
    private final MailEntityService mailEntityService;
    private final SolicitudRestService solicitudRestService;

    @Value( "${downloadPath}" )
    private String downloadPath;

    private final OrdenRestService ordenRestService;
    private final UserRestService userRestService;

    @Autowired
    public SendingEmailWithAttachment(SolicitudEntityService solicitudEntityService,
                                      UserFinneganEntityService userFinneganEntityService,
                                      ProveedorEntityService proveedorEntityService,
                                      MailEntityService mailEntityService, SolicitudRestService solicitudRestService, OrdenRestService ordenRestService, UserRestService userRestService) {
        this.solicitudEntityService = solicitudEntityService;
        this.userFinneganEntityService = userFinneganEntityService;
        this.proveedorEntityService = proveedorEntityService;
        this.mailEntityService = mailEntityService;
        this.solicitudRestService = solicitudRestService;
        this.ordenRestService = ordenRestService;
        this.userRestService = userRestService;
    }

    @Scheduled(fixedDelayString = "${downloadOrdersInterval}")
    public void descargarPdfs() {
        List<OrdenDTO> ordenes = null;
        try {
            ordenes = ordenRestService.findAllFromToday();
            DownloadPDFOrder pdfs = new DownloadPDFOrder();
            List<String> ids = ordenRestService.idsOrders();
            log.info(ids.toString());
            if (!ids.isEmpty()) {
                pdfs.downloadAllOrders(ids);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info(ordenes.toString());
    }

    @Scheduled(fixedDelayString = "${sendingEmailInterval}")
    public void obteniendoInformacion() {
        try {
            solicitudRestService.findAllFromToday();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<Mail> mails = mailEntityService.findAll();
        mails=mails.stream().filter(mail -> mail.getStatus().equals(Status.NEW)||mail.getStatus().equals(Status.FAILED)).collect(Collectors.toList());
        //(Status.NEW);
        log.info(mails.toString());
        mails.forEach(mail -> {
            Optional<Solicitud> solicitud = solicitudEntityService.findByTransaccionId(mail.getOrden()
                    .getTransaccionIdInicial());
            Optional<UserFinnegan> userFinnegan = userFinneganEntityService.findByNombre(solicitud.isPresent() ? solicitud.get().getNombreUsuarioAlta() : "");
            String emailUsuario = !userFinnegan.isPresent() ? "andresoicsa@gmail.com" : userFinnegan.get().getEmail();
            Optional<Proveedor> proveedor = proveedorEntityService.findByNombre(mail.getOrden().getEmpresa());
            String emailProveedor = !proveedor.isPresent() ? "andresoicsa@gmail.com" : proveedor.get().getEmail();
            SendEmail sendEmail = new SendEmail();
            try {
                sendEmail.send(emailProveedor, emailUsuario, downloadPath + "/ordenes/" + mail.getOrden()
                        .getTransaccionId().toString() + ".pdf");
                mail.setSendDate(new Date().toInstant());
                mail.setStatus(Status.COMPLETED);
            } catch (Exception e) {
                mail.setStatus(Status.FAILED);
            } finally {
                mailEntityService.update(mail);
            }
        });
    }

}
