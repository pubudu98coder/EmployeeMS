package com.SpringPractice.employeeMS.controller;

import com.SpringPractice.employeeMS.dto.EmployeeDTO;
import com.SpringPractice.employeeMS.dto.ResponseDto;
import com.SpringPractice.employeeMS.service.EmployeeService;
import com.SpringPractice.employeeMS.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    ResponseDto responseDto;

    @PostMapping("/save")
    public ResponseEntity saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res=employeeService.saveEmployee(employeeDTO);
            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
            else if(res.equals("06")){
                responseDto.setCode(VarList.RSP_DUPLICATED);
                responseDto.setMessage("Employee Regsitered");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
            else{
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Fail");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@RequestBody EmployeeDTO employeeDTO){
        try{
            String res=employeeService.updateEmployee(employeeDTO);
            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
            else {
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Employee not found");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
            /*else{
                responseDto.setCode(VarList.RSP_FAIL);
                responseDto.setMessage("Fail");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }*/
        }
        catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("getAll")
    public ResponseEntity getAllEmployee(){
        try{
            List<EmployeeDTO> employeeDTOList= employeeService.getAllemployee();
            responseDto.setCode(VarList.RSP_SUCCESS);
            responseDto.setMessage("Success");
            responseDto.setContent(employeeDTOList);
            return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/search/{empID}")
    public ResponseEntity searchEmployee(@PathVariable int empID){
        try{
            EmployeeDTO employeeDTO=employeeService.searchEmployee(empID);
            if(employeeDTO!=null){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
            else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Employee not found");
                responseDto.setContent(employeeDTO);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(value = "/delete/{empID}")
    public ResponseEntity deleteEmployee(@PathVariable int empID){
        try{
             String res=employeeService.deleteEmployee(empID);
            if(res.equals("00")){
                responseDto.setCode(VarList.RSP_SUCCESS);
                responseDto.setMessage("Success");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.ACCEPTED);
            }
            else{
                responseDto.setCode(VarList.RSP_NO_DATA_FOUND);
                responseDto.setMessage("Employee not found");
                responseDto.setContent(null);
                return new ResponseEntity(responseDto, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception ex){
            responseDto.setCode(VarList.RSP_ERROR);
            responseDto.setMessage(ex.getMessage());
            responseDto.setContent(null);
            return new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
