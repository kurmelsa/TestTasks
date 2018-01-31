package com.company;
import java.util.*;
import java.io.*;

/*
Аня недавно узнала, что такое квадратная матрица размерности n. Это таблица n×n с целыми числами в ячейках.
Число, стоящее на пересечении i-ой строки и j-ого столбца матрицы A, кратко обозначается A[i, j].
Матрицы можно умножать, и Аня быстро освоила, как запрограммировать эту операцию с помощью циклов.
Результатом умножения двух матриц A и B будет матрица C, элементы которой определяются следующим образом:

Матрицы ей понадобились для конкретной задачи, в которой надо узнать определенный элемент произведения нескольких матриц.
Это уже достаточно сложная задача для Ани, но она усложняется тем, что все вычисления ведутся по модулю некоторого простого числа p,
то есть если при арифметических операциях получается число, большее, либо равное p, оно заменяется на остаток при делении на p.

Помогите Ане вычислить нужный ей элемент.

Входные данные
В первой строчке входного файла INPUT.TXT стоят два числа: m - количество матриц, n - размер каждой из матриц (1 ≤ m ≤ 130, 1 ≤ n ≤ 130).
В следующей строчке содержатся номер строки и столбца, интересующего Аню элемента 1 ≤ a ≤ n, 1 ≤ b ≤ n. В третьей строке содержится
простое число p ≤ 1000. Далее следует описание m матриц. Описание каждой матрицы состоит из n строк. В каждой из строк содержится n
неотрицательных целых чисел, меньших p. Соседние числа в строке разделены пробелом, а перед каждой матрицей пропущена строка.

Выходные данные
В выходной файл OUTPUT.TXT выведите нужный Ане элемент произведения матриц.
 */

public class Main {
    public static void main(String[] argv) throws IOException {
        new Main().run();
    }

    PrintWriter pw;
    Scanner sc;

    public void run() throws IOException {
        try {

            sc = new Scanner(new File("INPUT.TXT"));

            final int countMatrix = sc.nextInt();
            final int sizeMatrix = sc.nextInt();
            sc.nextLine();
            final int numberStr = sc.nextInt() - 1;
            final int numberColumn = sc.nextInt() - 1;
            sc.nextLine();

            final int p = sc.nextInt();

            sc.nextLine();
            sc.nextLine();
            int matrix1 [][] = new int[sizeMatrix][sizeMatrix];

            for (int i = 0; i < sizeMatrix;) {
                String string = sc.nextLine();
                if (string.equals("")) {
                    i = 0;
                    continue;
                }
                matrix1[i] = Arrays.stream(string.split(" ")).mapToInt(Integer::parseInt).toArray();
                i++;
            }

            /*
            for (int i =0; i < sizeMatrix; i++){
                for (int j =0; j < sizeMatrix; j++){
                    matrix1 [i][j] = sc.nextInt();
                }
            }
            */

            int matrix1Rows[] = matrix1[numberStr];

            int matrix2[][] = new int[sizeMatrix][sizeMatrix];

            for (int k = 2; k <= countMatrix; k++) {

                for (int i = 0; i < sizeMatrix; ) {
                    String line = sc.nextLine();
                    if (line.equals("")) {
                        i = 0;
                        continue;
                    }
                    matrix2[i] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                    i++;
                }

               /*
                for (int i = 0; i < sizeMatrix; i++) {
                    for (int j = 0; j < sizeMatrix; j++) {
                        matrix2[i][j] = sc.nextInt();

                    }
                }
                */

                int matrix2Modific[][] = new int[sizeMatrix][sizeMatrix];
                for (int i = 0; i < sizeMatrix; i++) {
                    for (int j = 0; j < sizeMatrix; j++) {
                        matrix2Modific[j][i] = matrix2[i][j];

                    }
                }

                int matrixMultiplic[] = new int[sizeMatrix];
                for (int j = 0; j < sizeMatrix; j++) {

                    int matrix2Column[] =  matrix2Modific[j];

                    int sum = 0;
                    for (int m = 0; m < sizeMatrix; m++) {
                        sum+= matrix1Rows[m]*matrix2Column[m];
                    }

                    if (sum >= p){
                        matrixMultiplic[j]= sum%p;
                    }
                    else{
                        matrixMultiplic[j]= sum;
                    }
                }

                matrix1Rows = matrixMultiplic;
            }

            pw = new PrintWriter(new File("OUTPUT.TXT"));
            pw.print(matrix1Rows[numberColumn]);
            pw.close();

        }

        catch(Exception e){
            System.out.println("Ошибка исходных данных!");
        }
    }
}

