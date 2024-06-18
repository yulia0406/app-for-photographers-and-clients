package org.example.repository;
import org.example.model.Versions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionsRepository extends JpaRepository<Versions, Long>
{
}
