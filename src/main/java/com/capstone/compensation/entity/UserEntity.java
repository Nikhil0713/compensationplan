package com.capstone.compensation.entity;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.capstone.compensation.model.UserRoles;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Entity
@Table(name = "User_details")
@JsonDeserialize(as= UserEntity.class)
public class UserEntity {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "employ_id")
		private Integer employId;
		@Column(name = "user_name")
		private String userName;
		@Column(name = "email_id")
		private String emailId;
		@Column(name = "password")
		private String password;
		@Column(name = "job_title")
		private String jobtitle;
		@Column(name = "location")
		private String location;
		@Column(name = "department")
		private String department;
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "user_roles", 
					joinColumns = @JoinColumn(name = "employ_id"), 
					inverseJoinColumns = @JoinColumn(name = "role_id"))
		private Set<UserRoleEntity> roles = new HashSet<>();
		
		@Enumerated(EnumType.STRING)
		@Column(length = 20)
		private UserRoles name;

		
		public String getEmailId() {
			return emailId;
		}

		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public Integer getEmployId() {
			return employId;
		}

		public void setEmployId(Integer employId) {
			this.employId = employId;
		}

		public String getJobtitle() {
			return jobtitle;
		}

		public void setJobtitle(String jobtitle) {
			this.jobtitle = jobtitle;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		
		public Set<UserRoleEntity> getRoles() {
			return roles;
		}

		public void setRoles(Set<UserRoleEntity> roles) {
			this.roles = roles;
		}

		public UserRoles getName() {
			return name;
		}

		public void setName(UserRoles name) {
			this.name = name;
		}

		public UserEntity() {
			super();
		}

		public UserEntity(String userName) {
			super();
			this.userName = userName;
		}

		public UserEntity(String userName, String emailId, String password,String jobtitle,String location,String department) {
			super();
			this.userName = userName;
			this.emailId = emailId;
			this.password = password;
			this.jobtitle= jobtitle;
			this.department= department;
			this.location = location;
			
			
		}

		public UserEntity(Integer employId, String userName, String emailId, String password,String jobtitle,String location,String department) {
			this(userName,emailId,password,jobtitle,department,location);
			this.employId = employId;
		}

		public UserEntity(String username, String email, String password) {
			this.userName = username;
			this.emailId = email;
			this.password = password;
		}

		@Override
		public String toString() {
			return "User [userId=" + employId + ", userName=" + userName + "]";
		}

	}



