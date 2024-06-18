package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Photosession;
import org.example.model.Status;
import org.example.model.Users;
import org.example.repository.PhotosessionRepository;
import org.example.repository.StatusRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;

@Service
public class PhotosessionService implements IPhotosessionService {
    @Autowired
    private final PhotosessionRepository photosessionRepository;
    private final StatusRepository statusRepository;

    public PhotosessionService(PhotosessionRepository photosessionRepository, StatusRepository statusRepository) {
        this.photosessionRepository = photosessionRepository;
        this.statusRepository = statusRepository;
    }
    public ResponseEntity<String> getAllPhotosession() {
        List<Photosession> photosessions1 = this.photosessionRepository.findAll();
        if (photosessions1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("photosessions", photosessions1);
        System.out.println(photosessions1);
        try{
            String str = objectMapper.writeValueAsString(jsonMap);
            return new ResponseEntity<>(str, HttpStatus.OK);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<String> getAllPhotosessionforPhot(Long id) {
        List<Photosession> photosessions2 = new ArrayList<>();
        List<Photosession> photosessions1 = this.photosessionRepository.findAll();
        if (photosessions1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for(int i = 0; i<photosessions1.size();i++)
        {
            Users users = photosessions1.get(i).getAuthor();
            if(Objects.equals(users.getIdusers(), id))
            {
                photosessions2.add(photosessions1.get(i));
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("photosessions", photosessions2);
        System.out.println(photosessions1);
        try{
            String str = objectMapper.writeValueAsString(jsonMap);
            return new ResponseEntity<>(str, HttpStatus.OK);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    public ResponseEntity<String> getAllPhotosessionforClient(Long id) {
        List<Photosession> photosessions2 = new ArrayList<>();
        List<Photosession> photosessions1 = this.photosessionRepository.findAll();
        if (photosessions1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        for(int i = 0; i<photosessions1.size();i++)
        {
            Users users = photosessions1.get(i).getClient();

            if((users!=null)&&(users.getIdusers() == id))
            {
                photosessions2.add(photosessions1.get(i));
            }
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("photosessions", photosessions2);
        System.out.println(photosessions1);
        try{
            String str = objectMapper.writeValueAsString(jsonMap);
            return new ResponseEntity<>(str, HttpStatus.OK);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    public ResponseEntity<Photosession> deletePhotosession(Long id) {
        Photosession order = this.photosessionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Фотосессия с указанным ID не найдена"));
        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.photosessionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<String> updatePhotosessionStatus(String idPhotosession, String name) {
        HttpHeaders heards = new HttpHeaders();
        Photosession photosession = photosessionRepository.findById(Long.parseLong(idPhotosession)).orElseThrow(() -> new EntityNotFoundException("Фотосессия с указанным ID не найдена"));
        if (photosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Status status = statusRepository.findByNameStatus(name);
        if (status == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        photosession.setStatus(status);
        System.out.println("photo");
        this.photosessionRepository.save(photosession);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("str", "Изменено успешно");
        String json2 = jsonObject1.toString();
        return new ResponseEntity<>(json2, heards, HttpStatus.OK);
    }


    public ResponseEntity<String> updatePhotosessionWithoutClient(String idPhotosession, Photosession photosession1) {
        HttpHeaders heards = new HttpHeaders();
        Photosession photosession = photosessionRepository.findById(Long.parseLong(idPhotosession)).orElseThrow(() -> new EntityNotFoundException("Фотосессия с указанным ID не найдена"));
        if (photosession1 == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        photosession.setNumProcessed(photosession1.getNumProcessed());
        String json = new JSONObject(photosession1.getStatus()).toString();
        System.out.println(json);
        Status status = Status.getStatus(json);
        photosession.setStatus(status);
        this.photosessionRepository.save(photosession);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("str", "Изменено успешно");
        String json2 = jsonObject1.toString();
        return new ResponseEntity<>(json2, heards, HttpStatus.OK);
    }

    public ResponseEntity<String> updatePhotosession(String idPhotosession, Photosession photosession1) {
        HttpHeaders heards = new HttpHeaders();
        Photosession photosession = photosessionRepository.findById(Long.parseLong(idPhotosession)).orElseThrow(() -> new EntityNotFoundException("Фотосессия с указанным ID не найдена"));
        if (photosession1 == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String json = new JSONObject(photosession1.getClient()).toString();
        Users users1 = Users.getClient(json);
        photosession.setClient(users1);
        photosession.setNumProcessed(photosession1.getNumProcessed());
        this.photosessionRepository.save(photosession);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("str", "Изменено успешно");
        String json2 = jsonObject1.toString();
        return new ResponseEntity<>(json2, heards, HttpStatus.OK);
    }

    public ResponseEntity<String> createPhotosession(Photosession photosession) {
        HttpHeaders headers = new HttpHeaders();
        if (photosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.photosessionRepository.save(photosession);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strcr", "Альбом создан!");
        String json1 = jsonObject1.toString();
        return new ResponseEntity<>(json1, headers, HttpStatus.OK);
    }

    public ResponseEntity<Photosession> getPhotosession(Long idPhotosession) {
        if (idPhotosession == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Photosession photosession = this.photosessionRepository.findById(idPhotosession).orElseThrow(() -> new EntityNotFoundException("Фотосессия с указанным ID не найдена"));
        if (idPhotosession == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(photosession, HttpStatus.OK);


    }
}
