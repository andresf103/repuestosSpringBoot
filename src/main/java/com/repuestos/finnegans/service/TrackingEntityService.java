package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoMail;
import com.repuestos.finnegans.dao.DaoTracking;
import com.repuestos.finnegans.entity.Mail;
import com.repuestos.finnegans.entity.Tracking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackingEntityService {

    private DaoTracking daoTracking;
    private DaoMail daoMail;

    @Autowired
    TrackingEntityService(DaoTracking daoTracking, DaoMail daoMail) {
        this.daoTracking = daoTracking;
        this.daoMail=daoMail;
    }

    public Tracking save(Tracking tracking) {
        Mail mail=new Mail(tracking);
        daoMail.save(mail);
        return daoTracking.save(tracking);
    }

    public Tracking update(Tracking tracking) {
        return daoTracking.save(tracking);
    }

    public Tracking findByTransactionId(Long transaccionId) {
        Tracking tracking = daoTracking.findByTransactionId(transaccionId).orElse(null);
        return tracking;
    }

    public List<Tracking> findAll() {
        return daoTracking.findAll();
    }

}
