package SessionSolutions;

public class School {
    private double tuition;
    private double teacherSalary;
    private int numTeachers;
    private int numStudents;
    private String name;
    
    public School() {
        this.name = "";
    }
    
    public School(String name, double tuition, double teacherSalary, int numTeachers, int numStudents) {
        this.name = name;
        this.tuition = tuition;
        this.teacherSalary = teacherSalary;
        this.numTeachers = numTeachers;
        this.numStudents = numStudents;
    }
    
    public double getTotalTuition() {
        return numStudents * tuition;
    }
    
    public double getTeacherExpenses() {
        return teacherSalary * numTeachers;
    }
    
    public void increaseTuition(int amount) {
        tuition = tuition + amount;
    }

    public double getTeacherSalary() {
        return teacherSalary;
    }

    public void fireTeachers(int number) {
        numTeachers = numTeachers - number;
        if (numTeachers < 0)
            numTeachers = 0;
    }
    
    public void hireTeachers(int number) {
        numTeachers = numTeachers + number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}