package org.example.service;

import org.example.model.Comments;
import org.example.model.Format;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFormatsService {
    ResponseEntity<Format> getFormat(String nameFormat);
}
