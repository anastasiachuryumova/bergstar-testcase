package org.bergstar.testcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Код ОКВЭД с максимальным совпадением по окончанию нормализованного номера", type = "object")
public class ResultDto {

    @Schema(description = "Нормализованный номер")
    private String normalizedNumber;

    @Schema(description = "Найденный код ОКВЭД")
    private String code;

    @Schema(description = "Название найденного кода ОКВЭД")
    private String name;

    @Schema(description = "Длина совпадения")
    private String matchingCharacters;
}
