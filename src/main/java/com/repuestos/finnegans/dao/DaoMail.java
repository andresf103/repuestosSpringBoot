package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Mail;
import com.repuestos.finnegans.entity.Tracking;
import com.repuestos.finnegans.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DaoMail extends JpaRepository<Mail,Long> {
    Optional<Mail> findByTracking(Tracking tracking);
    List<Mail> findAllByStatus(Status status);
}
