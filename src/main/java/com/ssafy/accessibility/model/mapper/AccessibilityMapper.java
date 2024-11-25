package com.ssafy.accessibility.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.accessibility.model.AccessibilityDto;

import java.util.List;

@Mapper
public interface AccessibilityMapper {
	void insertAccessibility(AccessibilityDto accessibilityDto);

	List<AccessibilityDto> selectAllAccessibility();
}
