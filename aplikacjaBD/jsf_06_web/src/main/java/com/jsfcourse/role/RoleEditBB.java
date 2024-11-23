package com.jsfcourse.role;

import java.io.IOException;
import java.io.Serializable;

import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.context.Flash;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

import com.jsf.dao.RoleDAO;
import com.jsf.entities.Role;

@Named
@ViewScoped
public class RoleEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_ROLE_LIST = "personList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Role role = new Role();
	private Role loaded = null;

	@EJB
	RoleDAO roleDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Role getRole() {
		return role;
	}

	public void onLoad() throws IOException {
		loaded = (Role) flash.get("role");

		if (loaded != null) {
			role = loaded;
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
		}

	}

	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (role.getIdRole() == null) {
				roleDAO.create(role);
			} else {
				roleDAO.merge(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_ROLE_LIST;
	}
}
