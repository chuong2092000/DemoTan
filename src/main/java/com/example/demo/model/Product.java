package com.example.demo.model;

import jakarta.persistence.*;
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
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Products", schema = "dbo", catalog = "java6")
//Khang
@ToString(exclude = "invoiceItems")
public class Product {
	//ly
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@Column(nullable = false)
	private String productName;

	@Column(nullable = false)
	private float price;

	@Column(nullable = false)
	private int publishingYear;

	@Column(nullable = false)
	private String weight;

	@Column(nullable = false)
	private String size;

	@Column(nullable = false)
	private String NumberOfPages;

	@Column(nullable = false)
	private String language;

	@Column(nullable = false)
	private String author;

	@Column(nullable = false, columnDefinition = "NVARCHAR(MAX)")
	private String description;

	@Column(nullable = false)
	private String manufacturer;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date PostingDate;

	@Column(nullable = false)
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;

	@ManyToOne
	@JoinColumn(name = "imageId", nullable = false)
	private Image imageId;
	
	@OneToMany(mappedBy = "product")
	private List<InvoiceItem> invoiceItems;
}
