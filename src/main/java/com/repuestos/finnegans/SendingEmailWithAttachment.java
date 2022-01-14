package com.repuestos.finnegans;

import com.repuestos.finnegans.dto.TrackingDTO;
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

    private final TrackingRestService trackingRestService;
    private final UserRestService userRestService;
    private final OrdenEntityService ordenEntityService;
    private final ProveedorRestService proveedorRestService;


    @Autowired
    public SendingEmailWithAttachment(SolicitudEntityService solicitudEntityService,
                                      UserFinneganEntityService userFinneganEntityService,
                                      ProveedorEntityService proveedorEntityService,
                                      MailEntityService mailEntityService,
                                      SolicitudRestService solicitudRestService,
                                      TrackingRestService trackingRestService,
                                      UserRestService userRestService,
                                      OrdenEntityService ordenEntityService,ProveedorRestService proveedorRestService) {
        this.solicitudEntityService = solicitudEntityService;
        this.userFinneganEntityService = userFinneganEntityService;
        this.proveedorEntityService = proveedorEntityService;
        this.mailEntityService = mailEntityService;
        this.solicitudRestService = solicitudRestService;
        this.trackingRestService = trackingRestService;
        this.userRestService = userRestService;
        this.ordenEntityService = ordenEntityService;
        this.proveedorRestService = proveedorRestService;
    }

    @Scheduled(fixedDelayString = "${downloadOrdersInterval}")
    public void descargarPdfs() {
        List<TrackingDTO> ordenes = null;
        try {
            ordenes = trackingRestService.findAllFromToday();
            DownloadPDFOrder pdfs = new DownloadPDFOrder();
            List<TrackingDTO> ids = trackingRestService.idsOrders();
            log.info(ids.toString());
            if (!ids.isEmpty()) {
                pdfs.downloadAllOrders(ids);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        log.info(ordenes.toString());
    }
    /*
    * todo:por cada solicitud solo envia la primer orden en caso de que
    *  tenga mas de una orden asociada a la solicitud
    * debemos encontrar la manera de que considere todas las ordenes*/
@Scheduled(fixedDelay = 86400000L, initialDelay =86400000L)
public void obteniendoProveedores(){
    try {proveedorRestService.findAll();
    } catch (URISyntaxException e) {
        e.printStackTrace();
    }
}
    @Scheduled(fixedDelayString = "${sendingEmailInterval}", initialDelay = 900000L)
    public void sendEmails() {
        try {
            solicitudRestService.findAllFromToday();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        List<Mail> mails = mailEntityService.findAll();
        mails=mails.stream().filter(mail -> mail.getStatus().equals(Status.NEW)
                ||mail.getStatus().equals(Status.FAILED)).collect(Collectors.toList());
        log.info(mails.toString());
        mails.forEach(mail -> {
            Optional<Solicitud> solicitud = solicitudEntityService.findByTransaccionId(mail.getTracking()
                    .getTransactionIdInicial());
            Optional<UserFinnegan> userFinnegan = userFinneganEntityService.findByNombre(solicitud.isPresent() ? solicitud.get().getNombreUsuarioAlta() : "noencontrado");
            String emailUsuario = !userFinnegan.isPresent() ? "andresoicsa@gmail.com" : userFinnegan.get().getEmail();
            Long transactionIdOrden=mail.getTracking().getTransactionId();
            Optional<Orden> orden=ordenEntityService.findByTransactionId(transactionIdOrden);
            Optional<Proveedor> proveedor = proveedorEntityService.findByNombre(orden.isPresent()?orden.get().getProveedor():"noencontrado");
            String emailProveedor = !proveedor.isPresent() ? "andresoicsa@gmail.com" : proveedor.get().getEmail();
            emailProveedor=emailProveedor.isEmpty()?"andresoicsa@gmail.com":emailProveedor;
            SendEmail sendEmail = new SendEmail();
            try {
                sendEmail.send(solicitud.get(), emailProveedor, emailUsuario, downloadPath + "/ordenes/"
                        + mail.getTracking().getNumero() + ".pdf");
                mail.setSendDate(new Date().toInstant());
                mail.setStatus(Status.COMPLETED);
            } catch (Exception e) {
                e.printStackTrace();
                mail.setStatus(Status.FAILED);
            } finally {
                mailEntityService.update(mail);
            }
        });
    }

}
