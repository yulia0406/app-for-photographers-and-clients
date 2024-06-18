package org.example.rest;

import org.example.service.CommentsService;
import org.example.service.ICommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.model.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comments/")
public class CommentsRestController {
    @Autowired
    private ICommentsService commentsService;


    @RequestMapping(value = "{id_Comments}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<org.example.model.Comments> getOrderContent(@PathVariable("id_Comments") Long idComments) {
        return commentsService.getComment(idComments);
    }


    @RequestMapping(value = "createComments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createComments(@RequestBody @Valid Comments comments) {
        return commentsService.createComments(comments);
    }


    @RequestMapping(value = "getAllComments", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<org.example.model.Comments>> getAllComments(@RequestBody Image imagePhotosession) {
       return commentsService.getAllComments(imagePhotosession);

    }

}
