import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.LinkedTransferQueue;

public class AdventSolver {
    public static void main(String[] args) {

        ArrayList<String> fileData = getFileData("src/InputFile");
        // you now have a list of Strings from the file "InputFile"
        ArrayList<ArrayList<Integer>> intNums;
        intNums = new ArrayList<ArrayList<Integer>>();
        for (String num : fileData) {
            String [] nums = num.split(" ");
            ArrayList<Integer> toInt = new ArrayList<Integer>();
            for (String digit : nums){
                toInt.add(Integer.parseInt(digit));
            }
            intNums.add(toInt);
        }
        //Part 1:
        int safeCodes = 0;
        for (ArrayList<Integer> digits:intNums){
            if (check(digits)){
                safeCodes++;
            }
        }
        System.out.println(safeCodes);

        //Part2:
        safeCodes = 0;
        int safe =  0;
        int idx = -1;
        for (ArrayList<Integer> digits:intNums){
            if (check(digits)){
                safeCodes++;
            }
            else if (checkMinusOne(digits)){
                safeCodes++;
            }
        }
        System.out.println(safeCodes);
    }
    public static boolean checkMinusOne(ArrayList<Integer> digits){
        boolean safe = false;
        for (int i=0; i<digits.size();i++){
            int num = digits.get(i);
            ArrayList<Integer> test = new ArrayList<>();
            for (Integer x: digits){
                test.add(x);
            }
            test.remove(i);
            if (check(test)){
                safe = true;
            }
        }
        return safe;
    }
    public static boolean check(ArrayList<Integer> digits){
        boolean safe = true;
        ArrayList<Integer> diff = new ArrayList<Integer>();
        for (int i = 0; i<digits.size()-1;i++){
            int sub = digits.get(i)-digits.get(i+1);
            diff.add(sub);
        }
        if (!isPos(diff)&&!isNeg(diff)){
            return safe = false;
        }
        return isInRange(diff);
    }

    public static boolean isInRange(ArrayList<Integer>diff){
        for (int difference : diff){
            int abs = Math.abs(difference);
            if (abs<1){
                return false;
            }
            if (abs>3){
                return false;
            }
        }
        return true;
    }
    public static boolean isPos(ArrayList<Integer>diff){
        for (int num : diff){
            if (num <=0 ){
                return false;
            }
        }
        return true;
    }
    public static boolean isNeg(ArrayList<Integer>diff) {
        for (int num : diff){
            if (num>=0){
                return false;
            }
        }
        return true;
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