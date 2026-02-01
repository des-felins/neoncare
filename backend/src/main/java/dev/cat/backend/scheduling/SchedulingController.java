package dev.cat.backend.scheduling;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping("/{triageCaseId}")
    public ResponseEntity<List<SlotSuggestionResponse>> getSuggestedSlotsByTriageCaseId(@PathVariable Long triageCaseId) {
        return ResponseEntity.ok(schedulingService.suggestSlotsForTriageCase(triageCaseId));
    }

}
