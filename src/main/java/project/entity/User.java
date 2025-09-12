package project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "user")
@Builder
@Getter 
@Setter 
@AllArgsConstructor 
@NoArgsConstructor
public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private int id;

	private String username;
	
	private String email;

}
