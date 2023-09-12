package boundary;

import entity.*;
import java.util.Scanner;

/**
 *
 * @author Loh JW
 */
public class AssignmentTeamManagementUI {

    Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.print("\n");
        System.out.println(getDottedLines(41));
        System.out.println("Assignment Team Management System");
        System.out.println(getDottedLines(41));
        System.out.println("1) Create assignment team");
        System.out.println("2) Remove assignment team");
        System.out.println("3) Amend assignment team details");
        System.out.println("4) Add student to assignment team");
        System.out.println("5) Remove student from assignment team");
        System.out.println("6) Filter assignment teams");
        System.out.println("7) List assignment teams");
        System.out.println("8) List students under an assignment team");
        System.out.println("9) View Report");
        System.out.println("0) Exit");
        System.out.println(getDottedLines(41));
        System.out.print("Enter Choice : ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public String getDottedLines(int quantity) {
        String str = "";
        for (int i = 0; i <= quantity; i++) {
            str += "=";
        }
        return str;
    }

    public void listAllAssignmentTeams(String allAssignmentTeam) {
        System.out.println("\nAssignment Teams List");
        System.out.println(getDottedLines(20));
        System.out.print(allAssignmentTeam);
        System.out.println(getDottedLines(20));
    }

    public void listAllAssignmentTeamDetails(AssignmentTeam assignmentTeam, String student) {
        System.out.println("\n" + assignmentTeam.getAssignmentTeamName() + " Details");
        System.out.println(getDottedLines(68));
        System.out.println("Assignment Team Id: " + assignmentTeam.getAssignmentTeamId());
        System.out.println("Assignment Team Name: " + assignmentTeam.getAssignmentTeamName());
        System.out.print("Assignment Team Members:\n");
        System.out.println(getDottedLines(68));
        System.out.print(student);
        System.out.println(getDottedLines(68));
    }

    public void listAllStudent(AssignmentTeam assignmentTeam, String student) {
        System.out.println("\n" + assignmentTeam.getAssignmentTeamName() + " Student List");
        System.out.println(getDottedLines(68));
        System.out.print(student);
        System.out.println(getDottedLines(68));
    }

    public int inputAssignmentTeamId() {
        System.out.print("Enter Assignment Team ID in number (Eg: 10001): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public int inputNewAssignmentTeamId() {
        System.out.print("Enter New Assignment Team ID in number (Eg: 10001): ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    public String inputAssignmentTeamName() {
        System.out.print("Enter Assignment Team name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputNewAssignmentTeamName() {
        System.out.print("Enter New Assignment Team name: ");
        String name = scanner.nextLine();
        return name;
    }

    public String inputStudentId() {
        System.out.print("Enter Student ID: ");
        String studId = scanner.nextLine();
        return studId;
    }

    public String inputNewStudentId() {
        System.out.print("Enter New Student ID: ");
        String studId = scanner.nextLine();
        return studId;
    }

    public String inputStudentName() {
        System.out.print("Enter Student name: ");
        String studName = scanner.nextLine();
        return studName;
    }

    public String inputNewStudentName() {
        System.out.print("Enter New Student name: ");
        String studName = scanner.nextLine();
        return studName;
    }

    public String inputStudentGender() {
        System.out.print("Enter Student gender: ");
        String studGender = scanner.next();
        return studGender;
    }

    public String inputNewStudentGender() {
        System.out.print("Enter New Student gender: ");
        String studGender = scanner.nextLine();
        return studGender;
    }

    public int inputStudentStatus() {
        System.out.print("\nSelect Student Status\n");
        System.out.println(getDottedLines(25));
        System.out.println("1) Non-repeat Student");
        System.out.println("2) Repeat Student");
        System.out.println(getDottedLines(25));
        System.out.print("Enter Selection: ");
        int studStatus = scanner.nextInt();
        scanner.nextLine();
        return studStatus;
    }

    public int inputNewStudentStatus() {
        System.out.print("\nSelect Student Status\n");
        System.out.println(getDottedLines(25));
        System.out.println("1) Non-repeat Student");
        System.out.println("2) Repeat Student");
        System.out.println("3) Withdrawn");
        System.out.println(getDottedLines(25));
        System.out.print("Enter Selection: ");
        int studStatus = scanner.nextInt();
        scanner.nextLine();
        return studStatus;
    }

    public AssignmentTeamStudent inputLeaderStudentDetails() {
        String studentId = inputStudentId();
        String studentName = inputStudentName();
        String studentGender = inputStudentGender();
        int studentStatus = inputStudentStatus();
        AssignmentTeamStudent student = new AssignmentTeamStudent(studentId, studentName, studentGender, 1, studentStatus);
        return student;
    }

    public AssignmentTeamStudent inputMemberStudentDetails() {
        String studentId = inputStudentId();
        String studentName = inputStudentName();
        String studentGender = inputStudentGender();
        int studentStatus = inputStudentStatus();
        AssignmentTeamStudent student = new AssignmentTeamStudent(studentId, studentName, studentGender, 2, studentStatus);
        return student;
    }

    public AssignmentTeam inputAssignmentTeamDetails() {
        int assignmentTeamId = inputAssignmentTeamId();
        String assignmentTeamName = inputAssignmentTeamName();
        return new AssignmentTeam(assignmentTeamId, assignmentTeamName);
    }

    public int selectAssignmentTeam() {
        System.out.print("Please enter the Assignment Team ID in number (Eg: 10001): ");
        int assignmentTeamId = scanner.nextInt();
        scanner.nextLine();
        return assignmentTeamId;
    }

    public void inputLeaderDetails() {
        System.out.println("\nPlease enter Team Leader Details");
        System.out.println(getDottedLines(32));
    }

    public int selectAssignmentTeamDetailsToEdit() {
        System.out.println("\nPlease select the Assignment Team Details you want to Amend");
        System.out.println(getDottedLines(58));
        System.out.println("1) Assignmnet ID");
        System.out.println("2) Assignment Name");
        System.out.println("3) Student");
        System.out.println(getDottedLines(58));
        System.out.println("0) Back");
        System.out.println(getDottedLines(58));
        System.out.print("Your Choice: ");
        int amendAssTeamChoice = scanner.nextInt();
        scanner.nextLine();
        return amendAssTeamChoice;
    }

    public int selectStudentDetailsToEdit() {
        System.out.println("\nPlease select the Student Details you want to Amend");
        System.out.println(getDottedLines(50));
        System.out.println("1) ID");
        System.out.println("2) Name");
        System.out.println("3) Gender");
        System.out.println("4) Role");
        System.out.println("5) Status");
        System.out.println(getDottedLines(50));
        System.out.println("0) Back");
        System.out.println(getDottedLines(50));
        System.out.print("Your Choice: ");
        int amendStudentChoice = scanner.nextInt();
        scanner.nextLine();
        return amendStudentChoice;
    }

    public String selectStudentId() {
        System.out.print("Please enter the Student ID: ");
        String studentId = scanner.next();
        return studentId;
    }

    public char addNextStudentConfirmation() {
        System.out.print("Do you want to add next student? (y/n): ");
        char addNext = scanner.next().charAt(0);
        scanner.nextLine();
        return addNext;
    }

    public char askForConfirmation() {
        System.out.print("Are you Confrim? (y/n): ");
        char confirm = scanner.next().charAt(0);
        scanner.nextLine();
        return confirm;
    }

    public int selectRemoveMenu() {
        System.out.println("Select what to remove");
        System.out.println(getDottedLines(50));
        System.out.println("1) Remove All");
        System.out.println("2) Remove with ID");
        System.out.println(getDottedLines(50));
        System.out.println("0) Back");
        System.out.println(getDottedLines(50));
        System.out.print("Your Choice: ");
        int removeChoice = scanner.nextInt();
        scanner.nextLine();
        return removeChoice;
    }

    public void displayRemovedAssignmentTeamResult(AssignmentTeam assignmentTeam) {
        System.out.println(assignmentTeam.getAssignmentTeamName() + " Removed Successfully.");
    }

    public void displayRemovedStudentResult(AssignmentTeamStudent student) {
        System.out.println(student.getStudentName() + "Removed Successfully.");
    }

    public int filterMenu() {
        System.out.println("\nFilter Assignment Team by:");
        System.out.println(getDottedLines(50));
        System.out.println("1) Assignment Team Student Less than...");
        System.out.println("2) Assignment Team Student More than...");
        System.out.println("3) Assignment Team with all Male Student.");
        System.out.println("4) Assignment Team with all Female Student.");
        System.out.println("5) Assignment Team contain Repeat-Student.");
        System.out.println("6) Assignment Team contain Withdrawn-Student.");
        System.out.println(getDottedLines(50));
        System.out.println("0) Back");
        System.out.println(getDottedLines(50));
        System.out.print("Your Choice: ");
        int filterChoice = scanner.nextInt();
        scanner.nextLine();
        return filterChoice;
    }

    public int inputStudentLessThan() {
        System.out.print("Filter by Assignment Team Student Less than: ");
        int lessThanChoice = scanner.nextInt();
        scanner.nextLine();
        return lessThanChoice;
    }

    public int inputStudentMoreThan() {
        System.out.print("Filter by Assignment Team Student More than: ");
        int moreThanChoice = scanner.nextInt();
        scanner.nextLine();
        return moreThanChoice;
    }

    public String formatReport(int assId, String assName, String students, int repeatStudent, int withdrawnStudent) {
        String str = String.format("%3s %5d  %5s %-25s\n\n%-8s\n%-65s%-15s %-2d  %-18s %-2d\n%70s\n", "ID:", assId, "Name:", assName, "Student:", students, "Repeat-Student:", repeatStudent, "Withdrawn Student:", withdrawnStudent, getDottedLines(70));
        return str;
    }

    public String formatReportSummary(int totalStudent, int totalRepeatStudent, int totalWithdrawnStudent, int totalMaleStudent, int totalFemaleStudent, int totalAssignmentTeam) {
        String str = String.format("%70s\n%-22s %-3d\n%-14s %-3d\n%-21s %-3d  %-24s %-3d\n%-18s %-3d  %-21s %-3d\n%70s", getDottedLines(70), "Total Assignment Team:", totalAssignmentTeam, "Total Student:", totalStudent, "Total Repeat-Student:", totalRepeatStudent, "Total Withdrawn Student:", totalWithdrawnStudent, "Total Male Student:", totalMaleStudent, "Total Female Student:", totalFemaleStudent, getDottedLines(70));
        return str;
    }

    public void displayReport(String reportStr) {
        System.out.println("\t\t\tAssignment Team Report");
        System.out.println(getDottedLines(70));
        System.out.println(reportStr);
    }
}
