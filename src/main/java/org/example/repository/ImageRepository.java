package org.example.repository;
import org.example.model.Image;

import org.example.model.Photosession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends  JpaRepository<Image,Long>{
    public List<Image> findImageByPhotosession(Photosession photosession);

}
