package org.bergstar.testcase.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.bergstar.testcase.dto.PhoneDto;
import org.bergstar.testcase.dto.ResultDto;
import org.bergstar.testcase.service.FindMatchService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("FindMatchController")
@RequestMapping("/api/v1/findMatch")
@Tag(name = "FindMatch", description = "Найти код ОКВЭД с максимальным совпадением по окончанию нормализованного номера")
@RequiredArgsConstructor
public class FindMatchController {

    private final FindMatchService service;

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(summary = "Найти код ОКВЭД с максимальным совпадением по окончанию нормализованного номера.", description = "Найти код ОКВЭД с максимальным совпадением по окончанию нормализованного номера.")
    public ResponseEntity<List<ResultDto>> submit(@RequestBody PhoneDto phoneDto) {
        return ResponseEntity.ok(service.findMatch(phoneDto));
    }
}
