package dev.cat.backend.slot;


import dev.cat.backend.slot.dto.SlotRequest;
import dev.cat.backend.slot.dto.SlotResponse;
import dev.cat.backend.slot.validation.district.ExistingDistrict;
import dev.cat.backend.slot.validation.facility.ExistingFacility;
import dev.cat.backend.slot.validation.specialty.ExistingSpecialty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/slot")
public class AppointmentSlotController {

    private final AppointmentSlotService appointmentSlotService;

    public AppointmentSlotController(AppointmentSlotService appointmentSlotService) {
        this.appointmentSlotService = appointmentSlotService;
    }

    @PostMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<SlotResponse> getAllSlots(
            @NotNull
            @RequestBody
            @ExistingDistrict
            @ExistingFacility
            @ExistingSpecialty
            SlotFilter filter) {
        return appointmentSlotService.findSlots(filter);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SlotResponse getSlotById(
            @NotNull
            @PathVariable
            Long id) {
        return appointmentSlotService.findSlotById(id);

    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createSlot(
            @NotNull
            @RequestBody
            @ExistingFacility
            @ExistingSpecialty
            SlotRequest slotDto) {
        return appointmentSlotService.createSlot(slotDto);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateSlot(
            @NotNull
            @PathVariable
            Long id,
            @NotNull
            @RequestBody
            @Positive(message = "Slot capacity should be greater than zero.")
            int newCapacity) {
        appointmentSlotService.updateSlot(id, newCapacity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSlot(
            @NotNull
            @PathVariable Long id) {
        appointmentSlotService.deleteSlot(id);
    }

}
