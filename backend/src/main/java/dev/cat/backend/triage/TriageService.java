package dev.cat.backend.triage;

import dev.cat.backend.triage.dto.TriageCaseDetailsResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TriageService {

    private final TriageRepository triageRepository;

    public TriageService(TriageRepository triageRepository) {
        this.triageRepository = triageRepository;
    }

    public Optional<TriageCaseDetailsResponse> findTriageCase(Long triageCaseId) {
        return triageRepository.findTriageCase(triageCaseId);
    }

}
