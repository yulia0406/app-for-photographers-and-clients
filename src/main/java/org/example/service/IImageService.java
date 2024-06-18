package org.example.service;
import org.example.model.Image;
import org.example.model.Photosession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    ResponseEntity<Image> getImage(Long idImage);
    ResponseEntity<List<Image>> getImagePhot(Photosession photosession);
    ResponseEntity<byte[]> getImagesLink(Photosession photosession);
    ResponseEntity<byte[]> getImagesLinkProcess(List<String> links);
    ResponseEntity<Image> deleteImagePhotosession(Image imagePhotosession);
    ResponseEntity<List<String>> getNameImage(Photosession photosession);
    ResponseEntity<String> createLink(MultipartFile file, String name);
    ResponseEntity<String> createLinkProcessing(MultipartFile file, String name);
    ResponseEntity<org.springframework.core.io.Resource> downloadPhoto(String name);
    ResponseEntity<org.springframework.core.io.Resource> downloadPhotoProcess(String name);
    ResponseEntity<Image> createImage(Image image);
    ResponseEntity<Image> updateImage(Long id, Image image);
    ResponseEntity<String> getAllImage();
}
