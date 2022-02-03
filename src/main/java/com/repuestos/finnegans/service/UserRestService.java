package com.repuestos.finnegans.service;

import com.repuestos.finnegans.EndPoints;
import com.repuestos.finnegans.dto.SolicitudDTO;
import com.repuestos.finnegans.dto.UserDTO;
import com.repuestos.finnegans.entity.Solicitud;
import com.repuestos.finnegans.entity.UserFinnegan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@Service
public class UserRestService extends AbstractRestService{

    private UserFinneganEntityService userFinneganEntityService;

    @Autowired
    public UserRestService(UserFinneganEntityService userFinneganEntityService){
        super();
        this.userFinneganEntityService=userFinneganEntityService;
    }

    public void getUsers() throws URISyntaxException {
        ParameterizedTypeReference<List<UserDTO>> userList;
        userList = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<UserDTO>> response = executeRequest(EndPoints.USUARIOS.getUrl(), HttpMethod.GET, userList);
        if (HttpStatus.OK.equals(response.getStatusCode())) {
            log.info(response.getBody().toString());
            List<UserDTO> list = response.getBody();
            list.forEach(userDTO -> {
                if(!userFinneganEntityService.findByNombre(userDTO.getNombre()).isPresent()){
                    userFinneganEntityService.save(new UserFinnegan(userDTO));
                }
            });
        } else {
            log.error("There was an error\n" + response.getBody());
            throw new RuntimeException("Hubo un error");
        }
    }

}
