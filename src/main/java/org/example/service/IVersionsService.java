package org.example.service;
import org.example.model.Image;
import org.example.model.Photosession;
import org.example.model.Versions;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IVersionsService {
    ResponseEntity<String> getVersion();
    ResponseEntity<byte[]> getImagesLinkProcess();
    ResponseEntity<byte[]> getImagesPublicPhotograph(String name);
    ResponseEntity<byte[]> getImagesPublicClient(String name);
    ResponseEntity<List<Image>> getImages();
    ResponseEntity<List<Image>> getImagesForClient(String name);
    ResponseEntity<String> createVersion(Versions versions, String name);
    ResponseEntity<byte[]> getImagesLink(Photosession photosession);
    ResponseEntity<List<String>> getNameImage(Photosession photosession);
    ResponseEntity<String> updateVersionAuthor(String idVersion, String name);
    ResponseEntity<String> updateCancel(Photosession photosession);
    ResponseEntity<String> updateVdersionClient(String idVersion, String name);
    ResponseEntity<List<Long>> getAllVersions(String login);
}
