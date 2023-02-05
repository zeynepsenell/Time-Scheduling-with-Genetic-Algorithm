public class TimeScheduleWithGA {
	private static GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(150, 0.9, 0.5);
	
	/**
	 * Main function of the program. Genetic Algorithm will be work in this function. 
	 * @param args
	 */
	public static void main(String[] args) {
        Planner planner = fillTableWithData();
        Population population = geneticAlgorithm.initalizePopulation(planner);
        geneticAlgorithm.fitnessOfPopulation(population, planner);
        
        int generation = 1;
        
        while (population.getBestChoice(0).getFitness() != 1.0) {
            System.out.println("Generation number " + generation + 
            		" with fitness value: " + population.getBestChoice(0).getFitness());

            population = geneticAlgorithm.crossoverPopulation(population);
            population = geneticAlgorithm.mutatePopulation(population, planner);
            geneticAlgorithm.fitnessOfPopulation(population, planner);
            generation++;
        }

        planner.createClasses(population.getBestChoice(0));
            
        System.out.println("Time-Schedule solution found in generation " + generation + "!");

        Class classes[] = planner.getClasses();
        
        TimeTableDrawing table = new TimeTableDrawing();
        for (Class bestClass : classes) {
        	if(planner.getTimeslot(bestClass.getTimeslotId()).getTime().contains("Mon")) {
        		drawTableWithResults(planner.getTimeslot(bestClass.getTimeslotId()).getTime(),
        				1,
        				table,
        				"<html> Veranstaltung: " + planner.getcourse(bestClass.getCourseId()).getCourseName() + "<br> Zimmer: "
        				+ planner.getRoom(bestClass.getRoomId()).getRoomNumber() + "<br> Professor: "
        				+ planner.getProfessor(bestClass.getProfessorId()).getProfessorName()
        				);
        	}
        	else if(planner.getTimeslot(bestClass.getTimeslotId()).getTime().contains("Tue")) {
        		drawTableWithResults(planner.getTimeslot(bestClass.getTimeslotId()).getTime(),
        				2,
        				table,
        				"<html> Veranstaltung: " + planner.getcourse(bestClass.getCourseId()).getCourseName()  + "<br> Zimmer: "
        				+ planner.getRoom(bestClass.getRoomId()).getRoomNumber() + "<br> Professor: "
        				+ planner.getProfessor(bestClass.getProfessorId()).getProfessorName() 
        				);
        	}
        	else if(planner.getTimeslot(bestClass.getTimeslotId()).getTime().contains("Wed")) {
        		drawTableWithResults(planner.getTimeslot(bestClass.getTimeslotId()).getTime(),
        				3,
        				table,
        				"<html> Veranstaltung: " + planner.getcourse(bestClass.getCourseId()).getCourseName()  + "<br> Zimmer: "
        				+ planner.getRoom(bestClass.getRoomId()).getRoomNumber() + "<br> Professor: "
        				+ planner.getProfessor(bestClass.getProfessorId()).getProfessorName()
        				);
        	}
        	else if(planner.getTimeslot(bestClass.getTimeslotId()).getTime().contains("Thu")) {
        		drawTableWithResults(planner.getTimeslot(bestClass.getTimeslotId()).getTime(),
        				4,
        				table,
        				"<html> Veranstaltung: " + planner.getcourse(bestClass.getCourseId()).getCourseName()  + "<br> Zimmer: "
        				+ planner.getRoom(bestClass.getRoomId()).getRoomNumber() + "<br> Professor: "
        				+ planner.getProfessor(bestClass.getProfessorId()).getProfessorName() 
        				);
        	}
        	else {
        		drawTableWithResults(planner.getTimeslot(bestClass.getTimeslotId()).getTime(),
        				5,
        				table,
        				"<html> Veranstaltung: " + planner.getcourse(bestClass.getCourseId()).getCourseName()  + "<br> Zimmer: "
        					+ planner.getRoom(bestClass.getRoomId()).getRoomNumber() + "<br> Professor: "
                			+ planner.getProfessor(bestClass.getProfessorId()).getProfessorName()
                		);
        	}
        }
        table.setVisible(true);
    }
	
	/**
	 * For drawing table with data.
	 * @param timeSlot time slots of classes
	 * @param day class days
	 * @param table	current table
	 * @param value value containing class, professor and room name
	 */
    private static void drawTableWithResults(String timeSlot, int day, TimeTableDrawing table, String value) {
    	if(timeSlot.contains("9:00 - 11:00")) {
    		String oldValue;
    		if(table.getModel().getValueAt(0, day) != null) {
    			oldValue = table.getModel().getValueAt(0, day).toString() + 
    					"<br>" + "---------------" + "<br>" + value;
    			table.getModel().setValueAt(oldValue, 0,day);
    		}
    		else {
    			table.getModel().setValueAt(value, 0, day);
    		}
		}
		else if(timeSlot.contains("11:00 - 13:00")) {
			String oldValue;
    		if(table.getModel().getValueAt(1, day) != null) {
    			oldValue = table.getModel().getValueAt(1, day).toString() + 
    					 "<br>" + "---------------" + "<br>" + value ;
    			table.getModel().setValueAt(oldValue, 1,day);
    		}
    		else {
    			table.getModel().setValueAt(value, 1, day);
    		}
		}
		else if(timeSlot.contains("13:00 - 15:00")) {
			String oldValue;
    		if(table.getModel().getValueAt(2, day) != null) {
    			oldValue = table.getModel().getValueAt(2, day).toString() + 
    					 "<br>" + "---------------" + "<br>" + value;
    			table.getModel().setValueAt(oldValue, 2,day);
    		}
    		else {
    			table.getModel().setValueAt(value, 2, day);
    		}
		}
		else if(timeSlot.contains("15:00 - 17:00")) {
			String oldValue;
    		if(table.getModel().getValueAt(3, day) != null) {
    			oldValue = table.getModel().getValueAt(3, day).toString() + 
    					"<br>" + "---------------" + "<br>" + value ;
    			table.getModel().setValueAt(oldValue, 3,day);
    		}
    		else {
    			table.getModel().setValueAt(value, 3, day);
    		}
		}
    }
    
    /**
     * With this function data will be filled manually. 
     * Class names, time slots and names of professors can be changed in this function.
     * @return planner with data.
     */
	private static Planner fillTableWithData() {
		Planner planner = new Planner();

		planner.addRoom(1, "MD A-ED-1-2");
		planner.addRoom(2, "MD B-ED-2-15");
		planner.addRoom(3, "MD B-ED-3-8");
		
		String[] days = {"Mon" , "Tue", "Wed", "Thu","Fri"};
		String[] hours = {"9:00 - 11:00",
				"11:00 - 13:00",
				"13:00 - 15:00",
				"15:00 - 17:00"};
		int timeSlotId = 0;
		
		for(int i= 0 ; i <days.length ;i++) {
			for(int j= 0 ; j<hours.length ; j++) {
				planner.addTimeslot(timeSlotId, days[i] + " " + hours[j]);
				timeSlotId++;
			}
		}
		
		planner.addProfessor(1, "Dr. I.G.");
		planner.addProfessor(2, "Dr. V.G. (FF)");
		planner.addProfessor(3, "F.A.");
		planner.addProfessor(5, "C.L.");
		planner.addProfessor(6, "Dr. E.M.L");
		planner.addProfessor(7, "Rektorat");
		planner.addProfessor(8, "Dipl.-Ing. O.K.");
		planner.addProfessor(9, "E.I.");
		planner.addProfessor(10, "S.N.");
		planner.addProfessor(11, "Doc. Dr. H.A.");
		planner.addProfessor(13, "YDYO");
		planner.addProfessor(14, "Dekanat");
		planner.addProfessor(15, "Dr. S.I.A.");
		
		planner.addCourse("MAT103", "Analysis I", new int[] {1});
		planner.addCourse("INF101", "Einfuhrung in die Informatik und Programmierung", new int[] { 2 });
		planner.addCourse("INF103", "Logik für Informatiker", new int[] {3});
		planner.addCourse("INF107", "Rechnerorganisation", new int[] {3});
		planner.addCourse("DEU121", "Technisches Deutsch I", new int[] { 13 });
		planner.addCourse("ENG101", "Englisch I", new int[] {13});
		planner.addCourse("TUR001", "Turkisch I", new int[] {13});
		
		planner.addCourse("INF201", "Diskrete Strukturen", new int[] {5});
		planner.addCourse("INF203", "Algorithmen und Datenstrukturen I", new int[] {6});
		planner.addCourse("INF205", "Datenbanksysteme", new int[] {5});
		planner.addCourse("INF209", "Computernetze", new int[] {3});
		planner.addCourse("ENG201", "Englisch III", new int[] {13});
		planner.addCourse("AIT001", "Ataturks Grundsaetze und Revolutionsgeschichte I", new int[] {7});
		
		planner.addCourse("INF303", "Software Engineering Projekt", new int[] {8});
		planner.addCourse("ETE101", "Digitaldesign", new int[] {10});
		planner.addCourse("MAT201", "Differentialgleichungen", new int[] {11});
		planner.addCourse("USD001", "Wahlbereich Studium Generale I", new int[] {7});
		planner.addCourse("ISG001", "Arbeitsgesundheit und -sicherheit I", new int[] {14});
		planner.addCourse("ENG301", "Fortgeschrittenes Englisch I", new int[] {13});
		
		planner.addCourse("INF499", "Fachpraktikum", new int[] {9});
		planner.addCourse("INF401", "Wissenschaftliche Studienmethoden", new int[] {5, 9,6});
		planner.addCourse("INF492", "Bachelorarbeit", new int[] {5, 9,6});
		planner.addCourse("INF701", "Maschinelles Lernen", new int[] {5});
		planner.addCourse("INF506", "Statistische Methoden der Datenanalyse", new int[] {9});
		planner.addCourse("INF714", "Fortgeschrittene Themen der Mathematik für Informatiker", new int[] {9});
		planner.addCourse("INF517", "Medizinische Bildverarbeitung", new int[] {15});

		planner.addSemester(1,  new String[] { "MAT103", "INF101","INF103","INF107","DEU121","ENG101","TUR001"});
		planner.addSemester(2,  new String[] { "INF201","INF203","INF205","INF209","ENG201","AIT001"});
		planner.addSemester(3,  new String[] { "INF303","ETE101","MAT201","USD001","ISG001","ENG301" });
		planner.addSemester(4,  new String[] { "INF499","INF401","INF492","INF701","INF506","INF714","INF517"});

		return planner;
	}
}