package com.ftn.fishingbooker.mapper;

import com.ftn.fishingbooker.dto.AdventureDto;
import com.ftn.fishingbooker.model.Adventure;
import com.ftn.fishingbooker.model.Utility;

public class AdventureMapper {

    public static AdventureDto mapToDto(Adventure adventure) {
        AdventureDto dto = new AdventureDto();
        dto.setId(adventure.getId());
        dto.setTitle(adventure.getTitle());
        dto.setAddress(adventure.getAddress());
        dto.setDescription(adventure.getDescription());
        dto.setImages(ImageMapper.map(adventure.getImages()));
        dto.setPricePerDay(adventure.getPricePerDay());
        dto.setCancelingPercentage(adventure.getCancelingPercentage());
        dto.setMaxNumberOfParticipants(adventure.getMaxNumberOfParticipants());
        dto.setInstructorFirstName(adventure.getInstructor().getFirstName());
        dto.setInstructorBio(adventure.getInstructor().getBiography());
        dto.setInstructorLastName(adventure.getInstructor().getLastName());
        dto.setFishingEquipment(adventure.getFishingEquipment());
        dto.setUtilities(UtilityMapper.map(adventure.getUtilities()));
        return dto;

    }
}
