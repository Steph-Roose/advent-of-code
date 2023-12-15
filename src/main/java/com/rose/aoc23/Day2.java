package com.rose.aoc23;

import com.rose.helpers.Readers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
* Part I
* Which games are possible if the bag contains 12 red cubes, 13 green cubes, and 14 blue cubes?
*
* Part II
* What is the fewest number of cubes of each color that could have been in the bag to make the game possible?
* What is the power of a set of cubes per game?
* What is the sum of the power of these sets?
* */
public class Day2 {
    static List<Game> allGames= new ArrayList<>();
    static int countPossibleGamesIds;
    static int countPowerSets;

    public static void main(String[] args) {
        List<String> input = Readers.inputReader("src/main/resources/day_2_input.txt");

        allGames = input.stream().map(Day2::gameStringToObject).collect(Collectors.toList());

        allGames.stream().filter(Day2::checkCubesAmount).forEach(game -> countPossibleGamesIds += game.id);

        allGames.stream().map(Day2::findPowerOfCubes).forEach(i -> countPowerSets += i);

        System.out.println(countPossibleGamesIds);
        System.out.println(countPowerSets);
    }

    public static int findPowerOfCubes(Game game) {
        int red = 0;
        int green = 0;
        int blue = 0;

        for(Round round:game.rounds) {
            if(round.red > red) {
                red = round.red;
            }
            if(round.green > green) {
                green = round.green;
            }
            if(round.blue > blue) {
                blue = round.blue;
            }
        }
        return red*green*blue;
    }

    public static boolean checkCubesAmount(Game game) {
        for(Round round:game.rounds) {
            if(round.red > 12 | round.green > 13 | round.blue > 14) {
                return false;
            }
        }
        return true;
    }

    public static Game gameStringToObject(String game) {
        String[] parts = game.split(": ");
        int gameId = extractGameId(parts[0]);
        List<Round> rounds = extractRounds(parts[1]);

        return new Game(gameId, rounds);
    }

    public static int extractGameId(String game) {
        String[] parts = game.split(" ");
        return Integer.parseInt(parts[1]);
    }

    public static List<Round> extractRounds(String game) {
        List<Round> rounds = new ArrayList<>();

        String[] roundsData = game.split("; ");
        for(String roundData:roundsData) {
            String[] colours = roundData.split(", ");
            Round round = new Round();
            for(String colour:colours) {
                String[] parts = colour.split(" ");
                int count = Integer.parseInt(parts[0]);
                String colourName = parts[1];
                round.setColourCount(colourName, count);
            }
            rounds.add(round);
        }
        return rounds;
    }

}

class Game {
    int id;
    List<Round> rounds;

    public Game(int id, List<Round> rounds) {
        this.id = id;
        this.rounds = rounds;
    }
}

class Round {
    int red;
    int green;
    int blue;

    public void setColourCount(String colour, int count) {
        switch(colour) {
            case "red":
                this.red = count;
                break;
            case "green":
                this.green = count;
                break;
            case "blue":
                this.blue = count;
                break;
            default:
                break;
        }
    }
}
