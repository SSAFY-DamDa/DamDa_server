// JourneyService.java
package com.ssafy.journey.model.service;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import com.ssafy.trip.model.TripDto;

import java.util.List;
import java.util.Map;

public interface JourneyService {
    int registerJourney(JourneyDto journeyDto) throws Exception;
    void registerJourneyRoute(JourneyRouteDto journeyRouteDto) throws Exception;
    void registerMemberJourney(String userId, int journeyId) throws Exception;
    JourneyDto getJourneyById(int journeyId) throws Exception;
    List<JourneyDto> selectJourneyByUserId(String userId);
    Map<String, List<TripDto>> getJourneyDetail(int journeyId) throws Exception;
    void deleteJourney(int journeyid);
}