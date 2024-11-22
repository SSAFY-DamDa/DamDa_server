package com.ssafy.trip.model.service;

import java.sql.SQLException;
import java.util.*;

import com.ssafy.trip.model.*;

public interface TripService {
	public List<TripDto> searchListAll(Map<String, Object> map)throws SQLException ;
	public List<AreaDto> selectAllSi()throws SQLException ;
	public List<TripDto> selectAll(int pgNo, int sizePerPage) throws SQLException;
	public int getTotalCount() throws SQLException;
	public List<AreaDto> selectGuGun(int sidoCode) throws SQLException;

    // 검색 결과의 총 개수를 반환하는 메서드
    int getSearchTotalCount(TripDto tripDto) throws SQLException;
}
