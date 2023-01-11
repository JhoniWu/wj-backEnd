package com.evan.wj.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"handler" , "hibernateLazyInitializer"})
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")

	int id;
	String name;

	public int getId(){return this.id;};
	public String getName(){return this.name;};
	public void setId(int id){this.id = id;};
	public void setName(String name){this.name = name;};

}
