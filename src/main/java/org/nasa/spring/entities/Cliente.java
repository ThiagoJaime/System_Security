package org.nasa.spring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 128)
    private String nome; 
	
	@Column(nullable = false, length = 128)
	private String email; 
	
	@Column(nullable = false, length = 60)
	private String senha;
	
	@Column(nullable = false, length = 15)
	private String telefone;



}
		

