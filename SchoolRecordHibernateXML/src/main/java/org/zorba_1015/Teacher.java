package org.zorba_1015;

public class Teacher {
    public int teacherId;
    public String teacherName;
    public String teacherQualification;
    public int experienceOfTeaching;
    public org.zorba_1015.Subject subject;

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

public String getTeacherQualification() {
        return teacherQualification;
}
public void setTeacherQualification(String teacherQualification) {
        this.teacherQualification = teacherQualification;
}
public int getExperienceOfTeaching() {
        return experienceOfTeaching;
}
public void setExperienceOfTeaching(int experienceOfTeaching){
        this.experienceOfTeaching = experienceOfTeaching;
}
public Subject getSubject() {
        return subject;
}
public void setSubject(org.zorba_1015.Subject subject) {
        this.subject = subject;
}
}