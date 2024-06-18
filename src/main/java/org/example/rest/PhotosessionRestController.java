package org.example.rest;

import org.example.model.Photosession;
import org.example.service.IPhotosessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/photosession/")
public class PhotosessionRestController {
    @Autowired
    private IPhotosessionService photosessionService;


    @RequestMapping(value = "get/{id_photosession}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Photosession> getPhotosession(@PathVariable("id_photosession") Long idPhotosession) {
       return photosessionService.getPhotosession(idPhotosession);
    }


    @RequestMapping(value = "createPhotosession", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createPhotosession(@RequestBody @Valid Photosession photosession) {
        return photosessionService.createPhotosession(photosession);
    }

    @RequestMapping(value = "updatePhotosession/{idPhotosession}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updatePhotosession(@PathVariable String idPhotosession, @RequestBody Photosession photosession1, UriComponentsBuilder builder) {
        return photosessionService.updatePhotosession(idPhotosession, photosession1);
    }

    @RequestMapping(value = "updatePhotosessionWithoutClient/{idPhotosession}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updatePhotosessionWithoutClient(@PathVariable String idPhotosession, @RequestBody Photosession photosession1, UriComponentsBuilder builder) {
       return photosessionService.updatePhotosessionWithoutClient(idPhotosession, photosession1);
    }

    @RequestMapping(value = "updatePhotosessionStatus/{idPhotosession}/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updatePhotosessionStatus(@PathVariable String idPhotosession, @PathVariable String name) {
        return photosessionService.updatePhotosessionStatus(idPhotosession, name);
    }

    @RequestMapping(value = "delete/{id_Photosession}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Photosession> deletePhotosession(@PathVariable("id_Photosession") Long id) {
        return photosessionService.deletePhotosession(id);
    }


    @RequestMapping(value = "getAllPhotosessionsforClient/{id_client}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getAllPhotosessionforClient(@PathVariable("id_client") Long id) {
        return photosessionService.getAllPhotosessionforClient(id);
    }

    @RequestMapping(value = "getAllPhotosessionsforPhot/{id_phot}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getAllPhotosessionforPhot(@PathVariable("id_phot") Long id) {
       return photosessionService.getAllPhotosessionforPhot(id);
    }

    @RequestMapping(value = "getAllPhotosessions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getAllPhotosession() {
        return photosessionService.getAllPhotosession();

    }

}
