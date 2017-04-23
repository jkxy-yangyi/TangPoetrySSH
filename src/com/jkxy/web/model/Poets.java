package com.jkxy.web.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Poets {
	private Integer id;
	private String name;	////作者
	private Date created_at;	//创建时间
	private Date updated_at;	//更新时间
	private Set poetries = new HashSet(0);
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public Date getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	public Set getPoetries() {
		return poetries;
	}
	public void setPoetries(Set poetries) {
		this.poetries = poetries;
	}
	
	
}
