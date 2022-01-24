package org.enricogiurin.poc.italymunicipalities.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class Municipality {
    private String name;
    private String province;
    private String region;
}
