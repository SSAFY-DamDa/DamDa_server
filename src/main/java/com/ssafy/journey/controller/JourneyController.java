package com.ssafy.journey.controller;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import com.ssafy.journey.model.service.JourneyService;
import com.ssafy.trip.model.TripDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journey")
public class JourneyController {

    @Autowired
    private JourneyService journeyService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Integer>> registerJourney(@RequestBody JourneyDto journeyDto, @RequestParam String userId) {
    	Map<String, Integer> resultMap = new HashMap<>();
        try {
            System.out.println("register journey" + journeyDto);
            // 1. 여행 정보 등록 후 생성된 journeyId 반환
            int journeyId = journeyService.registerJourney(journeyDto);
            
            System.out.println("journeyId " + journeyId);
            System.out.println("userId " + userId);
            
            // 2. member_journey 테이블에 해당 유저와 여행 연결
            journeyService.registerMemberJourney(userId, journeyId);
            
            System.out.println("register member journey work ");
            
            resultMap.put("journeyId", journeyId);

            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerDetail")
    public ResponseEntity<String> registerJourneyDetail(@RequestBody Map<String, List<TripDto>> journeyDetail, @RequestParam int journeyId) {
        try {
        	System.out.println("detail 들어옴 " + journeyDetail);
            // 3. journeyDetail로부터 경로 정보 저장
            if (journeyDetail != null) {
                for (Map.Entry<String, List<TripDto>> entry : journeyDetail.entrySet()) {
                    int dayNumber = Integer.parseInt(entry.getKey().replace("day", ""));
                    List<TripDto> tripList = entry.getValue();
                    for (int i = 0; i < tripList.size(); i++) {
                        TripDto tripDto = tripList.get(i);
                        JourneyRouteDto journeyRouteDto = new JourneyRouteDto();
                        journeyRouteDto.setJourneyId(journeyId);
                        journeyRouteDto.setDay(dayNumber);
                        journeyRouteDto.setOrderInDay(i + 1);
                        journeyRouteDto.setContentId(tripDto.getContent_id());
                        journeyService.registerJourneyRoute(journeyRouteDto);
                    }
                }
            }

            return new ResponseEntity<>("Journey detail registered successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering journey detail: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<JourneyDto> getJourneyById(@PathVariable int id) {
        try {
            JourneyDto journeyDto = journeyService.getJourneyById(id);
            return new ResponseEntity<>(journeyDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getUserJourney(@RequestParam String userId) {
    	try {
    		System.out.println("userId " + userId);
    		List<JourneyDto> selectJourneyByUserId = journeyService.selectJourneyByUserId(userId);
    		Map<String, Object> dataMap = new HashMap<>();
    		dataMap.put("journey", selectJourneyByUserId);
    		return new ResponseEntity<>(dataMap, HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    }
    
    @GetMapping("/detail/{journeyid}")
    public ResponseEntity<Map<String, List<TripDto>>> getJourneyDetail(@PathVariable int journeyid) {
        try {
        	System.out.println("ji: " + journeyid);
            Map<String, List<TripDto>> journeyDetail = journeyService.getJourneyDetail(journeyid);
            return new ResponseEntity<>(journeyDetail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
