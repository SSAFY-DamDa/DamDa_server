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
}
