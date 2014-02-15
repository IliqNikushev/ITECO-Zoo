import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Zoo {
	private List<Animal> animals = new ArrayList<Animal>();
	
	private File baseFile;
	public File getBaseFile(){
		return this.baseFile;
	}
	
	public Zoo(File file){
		this.baseFile = file;
		
		String[] lines = Utils.getFileLines(file);
		for (String line : lines) {
			try{
				Animal a = Animal.Parse(line);				
				this.animals.add(a);
			}
			catch(Exception ex){
				Console.LogException(ex);
			}
		}
	}
	
	private List<Animal> getAnimalsWhere(Function<Animal> predicate)
	{
		List<Animal> a = new ArrayList<Animal>();
		for (Animal animal : this.animals) {
			if(predicate.isMetFor(animal))
				a.add(animal);
		}
		return a;
	}
	
	public void solve()
	{
		if(this.animals.size() == 0){
			Console.Log("No animals found.");
			return;
		}
		
		/* A slightly more OOP approach
		List<Task> tasks = new ArrayList<Task>();
		
		Task t = new Task(){
			@Override
			public void solve() {
				Console.Log(this.id);
				int predators = 0;
				for (Animal animal : animals) {
					if(animal.getIsHunter())
						predators += 1;
				}
				
				int prey = animals.size() - predators;
				
				if(predators > prey)
					Console.Log("Predators outnumber the others.");
				else if(predators == prey)
					Console.Log("Predators are half of the population in the zoo.");
				else
					Console.Log("Predators are not more than the other animals.");
			}
		}
		tasks.add(t);
		
		for (Task task : tasks) {
			task.solve();
		}
		*/
		
		printTaskNumber(1);
		Task1();
		
		printTaskNumber(2);
		Task2();
		
		printTaskNumber(3);
		Task3();
		
		printTaskNumber(4);
		Task4();
		
		printTaskNumber(5);
		Task5();
		
		printTaskNumber(6);
		Task6();
		
		printTaskNumber(7);
		Task7();
	}
	
	private void printTaskNumber(int i){
		Console.Log("\nTask [" + i + "]:");
	}
	
	private void Task1(){ // Predators are more than the pray
		int predators = 0;
		for (Animal animal : this.animals) {
			if(animal.getIsPredator())
				predators += 1;
		}
		
		int prey = this.animals.size() - predators;
		
		if(predators > prey)
			Console.Log("Predators outnumber the others.");
		else if(predators == prey)
			Console.Log("Predators are half of the population in the zoo.");
		else
			Console.Log("Predators are not more than the other animals.");
	}
	
	private void Task2(){ //Oldest : 
		int oldestAge = Integer.MIN_VALUE;
		List<String> oldestAnimals = new ArrayList<String>(); 
		//Better way : Find the biggest age and get all animals with that age
		for(Animal animal : this.animals){
			int age = animal.getMonthsOld();
			if(oldestAge < age)
			{
				oldestAnimals.clear();
				oldestAge = age;
			}
			if(oldestAge == age){
				oldestAnimals.add(animal.getName());
			}
		}
		
		Console.Log("Oldest age:" + oldestAge);
		for (String name : oldestAnimals) {
			Console.Log(name);
		}
	}
	
	private void Task3(){ //Youngest : 
		int youngestAge = Integer.MAX_VALUE;
		List<String> youngestAnimals = new ArrayList<String>(); 
		//Better way : Find the smallest age and get all animals with that age
		for(Animal animal : this.animals){
			int age = animal.getMonthsOld();
			if(youngestAge > age)
			{
				youngestAnimals.clear();
				youngestAge = age;
			}
			if(youngestAge == age){
				youngestAnimals.add(animal.getName());
			}
		}
		
		Console.Log("Youngest age:" + youngestAge);
		for (String name : youngestAnimals) {
			Console.Log(name);
		}
	}
	
	private void Task4(){ //Kind of animal with biggest sum in years : 
		Object[] possibleKinds = AnimalKind.class.getEnumConstants();
		int[] sum = new int[possibleKinds.length];
		for (int i = 0; i < sum.length; i++) {
			sum[i] = 0;
		}
		for (Animal animal : this.animals) {
			AnimalKind animalKind = animal.getKind();
			for (int i = 0; i < sum.length; i++) {
				if(possibleKinds[i] == animalKind)
					sum[i] += animal.getMonthsOld();
			}
		}
		
		int max = Integer.MIN_VALUE;
		int maxID = -1;
		for (int i = 0; i < sum.length; i++) {
			if(max < sum[i])
			{
				maxID = i;
				max = sum[i];
			}
		}
		
		Console.Log("The biggest sum of age by kind of animal belongs to the " + possibleKinds[maxID] + " group, with " + max);
	}
	
	private void Task5(){ //Animal group by Morphological Sign lowest age sum : 
		Object[] possibleSigns = AnimalMorphologicalSign.class.getEnumConstants();
		int[] sum = new int[possibleSigns.length];
		for (int i = 0; i < sum.length; i++) {
			sum[i] = 0;
		}
		for (Animal animal : this.animals) {
			AnimalMorphologicalSign animalSign = animal.getSign();
			for (int i = 0; i < sum.length; i++) {
				if(possibleSigns[i] == animalSign)
					sum[i] += animal.getMonthsOld();
			}
		}
		
		int min = Integer.MAX_VALUE;
		int minID = -1;
		for (int i = 0; i < sum.length; i++) {
			if(sum[i] == 0) continue;
			if(min > sum[i])
			{
				minID = i;
				min = sum[i];
			}
		}
		
		Console.Log("The lowest sum of age by the animal's morphological sign belongs to the " + possibleSigns[minID] + " group, with " + sum[minID]);
	}
	
	private void Task6(){ //Animal group by Morphological sign highest population : 
		Map<AnimalMorphologicalSign, List<Animal>> groups = new HashMap<AnimalMorphologicalSign, List<Animal>>();
		
		/* Old version, without the function
		for (Animal animal : this.animals) {
			AnimalMorphologicalSign sign = animal.getSign();
			if(!groups.containsKey(sign)){
				groups.put(sign, new ArrayList<Animal>());
			}
			groups.get(sign).add(animal);
		}
		*/
		
		// Function intended use : this.animals.Where(x => x.getSign() == sign) //( C# Function )
		for (final AnimalMorphologicalSign sign : AnimalMorphologicalSign.class.getEnumConstants()) {
			groups.put(sign, getAnimalsWhere(new Function<Animal>(){
						@Override
						public boolean isMetFor(Animal obj) {
							return obj.getSign() == sign;
						}
					}
					));
		}
		
		List<Animal> biggestGroup = getBiggest(groups);
		
		if(biggestGroup.size() > 0)
			Console.Log("The group of animals by morphological sign, with highest population is the " + biggestGroup.get(0).getSign() + " group, with " + biggestGroup.size());
		else
			Console.Log("Biggest group is none, no animals in the zoo!");
	}
	
	private void Task7(){ //Smallest population of kind of animal. What are their names?		
		Map<AnimalKind, List<Animal>> groups = new HashMap<AnimalKind, List<Animal>>();
		
		/* Old version, without the function
		for (Animal animal : this.animals) {
			AnimalKind animalKind = animal.getKind();
			if(!groups.containsKey(animalKind)){
				groups.put(animalKind, new ArrayList<Animal>());
			}
			groups.get(animalKind).add(animal);
		}
		*/
		
		// Function intended use : this.animals.Where(x => x.getKind() == kind) //( C# Function )
		for (final AnimalKind kind : AnimalKind.class.getEnumConstants()) {
			groups.put(kind, getAnimalsWhere(new Function<Animal>(){
						@Override
						public boolean isMetFor(Animal obj) {
							return obj.getKind() == kind;
						}
					}
					));
		}
		
		List<Animal> group = getSmallest(groups);
		if(group.size() > 0){
			Console.Log("The group of animals by kind, with smallest population is the " + group.get(0).getKind() + " group, with " + group.size());
			Console.Log("Their names are:");
			for (int i = 0; i < group.size(); i++) {
				Console.Log(group.get(i).getName());
			}
		}
		else{
			Console.Log("Biggest group consists of 0");
		}
	}
	
	private <K, T> List<T> getBiggest(Map<K, List<T>> groups)
	{
		int max = Integer.MIN_VALUE;
		K maxID = null;
		
		for (K key : groups.keySet()) {
			int size = groups.get(key).size();
			if(max < size)
			{
				maxID = key;
				max = size;
			}
		}
		
		return groups.get(maxID);
	}
	
	private <K, T> List<T> getSmallest(Map<K, List<T>> groups)
	{
		int min = Integer.MAX_VALUE;
		K minID = null;
		
		for (K key : groups.keySet()) {
			int size = groups.get(key).size();
			if(min > size)
			{
				minID = key;
				min = size;
			}
		}
		
		return groups.get(minID);
	}
}
