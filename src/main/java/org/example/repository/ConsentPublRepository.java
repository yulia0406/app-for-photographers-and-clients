package org.example.repository;

import org.example.model.ConsentPubl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsentPublRepository extends JpaRepository<ConsentPubl, Long> {
    public ConsentPubl findConsentPublByNameConsentPubl(String nameConsentPubl);
}
