package entities;

import java.time.LocalDateTime;

public class Expense {

	private int id;
	private String description;
	private double amount;
	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
	String category;
	
	public Expense (int id, String description, double amount, String category) {
		this.id = id;
		this.description = description;
		this.amount = amount;
		createdAt = LocalDateTime.now();
		modifiedAt = LocalDateTime.now();
		this.category = category;
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}
	public void setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
