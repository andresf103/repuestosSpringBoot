package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoTracking extends JpaRepository<Tracking,Long> {

    Optional<Tracking> findByTransactionId(Long transactionId);
}
