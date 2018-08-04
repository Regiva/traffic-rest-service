package ru.dz.traffic_rest_service.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Response {

    private String description;

    private Long count;
}
