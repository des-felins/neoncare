package dev.cat.backend.triage;

import dev.cat.backend.triage.dto.TriageCaseDetailsResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/triage")
public class TriageController {

    private final TriageService triageService;

    public TriageController(TriageService triageService) {
        this.triageService = triageService;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TriageCaseDetailsResponse getTriageCaseById(
            @NotNull
            @PathVariable
            Long id) {
        return triageService.findTriageCase(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Triage case not found"));

    }
}
