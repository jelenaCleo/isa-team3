package com.ftn.fishingbooker.controller;

import com.ftn.fishingbooker.model.FishingEquipment;
import com.ftn.fishingbooker.service.FishingEquipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/fishing-equipment")
public class FishingEquipmentController {

    private final FishingEquipmentService fishingEquipmentService;

    public FishingEquipmentController(FishingEquipmentService fishingEquipmentService) {
        this.fishingEquipmentService = fishingEquipmentService;
    }

    @GetMapping
    public ResponseEntity<List<FishingEquipment>> getAll(){
        return ResponseEntity.ok(fishingEquipmentService.getAll());
    }
}
