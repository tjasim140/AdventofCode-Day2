import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdventSolver {
    public static boolean isIncreasing(String num){
       boolean isIncreasing = true;
        for (int i = 0; i<num.length();i++) {
            if (num.charAt(i+1)<=num.charAt(i)) {
                isIncreasing = false;
            }
        }
        return isIncreasing;
    }

    public static boolean isDecreasing(String num){
        boolean isDecreasing = true;
        for (int i = 0; i<num.length();i++) {
            if (num.charAt(i)<=num.charAt(i+1)) {
                isDecreasing = false;
            }
        }
        return isDecreasing;
    }
    public static void main(String[] args) {
        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"

        for (int i = 0; i< fileData.size();i++){
            String num = fileData.get(i);
            String isSafe ="Unsafe";
            if (isIncreasing(num)){
                for (int j = 0;j<num.length()-1;j++){
                    isSafe = "Unsafe";
                    int first = Integer.parseInt(num.substring(j+1));
                    int second = Integer.parseInt(num.substring(j));
                    int difference = first-second;
                    if (difference>=1&&difference<=3){
                        isSafe="Safe";
                    }
                }
            } else if (isDecreasing(num)) {
                for (int j = 0; j<num.length()-1;j++){
                    isSafe = "Unsafe";
                    int first = Integer.parseInt(num.substring(j));
                    int second = Integer.parseInt(num.substring(j+1));
                    int difference = first-second;
                    if (difference>=1&&difference<=3){
                        isSafe="Safe";
                    }
                }
            }
            else{
                isSafe = "Unsafe";
            }
            System.out.println(isSafe);
        }

    }


    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }
}