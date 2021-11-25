package com.repuestos.finnegans.dao;

import com.repuestos.finnegans.entity.UserFinnegan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DaoUserFinnegan extends JpaRepository<UserFinnegan, Long> {
    UserFinnegan findByNombre(String nombre);
}
