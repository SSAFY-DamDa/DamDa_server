package com.ssafy.accessibility.model.service;

import java.util.List;

import com.ssafy.accessibility.model.AccessibilityDto;

public interface AccessibilityService {
	void addAccessibility(AccessibilityDto accessibilityDto);

	List<AccessibilityDto> getAllAccessibility();
}
