package org.example.repository;
import org.example.model.Photosession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotosessionRepository extends JpaRepository<Photosession,Long> {
}
