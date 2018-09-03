package com.All.mybatis.mapper;

import com.All.entity.Employee;

//使用xml配置文件的方式使用Mybatis
public interface EmployeeMapper {

	public Employee getEmpById(Integer id);

	public void insertEmp(Employee employee);

	public void updateEmp(Employee employee);

	public void deleteEmpById(Integer id);

	Employee getEmpByLastName(String lastName);

}
