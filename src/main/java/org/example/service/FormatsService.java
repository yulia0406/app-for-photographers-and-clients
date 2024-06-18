package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.Format;
import org.example.model.Versions;
import org.example.repository.FormatsRepository;
import org.example.service.VersionsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
@Service
public class FormatsService implements IFormatsService{
    @Autowired
    private final FormatsRepository formatsRepository;

    public FormatsService(FormatsRepository formatsRepository) {
        this.formatsRepository = formatsRepository;
    }

    public ResponseEntity<Format> getFormat(String nameFormat) {
        if (nameFormat == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<Format> formats = this.formatsRepository.findAll();
        for(int i = 0; i < formats.size(); i++)
        {
            if(nameFormat.equals(formats.get(i).getNameFormat())){
                return new ResponseEntity<>(formats.get(i), HttpStatus.OK);
            }
        }
        System.out.println("ЗАШЛО В ПОЛУЧЕНИЕ ФОРМАТА");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}
