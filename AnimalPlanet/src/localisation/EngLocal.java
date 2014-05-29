package localisation;

public interface EngLocal{

	String EXTEPTION_INCORRECT_NUTRITIONAL_VALUE 		= "NutritionalValue must be equal [0, 1]";
	String EXTEPTION_INCORRECT_WEIGHT 					= "Weight must be > 0";
	String EXTEPTION_NOT_SUITABLE_FOOD_FOR_HERBIVOROU 	= "Herbivorou eats only plants";
	String EXTEPTION_NOT_SUITABLE_FOOD_FOR_PREDATOR 	= "Predator eats only animals";
	String EXTEPTION_PREDATOR_NOT_EAT_DEAD_ANIMALS	 	= "Predator do not eat dead animals";
	String EXTEPTION_THIS_ANIMAL_HAS_DIED				= "This animal has died";
	
	String DEFAULT_MESSAGE_ANIMAL_ALREADY_DIED 			= "What Is Dead May Never Die";
	String DEFAULT_MESSAGE_INCORRECT_WEIGHT 			= "Invalid arguments";
	String DEFAULT_MESSAGE_NOT_SUITABLE_FOOD 			= "Animal does not eat such food";
	
	String TITLE_USER_INTERFACE							= "Animal Planet";
	String SEASON_USER_INTERFACE						= "Season: ";
	String SEPARETOR_USER_INTERFACE						= "____________________________________________________";
	String FILE_IS_NOT_FOUND							= "File is not found ";
	String INPUT_USER_INTERFACE							= "Input: ";
	String END_TEST										= "End!";
	
	String PROPERTIES_INITIALIZATION					= "Init";
	
	String DEFAULT_INITIALIZATION						= "default";
	String EMPTY_INITIALIZATION							= "empty";
	String INITIALIZATION_FROM_FILE						= "file";
	
	String PROPERTIES_INITIALIZATION_FILE				= "InitFile";
}
