package com.vladislavlevchik;

import com.vladislavlevchik.services.PrintContextMenuService;

import java.util.*;

public class Launcher {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            PrintContextMenuService.startContextMenu();
            switch (scanner.nextLine()) {
                case "1" -> startSimulation();
                case "2" -> System.exit(0);
                default -> System.out.println("Некорректный ввод!!! Введите 1 или 2");
            }
        }

    }

    private static void startSimulation() {
        int length = inputUserLengthOrHeight("ширину");
        int height = inputUserLengthOrHeight("длину");

        MapFactory mapFactory = new MapFactory();

        WorldMap worldMap = mapFactory.create(length, height, 2, 2, 2, 10, 2);

        Simulation simulation = new Simulation(worldMap);
        simulation.play();
    }

    private static int inputUserLengthOrHeight(String whatFind) {
        int input = 0;
        boolean flag = false;
        String userInput;
        while (!flag) {
            System.out.println("Введите " + whatFind + " карты от 10 до 15");
            userInput = scanner.nextLine();
            try {
                input = Integer.parseInt(userInput);
                if (input >= 10 && input <= 15) {
                    flag = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод, попробуйте снова");
            }
        }
        return input;
    }
}



