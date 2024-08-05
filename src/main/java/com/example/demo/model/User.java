
package com.example.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Users", schema = "dbo", catalog = "java6")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int usersId;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String fullName;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String profileImage;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@Column(nullable = false)
	private Boolean gender;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String phone;

	@ManyToOne
	@JoinColumn(name = "roleId", nullable = false)
	private Role roleId;
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ShoppingCart> shoppingCarts = new ArrayList<>();

	@OneToMany(mappedBy = "users_id", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Address> addresses = new ArrayList<>();


	@Override
	public String toString() {
		return "User{" +
				"usersId=" + usersId +
				", username='" + username + '\'' +
				", fullName='" + fullName + '\'' +
				", password='" + password + '\'' +
				", profileImage='" + profileImage + '\'' +
				", birthDate=" + birthDate +
				", gender=" + gender +
				", email='" + email + '\'' +
				", phone='" + phone + '\'' +
				", roleId=" + roleId +
				", shoppingCarts=" + shoppingCarts +
				'}';
	}
}
