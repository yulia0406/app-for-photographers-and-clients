package org.example.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.model.Format;
import org.example.model.Versions;
import org.example.service.FormatsService;
import org.example.service.IFormatsService;
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
import java.util.*;

@RestController
@RequestMapping("/api/v1/format/")
public class FormatRestController {
    @Autowired
    private IFormatsService formatsService;

    @RequestMapping(value = "getId/{name_format}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Format> getFormat(@PathVariable("name_format") String nameFormat) {
        return formatsService.getFormat(nameFormat);
    }
}
