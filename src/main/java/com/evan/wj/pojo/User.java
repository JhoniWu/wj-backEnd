package com.evan.wj.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;

/*
	@Entity 表示这是一个实体类
	@Table(name=“user”) 表示对应的表名是 user
*/
@Entity
@Table(name = "user")
/*
	前后端分离，交互使用的格式是JSON，所以User对象会被转换成Json数据。
	jpa默认会使用hibernate，在jpa工作过程中，会创造代理类继承User，并添加handler和hibernaLazyInitializer
	这两个无序json的属性，所以用这个JsonIgnoreProperties来把这两个属性忽略
 */
@JsonIgnoreProperties({"handle", "hibernateLazyInitializer"})

public class User {

	@Id
	@GeneratedValue
	@Column(name = "id")
	int id;

	String username;
	String password;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
