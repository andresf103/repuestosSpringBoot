package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.Orden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DaoOrden extends JpaRepository<Orden,Long> {

    Optional<Orden> findByTransactionId(Long transaccionId);
   /* @Query("SELECT m FROM MLRawNotification m WHERE m.userId=?1 AND (m.status='READED' OR m.status='NEW') ORDER BY id DESC")
    Page<MLRawNotification> findByUserIdAndStatusesOrderByIdDesc(String userId, Pageable paging);*/
    @Query("SELECT o FROM Orden o ORDER BY o.transactionId DESC")
    Page<Orden> findTopOrderDesc(Pageable paging);

}
