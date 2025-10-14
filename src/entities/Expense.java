package entities;

import java.time.LocalDate;

public class Expense {

	private int id;
	private String description;
	private double amount;
	private LocalDate createdAt;
	private LocalDate modifiedAt;
	
	public Expense (int id, String description, double amount) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		createdAt = LocalDate.now();
		modifiedAt = LocalDate.now();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDate modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	
}
