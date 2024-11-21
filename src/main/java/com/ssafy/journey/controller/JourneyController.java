package com.ssafy.journey.controller;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @PostMapping("/register")
    public String registerJourney(@RequestBody JourneyDto journeyDto) {
        journeyService.registerJourney(journeyDto);
        return "Journey registered successfully";
    }

    @PutMapping("/update/{id}")
    public String updateJourney(@PathVariable int id, @RequestBody JourneyDto journeyDto) {
        journeyDto.setId(id);
        journeyService.updateJourney(journeyDto);
        return "Journey updated successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteJourney(@PathVariable int id) {
        journeyService.deleteJourney(id);
        return "Journey deleted successfully";
    }

    @GetMapping("/list")
    public List<JourneyDto> getJourneyList() {
        return journeyService.getJourneyList();
    }

    @GetMapping("/{id}")
    public JourneyDto getJourney(@PathVariable int id) {
        return journeyService.getJourneyById(id);
    }
}
