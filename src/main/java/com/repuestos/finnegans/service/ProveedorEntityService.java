package com.repuestos.finnegans.service;

import com.repuestos.finnegans.dao.DaoOrden;
import com.repuestos.finnegans.dao.DaoProveedor;
import com.repuestos.finnegans.entity.Orden;
import com.repuestos.finnegans.entity.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorEntityService {

    private DaoProveedor daoProveedor;

    @Autowired
    ProveedorEntityService(DaoProveedor daoProveedor) {
        this.daoProveedor = daoProveedor;
    }

    public Proveedor save(Proveedor proveedor) {
        return daoProveedor.save(proveedor);
    }

    public Proveedor update(Proveedor proveedor) {
        return daoProveedor.save(proveedor);
    }

    public Optional<Proveedor> findByNombre(String nombre) {
        return daoProveedor.findByNombre(nombre);
    }

    public List<Proveedor> findAll() {
        return daoProveedor.findAll();
    }

}
