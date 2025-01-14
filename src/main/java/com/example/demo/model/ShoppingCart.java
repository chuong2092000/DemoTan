package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Shoppingcarts")
public class ShoppingCart {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
	
    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private Double finalPrice;

    //Khang
    @ManyToOne
	@JoinColumn(name = "users_id", nullable = false)
	private User user;

    //Khang
	public String getUsername() {
		return user.getUsername();
	}
    
    //ly
    @OneToMany(mappedBy = "shoppingCart")
    private List<CartItem> cartItems;
    
//    @Override
//    public String toString() {
//        return "ShoppingCart{" +
//               "cartId=" + cartId +
//               ", quantity=" + quantity +
//               ", totalPrice=" + totalPrice +
//               ", finalPrice=" + finalPrice +
//               '}';
//    }
}