import java.util.Arrays;
import java.util.Comparator;

/**
 * This class contains everything about Genetic Algorithm. It contains information about population, single person and 
 * algorithm itself. 
 * @author zsenel
 *
 */
public class GeneticAlgorithm {

	private int populationSize;
	private double mutationRate;
	private double crossoverRate;

	public GeneticAlgorithm(int populationSize, double mutationRate, double crossoverRate) {
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
		this.crossoverRate = crossoverRate;
	}

	public Population initalizePopulation(Planner timetable) {
		Population population = new Population(this.populationSize, timetable);
		return population;
	}
	
	/**
	 * Fitness value measurement to understand the goodness of the algorithm
	 * @param singleClass
	 * @param timetable
	 * @return fitness value
	 */
	public double calculateFitness(SingleClass singleClass, Planner timetable) {
		timetable.createClasses(singleClass);

		int conflicts = timetable.calculateConflicts();
		double fitness = 1 / (double) (conflicts + 1);

		singleClass.setFitness(fitness);
		return fitness;
	}

	/**
	 * For calculating fitness of a population
	 * @param population
	 * @param timetable
	 */
	public void fitnessOfPopulation(Population population, Planner timetable) {
		double populationFitness = 0;		
		for (SingleClass singleClass : population.getClasses()) {
			populationFitness += this.calculateFitness(singleClass, timetable);
		}
		population.setPopulationFitness(populationFitness);
	}

	/**
	 * Selecting parents randomly and then getting the better parent.
	 * @param population
	 * @return
	 */
	public SingleClass selectParent(Population population) {
		Population parents = new Population(2);
		SingleClass parent0 = population.getSingleClass((int)Math.random());
		parents.setSingleClass(0, parent0);
		SingleClass parent1 = population.getSingleClass((int)Math.random());
		parents.setSingleClass(1, parent1);
		
		return parents.getBestChoice(0);
	}

	/**
	 * For mutating the population according the mutation rate.
	 * @param population
	 * @param timetable
	 * @return
	 */
	public Population mutatePopulation(Population population, Planner timetable) {
		Population newPopulation = new Population(this.populationSize);
		for (int populationIndex = 0; populationIndex < population.size(); populationIndex++) {
			SingleClass singleClass = population.getBestChoice(populationIndex);
			SingleClass randomsingleClass = new SingleClass(timetable);
			for (int featureIndex = 0; featureIndex < singleClass.getNumberOfFeatures(); featureIndex++) {
				if (this.mutationRate > Math.random()) {
					singleClass.setFeature(featureIndex, randomsingleClass.getFeature(featureIndex));
				}
			}
			newPopulation.setSingleClass(populationIndex, singleClass);
		}
		return newPopulation;
	}

	/**
	 * Crossover operator
	 * @param population
	 * @return
	 */
	public Population crossoverPopulation(Population population) {
		Population newPopulation = new Population(population.size());
		for (int index = 0; index < population.size(); index++) {
			SingleClass parent1 = population.getBestChoice(index);
			if (this.crossoverRate > Math.random()) {
				SingleClass child1 = new SingleClass(parent1.getNumberOfFeatures());
				SingleClass parent2 = selectParent(population);
				for (int feature = 0; feature < parent1.getNumberOfFeatures(); feature++) {
					if (0.3 < Math.random()) {
						child1.setFeature(feature, parent1.getFeature(feature));
					} else {
						child1.setFeature(feature, parent2.getFeature(feature));
					}
				}
				newPopulation.setSingleClass(index, child1);
			} else {
				newPopulation.setSingleClass(index, parent1);
			}
		}

		return newPopulation;
	}
}

/*
 * This class represents one person in the population.
 * For this problem, it is a class with professor, room and etc...
 */
class SingleClass {
	
	private int[] features;
	private double fitness;

	public SingleClass(Planner planner) {
		this.createClass(planner);
	}

	public SingleClass(int numberOfFeatures) {
		int[] singleClass = new int[numberOfFeatures];
		for (int index = 0; index < numberOfFeatures; index++) {
			singleClass[index] = index;
		}
		this.features = singleClass;
	}
	
	private void createClass(Planner planner) {
		int newFeatures[] = new int[planner.getcourses().size() * 3];
		int featureIndex = 0;

		for (Semester semester : planner.getSemestersArray()) {
			for (String courseId : semester.getCourseIds()) {
				int timeslotId = planner.getRandomTimeslot().getTimeId();
				newFeatures[featureIndex] = timeslotId;
				featureIndex++;

				int roomId = planner.getRandomRoom().getRoomId();
				newFeatures[featureIndex] = roomId;
				featureIndex++;

				Course course = planner.getcourse(courseId);
				newFeatures[featureIndex] = course.selectProfessorId();
				featureIndex++;
			}
		}

		this.features = newFeatures;
	}
	
	public int[] getFeatures() {
		return this.features;
	}

	public int getNumberOfFeatures() {
		return this.features.length;
	}

	public void setFeature(int offset, int feature) {
		this.features[offset] = feature;
	}

	public int getFeature(int offset) {
		return this.features[offset];
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public double getFitness() {
		return this.fitness;
	}

}

class Population {
	private SingleClass population[]; 
	private double populationFitness = -1;

	public Population(int populationSize) {
		this.population = new SingleClass[populationSize];
	}
	
	public Population(int populationSize, Planner planner) {
		this.population = new SingleClass[populationSize];
		for (int singleClassCount = 0; singleClassCount < populationSize; singleClassCount++) {
			SingleClass singleClass = new SingleClass(planner);
			this.population[singleClassCount] = singleClass;
		}
	}

	public SingleClass getBestChoice(int index) {
		Arrays.sort(this.population, new Comparator<SingleClass>() {
			@Override
			public int compare(SingleClass class1, SingleClass class2) {
				if (class1.getFitness() < class2.getFitness()) {
					return 1;
				} else if (class1.getFitness() > class2.getFitness()) {
					return -1;
				}
				return 0;
			}
		});

		return this.population[index];
	}

	public void setPopulationFitness(double fitness) {
		this.populationFitness = fitness;
	}

	public double getPopulationFitness() {
		return this.populationFitness;
	}

	public int size() {
		return this.population.length;
	}
	
	public SingleClass[] getClasses() {
		return this.population;
	}

	public SingleClass setSingleClass(int offset, SingleClass singleClass) {
		return population[offset] = singleClass;
	}

	public SingleClass getSingleClass(int offset) {
		return population[offset];
	}

}
