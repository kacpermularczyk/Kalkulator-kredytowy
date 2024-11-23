package com.jsfcourse.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ejb.EJB;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.RoleDAO;
import com.jsf.entities.Role;

@Named
@RequestScoped
public class RoleListBB {
	private static final String PAGE_ROLE_EDIT = "personEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String role;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	RoleDAO roleDAO;
		
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Role> getFullList(){
		return roleDAO.getFullList();
	}

	public List<Role> getList(){
		List<Role> list = null;
		
		
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (role != null && role.length() > 0){
			searchParams.put("role", role);
		}
		
		
		list = roleDAO.getList(searchParams);
		
		return list;
	}

	public String newRole(){
		Role role = new Role();
		
		
		flash.put("role", role);
		
		return PAGE_ROLE_EDIT;
	}

	public String editRole(Role role){
		
		flash.put("role", role);
		
		return PAGE_ROLE_EDIT;
	}

	public String deleteRole(Role role){
		roleDAO.remove(role);
		return PAGE_STAY_AT_THE_SAME;
	}
}
