package com.ssafy.accessibility.model.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.accessibility.model.AccessibilityDto;
import com.ssafy.accessibility.model.mapper.AccessibilityMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KorWithService {

    private final RestTemplate restTemplate;
    private final AccessibilityMapper accessibilityMapper;

    @Value("${api.public.data.key}")
    private String apiKey;

    public KorWithService(RestTemplate restTemplate, AccessibilityMapper accessibilityMapper) {
        this.restTemplate = restTemplate;
        this.accessibilityMapper = accessibilityMapper;
    }

    public void fetchAndSaveAccessibilityData() {
        // URL 구성
        String url = "http://apis.data.go.kr/B551011/KorWithService1/detailWithTour1";
        String fullUrl = url + "?serviceKey=" + apiKey
                + "&MobileOS=ETC"
                + "&MobileApp=AppTest"
                + "&contentId=129619" // contentId로 하나의 결과만 가져옴
                + "&_type=json";

        try {
            // API 호출하여 데이터 가져오기
            String jsonData = restTemplate.getForObject(fullUrl, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonData);

            // 데이터 파싱 (JSON 구조에 맞게 파싱)
            JsonNode itemsNode = rootNode.path("response").path("body").path("items").path("item");

            if (itemsNode.isArray()) {
                for (JsonNode itemNode : itemsNode) {
                    AccessibilityDto accessibilityDto = new AccessibilityDto();

                    // JSON 데이터에서 각 필드를 DTO로 매핑
                    accessibilityDto.setContentId(itemNode.path("contentid").asInt());
                    accessibilityDto.setParking(itemNode.path("parking").asText(""));
                    accessibilityDto.setRoute(itemNode.path("route").asText(""));
                    accessibilityDto.setPublicTransport(itemNode.path("publictransport").asText(""));
                    accessibilityDto.setTicketOffice(itemNode.path("ticketoffice").asText(""));
                    accessibilityDto.setPromotion(itemNode.path("promotion").asText(""));
                    accessibilityDto.setWheelchair(itemNode.path("wheelchair").asText(""));
                    accessibilityDto.setExit(itemNode.path("exit").asText(""));
                    accessibilityDto.setElevator(itemNode.path("elevator").asText(""));
                    accessibilityDto.setRestroom(itemNode.path("restroom").asText(""));
                    accessibilityDto.setAuditorium(itemNode.path("auditorium").asText(""));
                    accessibilityDto.setRoom(itemNode.path("room").asText(""));
                    accessibilityDto.setHandicapEtc(itemNode.path("handicapetc").asText(""));
                    accessibilityDto.setBraileBlock(itemNode.path("braileblock").asText(""));
                    accessibilityDto.setHelpDog(itemNode.path("helpdog").asText(""));
                    accessibilityDto.setGuideHuman(itemNode.path("guidehuman").asText(""));
                    accessibilityDto.setAudioGuide(itemNode.path("audioguide").asText(""));
                    accessibilityDto.setBigPrint(itemNode.path("bigprint").asText(""));
                    accessibilityDto.setBrailePromotion(itemNode.path("brailepromotion").asText(""));
                    accessibilityDto.setGuideSystem(itemNode.path("guidesystem").asText(""));
                    accessibilityDto.setBlindHandicapEtc(itemNode.path("blindhandicapetc").asText(""));
                    accessibilityDto.setSignGuide(itemNode.path("signguide").asText(""));
                    accessibilityDto.setVideoGuide(itemNode.path("videoguide").asText(""));
                    accessibilityDto.setHearingRoom(itemNode.path("hearingroom").asText(""));
                    accessibilityDto.setHearingHandicapEtc(itemNode.path("hearinghandicapetc").asText(""));
                    accessibilityDto.setStroller(itemNode.path("stroller").asText(""));
                    accessibilityDto.setLactationRoom(itemNode.path("lactationroom").asText(""));
                    accessibilityDto.setBabySpareChair(itemNode.path("babysparechair").asText(""));
                    accessibilityDto.setInfantsFamilyEtc(itemNode.path("infantsfamilyetc").asText(""));

                    // 데이터베이스에 저장
                    accessibilityMapper.insertAccessibility(accessibilityDto);
                }
            } else if (itemsNode.isObject()) {
                // 하나의 객체가 반환될 때 처리 (isArray가 아니고 단일 객체로 반환된 경우)
                AccessibilityDto accessibilityDto = new AccessibilityDto();

                accessibilityDto.setContentId(itemsNode.path("contentid").asInt());
                accessibilityDto.setParking(itemsNode.path("parking").asText(""));
                accessibilityDto.setRoute(itemsNode.path("route").asText(""));
                accessibilityDto.setPublicTransport(itemsNode.path("publictransport").asText(""));
                accessibilityDto.setTicketOffice(itemsNode.path("ticketoffice").asText(""));
                accessibilityDto.setPromotion(itemsNode.path("promotion").asText(""));
                accessibilityDto.setWheelchair(itemsNode.path("wheelchair").asText(""));
                accessibilityDto.setExit(itemsNode.path("exit").asText(""));
                accessibilityDto.setElevator(itemsNode.path("elevator").asText(""));
                accessibilityDto.setRestroom(itemsNode.path("restroom").asText(""));
                accessibilityDto.setAuditorium(itemsNode.path("auditorium").asText(""));
                accessibilityDto.setRoom(itemsNode.path("room").asText(""));
                accessibilityDto.setHandicapEtc(itemsNode.path("handicapetc").asText(""));
                accessibilityDto.setBraileBlock(itemsNode.path("braileblock").asText(""));
                accessibilityDto.setHelpDog(itemsNode.path("helpdog").asText(""));
                accessibilityDto.setGuideHuman(itemsNode.path("guidehuman").asText(""));
                accessibilityDto.setAudioGuide(itemsNode.path("audioguide").asText(""));
                accessibilityDto.setBigPrint(itemsNode.path("bigprint").asText(""));
                accessibilityDto.setBrailePromotion(itemsNode.path("brailepromotion").asText(""));
                accessibilityDto.setGuideSystem(itemsNode.path("guidesystem").asText(""));
                accessibilityDto.setBlindHandicapEtc(itemsNode.path("blindhandicapetc").asText(""));
                accessibilityDto.setSignGuide(itemsNode.path("signguide").asText(""));
                accessibilityDto.setVideoGuide(itemsNode.path("videoguide").asText(""));
                accessibilityDto.setHearingRoom(itemsNode.path("hearingroom").asText(""));
                accessibilityDto.setHearingHandicapEtc(itemsNode.path("hearinghandicapetc").asText(""));
                accessibilityDto.setStroller(itemsNode.path("stroller").asText(""));
                accessibilityDto.setLactationRoom(itemsNode.path("lactationroom").asText(""));
                accessibilityDto.setBabySpareChair(itemsNode.path("babysparechair").asText(""));
                accessibilityDto.setInfantsFamilyEtc(itemsNode.path("infantsfamilyetc").asText(""));

                // 데이터베이스에 저장
                accessibilityMapper.insertAccessibility(accessibilityDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void scheduledDataFetch() {
        fetchAndSaveAccessibilityData();
    }
}
