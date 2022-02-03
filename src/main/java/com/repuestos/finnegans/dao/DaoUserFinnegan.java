package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.UserFinnegan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DaoUserFinnegan extends JpaRepository<UserFinnegan, Long> {
    Optional<UserFinnegan> findByNombre(String nombre);
}
