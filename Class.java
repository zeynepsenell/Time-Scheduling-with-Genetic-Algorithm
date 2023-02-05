/**
 * A class with information of semester, course id, professor, time slot and room.
 * @author zsenel
 *
 */
public class Class {
    private final int classId;
    private final int semesterId;
    private final String courseId;
    private int professorId;
    private int timeslotId;
    private int roomId;

    public Class(int classId, int semesterId, String moduleId){
        this.classId = classId;
        this.courseId = moduleId;
        this.semesterId = semesterId;
    }
    
    public void addProfessor(int professorId){
        this.professorId = professorId;
    }
    
    public void addTimeslot(int timeslotId){
        this.timeslotId = timeslotId;
    }    
    
    public void setRoomId(int roomId){
        this.roomId = roomId;
    }
    
    public int getClassId(){
        return this.classId;
    }
    
    public int getSemesterId(){
        return this.semesterId;
    }
    
    public String getCourseId(){
        return this.courseId;
    }    

    public int getProfessorId(){
        return this.professorId;
    }
    
    public int getTimeslotId(){
        return this.timeslotId;
    }

    public int getRoomId(){
        return this.roomId;
    }
}

