// JourneyServiceImpl.java
package com.ssafy.journey.model.service;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import com.ssafy.journey.model.mapper.JourneyMapper;
import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.mapper.TripMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private JourneyMapper journeyMapper;
    
    @Autowired
    private TripMapper tripMapper;

    @Override
    public int registerJourney(JourneyDto journeyDto) throws Exception {
        journeyMapper.insertJourney(journeyDto);
        return journeyDto.getId(); // Auto-generated key should be set after insert
    }

    @Override
    public void registerJourneyRoute(JourneyRouteDto journeyRouteDto) throws Exception {
        journeyMapper.insertJourneyRoute(journeyRouteDto);
    }

    @Override
    public void registerMemberJourney(String userId, int journeyId) throws Exception {
        journeyMapper.insertMemberJourney(userId, journeyId);
    }

    @Override
    public JourneyDto getJourneyById(int journeyId) throws Exception {
        JourneyDto journeyDto = journeyMapper.selectJourneyById(journeyId);
        if (journeyDto == null) {
            throw new Exception("Journey not found");
        }
        return journeyDto;
    }

	@Override
	public List<JourneyDto> selectJourneyByUserId(String userId) {
		// TODO Auto-generated method stub
		return journeyMapper.selectJourneyByUserId(userId);
	}

	@Override
	public Map<String, List<TripDto>> getJourneyDetail(int journeyId) throws Exception {
		List<JourneyRouteDto> routeList = journeyMapper.selectJourneyRoutesByJourneyId(journeyId);
		System.out.println(routeList);
        // 결과를 Map<String, List<TripDto>> 형식으로 변환
        Map<String, List<TripDto>> journeyDetail = new LinkedHashMap<>();
        int day = 1;
        List<TripDto> trips = new ArrayList<>();
        for (JourneyRouteDto route : routeList) {
        	int nowDay = route.getDay();
        	if(day != nowDay) {
        		String dayKey = "day" + day;
        		journeyDetail.put(dayKey, trips);
        		day = nowDay;
        		trips = new ArrayList<>();
        	}
            TripDto attractionsByContentId = tripMapper.getAttractionsByContentId(route.getContentId());
            trips.add(attractionsByContentId);
        }
        journeyDetail.put("day" + day, trips);

        return journeyDetail;
	}
	
	@Override
    public void deleteJourney(int journeyId) {
        // 1. journey_routes 삭제
        journeyMapper.deleteJourneyRoutes(journeyId);

        // 2. journey_accessibility 삭제
        journeyMapper.deleteJourneyAccessibility(journeyId);

        // 3. member_journey 삭제
        journeyMapper.deleteMemberJourney(journeyId);

        // 4. journeys 삭제
        journeyMapper.deleteJourney(journeyId);
    }

}
