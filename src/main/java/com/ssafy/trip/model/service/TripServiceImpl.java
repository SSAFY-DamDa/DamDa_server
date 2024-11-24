package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.trip.model.AreaDto;
import com.ssafy.trip.model.TripDto;
import com.ssafy.trip.model.mapper.TripMapper;

@Service
public class TripServiceImpl implements TripService {

	@Autowired
	private TripMapper tripMapper;

	@Override
	public List<TripDto> searchListAll(Map<String, Object> map) throws SQLException {
		int offset = (int) map.get("offset") - 1;
		int totalCount = (int) map.get("totalCount");
		map.replace("offset", offset * totalCount);
		return tripMapper.searchListAll(map);
	}
	
	@Override
	public List<TripDto> searchAI(TripDto tripDto) throws SQLException {
		return tripMapper.searchAI(tripDto);
	}

	@Override
	public List<AreaDto> selectAllSi() throws SQLException {
		return tripMapper.selectAllSi();
	}

	@Override
	public int getSearchTotalCount(TripDto tripDto) throws SQLException {
		return tripMapper.getSearchTotalCount(tripDto);
	}

	@Override
	public List<TripDto> selectAll(int pgNo, int sizePerPage) throws SQLException {
		System.out.println("service pgNo: " +pgNo + " sizePerPage: " + sizePerPage);
		Map<String, Object> map = new HashMap<>();
		int offset = pgNo- 1;
		int totalCount = sizePerPage;
		map.put("offset", offset * totalCount);
		map.put("totalCount", sizePerPage);
		return tripMapper.selectAll(map);
	}

	@Override
	public int getTotalCount() throws SQLException {
		return tripMapper.getTotalCount();
	}

	@Override
	public List<AreaDto> selectGuGun(int sidoCode) throws SQLException {
		return tripMapper.selectGuGun(sidoCode);
	}
}
