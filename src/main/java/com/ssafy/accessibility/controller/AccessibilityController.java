package com.ssafy.accessibility.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.accessibility.model.AccessibilityDto;
import com.ssafy.accessibility.model.service.AccessibilityService;

@RestController
@RequestMapping("/accessibility")
public class AccessibilityController {

    @Autowired
    private AccessibilityService accessibilityService;

    @PostMapping
    public String addAccessibility(@RequestBody AccessibilityDto accessibilityDto) {
        accessibilityService.addAccessibility(accessibilityDto);
        return "Accessibility data added successfully.";
    }

    @GetMapping
    public List<AccessibilityDto> getAllAccessibility() {
        return accessibilityService.getAllAccessibility();
    }
}