package fr.pomverte;

import fr.pomverte.model.Slots;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "devoox-api", url = "${fr.devoxx.api.url}")
public interface DevooxTalksRestClient {
    @GetMapping("/conferences/DevoxxFR2017/schedules/{day}")
    ResponseEntity<Slots> getSlotsByDay(@PathVariable("day") String day);
}
