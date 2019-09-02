package ccs.rtm.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FrameData {

    private String deviceId;

    @JsonProperty("panicButoom")
    private Integer panicButoom;

//    @JsonProperty("temperature")
    private Float temperature;

//    @JsonProperty("doorSensor")
    private Integer doorSensor;

//    @JsonProperty("accidentSensor")
    private Integer accidentSensor;

}
