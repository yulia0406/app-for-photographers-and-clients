package org.example.service;
import org.example.model.Comments;
import org.example.model.Image;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICommentsService {
    ResponseEntity<Comments> getComment(Long idComments);
    ResponseEntity<String> createComments(Comments comments);
    ResponseEntity<List<Comments>> getAllComments(Image imagePhotosession);
}
