package tl.pojo;

import lombok.Data;

@Data
public class Volunteer {
    private int id;
    private String studentId;
    private String teacherId;
    private int order;
    private String time;
}
