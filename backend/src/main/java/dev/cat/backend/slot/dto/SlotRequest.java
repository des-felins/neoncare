package dev.cat.backend.slot.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record SlotRequest(
        @NotNull @Positive Long facilityId,
        String facilityName,
        @NotNull @Positive Long specialtyId,
        String specialtyName,
        @NotNull @Positive @Future OffsetDateTime startsAt,
        @NotNull @Positive @Future OffsetDateTime endsAt,
        @NotNull @Positive int capacity) {
}
