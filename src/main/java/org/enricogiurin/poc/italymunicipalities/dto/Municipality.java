package org.enricogiurin.poc.italymunicipalities.dto;

import lombok.Builder;


@Builder
public record Municipality(String code, String name, String province, String region) {

}
