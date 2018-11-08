package com.starstel.telcopro.stocks.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Sosthene Nouebissi
 * @version 1.0
 * @created 13-oct.-2018 07:24:13
 */
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Portable extends Product 
{
	private String connection;
	private Double screen;
	private String battery;
	private String sim;
	private Double dimension;
	private Double weight;
	private String ipRating;
	private Boolean fingerprint;
	private Boolean waterproof;
	private Boolean splashproof;
	private Boolean dushproof;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="portable")
	@JsonIgnore
	private Set<PortableItem> portableItem = new HashSet<>();
	@ManyToOne
	private Memory memory;
	@ManyToOne
	private Camera camera;
	@ManyToOne
	private Cpu cpu;
	@ManyToOne
	private SystemOS os;
	@ManyToOne
	private PortableCategory portableCategory;
	
	public Portable(Long id, Double quantity, Date dateCreation, String designation, String image, Double priceUnit,
			Double priceUnitWholesaler, Double priceUnitSemiWholesaler, Double stockMinim, Double stockAlert,
			Double volume, String note, State state, Emplacement emplacement, MeasureUnit measureUnit,
			ProductCategory productCategory, Set<MouvmentLine> mouvmentLines, String connection, Double screen,
			String battery, String sim, Double dimension, Double weight, String ipRating, Boolean fingerprint,
			Boolean waterproof, Boolean splashproof, Boolean dushproof, Set<PortableItem> portableItem, Memory memoire,
			Camera camera, Cpu cpu, SystemOS os, PortableCategory portableCategory, AppColor appColor) {
		super(id, quantity, dateCreation, designation, image, priceUnit, priceUnitWholesaler, priceUnitSemiWholesaler,
				stockMinim, stockAlert, volume, note, state, emplacement, measureUnit, productCategory, appColor,mouvmentLines);
		this.connection = connection;
		this.screen = screen;
		this.battery = battery;
		this.sim = sim;
		this.dimension = dimension;
		this.weight = weight;
		this.ipRating = ipRating;
		this.fingerprint = fingerprint;
		this.waterproof = waterproof;
		this.splashproof = splashproof;
		this.dushproof = dushproof;
		this.portableItem = portableItem;
		this.memory = memoire;
		this.camera = camera;
		this.cpu = cpu;
		this.os = os;
		this.portableCategory = portableCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 37;
		int result = super.hashCode();
		result = prime * result + ((battery == null) ? 0 : battery.hashCode());
		result = prime * result + ((connection == null) ? 0 : connection.hashCode());
		result = prime * result + ((dimension == null) ? 0 : dimension.hashCode());
		result = prime * result + ((dushproof == null) ? 0 : dushproof.hashCode());
		result = prime * result + ((fingerprint == null) ? 0 : fingerprint.hashCode());
		result = prime * result + ((ipRating == null) ? 0 : ipRating.hashCode());
		result = prime * result + ((screen == null) ? 0 : screen.hashCode());
		result = prime * result + ((sim == null) ? 0 : sim.hashCode());
		result = prime * result + ((splashproof == null) ? 0 : splashproof.hashCode());
		result = prime * result + ((waterproof == null) ? 0 : waterproof.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Portable other = (Portable) obj;
		if (battery == null) {
			if (other.battery != null)
				return false;
		} else if (!battery.equals(other.battery))
			return false;
		if (connection == null) {
			if (other.connection != null)
				return false;
		} else if (!connection.equals(other.connection))
			return false;
		if (dimension == null) {
			if (other.dimension != null)
				return false;
		} else if (!dimension.equals(other.dimension))
			return false;
		if (dushproof == null) {
			if (other.dushproof != null)
				return false;
		} else if (!dushproof.equals(other.dushproof))
			return false;
		if (fingerprint == null) {
			if (other.fingerprint != null)
				return false;
		} else if (!fingerprint.equals(other.fingerprint))
			return false;
		if (ipRating == null) {
			if (other.ipRating != null)
				return false;
		} else if (!ipRating.equals(other.ipRating))
			return false;
		if (screen == null) {
			if (other.screen != null)
				return false;
		} else if (!screen.equals(other.screen))
			return false;
		if (sim == null) {
			if (other.sim != null)
				return false;
		} else if (!sim.equals(other.sim))
			return false;
		if (splashproof == null) {
			if (other.splashproof != null)
				return false;
		} else if (!splashproof.equals(other.splashproof))
			return false;
		if (waterproof == null) {
			if (other.waterproof != null)
				return false;
		} else if (!waterproof.equals(other.waterproof))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Portable [id="+getId()+"connection=" + connection + ", screen=" + screen + ", battery=" + battery + ", sim=" + sim
				+ ", dimension=" + dimension + ", weight=" + weight + ", ipRating=" + ipRating + ", fingerprint="
				+ fingerprint + ", waterproof=" + waterproof + ", splashproof=" + splashproof + ", dushproof="
				+ dushproof + ", portableItem=" + portableItem + ", memory=" + memory + ", camera=" + camera + ", cpu="
				+ cpu + ", os=" + os + ", portableCategory=" + portableCategory + "]";
	}
	
	
}