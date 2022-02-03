package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoMail;
import com.repuestos.finnegans.entity.Mail;
import com.repuestos.finnegans.entity.Tracking;
import com.repuestos.finnegans.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MailEntityService {

    private DaoMail daoMail;

    @Autowired
    MailEntityService(DaoMail daoMail) {
        this.daoMail = daoMail;
    }
    public Mail findByTracking(Tracking tracking){
        return daoMail.findByTracking(tracking).orElse(null);
    }
    public Mail save(Mail mail) {
        return daoMail.save(mail);
    }

    public Mail update(Mail mail) {
        return daoMail.save(mail);
    }

    public List<Mail> findAllByStatus(Status status) {
        return daoMail.findAllByStatus(status);
    }
    public List<Mail> findAll(){
        return daoMail.findAll();
    }

}
