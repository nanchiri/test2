package com.management.service;

import java.util.List;

import com.management.entities.Item;

public interface ItemService {
	
	public List<Item> searchItemByOrder(Integer id);
	
	public List<Item> searchItemByUser(Integer id);
	
	public Item searchItemById(Integer id);
	
	public void registItem(Item item);
	
	public void alertItem(Item item);
	
	public void deleteItem(Integer id);
	

}
