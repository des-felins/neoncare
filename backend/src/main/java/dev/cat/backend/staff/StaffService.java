package dev.cat.backend.staff;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<StaffResponse> findStaffBySpecialtyId(Long specialtyId) {

        if (specialtyId <= 0) {
            return new ArrayList<>();
        }

        return staffRepository.findStaffBySpecialtyId(specialtyId);
    }

}
