package dev.cat.backend.slot;


import dev.cat.backend.slot.validation.district.ExistingDistrict;
import dev.cat.backend.slot.validation.facility.ExistingFacility;
import dev.cat.backend.slot.validation.specialty.ExistingSpecialty;
import jakarta.validation.constraints.NotNull;

public record SlotFilter(@ExistingFacility Long facilityId,
                         @ExistingSpecialty @NotNull Long specialtyId,
                         @ExistingDistrict Long districtId
) {
}
