package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.starstel.telcopro.rh.entities.Employee;
import com.starstel.telcopro.rh.entities.WorkSpace;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Commande extends Mouvment implements Serializable {
	private boolean succeed;
	private Date limitDelayDate;
	public Commande(Mouvment mouvment, boolean confirmer, Date dateLimiteAttente) {
		super(null, mouvment.getDate(), mouvment.getQuantity(), mouvment.getPriceTotal(), 
				mouvment.getWorkSpaceReceiver(), mouvment.getMouvmentLines(),
				mouvment.getMouvmentType(),mouvment.getUser(), mouvment.getReceiver(), mouvment.getRecipient());
		this.succeed = confirmer;
		this.limitDelayDate = dateLimiteAttente;
	}
	
}
