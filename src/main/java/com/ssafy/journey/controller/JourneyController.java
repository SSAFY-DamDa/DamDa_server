package com.ssafy.journey.controller;

import com.ssafy.faq.model.FAQDto;
import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.service.JourneyService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journey")
@Tag(name = "여행 컨트롤러", description = "여행 계획 관련 작업을 처리하는 클래스입니다.")
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
        journeyDto.setJourneyId(id);
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

    @GetMapping
    public ResponseEntity<Map<String, Object>> getJourney(@RequestParam String id) {
    	System.out.println("id is " + id);
    	try {
    		List<JourneyDto> journey = journeyService.getJourneyById(id);
    		System.out.println("run1");
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("journey", journey);
			System.out.println("run2 " + dataMap);
			return new ResponseEntity<>(dataMap, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
