package utility;

/**
 *
 * @author Loh JW
 */
public class AssignmentTeamManagementMessageUI {

    public static void displayExitMessage() {
        System.out.println("Exiting System, Bye...");
    }

    public static void displayInvalidChoiceMessage() {
        System.out.println("\nInvalid Choice.\nPlease Select again.\n");
    }

    public static void displayInvalidInputMessage() {
        System.out.println("\nSo such Assignment Team.");
    }

    public static void displayInvalidAssignmentTeamMessage() {
        System.out.println("\nInvalid Assignment Team, Assignment Team does not exist.\nPlease enter again...\n");
    }

    public static void displayAssignmentTeamExistMessage() {
        System.out.println("\nAssignment Team with the ID already existed.\nPlease enter another ID.\n");
    }

    public static void displayInvalidStudentMessage() {
        System.out.println("\nInvalid Student, Student does not exist.\nPlease enter again...\n");
    }

    public static void displayNoRecordMessage() {
        System.out.println("No records!\nPlease Create new Assignment Team.\n");
    }

    public static void displayAssignmentTeamAddedSeccessfullyMessage() {
        System.out.println("Assignment Team Added Successfully.");
    }

    public static void displayChangeLeaderMessage() {
        System.out.println("Tranfer Leader to this student...");
    }

    public static void displayStudentAcceedMaximun() {
        System.out.println("\nStudents in this assignment group already up to the limit.\n");
    }

    public static void displayCanNotChangeLeaderRoleMessage() {
        System.out.println("\nLeader's Role Change to be change.\nPlease change another Student role to tranfer Leader Role.\n");
    }
}
