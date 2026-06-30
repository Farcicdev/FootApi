package farcic.dev.footApi.controller;

import farcic.dev.footApi.BaseIntegrationTest;
import farcic.dev.footApi.dto.request.StadiumRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class StadiumControllerTest extends BaseIntegrationTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @WithMockUser(authorities = {"SCOPE_stadium:write"})
    @Test
    @DisplayName("Should create a stadium successfully")
    void createStadium() throws Exception {

        StadiumRequestDto requestDto = StadiumRequestDto.builder()
                .name("Test Stadium")
                .city("Test City")
                .capacity(50000)
                .urlImg("http://test.com/img.png")
                .build();

        mockMvc.perform(post("/stadium")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)));
    }
}