import java.util.*;

public class Calculator {
    public static void main(String[] args) {

        Map<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
        map.put(1,"I");
        map.put(2,"II");
        map.put(3,"III");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(6,"VI");
        map.put(7,"VII");
        map.put(8,"VIII");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(90,"XC");
        map.put(100,"C");


        int x1=0;
        int x2=0;
        String s = " ";
        String[] str;
        int res = 0;
        StringBuilder rimRes = new StringBuilder();
        int isRim = 0;


        Scanner scanner = new Scanner(System.in);

        s = scanner.nextLine();
        str = s.split(" ");

        try {

            if (str[0].matches("[-+]?\\d+")) x1 = Integer.parseInt(str[0]);
            else {
                isRim++;
                x1 = map.entrySet().stream().filter(entry -> entry.getValue().equals(str[0])).findFirst().map(Map.Entry::getKey).get();
            }
            s = str[1];
            if (str[2].matches("[-+]?\\d+")) x2 = Integer.parseInt(str[2]);
            else {
                isRim++;
                x2 = map.entrySet().stream().filter(entry -> entry.getValue().equals(str[2])).findFirst().map(Map.Entry::getKey).get();
            }
        } catch (Exception e) {
            throw new RuntimeException("строка не является математической операцией");
        }

        if (isRim==1) throw new RuntimeException("Используются одновременно разные системы счисления");
        if (str.length>2) throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

            switch (s) {
                case "+" -> res = x1 + x2;
                case "-" -> {res = x1 - x2; if (isRim ==2 && res<1) throw new RuntimeException("в римской системе нет отрицательных чисел");}
                case "*" -> res = x1 * x2;
                case "/" -> res = x1 % x2;
                default -> throw new RuntimeException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }

        if(isRim==2) {
            while (res != 0) {
                for (Map.Entry<Integer, String> entry : map.entrySet()) {
                    while (res / entry.getKey() > 0) {
                        res -= entry.getKey();
                        rimRes.append(entry.getValue());
                    }
                }
            }
            System.out.println(rimRes.toString());
        } else System.out.println(res);
    }

}
