package com.rose.aoc23;

import com.rose.helpers.Readers;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Part I
* 1. Remove all letters
* 2. Find the first digit
* 3. Find the second digit
* 4. Combine the digits to form a single two-digit number
* 5. Get the sum of all calibration values
*
* Part II
* 1. Find the written digits
* 2. Replace written digits with numbers
* 3. Follow steps from part I
* */
public class Day1 {

    static Integer sumValuesPt1 = 0;
    static Integer sumValuesPt2 = 0;

    public static void main(String[] args) {
        List<String> input = Readers.inputReader("src/main/resources/day_1_input.txt");

        // Part I
        input.stream().map(Day1::getTwoDigitNumber).toList().forEach(Day1::getSumValuesPt1);
        System.out.println(sumValuesPt1);

        // Part II
        input.stream().map(Day1::replaceWrittenDigit).map(Day1::getTwoDigitNumber).toList().forEach(Day1::getSumValuesPt2);
        System.out.println(sumValuesPt2);
    }

    public static String getTwoDigitNumber(String value) {
        char[] charArray = removeLetters(value).toCharArray();
        return "" + charArray[0] + charArray[charArray.length-1];
    }

    public static void getSumValuesPt1(String value) {
        sumValuesPt1 += Integer.parseInt(value);
    }

    public static void getSumValuesPt2(String value) {
        sumValuesPt2 += Integer.parseInt(value);
    }

    public static String removeLetters(String value) {
        return value.replaceAll("[^0-9]", "");
    }

    public static String matchWrittenDigit(String value) {
        return switch(value) {
            case "oneight" -> "18";
            case "twone" -> "21";
            case "eightwo" -> "82";
            case "one" -> "1";
            case "two" -> "2";
            case "three" -> "3";
            case "four" -> "4";
            case "five" -> "5";
            case "six" -> "6";
            case "seven" -> "7";
            case "eight" -> "8";
            default -> "9";
        };
    }

    public static String replaceWrittenDigit(String value) {
        Pattern pattern = Pattern.compile("oneight|twone|eightwo|one|two|three|four|five|six|seven|eight|nine");
        Matcher matcher = pattern.matcher(value);
        StringBuilder result = new StringBuilder();

        while(matcher.find()) {
            String replacement = matchWrittenDigit(matcher.group().toLowerCase());

            if(replacement != null) {
                matcher.appendReplacement(result, replacement);
            }
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
