package com.ccil.vms.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "VMS_VENDOR_MST")
public class Vendor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String vendorName;
	private String vendorDomain;
	private String officialemailId;
	private String website;
	private String activeStatus;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorDomain() {
		return vendorDomain;
	}

	public void setVendorDomain(String vendorDomain) {
		this.vendorDomain = vendorDomain;
	}

	public String getOfficialemailId() {
		return officialemailId;
	}

	public void setOfficialemailId(String officialemailId) {
		this.officialemailId = officialemailId;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

}
