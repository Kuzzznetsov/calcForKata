import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        isMathOperation(input);
        isDigetSame(input);
        isRomanOrArabic(input);
    }

    public static boolean isArabicCorrectInput(String str) { //проверяет выражение на корректность арабского ввода
        String[] string = str.split(" ");
        if (str.matches("(\\d|10) [-+*/] (\\d|10)")) {
            return true;
        }
        return false;
    }

    public static boolean isRomanCorrectInput(String str) { //проверка выражения на корректность Римского ввода
        String[] string = str.split(" ");
        if (isCorrectRomanDiget(string[0]) && isCorrectRomanDiget(string[2])
                && string[1].matches("[-+*/]")) {
            return true;
        }
        return false;
    }

    public static boolean isCorrectRomanDiget(String str) { // проверяет является ли цифра римской?
        ArrayList<String> RomanDiget = new ArrayList<>();
        RomanDiget.add("I");
        RomanDiget.add("II");
        RomanDiget.add("III");
        RomanDiget.add("IV");
        RomanDiget.add("V");
        RomanDiget.add("VI");
        RomanDiget.add("VII");
        RomanDiget.add("VIII");
        RomanDiget.add("IX");
        RomanDiget.add("X");
        if (RomanDiget.contains(str)) {
            return true;
        }
        return false;
    }

    public static boolean isDigetSame(String str) { // приверяет раздные системы исчисления
        String[] string = str.split(" ");
        if (isCorrectRomanDiget(string[0]) && string[2].matches("(\\d|10)")
                && string[1].matches("[-+*/]")) {
            throw new Exception("используются одновременно разные системы счисления");
        }
        if (isCorrectRomanDiget(string[2]) && string[0].matches("(\\d|10)")
                && string[1].matches("[-+*/]")) {
            throw new Exception("используются одновременно разные системы счисления");
        }
        return true;
    }

    //строка не является математической операцией
    public static void isMathOperation(String str) {
        String[] string = str.split(" ");
        isMoreOfTwoDijit(str);
        if(string.length != 3){
            throw new Exception("строка не является математической операцией");
        }
        if (!(string[0].matches("(\\d|10)")
                && string[1].matches("[-+*/]") && string[2].matches("(\\d|10)"))
        && !(isCorrectRomanDiget(string[0])
                && string[1].matches("[-+*/]")
                && isCorrectRomanDiget(string[2]))) {
            isDigetSame(str);
            throw new Exception("строка не является математической операцией");
        }
    }
    //формат математической операции не удовлетворяет заданию - два операнда и один оператор
    public static void isMoreOfTwoDijit(String str){
        String[] string = str.split(" ");
        int count = 0;
        for (int i = 0; i < string.length; i++) {
           if(string[i].matches("[-+*/]")){
              count++;
           }
        }
        for (int i = 0; i < string.length; i++) {
           if(!(string[i].matches("(\\d|10)")
                   || isCorrectRomanDiget(string[i]) || string[i].matches("[-+*/]"))){
               return;
           }
        }
        if(count>1){
            throw new Exception("формат математической операции не удовлетворяет" +
                    " заданию - два операнда и один оператор");
        }
    }
    public static void isRomanOrArabic(String str){
        if(isRomanCorrectInput(str)){
            calkRoman(str);
        }
        if(isArabicCorrectInput(str)){
            calkArab(str);
        }
    }
    public static void calkArab(String str){
        String[] string = str.split(" ");
        int a = Integer.parseInt(string[0]);
        int b = Integer.parseInt(string[2]);
        int sum = calk(a, b, string[1]);
        System.out.println(sum);
    }
    public static void calkRoman(String str){
       String[] string = str.split(" ");
       int a = convertRomanToArab(string[0]);
       int b = convertRomanToArab(string[2]);
       int sum = calk(a, b, string[1]);
       if(sum<0){
           throw new Exception("в римской системе нет отрицательных чисел");
       }
       System.out.println(convertArabicToRoman(sum));
    }
    public static int calk (int a, int b, String symbol ){
        int sum = 0;
        if(symbol.equals("+")){
            sum = a + b;
        }
        if(symbol.equals("-")){
            sum = a - b;
        }
        if(symbol.equals("*")){
            sum = a * b;
        }
        if (symbol.equals("/")){
            if (b != 0){
                sum = a / b;
            } else {
                throw new Exception("На ноль делить нельзя");
            }
        }
        return sum;
    }
    public static int convertRomanToArab(String str){
        int answer = 0;
        switch (str){
            case "I":
             answer = 1;
             break;
            case "II":
                answer = 2;
                break;
            case "III":
                answer = 3;
                break;
            case "IV":
                answer = 4;
                break;
            case "V":
                answer = 5;
                break;
            case "VI":
                answer = 6;
                break;
            case "VII":
                answer = 7;
                break;
            case "VIII":
                answer = 8;
                break;
            case "IX":
                answer = 9;
                break;
            case "X":
                answer = 10;
                break;
        }
        return answer;
    }
    public static String convertArabicToRoman(int input) {
        String s = "";
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }
}