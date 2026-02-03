package org.bergstar.testcase.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bergstar.testcase.dto.ResultDto;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class JsonExtractor {

    @Value("${uri}")
    String uri;

    public List<ResultDto> fetchJson(String matchingNumber) {
        Document doc;
        try {
            doc = Jsoup.connect(uri).get();
            // Находим <script> с JSON
            Elements elements = doc.select("script[type=\"application/json\"]");

            if (!elements.isEmpty()) {
                //нужен 7 элемент
                String jsonRaw = elements.get(6).html();
                // до символа '[' идут элементы html
                char delimiter = '[';
                // Находим позицию символа
                int index = jsonRaw.indexOf(delimiter);

                // Обрезаем, если символ найден
                String result = (index != -1) ? jsonRaw.substring(index) : jsonRaw;

                String six = insertDots(matchingNumber);
                String five = insertDots(matchingNumber.substring(1));
                String four = insertDots(matchingNumber.substring(2));
                String three = insertDots(matchingNumber.substring(3));
                String two = matchingNumber.substring(4);

                log.info("six {}", six);
                log.info("five {}", five);
                log.info("four {}", four);
                log.info("three {}", three);
                log.info("two {}", two);

                return Stream.of(containsMatchingNumber(result, six),
                                containsMatchingNumber(result, five),
                                containsMatchingNumber(result, four),
                                containsMatchingNumber(result, three),
                                containsMatchingNumber(result, two)
                        )
                        .filter(Objects::nonNull)
                        .toList();
            }
        }  catch (HttpStatusException e) {
            log.error("HTTP error: {} URL: {}", e.getStatusCode(), e.getUrl());
            return null;
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return null;
    }

        private String getName(String result, String matchingNumber){
        String text = result.substring(result.indexOf(matchingNumber), result.indexOf(matchingNumber)+170);
        return text.replaceAll("[^а-яА-ЯёЁ\\s]", "").trim();
    }

    private ResultDto containsMatchingNumber(String result, String matchingNumber){
        if (result.contains(matchingNumber)){

            return ResultDto.builder()
                    .name(getName(result, matchingNumber))
                    .code(matchingNumber)
                    .matchingCharacters(matchingNumber)
                    .build();
        }
        else return null;
    }

    private String insertDots(String matchingCharacters){
        StringBuilder sb = new StringBuilder(matchingCharacters);
        if (matchingCharacters.length() == 6 || matchingCharacters.length() == 5){
            sb.insert(2, ".");
            sb.insert(5, ".");
        } else {
            sb.insert(2, ".");
        }

        return sb.toString();
    }
}
