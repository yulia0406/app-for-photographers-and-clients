package org.example.service;
import org.example.model.Comments;
import org.example.model.Image;
import org.example.repository.CommentsRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CommentsService implements ICommentsService {
    @Autowired
    private final CommentsRepository commentsRepository;

    public CommentsService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public ResponseEntity<List<Comments>> getAllComments(Image imagePhotosession) {
        List<Comments> orderContents1 = this.commentsRepository.findAll();
        if (orderContents1.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Comments> listComments = new ArrayList<>();
        for(int i = 0; i < orderContents1.size(); i++) {
            if (Objects.equals(orderContents1.get(i).getImage().getIdImage(), imagePhotosession.getIdImage()))
            {
                listComments.add(orderContents1.get(i));
                System.out.println(orderContents1.size());
            }
        }
        return new ResponseEntity<>(listComments, HttpStatus.OK);

    }

    public ResponseEntity<String> createComments(Comments comments) {
        HttpHeaders headers = new HttpHeaders();
        if (comments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println(comments.getDateCreate());
        this.commentsRepository.save(comments);
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("strcreatecontent", "Комментарий создан!");
        String json1 = jsonObject1.toString();
        return new ResponseEntity<>(json1, headers, HttpStatus.OK);
    }

    public ResponseEntity<org.example.model.Comments> getComment(Long idComments) {
        if (idComments == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        org.example.model.Comments comments = this.commentsRepository.findById(idComments).get();
        if (idComments == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
