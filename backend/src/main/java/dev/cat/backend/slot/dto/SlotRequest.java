package dev.cat.backend.slot.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record SlotRequest(
        @NotNull Long facilityId,
        @NotNull Long specialtyId,
        @NotNull @Future(message = "Slot should start in the future.") OffsetDateTime startsAt,
        @NotNull @Future(message = "Slot should end in the future.") OffsetDateTime endsAt,
        @NotNull @Positive(message = "Slot capacity should be greater than zero.") int capacity) {
}
