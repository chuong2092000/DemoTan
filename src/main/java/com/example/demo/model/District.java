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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Districts")
public class District {
    @Id
    private Integer district_id;
    
    @Column(nullable = false, length = 50)
    @Nationalized
    private String district_name;
    
    @ManyToOne
    @JoinColumn(name = "province_id", nullable = false)
    private Province province;
    @Override
    public String toString() {
        return "District{name='" + district_name + "'}";
    }
}