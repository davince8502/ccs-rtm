package ccs.rtm.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FrameData {

    private String deviceId;

    private Integer panicButoom;

    private Float temperature;

    private Integer doorSensor;

    private Integer accidentSensor;

}
