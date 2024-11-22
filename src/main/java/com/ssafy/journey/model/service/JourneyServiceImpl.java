// JourneyServiceImpl.java
package com.ssafy.journey.model.service;

import com.ssafy.journey.model.JourneyDto;
import com.ssafy.journey.model.JourneyRouteDto;
import com.ssafy.journey.model.mapper.JourneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JourneyServiceImpl implements JourneyService {

    @Autowired
    private JourneyMapper journeyMapper;

    @Override
    public void registerJourney(JourneyDto journeyDto) {
        journeyMapper.insertJourney(journeyDto);
        
    }

    @Override
    public void updateJourney(JourneyDto journeyDto) {
        journeyMapper.updateJourney(journeyDto);
    }

    @Override
    public void deleteJourney(int id) {
        journeyMapper.deleteJourney(id);
    }

    @Override
    public List<JourneyDto> getJourneyList() {
        return journeyMapper.selectJourneyList();
    }

    @Override
    public List<JourneyDto> getJourneyById(String id) {
        return journeyMapper.selectJourneyById(id);
    }
}