// JourneyService.java
package com.ssafy.journey.model.service;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;

import java.util.List;

public interface JourneyService {
    int registerJourney(JourneyDto journeyDto) throws Exception;
    void registerJourneyRoute(JourneyRouteDto journeyRouteDto) throws Exception;
    void registerMemberJourney(String userId, int journeyId) throws Exception;
    JourneyDto getJourneyById(int journeyId) throws Exception;
}