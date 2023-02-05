/**
 * Class representing semesters and courses that belongs to semesters.
 * @author zsenel
 *
 */
public class Semester {
  
	private final int semesterId;
    private final String courseIds[];

    public Semester(int semesterId, String courseIds[]){
        this.semesterId = semesterId;
        this.courseIds = courseIds;
    }
    
    public int getSemesterId(){
        return this.semesterId;
    }

    public String[] getCourseIds(){
        return this.courseIds;
    }
}
