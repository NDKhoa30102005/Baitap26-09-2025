package com.example.demo.entity;

import java.util.Set;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users") // Tên bảng trong cơ sở dữ liệu là 'Users'
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") 
    private Long id; 

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email") 
    private String email;

    public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	@Column(name = "password") 
    private String password;

    @Column(name = "phone") 
    private String phone;

    @ManyToMany
    @JoinTable(
        name = "user_category", // bảng liên kết 
        joinColumns = @JoinColumn(name = "user_id"), // Khóa ngoại cho User
        inverseJoinColumns = @JoinColumn(name = "category_id") // Khóa ngoại cho Category
    )
    private Set<Category> categories; // Quan hệ nhiều-nhiều với Category
}
