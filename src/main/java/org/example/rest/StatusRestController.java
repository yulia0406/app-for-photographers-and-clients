package org.example.rest;

import lombok.AllArgsConstructor;
import org.example.model.Format;
import org.example.model.Status;
import org.example.service.IStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/status/")
public class StatusRestController {
    @Autowired
    private IStatusService iStatusService;
    @RequestMapping(value = "getId/{name_format}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Status> getFormat(@PathVariable("name_format") String nameSratus) {
       return iStatusService.getFormat(nameSratus);
    }
}
