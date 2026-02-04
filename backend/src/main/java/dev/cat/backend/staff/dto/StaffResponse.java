package dev.cat.backend.staff.dto;

public record StaffResponse(Long staffId,
                            String handle,
                            boolean active,
                            String specialtyName,
                            String facilityName,
                            String facilityType,
                            String districtName
) { }
