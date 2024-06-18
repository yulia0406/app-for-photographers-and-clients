package org.example.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import liquibase.pro.packaged.V;
import org.example.model.ConsentPubl;
import org.example.model.Image;
import org.example.model.Photosession;
import org.example.model.Versions;
import org.example.service.ConsentPublService;
import org.example.service.IVersionsService;
import org.example.service.VersionsService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@RestController
@RequestMapping("/api/v1/version/")
public class VersionsRestController {
    @Autowired
    private IVersionsService versionsService;

    @RequestMapping(value = "getVersForClient", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getVersion() {
        return versionsService.getVersion();
    }

    @RequestMapping(value = "getImagesPublic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<byte[]> getImagesLinkProcess() {
        return versionsService.getImagesLinkProcess();

    }

    @RequestMapping(value = "getImagesPublicPhotograph/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<byte[]> getImagesPublicPhotograph(@PathVariable("name") String name) {
        return versionsService.getImagesPublicPhotograph(name);
    }

    @RequestMapping(value = "getImagesPublicClient/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<byte[]> getImagesPublicClient(@PathVariable("name") String name) {
        return versionsService.getImagesPublicClient(name);
    }



    @RequestMapping(value = "getImages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Image>> getImages() {
        return versionsService.getImages();
    }

    @RequestMapping(value = "getImagesForClient/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Image>> getImagesForClient(@PathVariable("name") String name) {
        return versionsService.getImagesForClient(name);
    }

    @RequestMapping(value = "createVersion/{name}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createVersion(@RequestBody @Valid Versions versions, @PathVariable("name") String name) {
        return versionsService.createVersion(versions, name);
    }

    @RequestMapping(value = "getImageLink", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<byte[]> getImagesLink(@RequestBody Photosession photosession) {
        return versionsService.getImagesLink(photosession);
    }

    @RequestMapping(value = "getNameImage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<String>> getNameImage(@RequestBody Photosession photosession) {
        return versionsService.getNameImage(photosession);
    }

    @RequestMapping(value = "updateVersionAuthor/{idVersion}/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateVersionAuthor(@PathVariable("idVersion") String idVersion,@PathVariable("name") String name, UriComponentsBuilder builder) {
        return versionsService.updateVersionAuthor(idVersion, name);
    }

    @RequestMapping(value = "updateCancel", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateCancel(@RequestBody Photosession photosession, UriComponentsBuilder builder) {
        return versionsService.updateCancel(photosession);
    }

    @RequestMapping(value = "updateVersionClient/{idVersion}/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> updateVersionClient(@PathVariable("idVersion") String idVersion,@PathVariable("name") String name, UriComponentsBuilder builder) {
        return versionsService.updateVdersionClient(idVersion, name);
    }

    @GetMapping(value = "/allIDversions/{login}")
    public ResponseEntity<List<Long>> getAllVersions(@PathVariable("login") String login) throws IOException {
        return versionsService.getAllVersions(login);
    }

}
