package fr.pomverte;

import fr.pomverte.model.Slot;
import fr.pomverte.model.Slots;
import fr.pomverte.model.Speaker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EnableFeignClients
@SpringBootApplication
public class DevooxTalksApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevooxTalksApplication.class, args);
    }
}

@Component
class DevooxToolsCommandLine implements CommandLineRunner {

    private final DevooxTalksRestClient devooxTalksRestClient;

    DevooxToolsCommandLine(DevooxTalksRestClient devooxTalksRestClient) {
        this.devooxTalksRestClient = devooxTalksRestClient;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Slot> slots = new ArrayList<>();

        ResponseEntity<Slots> responseEntity = this.devooxTalksRestClient.getSlotsByDay("wednesday");
        slots.addAll(responseEntity.getBody().getSlots());

        responseEntity = this.devooxTalksRestClient.getSlotsByDay("thursday");
        slots.addAll(responseEntity.getBody().getSlots());

        responseEntity = this.devooxTalksRestClient.getSlotsByDay("friday");
        slots.addAll(responseEntity.getBody().getSlots());

        slots.stream()
                .filter(s -> s.getTalk() != null)
                .forEach(slot -> {

                            String speakers = slot.getTalk().getSpeakers().stream()
                                    .map(Speaker::getName)
                                    .collect(Collectors.joining(", "));

                            System.out.println(String.format("%s|%s|%s|%s|%s|%s|%s",
                                    slot.getDay(),
                                    slot.getFromTime(),
                                    slot.getToTime(),
                                    slot.getTalk().getTalkType(),
                                    slot.getTalk().getTrack(),
                                    slot.getTalk().getTitle(),
                                    speakers
                            ));
                        }
                );
    }
}
