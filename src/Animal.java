import java.util.HashMap;
import java.util.Map;

public class Animal {
	private String name;
	private AnimalData data;
	private int monthsOld;
	
	public Animal(int id, String name, int monthsOld) throws KeyNotFoundException{
		this(AnimalDatabase.get(id), name, monthsOld);
	}
	
	public Animal(AnimalData base, String name, int monthsOld){
		this(name, base.getType(), base.getKind(), base.getSign(), base.getIsPredator(), monthsOld);
	}
	
	public Animal(String name, AnimalType type, AnimalKind kind, AnimalMorphologicalSign area,
			boolean isPredator, int monthsOld){
		this.name = name;
		this.data = new AnimalData(type, kind, area, isPredator);
		this.monthsOld = monthsOld;
	}
	
	public String getName(){
		return this.name;
	}
	
	public AnimalType getType(){
		return this.data.getType();
	}
	
	public AnimalKind getKind(){
		return this.data.getKind();
	}
	
	public AnimalMorphologicalSign getSign(){
		return this.data.getSign();
	}
	
	public boolean getIsPredator(){
		return this.data.getIsPredator();
	}
	
	public int getYearsOld(){
		return this.monthsOld / MainProgram.MONTHS_IN_A_YEAR;
	}
	
	public int getMonthsOld(){
		return this.monthsOld;
	}
	
	//Parses <id> <name> <age> to an animal
	public static Animal Parse(String line) throws AnimalParseException, KeyNotFoundException{
		String[] args = line.split(" ");
		if(args.length == 3){
			if(Utils.intCanParse(args[0])){
				if(Utils.intCanParse(args[2])){
					int id = Utils.parseInt(args[0]);
					String name = args[1];
					int monthsOld = Utils.parseInt(args[2]);
					
					return new Animal(id, name, monthsOld);
				}
				else{
					throw new AnimalParseException("Unable to parse animal age : " + args[2]);
				}
			}
			else{
				throw new AnimalParseException("Unable to parse animal kind : " + args[0]);
			}
		}
		throw new AnimalParseException("Unable to parse animal : " + line);
	}
}

class AnimalDatabase{
	private static Map<Integer, AnimalData> animals = new HashMap<Integer, AnimalData>(){
		private static final long serialVersionUID = 1L;

	{
		put(1, new AnimalData(AnimalType.Lion, AnimalKind.Mammal, AnimalMorphologicalSign.Land, true));
		put(2, new AnimalData(AnimalType.Dog, AnimalKind.Mammal, AnimalMorphologicalSign.Land, false));
		put(3, new AnimalData(AnimalType.Snake, AnimalKind.Reptile, AnimalMorphologicalSign.MixLandWater, true));
		put(4, new AnimalData(AnimalType.Lizard, AnimalKind.Reptile, AnimalMorphologicalSign.Land, false));
		put(5, new AnimalData(AnimalType.Trout, AnimalKind.Fish, AnimalMorphologicalSign.Water, false));
		put(6, new AnimalData(AnimalType.Shark, AnimalKind.Fish, AnimalMorphologicalSign.Water, true));
		put(7, new AnimalData(AnimalType.Dolphin, AnimalKind.Mammal, AnimalMorphologicalSign.Water, false));
		put(8, new AnimalData(AnimalType.Turtle, AnimalKind.Reptile, AnimalMorphologicalSign.MixLandWater, false));
	}};
	
	public static AnimalData get(int id) throws KeyNotFoundException{
		if(animals.containsKey(id))
			return animals.get(id);
		else
			throw new KeyNotFoundException("KEY " + id + " NOT FOUND in the animal database!");
	}
}
