package entity;

import adt.*;
import dao.StudentDAO;
import java.io.Serializable;

/**
 *
 * @author Loh Jian Wei
 */
public class AssignmentTeam implements Comparable<AssignmentTeam>, Serializable {

    private int assignmentTeamId;
    private String assignmentTeamName;
    private SortedListInterface<AssignmentTeamStudent> studentList = new SortedList<>();
    private StudentDAO studentDAO = new StudentDAO();

    public AssignmentTeam() {
        studentList = studentDAO.retrieveFromFile();
    }

    public AssignmentTeam(int assignmentTeamId) {
        this.assignmentTeamId = assignmentTeamId;
    }

    public AssignmentTeam(int assignmentTeamId, String assignmentTeamName) {
        this.assignmentTeamId = assignmentTeamId;
        this.assignmentTeamName = assignmentTeamName;
    }

    public int getAssignmentTeamId() {
        return assignmentTeamId;
    }

    public void setAssignmentTeamId(int assignmentTeamId) {
        this.assignmentTeamId = assignmentTeamId;
    }

    public String getAssignmentTeamName() {
        return assignmentTeamName;
    }

    public void setAssignmentTeamName(String assignmentTeamName) {
        this.assignmentTeamName = assignmentTeamName;
    }

    public SortedListInterface<AssignmentTeamStudent> getStudentList() {
        return studentList;
    }

    public void setStudentList(SortedListInterface<AssignmentTeamStudent> studentList) {
        this.studentList = studentList;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.assignmentTeamId;
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
        final AssignmentTeam other = (AssignmentTeam) obj;
        return this.assignmentTeamId == other.assignmentTeamId;
    }

    @Override
    public String toString() {
        return String.format("%5s  %-25s", assignmentTeamId, assignmentTeamName);
    }

    @Override
    public int compareTo(AssignmentTeam t) {
        if (this.assignmentTeamId > t.assignmentTeamId) {
            return 1;
        } else if (this.assignmentTeamId == t.assignmentTeamId) {
            return 0;
        } else {
            return -1;
        }
    }
}
