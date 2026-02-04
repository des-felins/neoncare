package dev.cat.backend.scheduling.dto;

import java.time.OffsetDateTime;

public record SlotSuggestionResponse(
        Long slotId,
        OffsetDateTime slotStartsAt,
        OffsetDateTime slotEndsAt,
        String facilityName,
        String districtName,
        String specialtyName,
        int remainingCapacity
) { }