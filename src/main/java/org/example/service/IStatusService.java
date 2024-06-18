package org.example.service;


import org.example.model.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IStatusService {

    ResponseEntity<Status> getFormat(String nameSratus);

}
