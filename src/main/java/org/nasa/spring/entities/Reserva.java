package org.nasa.spring.entities;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import lombok.Data;



@Data
@Entity
@Table(name = "Reserva")
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private double valor;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate  dataIda;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataRetorno;
	
	@ManyToOne
	@JoinColumn(name = "destino_id", foreignKey = @ForeignKey(name = "fk_reserva_destino", foreignKeyDefinition = "FOREIGN KEY (destino_id) REFERENCES Destino(id) ON UPDATE CASCADE ON DELETE CASCADE"))
	private Destino destino;

	@OneToOne
	@JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_reserva_cliente", foreignKeyDefinition = "FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON UPDATE CASCADE ON DELETE CASCADE"))
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "promocao_id", foreignKey = @ForeignKey(name = "fk_reserva_promocao", foreignKeyDefinition = "FOREIGN KEY (promocao_id) REFERENCES Promocoes(id) ON UPDATE CASCADE ON DELETE CASCADE"))
	private Promocao promocao;

	
}
