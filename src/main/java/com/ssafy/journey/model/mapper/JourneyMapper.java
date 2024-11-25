
// JourneyMapper.java
package com.ssafy.journey.model.mapper;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import com.ssafy.trip.model.TripDto;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface JourneyMapper {
    void insertJourney(JourneyDto journeyDto);
    void insertJourneyRoute(JourneyRouteDto journeyRouteDto);
    void insertMemberJourney(String userId, int journeyId);
    JourneyDto selectJourneyById(int id);
    List<JourneyDto> selectJourneyByUserId(String userId);
    List<JourneyRouteDto> selectJourneyRoutesByJourneyId(int journeyId);
    void deleteJourneyRoutes(int journeyId);
    void deleteJourneyAccessibility(int journeyId);
    void deleteMemberJourney(int journeyId);
    void deleteJourney(int journeyId);
}