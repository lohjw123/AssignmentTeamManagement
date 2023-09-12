package control;

import adt.*;
import boundary.AssignmentTeamManagementUI;
import dao.AssignmentTeamDAO;
import dao.StudentDAO;
import entity.*;
import java.util.Iterator;
import utility.AssignmentTeamManagementMessageUI;

/**
 *
 * @author Loh Jian Wei
 */
public class AssignmentTeamManagementSystem {

    private SortedListInterface<AssignmentTeam> assignmentTeamList = new SortedList<>();
    private AssignmentTeamDAO assignmentTeamDAO = new AssignmentTeamDAO();
    private AssignmentTeamManagementUI assTeamManagementUI = new AssignmentTeamManagementUI();
    AssignmentTeam assignmentTeam = new AssignmentTeam();
    AssignmentTeamStudent student = new AssignmentTeamStudent();
    private StudentDAO studentDAO = new StudentDAO();

    public AssignmentTeamManagementSystem() {
        assignmentTeamList = assignmentTeamDAO.retrieveFromFile();
    }

    public void startAssignmentTeamManagementSystem() {
        int choice = 0;
        do {
            choice = assTeamManagementUI.getMenuChoice();
            switch (choice) {
                case 0:
                    AssignmentTeamManagementMessageUI.displayExitMessage();
                    break;
                case 1:
                    // Create or Add an Assignment Team.
                    // *Must have at least 1 AssignmentTeamStudent
                    assignmentTeam = createAssignmentTeam();
                    AssignmentTeamManagementMessageUI.displayAssignmentTeamAddedSeccessfullyMessage();
                    assTeamManagementUI.listAllAssignmentTeamDetails(assignmentTeam, getAllStudent(assignmentTeam));
                    break;
                case 2:
                    // Remove or Delete an Assignment Team.
                    removeAssignmentTeam();
                    break;
                case 3:
                    // Amend or Edit an Assignment Team Details.
                    assignmentTeam = getAssignmentTeamWithValidation();
                    assTeamManagementUI.listAllAssignmentTeamDetails(assignmentTeam, getAllStudent(assignmentTeam));
                    editAssignmentTeamDetails(assignmentTeam);
                    break;
                case 4:
                    // Add student to an selected Assignment Team.
                    final int maxStudent = 5;
                    assignmentTeam = getAssignmentTeamWithValidation();
                    if (getStudentNumberOfEntries(assignmentTeam) < maxStudent) {
                        addStudentToAssignmentTeam(assignmentTeam);
                    } else {
                        AssignmentTeamManagementMessageUI.displayStudentAcceedMaximun();
                    }
                    break;
                case 5:
                    // Remove or Delete AssignmentTeamStudent from a selected Assignment Team.
                    removeStudent();
                    break;
                case 6:
                    // Filter Assignment Teams based on condition.
                    filterAssignmentTeam();
                    break;
                case 7:
                    // List or Display all Assignment Teams.
                    listAssignmentTeam();
                    break;
                case 8:
                    // List or Display all Students in an Assignment Team.
                    assignmentTeam = getAssignmentTeamWithValidation();
                    listStudentOfAssignmentTeam(assignmentTeam);
                    break;
                case 9:
                    // Display Report of Assignment Teams Details.
                    generateReport();
                    break;
                default:
                    AssignmentTeamManagementMessageUI.displayInvalidChoiceMessage();
            }
        } while (choice != 0);
    }

    public AssignmentTeam createAssignmentTeam() {
        boolean addSuccess = true;
        do {
            assignmentTeam = assTeamManagementUI.inputAssignmentTeamDetails();
            addSuccess = assignmentTeamList.add(assignmentTeam);
            inputValidation(addSuccess);
        } while (addSuccess == false);
        addStudentToAssignmentTeam(assignmentTeam);
        assignmentTeamDAO.saveToFile(assignmentTeamList);
        return assignmentTeam;
    }

    public void removeSelectedAssignmentTeam() {
        assignmentTeam = getAssignmentTeamWithValidation();
        char confirmAmendAssignmentTeam = assTeamManagementUI.askForConfirmation();
        if (confirmAmendAssignmentTeam == 'y') {;
            assignmentTeamList.remove(assignmentTeam);
            assignmentTeamDAO.saveToFile(assignmentTeamList);
            assTeamManagementUI.displayRemovedAssignmentTeamResult(assignmentTeam);
        }
    }

    public AssignmentTeam amendAssignmentTeamDetails(AssignmentTeam assignmentTeam, AssignmentTeam newAssignmentTeam) {
        newAssignmentTeam.setStudentList(assignmentTeam.getStudentList());
        AssignmentTeam replacedAssignmentTeam = assignmentTeamList.replace(assignmentTeam, newAssignmentTeam);
        assignmentTeamDAO.saveToFile(assignmentTeamList);
        return replacedAssignmentTeam;
    }

    public AssignmentTeamStudent amendStudentDetails(AssignmentTeam assignmentTeam, AssignmentTeamStudent student, AssignmentTeamStudent newStudent) {
        AssignmentTeamStudent replacedStudent = amendStudent(assignmentTeam, student, newStudent);
        assignmentTeamDAO.saveToFile(assignmentTeamList);
        return replacedStudent;
    }

    public void addStudentToAssignmentTeam(AssignmentTeam assignmentTeam) {
        boolean addSuccess = true;
        do {
            if (studentIsEmpty(assignmentTeam)) {
                assTeamManagementUI.inputLeaderDetails();
                student = assTeamManagementUI.inputLeaderStudentDetails();
            } else {
                student = assTeamManagementUI.inputMemberStudentDetails();
            }
            addStudent(assignmentTeam, student);
            inputValidation(addSuccess);
        } while (addSuccess == false);
        assignmentTeamDAO.saveToFile(assignmentTeamList);

    }

    public void removeSelectedStudentFromAssignmentTeam(AssignmentTeam assignmentTeam) {
        student = getStudentWithValidation();
        char confirmAmendAssignmentTeam = assTeamManagementUI.askForConfirmation();
        if (confirmAmendAssignmentTeam == 'y') {;
            removeStudent(assignmentTeam, student);
            assignmentTeamDAO.saveToFile(assignmentTeamList);
            assTeamManagementUI.displayRemovedStudentResult(student);
        }
    }

    public void filterAssignmentTeam() {
        int filterChoice = 0;
        int filterInput = 0;
        String filteredAssignmentTeam = "";
        do {
            filterChoice = assTeamManagementUI.filterMenu();
            switch (filterChoice) {
                case 0:
                    break;
                case 1:
                    assTeamManagementUI.listAllAssignmentTeams(getLessThanAssignmentTeam(assTeamManagementUI.inputStudentLessThan()));
                    break;
                case 2:
                    assTeamManagementUI.listAllAssignmentTeams(getMoreThanAssignmentTeam(assTeamManagementUI.inputStudentMoreThan()));

                    break;
                case 3:
                    assTeamManagementUI.listAllAssignmentTeams(getOnlyMaleAssignmentTeam());
                    break;
                case 4:
                    assTeamManagementUI.listAllAssignmentTeams(getOnlyFemaleAssignmentTeam());
                    break;
                case 5:
                    assTeamManagementUI.listAllAssignmentTeams(getContainRepeatStudentAssignmentTeam());
                    break;
                case 6:
                    assTeamManagementUI.listAllAssignmentTeams(getContainWithdrawnStudentAssignmentTeam());
                    break;
                default:
                    AssignmentTeamManagementMessageUI.displayInvalidChoiceMessage();
            }
        } while (filterChoice != 0);
    }

    public void listAssignmentTeam() {
        assTeamManagementUI.listAllAssignmentTeams(getAllAssignmentTeam());
    }

    public void listStudentOfAssignmentTeam(AssignmentTeam assignmentTeam) {
        assTeamManagementUI.listAllStudent(assignmentTeam, getAllStudent(assignmentTeam));
    }

    public void generateReport() {
        assTeamManagementUI.displayReport(generateAssignmentTeamReport());
    }

    public SortedListInterface<AssignmentTeamStudent> getAllStudents(AssignmentTeam assignmentTeam) {
        return assignmentTeam.getStudentList();
    }

    public void addStudent(AssignmentTeam assignmentTeam, AssignmentTeamStudent student) {
        assignmentTeam.getStudentList().add(student);
        studentDAO.saveToFile(assignmentTeam.getStudentList());
    }

    public void removeStudent(AssignmentTeam assignmentTeam, AssignmentTeamStudent student) {
        assignmentTeam.getStudentList().remove(student);
        studentDAO.saveToFile(assignmentTeam.getStudentList());
    }

    public void removeAllStudent(AssignmentTeam assignmentTeam) {
        assignmentTeam.getStudentList().clear();
        studentDAO.saveToFile(assignmentTeam.getStudentList());
    }

    public AssignmentTeamStudent amendStudent(AssignmentTeam assignmentTeam, AssignmentTeamStudent oldStudent, AssignmentTeamStudent newStudent) {
        AssignmentTeamStudent replacedStudent = assignmentTeam.getStudentList().replace(oldStudent, newStudent);
        studentDAO.saveToFile(assignmentTeam.getStudentList());
        return replacedStudent;
    }

    public AssignmentTeamStudent getStudent(AssignmentTeam assignmentTeam, AssignmentTeamStudent student) {
        return assignmentTeam.getStudentList().get(student);
    }

    public boolean studentIsEmpty(AssignmentTeam assignmentTeam) {
        return assignmentTeam.getStudentList().isEmpty();
    }

    public String getAllStudent(AssignmentTeam assignmentTeam) {
        String allStudent = "";
        int totalStudent = 0;
        Iterator<AssignmentTeamStudent> studentIt = assignmentTeam.getStudentList().getIterator();
        while (studentIt.hasNext()) {
            AssignmentTeamStudent student = studentIt.next();
            allStudent += student.toString() + "\n";
            totalStudent++;
        }
        allStudent += "\nTotal Student: " + totalStudent + "\n";
        return allStudent;
    }

    public AssignmentTeamStudent getSelectedStudent(AssignmentTeam assignmentTeam, AssignmentTeamStudent student) {
        Iterator<AssignmentTeamStudent> it = assignmentTeam.getStudentList().getIterator();
        while (it.hasNext()) {
            AssignmentTeamStudent stud = it.next();
            if (stud.compareTo(student) == 0) {
                return stud;
            }
        }
        return null;
    }

    public AssignmentTeamStudent getCurrentLeader(AssignmentTeam assignmentTeam) {
        Iterator<AssignmentTeamStudent> it = assignmentTeam.getStudentList().getIterator();
        while (it.hasNext()) {
            AssignmentTeamStudent stud = it.next();
            if (stud.getStudentRole() == 1) {
                return stud;
            }
        }
        return null;
    }

    public int countMaleStudents(AssignmentTeam assignmentTeam) {
        int filterMaleStudent = 0;
        Iterator<AssignmentTeamStudent> it = assignmentTeam.getStudentList().getIterator();
        while (it.hasNext()) {
            AssignmentTeamStudent stud = it.next();
            if (stud.getStudentGender().equals("Male")) {
                filterMaleStudent++;
            }
        }
        return filterMaleStudent;
    }

    public int countFemaleStudents(AssignmentTeam assignmentTeam) {
        int filterFemaleStudent = 0;
        Iterator<AssignmentTeamStudent> it = assignmentTeam.getStudentList().getIterator();
        while (it.hasNext()) {
            AssignmentTeamStudent stud = it.next();
            if (stud.getStudentGender().equals("Female")) {
                filterFemaleStudent++;
            }
        }
        return filterFemaleStudent;
    }

    public int countRepeatStudent(AssignmentTeam assignmentTeam) {
        int totalRepeatStudent = 0;
        Iterator<AssignmentTeamStudent> it = assignmentTeam.getStudentList().getIterator();
        while (it.hasNext()) {
            AssignmentTeamStudent stud = it.next();
            if (stud.getStudentStatus() == 2) {
                totalRepeatStudent++;
            }
        }
        return totalRepeatStudent;
    }

    public int countWithdrawnStudent(AssignmentTeam assignmentTeam) {
        int totalWithdrawnStudent = 0;
        Iterator<AssignmentTeamStudent> it = assignmentTeam.getStudentList().getIterator();
        while (it.hasNext()) {
            AssignmentTeamStudent stud = it.next();
            if (stud.getStudentStatus() == 3) {
                totalWithdrawnStudent++;
            }
        }
        return totalWithdrawnStudent;
    }

    public int getStudentNumberOfEntries(AssignmentTeam assignmentTeam) {
        return assignmentTeam.getStudentList().getNumberOfEntries();
    }

    public void getStudentList(AssignmentTeam assignmentTeam) {
        SortedListInterface<AssignmentTeamStudent> studentList = assignmentTeam.getStudentList();
        studentList = new SortedList<>();
    }

    public void inputValidation(boolean result) {
        if (result == false) {
            AssignmentTeamManagementMessageUI.displayAssignmentTeamExistMessage();
        }
    }

    public AssignmentTeam getAssignmentTeamWithValidation() {
        if (!getAllAssignmentTeam().equals("")) {
            do {
                assignmentTeam = getSelectedAssignmentTeam(userSelectAssignmentTeam());
                if (assignmentTeam == null) {
                    AssignmentTeamManagementMessageUI.displayInvalidAssignmentTeamMessage();
                }
            } while (assignmentTeam == null);
        } else {
            AssignmentTeamManagementMessageUI.displayNoRecordMessage();
            assignmentTeam = createAssignmentTeam();
        }
        return assignmentTeam;
    }

    public AssignmentTeamStudent getStudentWithValidation() {
        do {
            student = getSelectedStudent(assignmentTeam, new AssignmentTeamStudent(assTeamManagementUI.selectStudentId()));
            if (student == null) {
                AssignmentTeamManagementMessageUI.displayInvalidStudentMessage();
            }
        } while (student == null);
        return student;
    }

    public String getAllAssignmentTeam() {
        String allAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            allAssignmentTeam += e.toString() + "\n";
        }
        return allAssignmentTeam;
    }

    // If found return the selected Assignment Team, else return null
    public AssignmentTeam getSelectedAssignmentTeam(AssignmentTeam assignmentTeam) {
        AssignmentTeam assTeam = assignmentTeamList.get(assignmentTeam);
        return assTeam;
    }

    public String getLessThanAssignmentTeam(int lessThanNumber) {
        String filteredAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (getStudentNumberOfEntries(e) < lessThanNumber) {
                filteredAssignmentTeam += e.toString() + "\n";
            }
        }
        return filteredAssignmentTeam;
    }

    public String getMoreThanAssignmentTeam(int moreThanNumber) {
        String filteredAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (getStudentNumberOfEntries(e) > moreThanNumber) {
                filteredAssignmentTeam += e.toString() + "\n";
            }
        }
        return filteredAssignmentTeam;
    }

    public String getOnlyMaleAssignmentTeam() {
        String filteredAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (countMaleStudents(e) == getStudentNumberOfEntries(e)) {
                filteredAssignmentTeam += e.toString() + "\n";
            }
        }
        return filteredAssignmentTeam;
    }

    public String getOnlyFemaleAssignmentTeam() {
        String filteredAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (countFemaleStudents(e) == getStudentNumberOfEntries(e)) {
                filteredAssignmentTeam += e.toString() + "\n";
            }
        }
        return filteredAssignmentTeam;
    }

    public String getContainRepeatStudentAssignmentTeam() {
        String filteredAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (countRepeatStudent(e) != 0) {
                filteredAssignmentTeam += e.toString() + "\n";
            }
        }
        return filteredAssignmentTeam;
    }

    public String getContainWithdrawnStudentAssignmentTeam() {
        String filteredAssignmentTeam = "";
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            if (countWithdrawnStudent(e) != 0) {
                filteredAssignmentTeam += e.toString() + "\n";
            }
        }
        return filteredAssignmentTeam;
    }

    public AssignmentTeam userSelectAssignmentTeam() {
        assTeamManagementUI.listAllAssignmentTeams(getAllAssignmentTeam());
        return new AssignmentTeam(assTeamManagementUI.selectAssignmentTeam());
    }

    public void removeAssignmentTeam() {
        int removeSelection = assTeamManagementUI.selectRemoveMenu();
        switch (removeSelection) {
            case 1:
                assignmentTeamList.clear();
                assignmentTeamDAO.saveToFile(assignmentTeamList);
                break;
            case 2:
                removeSelectedAssignmentTeam();
                break;
            default:
                AssignmentTeamManagementMessageUI.displayInvalidChoiceMessage();
        }
    }

    public void removeStudent() {
        assignmentTeam = getAssignmentTeamWithValidation();
        int removeSelection = assTeamManagementUI.selectRemoveMenu();
        switch (removeSelection) {
            case 1:
                removeAllStudent(assignmentTeam);
                assignmentTeamDAO.saveToFile(assignmentTeamList);
                break;
            case 2:
                assTeamManagementUI.listAllStudent(assignmentTeam, getAllStudent(assignmentTeam));
                removeSelectedStudentFromAssignmentTeam(assignmentTeam);
                break;
            default:
                AssignmentTeamManagementMessageUI.displayInvalidChoiceMessage();
        }
    }

    public AssignmentTeam editAssignmentTeamDetails(AssignmentTeam assignmentTeam) {
        AssignmentTeam newAssignmentTeam = new AssignmentTeam(assignmentTeam.getAssignmentTeamId(), assignmentTeam.getAssignmentTeamName());
        int amendSelection = 0;
        amendSelection = assTeamManagementUI.selectAssignmentTeamDetailsToEdit();
        switch (amendSelection) {
            case 0:
                break;
            case 1:
                int newAssignmentTeamId = assTeamManagementUI.inputNewAssignmentTeamId();
                newAssignmentTeam.setAssignmentTeamId(newAssignmentTeamId);
                break;
            case 2:
                String newAssignmentTeamName = assTeamManagementUI.inputNewAssignmentTeamName();
                newAssignmentTeam.setAssignmentTeamName(newAssignmentTeamName);
                break;
            case 3:
                assTeamManagementUI.listAllStudent(assignmentTeam, getAllStudent(assignmentTeam));
                AssignmentTeamStudent student = getStudentWithValidation();
                editStudentDetails(student, assignmentTeam);
                break;
            default:
                AssignmentTeamManagementMessageUI.displayInvalidChoiceMessage();
        }
        amendAssignmentTeamDetails(assignmentTeam, newAssignmentTeam);
        System.out.println("\n" + newAssignmentTeam.getAssignmentTeamName() + "'s Assignmnet Team Details have been updated.\n");
        assTeamManagementUI.listAllAssignmentTeamDetails(newAssignmentTeam, getAllStudent(newAssignmentTeam));
        return newAssignmentTeam;
    }

    public AssignmentTeamStudent editStudentDetails(AssignmentTeamStudent student, AssignmentTeam assignmentTeam) {
        AssignmentTeamStudent newStudent = new AssignmentTeamStudent(student.getStudentId(), student.getStudentName(), student.getStudentGender(), student.getStudentRole(), student.getStudentStatus());
        int studentAmendSelection = 0;
        do {
            studentAmendSelection = assTeamManagementUI.selectStudentDetailsToEdit();
            switch (studentAmendSelection) {
                case 0:
                    break;
                case 1:
                    String newStudentId = assTeamManagementUI.inputNewStudentId();
                    newStudent.setStudentId(newStudentId);
                    break;
                case 2:
                    String newStudentName = assTeamManagementUI.inputNewStudentName();
                    newStudent.setStudentName(newStudentName);
                    break;
                case 3:
                    String newStudentGender = assTeamManagementUI.inputNewStudentGender();
                    newStudent.setStudentGender(newStudentGender);
                    break;
                case 4:
                    AssignmentTeamManagementMessageUI.displayChangeLeaderMessage();
                    newStudent.setStudentRole(1);
                    break;
                case 5:
                    int newStudentStatus = assTeamManagementUI.inputNewStudentStatus();
                    newStudent.setStudentStatus(newStudentStatus);
                    break;
                default:
                    AssignmentTeamManagementMessageUI.displayInvalidChoiceMessage();
            }
            if (studentAmendSelection != 0) {
                if (studentAmendSelection == 4) {
                    swapAssignmentTeamLeader(assignmentTeam);
                }
                amendStudentDetails(assignmentTeam, student, newStudent);
                System.out.println("\n" + newStudent.getStudentName() + "'s Student Details have been updated.\n");
                assTeamManagementUI.listAllStudent(assignmentTeam, getAllStudent(assignmentTeam));

            }
        } while (studentAmendSelection != 0);
        return newStudent;
    }

    public void swapAssignmentTeamLeader(AssignmentTeam assignmentTeam) {
        AssignmentTeamStudent assignmentTeamLeader = getCurrentLeader(assignmentTeam);
        assignmentTeamLeader.setStudentRole(2);
    }

    public String generateAssignmentTeamReport() {
        String allAssignmentTeamDetails = "";
        int totalStudent = 0;
        int totalRepeatStudent = 0;
        int totalWithdrawnStudent = 0;
        int totalMaleStudent = 0;
        int totalFemaleStudent = 0;
        Iterator<AssignmentTeam> it = assignmentTeamList.getIterator();
        while (it.hasNext()) {
            AssignmentTeam e = it.next();
            allAssignmentTeamDetails += assTeamManagementUI.formatReport(e.getAssignmentTeamId(), e.getAssignmentTeamName(), getAllStudent(e), countRepeatStudent(e), countWithdrawnStudent(e));
            totalStudent += getStudentNumberOfEntries(e);
            totalRepeatStudent += countRepeatStudent(e);
            totalWithdrawnStudent += countWithdrawnStudent(e);
            totalMaleStudent += countMaleStudents(e);
            totalFemaleStudent += countFemaleStudents(e);
        }
        allAssignmentTeamDetails += "\n" + assTeamManagementUI.formatReportSummary(totalStudent, totalRepeatStudent, totalWithdrawnStudent, totalMaleStudent, totalFemaleStudent, assignmentTeamList.getNumberOfEntries());
        return allAssignmentTeamDetails;
    }

    public static void main(String[] args) {
        AssignmentTeamManagementSystem AssignmentTeamManagementSystem = new AssignmentTeamManagementSystem();
        AssignmentTeamManagementSystem.startAssignmentTeamManagementSystem();
    }
}
