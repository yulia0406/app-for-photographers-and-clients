package org.example.service;

import org.example.model.Comments;
import org.example.model.ConsentPubl;
import org.example.repository.ConsentPublRepository;
import org.example.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsentPublService implements IConsentPublService{
    @Autowired
    private final ConsentPublRepository consentPublRepository;

    public ConsentPublService(ConsentPublRepository consentPublRepository) {
        this.consentPublRepository = consentPublRepository;
    }
    @Override
    public ConsentPubl getConsentPublByNameConsentPubl(String name)
    {
        return consentPublRepository.findConsentPublByNameConsentPubl(name);
    }
    @Override
    public List<ConsentPubl> getAll() {
        return consentPublRepository.findAll();
    }
}
