package com.starstel.telcopro.stocks.services;

import java.util.List;
import java.util.Set;

import com.starstel.telcopro.stocks.entities.Recipient;
import com.starstel.telcopro.stocks.entities.RecipientGroupe;

public interface RecipientService 
{
	// RecipientGroupe
	List<RecipientGroupe> listRecipientGroupe();
	RecipientGroupe createRecipientGroupe(RecipientGroupe recipientGroupe);
	RecipientGroupe editRecipientGroupe(RecipientGroupe recipientGroupe);
	Boolean deleteRecipientGroupe(Long id);
	RecipientGroupe getRecipientGroupe(Long id);
	List<RecipientGroupe> searchGroupe(String keyWords);
	
	// Recipient
	List<Recipient> listRecipient();
	Recipient createRecipient(Recipient recipient);
	Recipient editRecipient(Recipient recipient);
	Boolean deleteRecipient(Long id);
	Recipient getRecipient(Long id);
	Set<Recipient> listRecipientOfGroupe(Long id);
	Boolean addRecipientToGroupe(Long idRecipient,Long idGroupe);
	List<Recipient> search(String keyWords);
}
