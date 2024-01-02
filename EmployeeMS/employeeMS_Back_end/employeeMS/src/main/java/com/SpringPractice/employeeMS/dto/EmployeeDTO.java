package com.SpringPractice.employeeMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDTO {
    private int id;
    private String empName;
    private String empAddress;
    private  String empNumber;
}
