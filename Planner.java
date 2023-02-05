import java.util.HashMap;
/**
 * Planner for time scheduling.
 * @author zsenel
 *
 */
public class Planner {
	
	private HashMap<String, Course> courses = new HashMap<String, Course>();
	private HashMap<Integer, Semester> semesters  = new HashMap<Integer, Semester>();
	private HashMap<Integer, Room> rooms = new HashMap<Integer, Room>();
	private HashMap<Integer, Professor> professors = new HashMap<Integer, Professor>();
	private HashMap<Integer, Time> timeintervalls= new HashMap<Integer, Time>();
	
	private Class classes[];

	/**
	 * Two classes cannot be in the same room in the same time. This will cause conflicts.
	 * @return number of conflicts
	 */
	public int calculateConflicts() {
		int conflicts = 0;
		for (Class firstClass : this.classes) {
			for (Class secondClass : this.classes) {
				if (firstClass.getRoomId() == secondClass.getRoomId() && 
						firstClass.getTimeslotId() == secondClass.getTimeslotId() && 
						firstClass.getClassId() != secondClass.getClassId()) {
					conflicts++;
					break;
				}
				if (firstClass.getProfessorId() == secondClass.getProfessorId() && 
						firstClass.getTimeslotId() == secondClass.getTimeslotId()
						&& firstClass.getClassId() != secondClass.getClassId()) {
					conflicts++;
					break;
				}
			}
		}
		return conflicts;
	}
	
	/**
	 * For creating classes.
	 * @param singleClass
	 */
	public void createClasses(SingleClass singleClass) {
		Class classes[] = new Class[this.courses.size()];
		int features[] = singleClass.getFeatures();
		int index = 0;
		int classNumber = 0;
		for (Semester semester : this.getSemestersArray()) {
			String courseIds[] = semester.getCourseIds();
			for (String courseId : courseIds) {
				classes[classNumber] = new Class(classNumber, semester.getSemesterId(), courseId);
				classes[classNumber].addTimeslot(features[index]);
				index++;
				classes[classNumber].setRoomId(features[index]);
				index++;
				classes[classNumber].addProfessor(features[index]);
				index++;
				classNumber++;
			}
		}
		this.classes = classes;
	}
	
	/**
	 * For getting a random room.
	 * @return
	 */
	public Room getRandomRoom() {
		Object[] roomsArray = this.rooms.values().toArray();
		Room room = (Room) roomsArray[(int) (roomsArray.length * Math.random())];
		return room;
	}
	
	public void addRoom(int roomId, String roomName) {
		this.rooms.put(roomId, new Room(roomId, roomName));
	}

	public void addProfessor(int professorId, String professorName) {
		this.professors.put(professorId, new Professor(professorId, professorName));
	}

	public void addCourse(String courseCode, String course, int professorIds[]) {
		this.courses.put(courseCode, new Course(courseCode, course, professorIds));
	}

	public void addSemester(int semesterId,  String courseIds[]) {
		this.semesters.put(semesterId, new Semester(semesterId,  courseIds));
	}

	public void addTimeslot(int timeslotId, String timeslot) {
		this.timeintervalls.put(timeslotId, new Time(timeslotId, timeslot));
	}


	public Room getRoom(int roomId) {
		return this.rooms.get(roomId);
	}

	public Professor getProfessor(int professorId) {
		return this.professors.get(professorId);
	}

	public Course getcourse(String courseId) {
		return this.courses.get(courseId);
	}

	public String[] getGroupcourses(int semesterId) {
		Semester semester = this.semesters.get(semesterId);
		return semester.getCourseIds();
	}

	public Semester getSemester(int semesterId) {
		return this.semesters.get(semesterId);
	}

	public Semester[] getSemestersArray() {
		return this.semesters.values().toArray(new Semester[this.semesters.size()]);
	}

	public Time getTimeslot(int timeslotId) {
		return this.timeintervalls.get(timeslotId);
	}

	public Time getRandomTimeslot() {
		Object[] timeslotArray = this.timeintervalls.values().toArray();
		Time timeslot = (Time) timeslotArray[(int) (timeslotArray.length * Math.random())];
		return timeslot;
	}


	public Class[] getClasses() {
		return this.classes;
	}

	public HashMap<String, Course> getcourses() {
		return courses;
	}


}