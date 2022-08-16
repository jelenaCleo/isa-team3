package com.ftn.fishingbooker.service;

import com.ftn.fishingbooker.dto.AddressDto;
import com.ftn.fishingbooker.dto.AdventureAdditionalInfo;
import com.ftn.fishingbooker.dto.AdventureInfo;
import com.ftn.fishingbooker.model.Address;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Rule;

import java.util.*;

public interface AdventureService {

    Collection<Adventure> getAll();

    Collection<Adventure> getAllByInstructorEmail(String email);

    void addImage(Long adventureId, String fileName);

    Adventure addAdventure(Adventure adventure, String instructorEmail);

    Adventure getById(Long id);

    Adventure updateAdventureInfo(Long id, AdventureInfo updated);

    Adventure updateAdventureAdditionalInfo(Long id, AdventureAdditionalInfo updated);

    Adventure updateAdventureRules(Long id, Collection<Rule> updated);

    Adventure updateAdventureAddress(Long id, Address updated);

    void deleteById(Long id);

    Adventure save(Adventure adventure);
}
