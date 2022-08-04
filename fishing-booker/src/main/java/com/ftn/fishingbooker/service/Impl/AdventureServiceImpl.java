package com.ftn.fishingbooker.service.Impl;

import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.repository.AdventureRepository;
import com.ftn.fishingbooker.service.AdventureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AdventureServiceImpl implements AdventureService {

    private final AdventureRepository adventureRepository;

    public AdventureServiceImpl(AdventureRepository adventureRepository) {
        this.adventureRepository = adventureRepository;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
    public Collection<Adventure> getAll() {
        return adventureRepository.findAll();
    }
}

