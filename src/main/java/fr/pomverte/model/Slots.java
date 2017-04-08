package fr.pomverte.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class Slots {
    private List<Slot> slots;
}
