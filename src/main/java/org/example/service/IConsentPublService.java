package org.example.service;

import org.example.model.ConsentPubl;

import java.util.List;

public interface IConsentPublService {
    public ConsentPubl getConsentPublByNameConsentPubl(String name);
    public List<ConsentPubl> getAll();
}
