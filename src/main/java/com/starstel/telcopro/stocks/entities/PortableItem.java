package com.starstel.telcopro.stocks.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sosthene Nouebissi
 * @version 1.0
 * @created 13-oct.-2018 07:24:20
 */
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PortableItem implements Serializable
{
	@Id
	@SequenceGenerator(initialValue = 1, sequenceName = "PITEM_SEQ", allocationSize = 1, name = "pitem_id")
    @GeneratedValue(generator = "pitem_id")
	private Long id;
	private String codeQrc;
	private String codeBar;
	private String serial;
	private String reference;
	@ManyToOne @JsonIgnore
	private Portable portable;
	@ManyToOne @JsonIgnore
	private MouvmentLine mouvmentLine;
	@Override
	public int hashCode() {
		final int prime = 37;
		int result = 1;
		result = prime * result + ((codeBar == null) ? 0 : codeBar.hashCode());
		result = prime * result + ((codeQrc == null) ? 0 : codeQrc.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reference == null) ? 0 : reference.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortableItem other = (PortableItem) obj;
		if (codeBar == null) {
			if (other.codeBar != null)
				return false;
		} else if (!codeBar.equals(other.codeBar))
			return false;
		if (codeQrc == null) {
			if (other.codeQrc != null)
				return false;
		} else if (!codeQrc.equals(other.codeQrc))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		return true;
	}
	
	
}