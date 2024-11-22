package com.ssafy.trip.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.model.AreaDto;
import com.ssafy.trip.model.TripDto;

public interface TripMapper {
	public List<TripDto> searchListAll(Map<String, Object> map) throws SQLException;

	public List<TripDto> searchAI(TripDto tripDto) throws SQLException;

	public List<AreaDto> selectAllSi() throws SQLException;

	public List<TripDto> selectAll(int pgNo, int sizePerPage) throws SQLException;

	public int getTotalCount() throws SQLException;

	int getSearchTotalCount(TripDto tripDto) throws SQLException;

	public TripDto getAttractionsByContentId(int contentId);
	
	public List<AreaDto> selectGuGun(int sidoCode) throws SQLException;
}
