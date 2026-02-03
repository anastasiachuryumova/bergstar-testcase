package org.bergstar.testcase.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bergstar.testcase.dto.EconomicActivityClassifier;
import org.bergstar.testcase.dto.ResultDto;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonParser {

    private final ObjectMapper objectMapper;

    public ResultDto findMatch(EconomicActivityClassifier classifier, String matchingCharacters){
        log.info("classifier {}", classifier);
        ResultDto resultDto = ResultDto.builder().build();
        for (EconomicActivityClassifier.Item item: classifier.getItems()){
            if (item.getCode().equals(matchingCharacters.substring(0, 2))){
                resultDto.setCode(item.getCode());
                resultDto.setName(item.getName());
            }
            for (EconomicActivityClassifier.Item.Item1 item1: item.getItems()){
                if (item1.getCode().equals(matchingCharacters.substring(0, 3))) {
                    resultDto.setCode(item1.getCode());
                    resultDto.setName(item1.getName());
                }
                for (EconomicActivityClassifier.Item.Item1.Item2 item2: item1.getItems()){
                    if (item2.getCode().equals(matchingCharacters.substring(0, 4))) {
                        resultDto.setCode(item2.getCode());
                        resultDto.setName(item2.getName());
                    }
                    for (EconomicActivityClassifier.Item.Item1.Item2.Item3 item3: item2.getItems()){
                        if (item3.getCode().equals(matchingCharacters.substring(0, 5))) {
                            resultDto.setCode(item3.getCode());
                            resultDto.setName(item3.getName());
                        }
                        for (EconomicActivityClassifier.Item.Item1.Item2.Item3.Item4 item4: item3.getItems()){
                            if (item4.getCode().equals(matchingCharacters)) {
                                resultDto.setCode(item4.getCode());
                                resultDto.setName(item4.getName());
                            }
                        }
                    }
                }
            }
        }
        return resultDto;
    }
}
