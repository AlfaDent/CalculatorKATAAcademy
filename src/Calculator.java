import java.util.*;

public class Calculator {

    public static String[] str;
    public static Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
    public static int x1=0;
    public static int x2=0;
    public static int isRome = 0;
    public static int result;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        calc(scanner.nextLine());
    }

    public static String calc(String input){

        map.put(1,"I");     map.put(8,"VIII");
        map.put(2,"II");    map.put(9,"IX");
        map.put(3,"III");   map.put(10,"X");
        map.put(4,"IV");    map.put(40,"XL");
        map.put(5,"V");     map.put(50,"L");
        map.put(6,"VI");    map.put(90,"XC");
        map.put(7,"VII");   map.put(100,"C");

        str = input.split(" ");
        if (str.length<3) throw new RuntimeException("строка не является математической операцией");

        assignedValues();
        mathOperationChoose();

        if (isRome == 2) {
            System.out.println(convertToRome(result,map)); return convertToRome(result, map);}
        else {
            System.out.println(result); return String.valueOf(result);}
    }

    public static void assignedValues() {

        if (str[0].matches("[-+]?\\d+")) {
            x1 = Integer.parseInt(str[0]);
        }
        else if (map.containsValue(str[0]))
        {
            isRome++;
            x1 = map.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(str[0]))
                    .findFirst().map(Map.Entry::getKey).get();
        } else throw new RuntimeException("не соответствующий формат арабских или римских цифр");

        if (str[2].matches("[-+]?\\d+")) {
            x2 = Integer.parseInt(str[2]);
        }
        else if (map.containsValue(str[2]))
        {
            isRome++;
            x2 = map.entrySet().stream()
                    .filter(entry -> entry.getValue().equals(str[2]))
                    .findFirst().map(Map.Entry::getKey).get();
        } else throw new RuntimeException("не соответствующий формат арабских или римских цифр");

        if (isRome == 1) throw new RuntimeException("используются одновременно разные системы счисления");

    }

    public static void mathOperationChoose(){
        if (str.length == 3) {
            switch (str[1]) {
                case "+" -> result = x1 + x2;
                case "-" -> {
                    result = x1 - x2;
                    if (isRome == 2 && result < 1)
                        throw new RuntimeException("в римской системе нет отрицательных чисел");
                }
                case "*" -> result = x1 * x2;
                case "/" -> result = x1 / x2;
                default -> throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        } else throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
    }

    public static String convertToRome(int arabNum, Map<Integer, String> dictionary){
        StringBuilder romeNum = new StringBuilder();
        while (arabNum != 0) {
            for (Map.Entry<Integer, String> entry : dictionary.entrySet()) {
                while (arabNum / entry.getKey() > 0) {
                    arabNum -= entry.getKey();
                    romeNum.append(entry.getValue());
                }
            }
        }
        return romeNum.toString();
    }
}
