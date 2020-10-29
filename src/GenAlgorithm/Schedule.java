package GenAlgorithm;

import entities.*;

import java.util.Random;

public class Schedule {

    //number of "generations".
    public static final int MAX_ITERATIONS = 200;

    //number of individuals in population
    public static final int POPULATION_COUNT = 200;

    //number of individuals participating in tournament selection
    public static final int TOURNAMENT_PARTICIPANTS_COUNT = 5;

    //likelihood (in percent) of the mutation
    public static final double MUTATION_LIKELIHOOD= .5;

    public static int GENES_COUNT;

    private Chromosome[] population;

    public Chromosome[] getPopulation() {
        return population;
    }

    public void setPopulation(Chromosome[] population) {
        this.population = population;
    }

//    public static final String TIMES[] = new String[]{
//            "8:30",
//            "10:00",
//            "11:40",
//            "13:30",
//            "15:00",
//            "16:30",
//            "18:00"
//    };
//
//    public static final String WEEKDAYS[] = new String[]{
//            "Mon",
//            "Tue",
//            "Wed",
//            "Thu",
//            "Fri"
//    };
//
//    public static final ArrayList<Student> STUDENTS = new ArrayList<Student>(){{
//            add(new Student("Artem 1"));
//            add(new Student("Artem 2"));
//            add(new Student("Artem 3"));
//            add(new Student("Artem 4"));
//            add(new Student("Artem 5"));
//            add(new Student("Artem 6"));
//            add(new Student("Artem 7"));
//            add(new Student("Artem 8"));
//            add(new Student("Artem 9"));
//            add(new Student("Artem 10"));
//            add(new Student("Artem 11"));
//            add(new Student("Artem 12"));
//            add(new Student("Artem 13"));
//            add(new Student("Artem 14"));
//            add(new Student("Artem 15"));
//            add(new Student("Artem 16"));
//            add(new Student("Artem 17"));
//            add(new Student("Artem 18"));
//            add(new Student("Artem 19"));
//            add(new Student("Artem 20"));
//            add(new Student("Artem 21"));
//            add(new Student("Artem 22"));
//            add(new Student("Artem 23"));
//            add(new Student("Artem 24"));
//            add(new Student("Artem 25"));
//            add(new Student("Artem 26"));
//            add(new Student("Artem 27"));
//            add(new Student("Artem 28"));
//            add(new Student("Artem 29"));
//            add(new Student("Artem 30"));
//    }};
//
//    public static final ArrayList<Teacher> TEACHERS = new ArrayList<Teacher>(){{
//            add(new Teacher("Lidochka Tchaikovsky"));
//            add(new Teacher("Ruslan Sokolov"));
//            add(new Teacher("Vladislava Tkachenko"));
//            add(new Teacher("Rozaliya Shevchuk"));
//            add(new Teacher("Manya Morozov"));
//            add(new Teacher("Stanislava Mikhailov"));
//            add(new Teacher("Gala Antonov"));
////            add(new Teacher("Lyov Kovalev"));
////            add(new Teacher("Eva Tkachuk"));
////            add(new Teacher("Oleksandra Lebedev"));
////            add(new Teacher("Alina Kovalev"));
////            add(new Teacher("Inessa Novikov"));
////            add(new Teacher("Denis Utkin"));
////            add(new Teacher("Luba Pavlov"));
////            add(new Teacher("Apollinariya Ignatiev"));
////            add(new Teacher("Oleksandr Pasternak"));
////            add(new Teacher("Serafima Stasiuk"));
////            add(new Teacher("Oleh Havrylyuk"));
////            add(new Teacher("Makari Vasilev"));
////            add(new Teacher("Elena Andreev"));
//    }};
//
//    public static final Subject SUBJECTS[] = new Subject[]{
//        new Subject("Subject 1", TEACHERS.get(0), 2, TEACHERS.subList(1,2).toArray(new Teacher[]{}) , STUDENTS.subList(0,10).toArray(new Student[]{})),
//        new Subject("Subject 2", TEACHERS.get(3), 3, TEACHERS.subList(2,4).toArray(new Teacher[]{}) , STUDENTS.subList(5,15).toArray(new Student[]{})),
//        new Subject("Subject 3", TEACHERS.get(4), 4, TEACHERS.subList(3,6).toArray(new Teacher[]{}) , STUDENTS.subList(10,15).toArray(new Student[]{})),
//        new Subject("Subject 4", TEACHERS.get(0), 3, TEACHERS.subList(0,2).toArray(new Teacher[]{}) , STUDENTS.subList(12,20).toArray(new Student[]{})),
//        new Subject("Subject 5", TEACHERS.get(0), 3, TEACHERS.subList(1,3).toArray(new Teacher[]{}) , STUDENTS.subList(0,20).toArray(new Student[]{})),
//    };
//
//    public static final Classroom CLASSROOMS[] = new Classroom[]{
//            new Classroom(4,1),
//            new Classroom(8,2),
//            new Classroom(15,3),
//            new Classroom(20,4),
//            new Classroom(20,5),
//            new Classroom(7,6),
//            new Classroom(6,7),
//            new Classroom(9,8),
//            new Classroom(3,9),
//            new Classroom(6,10),
//            new Classroom(8,11),
//            new Classroom(9,12)
//    };

    public Schedule() {
        for (Subject subject : Constants.SUBJECTS)
            GENES_COUNT+= subject.getPracticesAmount()+1;


    }

    /*
     * Creates an initial population
     * */
    private void createInitialPopulation(){
        population = new Chromosome[POPULATION_COUNT];
        for (int i = 0; i<POPULATION_COUNT;++i){
            population[i]= new Chromosome();
            population[i].fillChromosomeWithRandomGenes();
        }
    }

    /*
     * Iterate through all chromosomes and fill their "fitness" property
     * */
    private void fillChromosomesWithFitnesses() {
        for ( int i=0; i<POPULATION_COUNT;++i ) {
            population[i].calculateFitness();
        }
    }

    /*
     * Returns random integer number between min and max ( all included :)  )
     * */
    public static int getRandomInt( int min, int max ){
        Random randomGenerator;
        randomGenerator = new Random();
        return  randomGenerator.nextInt( max+1 ) + min ;
    }

    /*
     * Returns random float number between min (included) and max ( NOT included :)  )
     * */
    public static float getRandomFloat( float min, float max ){
        return  (float) (Math.random()*max + min) ;
    }

    public Chromosome findIndividualWithMaxFitness(){
        double currMaxFitness = Double.MAX_VALUE;
        Chromosome result = population[0];
        for (int i = 0; i<POPULATION_COUNT;++i){
            if ( population[i].getFitness() < currMaxFitness ){
                result = population[i];
                currMaxFitness = population[i].getFitness();
            }
        }
        return result;
    }

    /*
     * Performs a tournament between TOURNAMENT_PARTICIPANTS_COUNT individuals,
     * selects the best of them and returns its index in population[] array
     * */
    private int findIndividualForCrossoverByTournament(){

        int bestIndividualNumber=0;
        double bestFitness= Double.MAX_VALUE;

        for ( int i=0;i<TOURNAMENT_PARTICIPANTS_COUNT;++i ){
            int currIndividualNumber = getRandomInt( 0 , POPULATION_COUNT-1);

            if ( population[ currIndividualNumber ].getFitness() < bestFitness    ){
                bestFitness = population[ currIndividualNumber ].getFitness();
                bestIndividualNumber = currIndividualNumber;
            }

        }



        return bestIndividualNumber;


    }

    private Chromosome[] crossoverAndMutation(){
        Chromosome nextGeneration[]=new Chromosome[POPULATION_COUNT];

        //the best individual goes to the next generation in any case.
        //Please note that because of this we start the next loop from 1, not from 0
        nextGeneration[0] = findIndividualWithMaxFitness();

        for (int i = 1; i<POPULATION_COUNT;++i) {
            int firstChromosome =  findIndividualForCrossoverByTournament()   ;

            int secondChromosome;
            do{
                secondChromosome =  findIndividualForCrossoverByTournament()   ;

            }while (firstChromosome==secondChromosome) ;  //prevent individual's crossover with itself :)

            Chromosome firstParent = population[firstChromosome];
            Chromosome secondParent = population[secondChromosome];

            nextGeneration[i]=firstParent.singleCrossover( secondParent );
            nextGeneration[i].mutateWithGivenLikelihood();
        }


        return nextGeneration;
    }

    public static void main(String[] args ) {
        Schedule schedule = new Schedule();
        schedule.createInitialPopulation();

        long iterationsNumber = 0;

        do {
            schedule.fillChromosomesWithFitnesses();

            schedule.setPopulation(schedule.crossoverAndMutation());

        } while ( iterationsNumber++<MAX_ITERATIONS && schedule.getPopulation()[0].getFitness() != 0);

        System.out.println("Iterations: "+ iterationsNumber +"\n" +
                "Final fitness : " + schedule.getPopulation()[0].getFitness() + "\n"
                + "Schedule: " +schedule.getPopulation()[0].toString());
    }
}
