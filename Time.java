/**
 * 
 * @author zsenel
 *
 */
public class Time {
    private int timeId;
    private String time;

    public Time(int timeslotId, String timeslot){
        this.timeId = timeslotId;
        this.time = timeslot;
    }

    public int getTimeId(){
        return this.timeId;
    }
    
    public String getTime(){
        return this.time;
    }
}
