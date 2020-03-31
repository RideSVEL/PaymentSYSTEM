package ua.nure.vasilchenko.SummaryTask4.db.enumeration;

import ua.nure.vasilchenko.SummaryTask4.db.entity.User;

/**
 * Role entity.
 * 
 * @author S.Vasilchenko
 * 
 */

public enum Role {
	ADMIN, CLIENT;
	
	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
