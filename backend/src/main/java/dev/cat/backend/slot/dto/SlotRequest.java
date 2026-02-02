package dev.cat.backend.slot.dto;

import dev.cat.backend.slot.validation.facility.ExistingFacility;
import dev.cat.backend.slot.validation.specialty.ExistingSpecialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.OffsetDateTime;

public record SlotRequest(
        @NotNull @ExistingFacility Long facilityId,
        @NotNull @ExistingSpecialty Long specialtyId,
        @NotNull @Future(message = "Slot should start in the future.") OffsetDateTime startsAt,
        @NotNull @Future(message = "Slot should end in the future.") OffsetDateTime endsAt,
        @NotNull @Positive(message = "Slot capacity should be greater than zero.") int capacity) {
}
