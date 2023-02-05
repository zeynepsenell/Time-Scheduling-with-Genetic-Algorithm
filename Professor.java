/**
 * Class representing professors.
 * @author 90543
 *
 */
public class Professor {
   
	private int professorId;
    private String professorName;

    public Professor(int professorId, String professorName){
        this.professorId = professorId;
        this.professorName = professorName;
    }
    
    public int getProfessorId(){
        return this.professorId;
    }
    
    public String getProfessorName(){
        return this.professorName;
    }
}
