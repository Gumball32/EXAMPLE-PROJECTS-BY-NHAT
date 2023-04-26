package Model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	  private static Cart instance;
	  private List<OrderItem> items;

	  private Cart() {
	    items = new ArrayList<>();
	  }

	  public static synchronized Cart getInstance() {
	    if (instance == null) {
	      instance = new Cart();
	    }
	    return instance;
	  }

	  public void addItem(OrderItem item) {
		  for (OrderItem existingItem : items) {
			System.out.println(existingItem.getName() + existingItem.getAmount() + "TOI DAY");
		    if (existingItem.getName().equals(item.getName())) {
		      // Update the existing item's quantity
		      existingItem.setAmount(existingItem.getAmount() + item.getAmount());
		      return;
		    }
		  }
		  // If the item doesn't exist in the cart, add it
		  items.add(item);
	  }

	  public void removeItem(OrderItem item) {
	    items.remove(item);
	  }

	  public List<OrderItem> getItems() {
	    return items;
	  }

	  public void clear() {
	    items.clear();
	  }

	  public int getItemCount() {
	    return items.size();
	  }

	  public double getTotal() {
	    double total = 0.0;
	    for (OrderItem item : items) {
	      total += item.getPrice() * item.getAmount();
	    }
	    return total;
	  }
	}
