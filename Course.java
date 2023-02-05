/**
 * A class representing courses with the professor teaching the course.
 * @author zsenel
 *
 */
public class Course {
	
	private String courseCode;
    private String course;
    private int professorIds[];
    
    public Course(String courseCode, String course, int professorIds[]){
        this.courseCode = courseCode;
        this.course = course;
        this.professorIds = professorIds;
    }
    
    public String getCourseCode(){
        return this.courseCode;
    }
    
    public String getCourseName(){
        return this.course;
    }
    
    /**
     * Professor id will be selected randomly.
     * @return the id of the professor 
     */
    public int selectProfessorId(){
        int professorId = professorIds[(int) (professorIds.length * Math.random())];
        return professorId;
    }
}
