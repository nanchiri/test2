package com.management.entities;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 实体类：商品
 */

public class Goods {

	public int amount;
	private Integer id;
	private String name;
	private List<String> img;
	private Double price;
	private Integer type_id;
	private String typeName;
	private Integer isDelete;
	private Integer isShow;
	private Map<String, String> errors = new HashMap<String, String>();

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

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

	public List<String> getImg() {
		return img;
	}

	public void setImg(List<String> img) {
		this.img = img;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}

	public Integer getIsShow() {
		return isShow;
	}

	public void setIsShow(Integer isShow) {
		this.isShow = isShow;
	}


	@Override
	public String toString() {
		return "Goods{" +
				"amount=" + amount +
				", id=" + id +
				", name='" + name + '\'' +
				", img=" + img +
				", price=" + price +
				", type_id=" + type_id +
				", typeName='" + typeName + '\'' +
				", isDelete=" + isDelete +
				", isShow=" + isShow +
				", errors=" + errors +
				'}';
	}
}
