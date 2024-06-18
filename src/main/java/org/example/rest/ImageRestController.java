package org.example.rest;

import org.example.model.Image;
import org.example.model.Photosession;
import org.example.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/image/")
public class ImageRestController {
    @Autowired
    private IImageService imageService;


    @RequestMapping(value = "{id_image}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Image> getImage(@PathVariable("id_image") Long idImage) {
        return imageService.getImage(idImage);
    }

    @RequestMapping(value = "getImageLink", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<byte[]> getImagesLink(@RequestBody Photosession photosession) {
       return  imageService.getImagesLink(photosession);
    }

    @RequestMapping(value = "getImageLinkProcess", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<byte[]> getImagesLinkProcess(@RequestBody List<String> links) {
       return imageService.getImagesLinkProcess(links);
    }

    @RequestMapping(value = "deleteImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Image> deleteImagePhotosession(@RequestBody Image imagePhotosession) {
        return imageService.deleteImagePhotosession(imagePhotosession);
    }

    @RequestMapping(value = "getNameImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> getNameImage(@RequestBody Photosession photosession) {
        return imageService.getNameImage(photosession);
    }

    @RequestMapping(value = "createLink", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createLink(@RequestParam("file")MultipartFile file, @RequestParam("name") String name) {
        return imageService.createLink(file, name);
    }

    @RequestMapping(value = "createLinkProcessing", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createLinkProcessing(@RequestParam("file")MultipartFile file, @RequestParam("name") String name) {
        System.out.println("fghjkl;");
        return imageService.createLinkProcessing(file, name);
    }

    @PostMapping("/downloadPhoto")
    public ResponseEntity<org.springframework.core.io.Resource> downloadPhoto(@RequestBody String name) throws IOException {
        return imageService.downloadPhoto(name);
    }

    @PostMapping("/downloadPhotoProcess")
    public ResponseEntity<org.springframework.core.io.Resource> downloadPhotoProcess(@RequestBody String name) throws IOException {
       return imageService.downloadPhotoProcess(name);
    }


    @RequestMapping(value = "createImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        return imageService.createImage(image);
    }

    @RequestMapping(value = "updateImage/{idImage}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Image> updateImage(@PathVariable("idImage") Long id,@RequestBody Image image) {
        return imageService.updateImage(id, image);
    }

    @RequestMapping(value = "getImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Image>> getImage(@RequestBody Photosession photosession) {
        return imageService.getImagePhot(photosession);
    }

    @RequestMapping(value = "getallimage", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getAllImage() {
        return imageService.getAllImage();
    }
    
}
