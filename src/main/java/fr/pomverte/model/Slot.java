package fr.pomverte.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Slot {
    private String roomId;
    private String fromTime;
    private Long fromTimeMillis;
    private String toTime;
    private Long toTimeMillis;
    private Talk talk;
    private String roomName;
    private String day;
}
