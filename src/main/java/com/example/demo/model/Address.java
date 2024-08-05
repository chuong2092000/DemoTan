package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Address")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Integer id;
	@Column(nullable = false, length = 255)
	@Nationalized
	private String street;

	@ManyToOne
	@JoinColumn(name = "commune_id", nullable = false)
	private Commune commune;

	@ManyToOne
	@JoinColumn(name = "district_id", nullable = false)
	private District district;

	@ManyToOne
	@JoinColumn(name = "province_id", nullable = false)
	private Province province;
	boolean status;
	@ManyToOne
	@JoinColumn(name = "users_id")
	private User users_id;

	@Override
	public String toString() {
		return "Address{" +
				"id=" + id +
				", street='" + street + '\'' +
				", commune=" + commune +
				", district=" + district +
				", province=" + province +
				", status=" + status +
				", users_id=" + users_id +
				'}';
	}
}
