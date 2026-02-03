package org.bergstar.testcase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bergstar.testcase.client.JsonExtractor;
import org.bergstar.testcase.dto.PhoneDto;
import org.bergstar.testcase.dto.ResultDto;
import org.bergstar.testcase.service.FindMatchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindMatchServiceImpl implements FindMatchService {


    private final JsonExtractor jsonExtractor;

    @Override
    public List<ResultDto> findMatch(PhoneDto phoneDto){
        String normalizeNumber = normalizeNumber(phoneDto);
        String matchingCharacters = Objects.requireNonNull(normalizeNumber).substring(6);
        log.info("Matching characters {} ", matchingCharacters);

        List<ResultDto> resultDto = jsonExtractor.fetchJson(matchingCharacters);
        for (ResultDto dto: resultDto){
            dto.setNormalizedNumber(normalizeNumber);
        }
        return resultDto;
    }

    private String normalizeNumber(PhoneDto phoneDto){
        String phone = phoneDto.getPhoneNumber();
        if (phone.startsWith("8")) {
            phone = "+7" + phone.substring(1);
            return phone.replaceAll("[^\\d+]", "");
        } else if (phone.startsWith("+7")) {
            return phone.replaceAll("[^\\d+]", "");
        } else if (phone.startsWith("9")) {
            phone = "+7" + phone;
            return phone.replaceAll("[^\\d+]", "");
        } else {
            log.error("Вы написали телефон, который начинается ни с 8 ни с +7 ни с 9");
            return null;
        }
    }
}
