package com.ccil.vms.serviceImpl;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccil.vms.model.Employee;
import com.ccil.vms.model.EmployeeName;
import com.ccil.vms.model.User;
import com.ccil.vms.model.UserIdentity;
import com.ccil.vms.repository.EmployeeRepository;
import com.ccil.vms.service.EmployeeService;
@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	EmployeeRepository employeeRepository;
		

	@Override
	@Transactional
	public Employee getEmployeeById(Long id) {
		return employeeRepository.getOne(id);
	}
	@Override
	@Transactional
	public void saveEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
	}
	@Override
	@Transactional
	public void updateEmployee(Employee employee,long id) {
		Employee employee2 = employeeRepository.findOne(id);
		if(employee2!=null){
		employee2.setAbout(employee.getAbout());
		employee2.setCountry(employee.getCountry());
		employee2.setGender(employee.getGender());
		employee2.setMobile(employee.getMobile());
		employee2.setAlternateEmail(employee.getAlternateEmail());
		employee2.setDateOfBirth(employee.getDateOfBirth());
		employee2.setOccupation(employee.getOccupation());
		employee2.setWebsiteURL(employee.getWebsiteURL());
		saveEmployee(employee2);
		}else{
			saveEmployee(employee);
		}
	}
	@Override
	@Transactional
	public void deleteEmployee(Long id) {
		employeeRepository.delete(id);
	}
	@Override
	@Transactional
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	@Override
	public Employee findByUserIdentity(UserIdentity userIdentity) {
		// TODO Auto-generated method stub
		return employeeRepository.findByUserIdentity(userIdentity);
	}
}
