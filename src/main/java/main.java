

import org.apache.maven.shared.utils.StringUtils;

import java.util.Scanner;


public class main {
    public static void main(String[] args) {
        System.out.println("введите выражение, например (2+2)");
        Scanner scanner = new Scanner(System.in);

        String rawString = scanner.nextLine();
        String rawOutSpace = rawString.replaceAll(" ", "");
        String numStr1;
        String numStr2;
        try {
            numStr1 = rawOutSpace.substring(0,indAction(rawOutSpace));
            numStr2 = rawOutSpace.substring(indAction(rawOutSpace)+1);
            presenceOfAction(rawOutSpace);
            actionPositionCheck(rawOutSpace);
            if (isLatinNumbers(numStr1) && isLatinNumbers(numStr2)){
                int numElement1 = Integer.parseInt(numStr1);
                int numElement2 = Integer.parseInt(numStr2);
                System.out.println(calculation(numElement1,numElement2,rawOutSpace));
            }else {
                int numElement1 = isRomanNumbers(numStr1);
                int numElement2 = isRomanNumbers(numStr2);
                int result = calculation(numElement1,numElement2,rawOutSpace);
                DataConversion dataConversion = new DataConversion();
                System.out.println(dataConversion.latinToRoman(result));
            }
        } catch (IncorrectDataException|NullPointerException e) {
            System.out.println("Введены некорректные данные!");
        }
        scanner.close();

    }
    private static int calculation (int firstElement, int secondElement, String rawData) throws IncorrectDataException {
        int result = 0;
        if ((0 < firstElement) && (firstElement < 11) && (0 < secondElement) && (secondElement < 11)){

            if (isAction(rawData).equals("+")){
                Operations operations = Operations.SUM;
                result = operations.action(firstElement,secondElement);
            }
            if (isAction(rawData).equals("-")){
                Operations operations = Operations.SUBTRACT;
                result = operations.action(firstElement,secondElement);
            }
            if (isAction(rawData).equals("*")){
                Operations operations = Operations.MULTIPLY;
                result = operations.action(firstElement,secondElement);
            }
            if (isAction(rawData).equals("/")){
                Operations operations = Operations.DIVISION;
                result = operations.action(firstElement,secondElement);
            }
        }
        else throw new IncorrectDataException("Введены некорректные данные!");
        return result;
    }

    private static int isRomanNumbers(String data) throws IncorrectDataException {
        int result = 0;
        data = data.toUpperCase();
        String[] romanNumbers = new String[] {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (int i = 0; i < romanNumbers.length; i++){
            if (data.equals(romanNumbers[i]))
                result = i + 1;
        }
        if (result == 0)
            throw new IncorrectDataException("Введены некорректные данные!");
        return result;
    }

    private static boolean isLatinNumbers(String data) {
        boolean result = true;
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }

    private static int indexAction(String rawData){
        return rawData.indexOf(isAction(rawData));
    }

    private static void actionPositionCheck(String rawData) throws IncorrectDataException {
        int indexAction = indexAction(rawData);
        if (indexAction == 0 || indexAction == rawData.length() - 1)
            throw  new IncorrectDataException("Введены некорректные данные!");
    }

    private static void presenceOfAction(String rawData) throws IncorrectDataException {
        int numberActions = 0;
        numberActions += StringUtils.countMatches(rawData, ("+"));
        numberActions += StringUtils.countMatches(rawData, ("-"));
        numberActions += StringUtils.countMatches(rawData, ("*"));
        numberActions += StringUtils.countMatches(rawData, ("/"));
        if (numberActions != 1)
            throw new IncorrectDataException("Ввели неверные данные!");
    }

    private static int indAction(String rawData){
        return rawData.indexOf(isAction(rawData));
    }

    private static String isAction(String rawData) {
        String op = null;
        if (rawData.contains("+"))
            op = "+";
        if (rawData.contains("-"))
            op = "-";
        if (rawData.contains("*"))
            op = "*";
        if (rawData.contains("/"))
            op = "/";
        return op;
    }
}
