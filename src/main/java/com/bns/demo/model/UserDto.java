package com.bns.demo.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String startDate;
    private String endDate;
    private String effectiveDate;
    private String role;
    private String status;
    private String emailId;
    private String contactNum;
}