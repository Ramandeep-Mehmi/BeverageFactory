package com.beveragefactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeverageFactoryApplication {
	public static void main(String[] args) throws InvalidOrderExcecption {
		BeverageBuilder bb = new BeverageBuilder();
		String[] test = new String[] { "Chai,-milk,-water", "Chai", "Coffee, -milk" };
		Order myOrder = bb.prepareOrder(test);
		myOrder.printOrder();
	}
}

interface Item {
	String getName();

	Double getCost();

	void print();
}

abstract class Beverage implements Item {
	protected String name;
	List<String> ingredientsRemoved;
	Set<String> allIngredients;
	protected IngredientsCostHelper ingredientsCostCalculator;

	Beverage() {
		ingredientsCostCalculator = IngredientsCostHelper.getInstance();
	}

	public void print() {
		System.out.print(name + ": \"");
		System.out.print(getBaseIngredient() + ",");
		int i = 1;
		if (ingredientsRemoved != null)
			allIngredients.removeAll(ingredientsRemoved);
		for (String ingredient : allIngredients) {
			if (i < allIngredients.size())
				System.out.print(ingredient + ",");
			else
				System.out.print(ingredient + "\"");
			i++;
		}
	}

	public Set<String> getAllIngredients() {
		return allIngredients;
	}

	public Double getCost() {
		return getBasePrice() + ingredientsCostCalculator.getCostOfIngredients(allIngredients);
	}

	protected abstract Double getBasePrice();

	public abstract String getBaseIngredient();
}

class IngredientsCostHelper {
	private static IngredientsCostHelper INSTANCE = new IngredientsCostHelper();
	Map<String, Double> ingredientsCostMap = new HashMap<>();

	IngredientsCostHelper() {
		ingredientsCostMap.put("milk", 1.0);
		ingredientsCostMap.put("sugar", 0.5);
		ingredientsCostMap.put("soda", 0.5);
		ingredientsCostMap.put("mint", 0.5);
		ingredientsCostMap.put("water", 0.5);
	}

	public Double getCostOfIngredients(Set<String> ingredients) {
		Double cost = 0.0;
		for (String ingredient : ingredients) {
			cost += ingredientsCostMap.get(ingredient.toLowerCase());
		}
		return cost;
	}

	public static IngredientsCostHelper getInstance() {
		return INSTANCE;
	}
}

class InvalidOrderExcecption extends Exception {
	private static final long serialVersionUID = 391132779138833156L;
	String message;

	InvalidOrderExcecption(String message) {
		super(message);
		this.message = message;
	}
}

class Chai extends Beverage {
	private Double baseIngredientPrice = new Double(2);
	private String baseIngredient = new String("Tea");

	public Chai(String name, List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		this.ingredientsRemoved = ingredientsRemoved;
		this.name = name;
		allIngredients = new HashSet<>();
		allIngredients.add("milk");
		allIngredients.add("sugar");
		allIngredients.add("water");

		if (ingredientsRemoved != null) {
			validateIngredients(ingredientsRemoved);
			allIngredients.removeAll(ingredientsRemoved);
		}
		if (allIngredients.size() == 0)
			throw new InvalidOrderExcecption("Order is invalid : Can't remove all ingredients");
	}

	private void validateIngredients(List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		if (!allIngredients.containsAll(ingredientsRemoved))
			throw new InvalidOrderExcecption("Invalid Order Exception : invalid ingredients");

	}

	public String getName() {
		return name;
	}

	public Double getBasePrice() {
		return baseIngredientPrice;
	}

	public String getBaseIngredient() {
		return baseIngredient;
	}
}

class Coffee extends Beverage {
	private Double basePrice = new Double(3);
	private String baseIngredient = new String("Coffee");

	public Coffee(String name, List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		this.name = name;
		this.ingredientsRemoved = ingredientsRemoved;

		allIngredients = new HashSet<>();
		allIngredients.add("milk");
		allIngredients.add("sugar");
		allIngredients.add("water");

		if (ingredientsRemoved != null) {
			validateIngredients(ingredientsRemoved);
			allIngredients.removeAll(ingredientsRemoved);
		}
		if (allIngredients.size() == 0)
			throw new InvalidOrderExcecption("Order is invalid : Can't remove all ingredients");
	}

	public String getName() {
		return name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public String getBaseIngredient() {
		return baseIngredient;
	}

	private void validateIngredients(List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		if (!allIngredients.containsAll(ingredientsRemoved))
			throw new InvalidOrderExcecption("Invalid Order Exception : invalid ingredients");

	}
}

class BananaSmoothie extends Beverage {
	private Double basePrice = new Double(4);
	private String baseIngredient = "banana";

	public BananaSmoothie(String name, List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		this.name = name;
		this.ingredientsRemoved = ingredientsRemoved;

		allIngredients = new HashSet<>();
		allIngredients.add("milk");
		allIngredients.add("sugar");
		allIngredients.add("water");

		if (ingredientsRemoved != null) {
			validateIngredients(ingredientsRemoved);
			allIngredients.removeAll(ingredientsRemoved);
		}
		if (allIngredients.size() == 0)
			throw new InvalidOrderExcecption("Order is invalid : Can't remove all ingredients");
	}

	public String getName() {
		return name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public String getBaseIngredient() {
		return baseIngredient;
	}

	private void validateIngredients(List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		if (!allIngredients.containsAll(ingredientsRemoved))
			throw new InvalidOrderExcecption("Invalid Order Exception : invalid ingredients");

	}
}

class StrawberryShake extends Beverage {
	private Double basePrice = new Double(5);
	private String baseIngredient = "Straberries";

	public StrawberryShake(String name, List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		this.name = name;
		this.ingredientsRemoved = ingredientsRemoved;

		allIngredients = new HashSet<>();
		allIngredients.add("milk");
		allIngredients.add("sugar");
		allIngredients.add("water");

		if (ingredientsRemoved != null) {
			validateIngredients(ingredientsRemoved);
			allIngredients.removeAll(ingredientsRemoved);
		}
		if (allIngredients.size() == 0)
			throw new InvalidOrderExcecption("Order is invalid : Can't remove all ingredients");
	}

	public String getName() {
		return name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public String getBaseIngredient() {
		return baseIngredient;
	}

	private void validateIngredients(List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		if (!allIngredients.containsAll(ingredientsRemoved))
			throw new InvalidOrderExcecption("Invalid Order Exception : invalid ingredients");

	}
}

class Mojito extends Beverage {
	private Double basePrice = new Double(5.5);
	private String baseIngredient = "Lemon";

	public Mojito(String name, List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		this.name = name;
		this.ingredientsRemoved = ingredientsRemoved;

		allIngredients = new HashSet<>();
		allIngredients.add("sugar");
		allIngredients.add("water");
		allIngredients.add("soda");
		allIngredients.add("mint");

		if (ingredientsRemoved != null) {
			validateIngredients(ingredientsRemoved);
			allIngredients.removeAll(ingredientsRemoved);
		}
		if (allIngredients.size() == 0)
			throw new InvalidOrderExcecption("Order is invalid : Can't remove all ingredients");

	}

	public String getName() {
		return name;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public String getBaseIngredient() {
		return baseIngredient;
	}

	private void validateIngredients(List<String> ingredientsRemoved) throws InvalidOrderExcecption {
		if (!allIngredients.containsAll(ingredientsRemoved))
			throw new InvalidOrderExcecption("Invalid Order Exception : invalid ingredients");

	}
}

class Order {
	List<Item> order = new ArrayList<>();

	public void addItem(Item item) {
		order.add(item);
	}

	public Double getCost() {
		Double cost = 0.0;
		for (Item item : order) {
			cost += item.getCost();
		}
		return cost;
	}

	public void printOrder() {
		for (Item item : order) {
			item.print();
			System.out.println(" Price: " + item.getCost() + "$");
		}
	}
}

class BeverageBuilder {

	private Order order;
	Set<String> ingredients;
	Set<String> beverages;

	BeverageBuilder() {
		order = new Order();
		ingredients = new HashSet<>();
		beverages = new HashSet<>();

		ingredients.add("milk");
		ingredients.add("sugar");
		ingredients.add("soda");
		ingredients.add("mint");
		ingredients.add("water");

		beverages.add("Chai");
		beverages.add("Coffee");
		beverages.add("Banana Smoothie");
		beverages.add("Strawberry Shake");
		beverages.add("Mojito");

	}

	private Order prepareOrder(String itemType, List<String> exclusions) throws InvalidOrderExcecption {
		validateOrder(itemType, exclusions);
		Item item = null;
		if ("Chai".equals(itemType)) {
			item = new Chai(itemType, exclusions);
		} else if ("Coffee".equals(itemType)) {
			item = new Coffee(itemType, exclusions);
		} else if ("Banana Smoothie".equals(itemType)) {
			item = new BananaSmoothie(itemType, exclusions);
		} else if ("Strawberry Shake".equals(itemType)) {
			item = new StrawberryShake(itemType, exclusions);
		} else if ("Mojito".equals(itemType)) {
			item = new Mojito(itemType, exclusions);
		}

		order.addItem(item);
		return order;
	}

	private void validateOrder(String itemType, List<String> exclusions) throws InvalidOrderExcecption {
		if (!beverages.contains(itemType)) {
			throw new InvalidOrderExcecption("Order is not valid : Contains invalid beverage");
		}
	}

	public Order prepareOrder(String[] items) throws InvalidOrderExcecption {
		if (items == null || items.length == 0)
			throw new InvalidOrderExcecption("Order is not valid : no items in the order");
		for (String item : items) {
			if (item.isEmpty())
				throw new InvalidOrderExcecption("Order is not valid : no items in the order");
			item = item.replaceAll("-", "");
			if (item.contains(",")) {
				List<String> itemAndExclusion = Arrays.stream(item.trim().split(",")).map(s->s.trim()).collect(Collectors.toList());
				String itemType = itemAndExclusion.get(0);
				itemAndExclusion.remove(0);
				order = prepareOrder(itemType, itemAndExclusion);
			} else {
				order = prepareOrder(item, null);
			}
		}
		return order;
	}
}

