package org.bergstar.testcase.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Российский мобильный номер в любом формате", type = "object")
public class PhoneDto {

    @Schema(description = "Российский мобильный номер в любом формате", example = "8-903-234-88-44")
    @NotEmpty
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phoneNumber;
}
