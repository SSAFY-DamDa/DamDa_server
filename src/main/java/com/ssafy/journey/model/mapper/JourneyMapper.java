
// JourneyMapper.java
package com.ssafy.journey.model.mapper;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface JourneyMapper {
    void insertJourney(JourneyDto journeyDto);
    void updateJourney(JourneyDto journeyDto);
    void deleteJourney(int id);
    List<JourneyDto> selectJourneyList();
    JourneyDto selectJourneyById(int id);
    void insertJourneyRoute(JourneyRouteDto journeyRouteDto);
}