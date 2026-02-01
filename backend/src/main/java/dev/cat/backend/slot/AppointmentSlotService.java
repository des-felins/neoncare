package dev.cat.backend.slot;


import dev.cat.backend.slot.dto.SlotRequest;
import dev.cat.backend.slot.dto.SlotResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AppointmentSlotService {

    private final AppointmentSlotRepository repository;

    public AppointmentSlotService(AppointmentSlotRepository appointmentSlotRepository) {
        this.repository = appointmentSlotRepository;
    }

    public List<SlotResponse> findSlots(SlotFilter filter) {
        return repository.findSlots(filter);
    }

    public Optional<SlotResponse> findSlotById(Long id) {
        if (id <= 0) {
            return Optional.empty();
        }
        return repository.findSlotById(id);
    }

    public Long createSlot(SlotRequest slot) {

        Objects.requireNonNull(slot, "slot must not be null");

        if (!slot.endsAt().isAfter(slot.startsAt())) {
            throw new IllegalArgumentException("endsAt must be after startsAt");
        }

        return repository.createSlot(slot);
    }

    public void updateSlot(Long slotId, int newCapacity) {

        if (newCapacity <= 0) throw new IllegalArgumentException("newCapacity must be > 0");
        repository.updateSlot(slotId, newCapacity);
    }

    public int deleteSlot(Long slotId) {
        return repository.deleteSlot(slotId);
    }

}
