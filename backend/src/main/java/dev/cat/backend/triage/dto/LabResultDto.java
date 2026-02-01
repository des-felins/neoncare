package dev.cat.backend.triage.dto;

import java.time.OffsetDateTime;

public record LabResultDto(
        String status,
        OffsetDateTime publishedAt
) { }
