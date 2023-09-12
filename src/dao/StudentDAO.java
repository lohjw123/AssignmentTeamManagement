package dao;

import adt.*;
import entity.AssignmentTeamStudent;
import java.io.*;

/**
 *
 * @author Loh Jian Wei (Source: ECBDemo, author Kathleen Tan Swee Neo)
 */
public class StudentDAO implements Serializable {

    private String fileName = "student.dat";

    public void saveToFile(SortedListInterface<AssignmentTeamStudent> studentList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(studentList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public SortedListInterface<AssignmentTeamStudent> retrieveFromFile() {
        File file = new File(fileName);
        SortedListInterface<AssignmentTeamStudent> assignmentTeamList = new SortedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            assignmentTeamList = (SortedList<AssignmentTeamStudent>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return assignmentTeamList;
        }
    }
}
