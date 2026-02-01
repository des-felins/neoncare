package dev.cat.backend.scheduling;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;

    public SchedulingService(SchedulingRepository schedulingRepository) {
        this.schedulingRepository = schedulingRepository;
    }

    public List<SlotSuggestionResponse> suggestSlotsForTriageCase(Long triageCaseId) {

        if (triageCaseId <= 0) {
            return new ArrayList<>();
        }
        return schedulingRepository.suggestSlots(triageCaseId);

    }

}
