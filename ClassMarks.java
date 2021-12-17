/*
 * This program reads both the students.txt and assignmets.txt files and 
 * writes the information in csv file in a table format
 *
 * By Zefeng Wang
 * Created on December 10, 2021
 *
*/

// import modules
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// class ClassMarks
class ClassMarks {
  public static void main(String[] args) throws IOException {
    
    // Reads information in both files and stores them each in their own separate arrays
    File dir = new File("/home/ubuntu/environment/files/Unit1-07");
    File[] files = dir.listFiles();
    List<String> stuList = new ArrayList<String>();
    BufferedReader inputStreamStu = new BufferedReader(new FileReader(files[1]));
    String line;
    while ((line = inputStreamStu.readLine()) != null) {
      stuList.add(line);
    }
    List<String> asList = new ArrayList<String>();
    BufferedReader inputStreamAssign = new BufferedReader(new FileReader(files[0]));
    while ((line = inputStreamAssign.readLine()) != null) {
      asList.add(line);
    }
    String[] studentArray =  Arrays.copyOf(stuList.toArray(), stuList.size(), String[].class);
    String[] assignmentsArray = Arrays.copyOf(asList.toArray(), asList.size(), String[].class);
 
    // Convert and display data in the  csv file
    List<String[]> csvData = generateMarks(studentArray, assignmentsArray);
    FileWriter csvWriter = new FileWriter("marks.csv");
    for (String[] arr : csvData) {
      for (String ele : arr) {
        csvWriter.write(ele + " ");
      }
      csvWriter.write("\n");
    }

    csvWriter.close();
  }

  public static List<String[]> generateMarks(String[] stuArr, String[] assignArr) {
    
    // Add "Student" header to beginning of the array
    String[] stuHeader = {"Student"};
    String[] header = new String[assignArr.length + 1];
    System.arraycopy(stuHeader, 0, header, 0, 1);
    System.arraycopy(assignArr, 0, header, 1, assignArr.length);
    List<String[]> list = new ArrayList<>();
    list.add(header);

    // Generates random marks with a mean of 75 and an SD of 10 for each student
    for (int row = 0; row < stuArr.length; row++) {
      String[] arr = new String[header.length];
      for (int col = 0; col < header.length; col++) {
        if (col == 0) {
          arr[col] = stuArr[row];
        } else {
          Random randomMarks = new Random();
          arr[col] = String.valueOf(randomMarks.nextGaussian() * 10 + 75);
        }
      }
      list.add(arr);
    }
    return list;
    
  }

}
