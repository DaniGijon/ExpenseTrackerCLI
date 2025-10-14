package entities;

import java.time.LocalDate;

public class Expense {

	private int lastId = 0;
	private int id;
	private String description;
	private double amount;
	private LocalDate createdAt;
	
	public Expense (String description, double amount) {
		this.id = ++lastId;
		this.description = description;
		this.amount = amount;
		createdAt = LocalDate.now();
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
	
	
}
