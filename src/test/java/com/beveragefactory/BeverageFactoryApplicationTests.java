package com.beveragefactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

public class BeverageFactoryApplicationTests {
	static BeverageBuilder beverageBuilder;

	@BeforeAll
	public static void init() {
		beverageBuilder = new BeverageBuilder();
	}

	@DisplayName("testNullInputArray")
	@ParameterizedTest()
	@NullSource
	public void testPrepareOrderWithNull_ShoudThrowInvalidOrderException(String[] itemsToBeOrdered) {
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	@DisplayName("testEmptyInputArray")
	@ParameterizedTest
	@EmptySource
	public void testPrepareOrderWithEmptyArray_ShoudThrowInvalidOrderException(String[] itemsToBeOrdered) {
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	

	@DisplayName("testtInvalidBeverage")
	@ParameterizedTest
	@MethodSource("inputInvalidBeverage")
	public void testPrepareOrderWithInvalidBeverage_ShoudThrowInvalidOrderException(String [] itemsToBeOrdered) {
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	@DisplayName("testInvalidIngredient")
	@ParameterizedTest
	@MethodSource("inputInvalidIngredient")
	public void testPrepareOrderWithInvalidIngredient_ShoudThrowInvalidOrderException(String [] itemsToBeOrdered) {
		//System.out.println(Arrays.toString(itemsToBeOrdered));
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	@DisplayName("testRemoveAllIngredients")
	@ParameterizedTest
	@MethodSource("inputRemoveAllIngredients")
	public void testPrepareOrderByRemovingAllIngredients_ShoudThrowInvalidOrderException(String [] itemsToBeOrdered) {
		//System.out.println(Arrays.toString(itemsToBeOrdered));
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	@DisplayName("testRemoveFromChaiIngredientsOfOtherBeverage")
	@ParameterizedTest
	@MethodSource("inputRemoveFromChaiIngredientsOfOtherBeverage")
	public void testRemoveFromChaiIngredientsOfOtherBeverage_ShoudThrowInvalidOrderException(String [] itemsToBeOrdered) {
		//System.out.println(Arrays.toString(itemsToBeOrdered));
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	@DisplayName("testRemoveFromMojitoIngredientsOfOtherBeverage")
	@ParameterizedTest
	@MethodSource("inputRemoveFromMojitoIngredientsOfOtherBeverage")
	public void testRemoveFromMojitoIngredientsOfOtherBeverage_ShoudThrowInvalidOrderException(String [] itemsToBeOrdered) {
		//System.out.println(Arrays.toString(itemsToBeOrdered));
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	@DisplayName("testinputWithoutBeverage")
	@ParameterizedTest
	@MethodSource("inputWithoutBeverage")
	public void testInputWithoutBeverage_ShoudThrowInvalidOrderException(String [] itemsToBeOrdered) {
		//System.out.println(Arrays.toString(itemsToBeOrdered));
		assertThrows(InvalidOrderExcecption.class, ()->beverageBuilder.prepareOrder(itemsToBeOrdered));
	}
	
	
	@DisplayName("testValidBeveragesShouldNotThrowInvalidOrderExecption")
	@ParameterizedTest
	@MethodSource("inputValidOrderItems")
	public void testValidBeverages_ShouldNotThrowInvalidOrderExecption(String [] itemsToBeOrdered) throws InvalidOrderExcecption {
		beverageBuilder.prepareOrder(itemsToBeOrdered);
	}
	
	@DisplayName("testBeveragesWithExceptedCost")
	@ParameterizedTest
	@MethodSource("inputBeveragesWithExceptedCost")
	public void testBeveragesWithExceptedCost_ShouldPass(String [] itemsToBeOrdered,Double expected) throws InvalidOrderExcecption {
		beverageBuilder=new BeverageBuilder();
		assertEquals(expected, beverageBuilder.prepareOrder(itemsToBeOrdered).getCost());
	}
	
	@DisplayName("testBeveragesWithIncorrectExceptedCost")
	@ParameterizedTest
	@MethodSource("inputBeveragesWithIncorrectExceptedCost")
	public void testBeveragesWithIncorrectExceptedCost_ShouldPass(String [] itemsToBeOrdered,Double expected) throws InvalidOrderExcecption {
		beverageBuilder=new BeverageBuilder();
		assertNotEquals(expected, beverageBuilder.prepareOrder(itemsToBeOrdered).getCost());
	}
	
	static Stream<Arguments> inputInvalidBeverage() {
	    return Stream.of(
	            Arguments.of((Object) new String[]{"Chaii, -milk"})
	    );
	}
	
	static Stream<Arguments> inputInvalidIngredient() {
	    return Stream.of(
	            Arguments.of((Object) new String[]{"Chai, -vine"})
	    );
	}
	
	static Stream<Arguments> inputRemoveAllIngredients() {
	    return Stream.of(
	            Arguments.of((Object) new String[]{"Chai, -water,-sugar,-milk"})
	    );
	}
	
	static Stream<Arguments> inputRemoveFromChaiIngredientsOfOtherBeverage() {
	    return Stream.of(
	            Arguments.of((Object) new String[]{"Chai, -water,-mint,-milk"})
	    );
	}
	
	static Stream<Arguments> inputWithoutBeverage() {
	    return Stream.of(
	    		Arguments.of((Object) new String[]{", -water,-mint,-milk"}),
	            Arguments.of((Object) new String[]{"-water,-mint,-milk"}),
	            Arguments.of((Object) new String[]{" -water,-mint,-milk"})
	    );
	}
	
	static Stream<Arguments> inputRemoveFromMojitoIngredientsOfOtherBeverage() {
	    return Stream.of(
	            Arguments.of((Object) new String[]{"Mojito, -water,-mint,-milk"})
	    );
	}
	
	static Stream<Arguments> inputValidOrderItems() {
	    return Stream.of(
	    		Arguments.of((Object) new String[]{"Chai"}),
	    		Arguments.of((Object) new String[]{"Chai, -water"}),
	            Arguments.of((Object) new String[]{"Chai, -water,-milk"}),
	            Arguments.of((Object) new String[]{"Coffee"}),
	            Arguments.of((Object) new String[]{"Coffee, -water"}),
	            Arguments.of((Object) new String[]{"Coffee, -water,-milk"}),
	            Arguments.of((Object) new String[]{"Banana Smoothie"}),
	            Arguments.of((Object) new String[]{"Banana Smoothie, -water"}),
	            Arguments.of((Object) new String[]{"Banana Smoothie, -water,-milk"}),
	            Arguments.of((Object) new String[]{"Mojito"}),
	            Arguments.of((Object) new String[]{"Mojito, -water"}),
	            Arguments.of((Object) new String[]{"Mojito, -water,-mint"}),
	            Arguments.of((Object) new String[]{"Mojito, -water,-soda,-mint"})
	    );
	}
	
	static Stream<Arguments> inputBeveragesWithExceptedCost() {
	    return Stream.of(
	    		Arguments.of((Object) new String[]{"Chai"},4.0d),
	    		Arguments.of((Object) new String[]{"Chai, -water"},3.5),
	            Arguments.of((Object) new String[]{"Chai, -water,-milk"},2.5),
	           
	            Arguments.of((Object) new String[]{"Coffee"},5.0d),
	            Arguments.of((Object) new String[]{"Coffee, -water"},4.5),
	            Arguments.of((Object) new String[]{"Coffee, -water,-milk"},3.5),
	           
	            Arguments.of((Object) new String[]{"Banana Smoothie"},6.0d),
	            Arguments.of((Object) new String[]{"Banana Smoothie, -water"},5.5),
	            Arguments.of((Object) new String[]{"Banana Smoothie, -water,-milk"},4.5),
	            
	            Arguments.of((Object) new String[]{"Strawberry Shake"},7.0d),
	            Arguments.of((Object) new String[]{"Strawberry Shake, -water"},6.5),
	            Arguments.of((Object) new String[]{"Strawberry Shake, -water,-milk"},5.5),
	            
	            Arguments.of((Object) new String[]{"Mojito"},7.5),
	            Arguments.of((Object) new String[]{"Mojito, -water"},7.0d),
	            Arguments.of((Object) new String[]{"Mojito, -water,-mint"},6.5),
	            Arguments.of((Object) new String[]{"Mojito, -water,-soda,-mint"},6.0d)
	    );
	}
	
	static Stream<Arguments> inputBeveragesWithIncorrectExceptedCost() {
	    return Stream.of(
	    		Arguments.of((Object) new String[]{"Chai"},45.0d),
	    		Arguments.of((Object) new String[]{"Chai, -water"},3.135),
	            Arguments.of((Object) new String[]{"Chai, -water,-milk"},21.53),
	           
	            Arguments.of((Object) new String[]{"Coffee"},5.1d),
	            Arguments.of((Object) new String[]{"Coffee, -water"},4.8),
	            Arguments.of((Object) new String[]{"Coffee, -water,-milk"},31.5),
	           
	            Arguments.of((Object) new String[]{"Banana Smoothie"},6.5d),
	            Arguments.of((Object) new String[]{"Banana Smoothie, -water"},5.3),
	            Arguments.of((Object) new String[]{"Banana Smoothie, -water,-milk"},4.49),
	            
	            Arguments.of((Object) new String[]{"Strawberry Shake"},7.1d),
	            Arguments.of((Object) new String[]{"Strawberry Shake, -water"},6.3),
	            Arguments.of((Object) new String[]{"Strawberry Shake, -water,-milk"},5.55),
	            
	            Arguments.of((Object) new String[]{"Mojito"},7.2),
	            Arguments.of((Object) new String[]{"Mojito, -water"},7.9d),
	            Arguments.of((Object) new String[]{"Mojito, -water,-mint"},6.3),
	            Arguments.of((Object) new String[]{"Mojito, -water,-soda,-mint"},6.11d)
	    );
	}
	
	
}
