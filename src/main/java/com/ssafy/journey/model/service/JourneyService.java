// JourneyService.java
package com.ssafy.journey.model.service;

import com.ssafy.journey.model.JourneyDto;

import java.util.List;

public interface JourneyService {
    void registerJourney(JourneyDto journeyDto);
    void updateJourney(JourneyDto journeyDto);
    void deleteJourney(int id);
    List<JourneyDto> getJourneyList();
    JourneyDto getJourneyById(int id);
}