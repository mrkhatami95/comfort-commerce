package model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class User {
	protected long id;
	protected String username;
	protected String password;
	protected BigDecimal budget;
	protected long basketId;
	protected int statusId;
	protected long addressId;
	protected long roleId;
	protected String phoneNumber;

	public User(long id, String username, String password, BigDecimal budget, long basketId, int statusId, long addressId, long roleId, String phoneNumber) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.budget = budget;
		this.basketId = basketId;
		this.statusId = statusId;
		this.addressId = addressId;
		this.roleId = roleId;
		this.phoneNumber = phoneNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public BigDecimal getBudget() {
		return budget;
	}

	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}

	public long getBasketId() {
		return basketId;
	}

	public void setBasketId(long basketId) {
		this.basketId = basketId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public long getAddressId() {
		return addressId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
}
