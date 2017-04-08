package fr.pomverte.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Talk {
    private String title;
    private String talkType;
    private String track;
    private String summary;
    private List<Speaker> speakers;
}
