package com.capstone.compensation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capstone.compensation.model.UserRoles;



@Entity
@Table(name="user_role")
public class UserRoleEntity {

	@Id
	@Column(name="role_id")
	private Long roleId;
	@Column(name="role_name")
	private String roleName;
	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private UserRoles name;
	@Column(name="location")
	private String location;
	@Column(name="jobtitle")
	private String jobtitle;
	@Column(name="department")
	private String department;
	  
	
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRoles getName() {
		return name;
	}
	public void setName(UserRoles name) {
		this.name = name;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobtitle() {
		return jobtitle;
	}
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "UserRoleVo [roleId=" + roleId + ", roleName=" + roleName + ", name=" + name + ", jobtitle=" + jobtitle + " "
				+ ", department=" + department + " location=" + location+ "  ]";
	}
	public UserRoleEntity() {
		super();
	}
	public UserRoleEntity(Long roleId, String roleName, UserRoles name,String location,String jobtitle,String department) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.name = name;
		this.jobtitle=jobtitle;
		this.location= location;
		this.department= department;
	}

	
	
}
