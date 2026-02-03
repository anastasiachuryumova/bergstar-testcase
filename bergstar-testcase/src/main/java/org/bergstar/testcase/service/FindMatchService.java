package org.bergstar.testcase.service;

import org.bergstar.testcase.dto.PhoneDto;
import org.bergstar.testcase.dto.ResultDto;

import java.util.List;

public interface FindMatchService {

    List<ResultDto> findMatch(PhoneDto phoneDto);
}
