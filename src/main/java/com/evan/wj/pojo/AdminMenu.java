package com.evan.wj.pojo;

import javax.persistence.*;

@Entity
@Table(name = "admin_menu")
public class AdminMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "path", length = 64)
	private String path;

	@Column(name = "name", length = 64)
	private String name;

	@Column(name = "name_zh", length = 64)
	private String nameZh;

	@Column(name = "icon_cls", length = 64)
	private String iconCls;

	@Column(name = "component", length = 64)
	private String component;

	@Column(name = "parent_id")
	private Integer parentId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameZh() {
		return nameZh;
	}

	public void setNameZh(String nameZh) {
		this.nameZh = nameZh;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

}