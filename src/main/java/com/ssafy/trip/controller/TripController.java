package com.ssafy.trip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.trip.model.AreaDto;
import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.service.TripService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/trip")
@Tag(name = "여행지 컨트롤러", description = "여행지 검색 및 관련 정보 조회를 처리하는 클래스입니다.")
public class TripController {

	private final Logger log = LoggerFactory.getLogger(TripController.class);
    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    @Operation(summary = "여행지 목록 조회", description = "전체 여행지 목록을 조회하여 페이지네이션 정보와 함께 반환합니다.")
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getFirstList(@RequestParam int pgno) {
        try {
        	System.out.println("pgno: " + pgno);
        	int sizePerPage = 10;
            int offset = pgno - 1;
            int totalCount = tripService.getTotalCount();
            if(offset <= 0) offset = 1;
            
            List<TripDto> tripList = tripService.selectAll(offset, sizePerPage);
            List<AreaDto> siList = tripService.selectAllSi();

            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("tripList", tripList);
            dataMap.put("siList", siList);
            int totalPage = totalCount / sizePerPage;
            dataMap.put("totalPage", totalCount % sizePerPage == 0 ? totalPage : totalPage + 1);
            
            return new ResponseEntity<>(dataMap, HttpStatus.OK);
        } catch (Exception e) {
            log.error("여행지 목록 조회 중 에러 발생: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "여행지 검색", description = "검색 조건에 맞는 여행지 목록을 조회하여 반환합니다.")
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestParam int areaCode, @RequestParam int contentTypeId, @RequestParam String title, @RequestParam int pgno) {
    	System.out.println("ac: " + areaCode + " cti: " + contentTypeId + " tt: " + title + " pgno: " + pgno);
    	try {
            int sizePerPage = 10;
            TripDto tripDto = new TripDto();  
            
            areaCode = areaCode == 0 ? -1 :areaCode;
            contentTypeId = contentTypeId == 0 ? -1 : contentTypeId;
            
            tripDto.setTitle(title);
            tripDto.setArea_code(areaCode);
            tripDto.setContent_type_id(contentTypeId);
            
            int offset = (pgno - 1);
            if(offset <= 0) offset = 1;
            
            Map<String, Object> map = new HashMap<>();
            map.put("tripDto", tripDto);
            map.put("offset", offset);
            map.put("totalCount", sizePerPage);
            
            List<TripDto> list = tripService.searchListAll(map);
            int searchTotalCount = tripService.getSearchTotalCount(tripDto);
            
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("tripList", list);
            System.out.println("sear: " + searchTotalCount);
            int totalPage = searchTotalCount / 10;
            dataMap.put("totalPage", searchTotalCount % sizePerPage == 0 ? totalPage : totalPage + 1);
            System.out.println(dataMap.get("totalPage"));

            return new ResponseEntity<>(dataMap, HttpStatus.OK);
        } catch (Exception e) {
            log.error("여행지 검색 중 에러 발생: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "스크롤로 여행지 목록 조회", description = "스크롤을 이용해 여행지 목록을 추가적으로 조회하여 반환합니다.")
    @GetMapping("/scrolllist")
    public ResponseEntity<List<TripDto>> getList(@RequestParam int pgno) {
        try {
            int sizePerPage = 20;
            tripService.getTotalCount();
            List<TripDto> tripList = tripService.selectAll(pgno, sizePerPage);

            return new ResponseEntity<>(tripList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("스크롤 여행지 목록 조회 중 에러 발생: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
