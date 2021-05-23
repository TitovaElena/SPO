import java.io.*;
import java.util.*;

public class lex {

    private static List<String> dataType = Arrays.asList("int", "long", "float", "double", "byte", "char", "boolean", "short");
    private static List<String> assignmentOperation = Arrays.asList("=");
    private static List<String> mathOperations = Arrays.asList("+", "-", "*", "/");
    private static List<String> comparisonOperations = Arrays.asList("<", ">", "==", "!=", "<=", ">=");
    private static List<String> loop = Arrays.asList("while");
    private static List<String> restraints = Arrays.asList("{", "(", ")", "}", ";");
    private static List<String> name = new ArrayList<>();
    private static int flag = 0;

    public static List<List<String>> fileReader() {

        String line;
        List<List<String>> listLexemes = new ArrayList<>();

        try {
            File file = new File("C:\\kod\\input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            while ((line = reader.readLine()) != null) {
                listLexemes.add(new ArrayList(Arrays.asList(line.split("\\s|\\t"))));
            }
            for (int i = 0; i < listLexemes.size(); i++) {
                listLexemes.get(i).removeAll(Arrays.asList("", null));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listLexemes;
    }

    //инициализация
    public static void initialization(List<List<String>> listLexemes, int i, int z) {
        System.out.println("ТИП_ДАННЫХ " + listLexemes.get(i).get(0));
        if (listLexemes.get(i).get(1).matches("[a-zA-Z]+")) {
            name.add(listLexemes.get(i).get(1));
            System.out.println("ИМЯ_ПЕРЕМЕННОЙ " + listLexemes.get(i).get(1));
            assignment(listLexemes, i, 2, z);
        } else {
            System.out.println("Ошибка при инициализации переменной в " + (i + 1) + " строке");
            System.exit(0);
        }
    }

    //присваивание
    public static void assignment(List<List<String>> listLexemes, int i, int k, int z) {
        if (assignmentOperation.contains(listLexemes.get(i).get(k))) {
            System.out.println("ПРИСВАИВАНИЕ " + assignmentOperation.get(0));
            if (listLexemes.get(i).size() > 4) {
                for (int n = k + 1; n < z; n += 2) {
                    if (name.contains(listLexemes.get(i).get(n)) ||
                            listLexemes.get(i).get(n).matches("[0-9]+")) {
                        System.out.println("ЗНАЧЕНИЕ " + listLexemes.get(i).get(n));
                    } else {
                        System.out.println("Ошибка при инициализации переменной в " + (i + 1) + " строке");
                        System.exit(0);
                    }
                    if (mathOperations.contains(listLexemes.get(i).get(n + 1))) {
                        System.out.println("МАТЕМАТИЧЕСКАЯ_ОПЕРАЦИЯ " + listLexemes.get(i).get(n + 1));
                    } else {
                        System.out.println("Ошибка при инициализации переменной в " + (i + 1) + " строке");
                        System.exit(0);
                    }
                }
                if (name.contains(listLexemes.get(i).get(z).substring(0, listLexemes.get(i).get(z).length() - 1)) ||
                        listLexemes.get(i).get(z).substring(0, listLexemes.get(i).get(z).length() - 1).matches("[0-9]+")) {
                    System.out.println("ЗНАЧЕНИЕ " + listLexemes.get(i).get(z)
                            .substring(0, listLexemes.get(i).get(z).length() - 1));
                } else {
                    System.out.println("Ошибка при инициализации переменной в " + (i + 1) + " строке");
                    System.exit(0);
                }
                System.out.println("ТОЧКА_С_ЗАПЯТОЙ " + restraints.get(4));
            } else {
                if (name.contains(listLexemes.get(i).get(3).substring(0, listLexemes.get(i).get(3).length() - 1)) ||
                        listLexemes.get(i).get(3).substring(0, listLexemes.get(i).get(3).length() - 1).matches("[0-9]+")) {
                    System.out.println("ЗНАЧЕНИЕ " + listLexemes.get(i).get(3)
                            .substring(0, listLexemes.get(i).get(3).length() - 1));
                } else {
                    System.out.println("Ошибка при инициализации переменной в " + (i + 1) + " строке");
                    System.exit(0);
                }
                if (listLexemes.get(i).get(3)
                        .substring(listLexemes.get(i).get(3).length() - 1, listLexemes.get(i).get(3).length())
                        .equals(restraints.get(4))) {
                    System.out.println("ТОЧКА_С_ЗАПЯТОЙ " + restraints.get(4));
                }
            }
        } else {
            System.out.println("Ошибка при инициализации переменной в " + (i + 1) + " строке");
            System.exit(0);
        }
    }

    //переопределение
    public static void redefinition(List<List<String>> listLexemes, int i, int z) {
        System.out.println("ИМЯ_ПЕРЕМЕННОЙ " + listLexemes.get(i).get(0));
        assignment(listLexemes, i, 1, z);
    }

    //цикл
    public static void loop(List<List<String>> listLexemes, int i, int z) {
        System.out.println("ЦИКЛ " + loop.get(0));
        if (listLexemes.get(i).get(1).substring(0, 1).equals(restraints.get(1))) {
            System.out.println("ОТКРЫВАЮЩАЯ_СКОБКА " + restraints.get(1));
            listLexemes.get(i).set(1, listLexemes.get(i).get(1).substring(1, listLexemes.get(i).get(1).length()));
            int j = 1;
            for (; !comparisonOperations.contains(listLexemes.get(i).get(j)); j++) {
                if (name.contains(listLexemes.get(i).get(j)) ||
                        listLexemes.get(i).get(j).matches("[0-9]+")) {
                    System.out.println("ЗНАЧЕНИЕ " + listLexemes.get(i).get(j));
                    if (mathOperations.contains(listLexemes.get(i).get(j + 1))) {
                        System.out.println("МАТЕМАТИЧЕСКАЯ_ОПЕРАЦИЯ " + listLexemes.get(i).get(j+1));
                        j++;
                        continue;
                    }
                    continue;
                }else{
                    System.out.println("Ошибка в условии в " + (i + 1) + " строке");
                    System.exit(0);
                }

            }

            if (comparisonOperations.contains(listLexemes.get(i).get(j))) {
                System.out.println("ОПЕРАЦИЯ_СРАВНЕНИЯ " + listLexemes.get(i).get(j));
                j++;
            }
            for (; !listLexemes.get(i).get(j)
                    .substring(listLexemes.get(i).get(j).length() - 1, listLexemes.get(i).get(j).length())
                    .equals(restraints.get(2)); j++) {
                if (name.contains(listLexemes.get(i).get(j)) ||
                        listLexemes.get(i).get(j)
                                .matches("[0-9]+")) {
                    System.out.println("ЗНАЧЕНИЕ " + listLexemes.get(i).get(j));
                    if (mathOperations.contains(listLexemes.get(i).get(j+1))) {
                        System.out.println("МАТЕМАТИЧЕСКАЯ_ОПЕРАЦИЯ " + listLexemes.get(i).get(j+1));
                        j++;
                        continue;
                    }
                    continue;
                }else{
                    System.out.println("Ошибка в условии в " + (i + 1) + " строке");
                    System.exit(0);
                }

            }
            if (name.contains(listLexemes.get(i).get(j).substring(0, listLexemes.get(i).get(j).length() - 1)) ||
                    listLexemes.get(i).get(j)
                            .substring(0, listLexemes.get(i).get(j).length() - 1).matches("[0-9]+")) {
                System.out.println("ЗНАЧЕНИЕ " + listLexemes.get(i).get(j)
                        .substring(0, listLexemes.get(i).get(j).length() - 1));
            }else{
                System.out.println("Ошибка в условии в " + (i + 1) + " строке");
                System.exit(0);
            }
            if (listLexemes.get(i).get(j)
                    .substring(listLexemes.get(i).get(j).length() - 1, listLexemes.get(i).get(j).length())
                    .equals(restraints.get(2))) {
                System.out.println("ЗАКРЫВАЮЩАЯ_СКОБКА " + restraints.get(2));
                try {
                    System.out.println("ОТКРЫВАЮЩАЯ_СКОБКА " + listLexemes.get(i).get(j + 1));
                    flag++;
                } catch (Exception e) {
                    System.out.println("Отсутствует { в " + (i + 1) + " строке");
                    System.exit(0);
                }
            } else {
                System.out.println("Отсутствует ) в " + (i + 1) + " строке");
                System.exit(0);
            }
        } else {
            System.out.println("Ошибка в условии в " + (i + 1) + " строке");
            System.exit(0);
        }

    }

    public static void lexicalAnalyzer(List<List<String>> listLexemes) {
        int i = 0;
        //комментарии
        for (; i < listLexemes.size(); i++) {
            int z = listLexemes.get(i).size() - 1;
            for (int j = 0; j < listLexemes.get(i).size(); j++) {
                try {
                    if (listLexemes.get(i).get(j).substring(0, 2).equals("//")) {
                        z = j - 1;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            if (dataType.contains(listLexemes.get(i).get(0))) {
                initialization(listLexemes, i, z);
            } else if (name.contains(listLexemes.get(i).get(0))) {
                redefinition(listLexemes, i, z);
            } else if (loop.contains(listLexemes.get(i).get(0))) {
                loop(listLexemes, i, z);
            } else if (listLexemes.get(i).get(0).equals(restraints.get(3))) {
                System.out.println("ЗАКРЫВАЮЩАЯ_СКОБКА " + restraints.get(3));
                flag--;
            }else{
                System.out.println("Ошибка при инициализации в " + (i + 1) + " строке");
                System.exit(0);
            }
        }
        if (flag != 0) {
            System.out.println("Отсутствует } в " + (i + 1) + " строке");
        }
    }

    public static void main(String[] args) {
        lexicalAnalyzer(fileReader());
    }
}
