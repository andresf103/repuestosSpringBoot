package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoProveedor;
import com.repuestos.finnegans.dao.DaoUserFinnegan;
import com.repuestos.finnegans.entity.Proveedor;
import com.repuestos.finnegans.entity.UserFinnegan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserFinneganEntityService {

    private DaoUserFinnegan daoUserFinnegan;

    @Autowired
    UserFinneganEntityService(DaoUserFinnegan daoUserFinnegan) {
        this.daoUserFinnegan = daoUserFinnegan;
    }

    public UserFinnegan save(UserFinnegan userFinnegan) {
        return daoUserFinnegan.save(userFinnegan);
    }

    public UserFinnegan update(UserFinnegan userFinnegan) {
        return daoUserFinnegan.save(userFinnegan);
    }

    public Optional<UserFinnegan> findByNombre(String nombre) {
        return daoUserFinnegan.findByNombre(nombre);
    }

    public List<UserFinnegan> findAll() {
        return daoUserFinnegan.findAll();
    }

}
