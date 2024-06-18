package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Status;
import org.example.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class StatusService implements IStatusService{
    @Autowired
    private final StatusRepository statusRepository;

    public ResponseEntity<Status> getFormat(String nameSratus) {
        if (nameSratus == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Status> statuses = this.statusRepository.findAll();

        for(int i = 0; i < statuses.size(); i++)
        {
            if(nameSratus.equals(statuses.get(i).getNameStatus())){
                return new ResponseEntity<>(statuses.get(i), HttpStatus.OK);
            }
        }
        System.out.println("ЗАШЛО В ПОЛУЧЕНИЕ ФОРМАТА");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}
