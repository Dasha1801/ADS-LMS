package by.it.group351051.stepanov.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        int n = one.length();
        int m = two.length();
        int[][] dp = new int[n + 1][m + 1];
        StringBuilder[][] operations = new StringBuilder[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                operations[i][j] = new StringBuilder();
                if (i == 0) {
                    dp[i][j] = j;
                    if (j > 0) {
                        operations[i][j].append(operations[i][j - 1]).append("+" + two.charAt(j - 1) + ",");
                    }
                } else if (j == 0) {
                    dp[i][j] = i;
                    operations[i][j].append(operations[i - 1][j]).append("-" + one.charAt(i - 1) + ",");
                } else {
                    int cost = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
                    if (dp[i - 1][j] + 1 <= dp[i][j - 1] + 1 && dp[i - 1][j] + 1 <= dp[i - 1][j - 1] + cost) {
                        dp[i][j] = dp[i - 1][j] + 1;
                        operations[i][j].append(operations[i - 1][j]).append("-" + one.charAt(i - 1) + ",");
                    } else if (dp[i][j - 1] + 1 <= dp[i - 1][j] + 1 && dp[i][j - 1] + 1 <= dp[i - 1][j - 1] + cost) {
                        dp[i][j] = dp[i][j - 1] + 1;
                        operations[i][j].append(operations[i][j - 1]).append("+" + two.charAt(j - 1) + ",");
                    } else {
                        dp[i][j] = dp[i - 1][j - 1] + cost;
                        if (cost == 0) {
                            operations[i][j].append(operations[i - 1][j - 1]).append("#,");
                        } else {
                            operations[i][j].append(operations[i - 1][j - 1]).append("~" + two.charAt(j - 1) + ",");
                        }
                    }
                }
            }
        }

        return operations[n][m].toString();
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/stepanov/lesson07/dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
    }

}
