import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Main {
    public static void main(String[] args) {
        System.out.print("введите что-нибудь ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int sch=0;
        for (char el:input.toCharArray()){
            if (el=='\"')sch++;
        }
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher = pattern.matcher(input);
        String sa = null;
        if (matcher.find()) {
            sa = matcher.group(1);
        }
        if (sa.length()>10){throw new RuntimeException("слишком длинная строка");}
        if (sch==4) {
            String sb = null;
            while (matcher.find()) {
                sb = matcher.group(1);
            }
            if (sb.length()>10){throw new RuntimeException("слишком длинная строка");}
            input=clean(input,sa);
            char sop = input.charAt(0);
            System.out.println("\"" + sans(sa, sb, sop) + "\"");
        }else if (sch==2){
            input=clean(input,sa);
            char sop = input.charAt(0);
            String sb=input.substring(1);
            if (dans(sa,sb,sop).length()>40){
                String big=dans(sa,sb,sop).substring(0,40);
                System.out.println("\"" + big + "...\"");
            }else System.out.println("\"" + dans(sa, sb, sop) + "\"");
        }else throw new RuntimeException("недопустимая операция");
    }
    static String sans(String sa, String sb, char sop) {
        switch (sop) {
            case '+':
                return sa + sb;
            case '-':
                return sa.replace(sb, "");
            default:
                throw new RuntimeException("недопустимая операция");
        }
    }
    static String dans(String sa, String sb, char sop) {
        switch (sop) {
            case '*':
                int b=Integer.parseInt(sb);
                if ((b<1)||(b>10)){
                    throw new RuntimeException("можно вводить числа от 1 до 10");}
                return sa.repeat(b);
            case '/':
                int c=Integer.parseInt(sb);
                if ((c<1)||(c>10)){
                    throw new RuntimeException("можно вводить числа от 1 до 10");}
                return sa.substring(0,(sa.length())/c);
            default:
                throw new RuntimeException("недопустимая операция");
        }
    }
    static String clean(String input,String sa){
        input = input.replace(sa, "");
        input = input.replace(" ", "");
        input = input.replace("\"", "");
        return input;
    }
}