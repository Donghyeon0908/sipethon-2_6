package com.sipethon.togather.controller;


import com.sipethon.togather.common.ApiResponse;
import com.sipethon.togather.common.EmptyJsonResponse;
import com.sipethon.togather.dto.content.ContentMapDto;
import com.sipethon.togather.dto.content.ContentRequestDto;
import com.sipethon.togather.dto.content.ContentResponseDto;
import com.sipethon.togather.service.ContentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @PostMapping("/content")
    public ApiResponse<EmptyJsonResponse> createContent(
        @RequestBody ContentRequestDto contentRequestDto) {
        contentService.createContent(contentRequestDto);
        return ApiResponse.success();
    }

    @GetMapping("/content/{id}")
    public ApiResponse<ContentResponseDto> getContent(@PathVariable Long id) {
        return ApiResponse.success(contentService.getContent(id));
    }

    @GetMapping("/contents")
    public ApiResponse<List<ContentMapDto>> getAllContents() {
        return ApiResponse.success(contentService.getAllContents());
    }
}
