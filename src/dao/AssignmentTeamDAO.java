package dao;

import adt.*;
import entity.AssignmentTeam;
import java.io.*;

/**
 *
 * @author Loh Jian Wei (Source: ECBDemo, author Kathleen Tan Swee Neo)
 */
public class AssignmentTeamDAO implements Serializable {

    private String fileName = "assignmentTeams.dat";

    public void saveToFile(SortedListInterface<AssignmentTeam> assignmentTeamList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(assignmentTeamList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
            System.out.println(ex.getMessage());
        }
    }

    public SortedListInterface<AssignmentTeam> retrieveFromFile() {
        File file = new File(fileName);
        SortedListInterface<AssignmentTeam> assignmentTeamList = new SortedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            assignmentTeamList = (SortedList<AssignmentTeam>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
            System.out.println(ex.getMessage());
        } finally {
            return assignmentTeamList;
        }
    }
}
