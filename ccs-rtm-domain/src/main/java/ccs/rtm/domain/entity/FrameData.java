package ccs.rtm.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalTime;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FrameData {

    private String deviceId;
    private Integer panicButoom;
    private Float temperature;
    private Integer doorSensor;
    private Integer accidentSensor;
    private Long dateArrive;

}
