package com.bns.demo.mapper;

import com.bns.demo.model.User;
import com.bns.demo.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setStartDate(user.getStartDate() != null ? user.getStartDate().toString() : null);
        dto.setEndDate(user.getEndDate() != null ? user.getEndDate().toString() : null);
        dto.setEffectiveDate(user.getEffectiveDate() != null ? user.getEffectiveDate().toString() : null);
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());
        dto.setEmailId(user.getEmailId());
        dto.setContactNum(user.getContactNum());
        return dto;
    }

    public User fromDto(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setUserId(dto.getUserId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setStartDate(dto.getStartDate() != null ? java.time.LocalDate.parse(dto.getStartDate()) : null);
        user.setEndDate(dto.getEndDate() != null ? java.time.LocalDate.parse(dto.getEndDate()) : null);
        user.setEffectiveDate(dto.getEffectiveDate() != null ? java.time.LocalDate.parse(dto.getEffectiveDate()) : null);
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        user.setEmailId(dto.getEmailId());
        user.setContactNum(dto.getContactNum());
        return user;
    }
}