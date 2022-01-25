package org.enricogiurin.poc.italymunicipalities.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@Builder
@EqualsAndHashCode(of = "code")
public class Municipality {

    private String code;
    private String name;
    private String province;
    private String region;
}
