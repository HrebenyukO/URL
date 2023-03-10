package Hilelli.Part1.Lesson15;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Train {
    public static void main(String[] args) throws IOException {
        //Создаем поток к нашему файлу с
        FileReader fileReader = new FileReader("C:\\Users\\38063\\Desktop\\URL adress.txt");

        // Создаем список доменов вызываея метод allDons
        List<String> domens = allDOns(fileReader);

        // Создаем мару, которая будет содержать String- наш домен, Integer- счетчик наших доменов
        Map<String, Integer> stringIntegerMap = new HashMap<>();

        //заполняем нашу мапу значениями где String наш ключ, а значением -кол-во повторений
        for (String domen : domens) {
            Integer count = stringIntegerMap.getOrDefault(domen, 0);
            stringIntegerMap.put(domen, count + 1);
        }
        // Создаем список пар (домен, количество повторений) и сортируем его в обратном порядке по убыванию
        List<Map.Entry<String, Integer>> domainCountList = new ArrayList<>(stringIntegerMap.entrySet());
        Collections.sort(domainCountList, (a, b) -> b.getValue().compareTo(a.getValue()));
        System.out.println(domainCountList.size());

        // Выводим топ 10 доменов
        for (int i = 0; i < 10; i++) {
            Map.Entry<String, Integer> domainCount = domainCountList.get(i);
            System.out.println(domainCount.getKey() + " - " + domainCount.getValue());
        }

    }

    static List<String> allDOns(FileReader fileReader) {
        List<String> allDomens = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String s;
            char simvol = '/';
            A:
            while (bufferedReader.ready()) {
                s = bufferedReader.readLine();
                B:
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == simvol) {
                        s = s.substring(0, i);
                        allDomens.add(s);
                        continue A;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
        return allDomens;
    }


}

