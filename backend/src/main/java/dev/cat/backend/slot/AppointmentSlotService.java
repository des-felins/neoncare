package dev.cat.backend.slot;


import dev.cat.backend.slot.dto.SlotRequest;
import dev.cat.backend.slot.dto.SlotResponse;
import org.springframework.stereotype.Service;

import java.util.List;
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
        return repository.findSlotById(id);
    }

    public Long createSlot(SlotRequest slot) {

        if (!slot.endsAt().isAfter(slot.startsAt())) {
            throw new IllegalArgumentException("End time must be later than start time");
        }

        return repository.createSlot(slot);
    }

    public void updateSlot(Long slotId, int newCapacity) {
        repository.updateSlot(slotId, newCapacity);
    }

    public void deleteSlot(Long slotId) {
        repository.deleteSlot(slotId);
    }

}
