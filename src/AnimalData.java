
public class AnimalData {
	private AnimalType type;
	private AnimalKind kind;
	private AnimalMorphologicalSign morphologicalSign;
	private boolean isHunter;
	
	public AnimalData(AnimalType type, AnimalKind kind, AnimalMorphologicalSign area, boolean isHunter){
		this.type = type;
		this.kind = kind;
		this.morphologicalSign = area;
		this.isHunter = isHunter;
	}
	
	public AnimalType getType(){
		return this.type;
	}
	
	public AnimalKind getKind(){
		return this.kind;
	}
	
	public AnimalMorphologicalSign getSign(){
		return this.morphologicalSign;
	}
	
	public boolean getIsPredator(){
		return this.isHunter;
	}
}
