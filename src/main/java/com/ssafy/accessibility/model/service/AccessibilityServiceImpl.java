package com.ssafy.accessibility.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.accessibility.model.AccessibilityDto;
import com.ssafy.accessibility.model.mapper.AccessibilityMapper;

import java.util.List;

@Service
public class AccessibilityServiceImpl implements AccessibilityService {

	private final AccessibilityMapper accessibilityMapper;

	public AccessibilityServiceImpl(AccessibilityMapper accessibilityMapper) {
		this.accessibilityMapper = accessibilityMapper;
	}

	@Override
	public void addAccessibility(AccessibilityDto accessibilityDto) {
		accessibilityMapper.insertAccessibility(accessibilityDto);
	}

	@Override
	public List<AccessibilityDto> getAllAccessibility() {
		return accessibilityMapper.selectAllAccessibility();
	}
}
