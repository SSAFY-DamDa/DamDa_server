
// JourneyMapper.java
package com.ssafy.journey.model.mapper;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JourneyMapper {
    void insertJourney(JourneyDto journeyDto);
    void insertJourneyRoute(JourneyRouteDto journeyRouteDto);
    void insertMemberJourney(String userId, int journeyId);
    JourneyDto selectJourneyById(int id);
    List<JourneyDto> selectJourneyByUserId(String userId);
}