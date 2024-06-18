package org.example.repository;

import org.example.model.Format;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormatsRepository extends JpaRepository<Format, Long> {
}
