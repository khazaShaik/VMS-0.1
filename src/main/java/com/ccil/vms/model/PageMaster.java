package com.ccil.vms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "VMS_PAGE_MASTER")
public class PageMaster {

	private Role role;

	private Long pageId;
	private String pageName;
	private String pageSubName;
	private String pageIcon;
	private String pageUrl;
	private String pageDiscriptiion;
	
	
	

	public String getPageName() {
		return pageName;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getPageSubName() {
		return pageSubName;
	}
	public void setPageSubName(String pageSubName) {
		this.pageSubName = pageSubName;
	}
	public String getPageIcon() {
		return pageIcon;
	}
	public void setPageIcon(String pageIcon) {
		this.pageIcon = pageIcon;
	}
	public String getPageDiscriptiion() {
		return pageDiscriptiion;
	}
	public void setPageDiscriptiion(String pageDiscriptiion) {
		this.pageDiscriptiion = pageDiscriptiion;
	}
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	
}
