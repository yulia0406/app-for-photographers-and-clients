package org.example.service;
import org.example.model.Photosession;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPhotosessionService {
    ResponseEntity<Photosession> getPhotosession(Long idPhotosession);
    ResponseEntity<String> createPhotosession(Photosession photosession);
    ResponseEntity<String> updatePhotosession(String idPhotosession, Photosession photosession1);
    ResponseEntity<String> updatePhotosessionWithoutClient(String idPhotosession, Photosession photosession1);
    ResponseEntity<String> updatePhotosessionStatus(String idPhotosession, String name);
    ResponseEntity<Photosession> deletePhotosession(Long id);
    ResponseEntity<String> getAllPhotosessionforClient(Long id);
    ResponseEntity<String> getAllPhotosessionforPhot(Long id);
    ResponseEntity<String> getAllPhotosession();

}
