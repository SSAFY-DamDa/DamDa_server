package com.ssafy.trip.model.service;

import java.sql.SQLException;
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
		System.out.println("service offset:" + offset);
		int totalCount = (int) map.get("totalCount");
		map.replace("offset", offset * totalCount);
		return tripMapper.searchListAll(map);
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
		return tripMapper.selectAll(pgNo, sizePerPage);
	}

	@Override
	public int getTotalCount() throws SQLException {
		return tripMapper.getTotalCount();
	}
}
