package entity;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Loh JW
 */
public class AssignmentTeamStudent implements Comparable<AssignmentTeamStudent>, Serializable {

    private String studentId;
    private String studentName;
    private String studentGender;
    private int studentRole;
    private int studentStatus;

    public AssignmentTeamStudent() {
    }

    public AssignmentTeamStudent(String studentId) {
        this.studentId = studentId;
    }

    public AssignmentTeamStudent(int studentRole) {
        this.studentRole = studentRole;
    }

    public AssignmentTeamStudent(String studentId, String studentName, String studentGender, int studentRole, int studentStatus) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.studentRole = studentRole;
        this.studentStatus = studentStatus;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public int getStudentRole() {
        return studentRole;
    }

    public void setStudentRole(int studentRole) {
        this.studentRole = studentRole;
    }

    public int getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(int studentStatus) {
        this.studentStatus = studentStatus;
    }

    public String getStudentRole(int studentRole) {
        String role = null;
        switch (studentRole) {
            case 1:
                role = "Leader";
                break;
            case 2:
                role = "Member";
                break;
        }
        return role;
    }

    public String getStudentStatus(int studentStatus) {
        String status = null;
        switch (studentStatus) {
            case 1:
                status = "Non-repeat Student";
                break;
            case 2:
                status = "Repeat Student";
                break;
            case 3:
                status = "Withdrawn";
                break;
        }
        return status;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.studentId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AssignmentTeamStudent other = (AssignmentTeamStudent) obj;
        return Objects.equals(this.studentId, other.studentId);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-25s %-6s %-6s %-18s", studentId, studentName, studentGender, getStudentRole(studentRole), getStudentStatus(studentStatus));
    }

    @Override
    public int compareTo(AssignmentTeamStudent s) {
        String thisId = this.studentId;
        String otherId = s.studentId;

        for (int i = 0; i < thisId.length(); i++) {
            char thisChar = thisId.charAt(i);
            char otherChar = otherId.charAt(i);

            if (thisChar < otherChar) {
                return -1;
            } else if (thisChar > otherChar) {
                return 1;
            }
        }
        return 0;
    }
}
