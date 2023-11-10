package ru.academits.kurlukov.temperature;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TemperatureConverter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Преобразователь температур");
            Image iconImage = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Пользователь\\IdeaProjects\\OopCourse\\Temperature\\src\\ru\\academits\\kurlukov\\icon_image\\icon_image.png");
            frame.setIconImage(iconImage);

            frame.setSize(350, 220);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2));

            JTextField inputField = new JTextField();
            JComboBox<String> fromScaleField = new JComboBox<>(new String[]{"Цельсий", "Фаренгейт", "Кельвин"});
            JComboBox<String> toScaleField = new JComboBox<>(new String[]{"Цельсий", "Фаренгейт", "Кельвин"});
            JTextField resultField = new JTextField();
            resultField.setEditable(false);

            panel.add(new JLabel("Температура:"));
            panel.add(inputField);
            panel.add(new JLabel("Из:"));
            panel.add(fromScaleField);
            panel.add(new JLabel("В:"));
            panel.add(toScaleField);
            panel.add(new JLabel("Результат:"));
            panel.add(resultField);

            JButton convertButton = new JButton("Перевести");
            convertButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(inputField.getText());
                    String fromScale = Objects.requireNonNull(fromScaleField.getSelectedItem()).toString();
                    String toScale = Objects.requireNonNull(toScaleField.getSelectedItem()).toString();
                    double result = convertTemperature(temperature, fromScale, toScale);
                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода. Введите число.");
                }
            });

            frame.add(panel, BorderLayout.CENTER);
            frame.add(convertButton, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }

    private static double convertTemperature(double temperature, String fromScale, String toScale) {
        if (fromScale.equals("Цельсий") && toScale.equals("Фаренгейт")) {
            return (temperature * 9 / 5) + 32;
        }

        if (fromScale.equals("Цельсий") && toScale.equals("Кельвин")) {
            return temperature + 273.15;
        }

        if (fromScale.equals("Фаренгейт") && toScale.equals("Цельсий")) {
            return (temperature - 32) * 5 / 9;
        }

        if (fromScale.equals("Фаренгейт") && toScale.equals("Кельвин")) {
            return (temperature - 32) * 5 / 9 + 273.15;
        }

        if (fromScale.equals("Кельвин") && toScale.equals("Цельсий")) {
            return temperature - 273.15;
        }

        if (fromScale.equals("Кельвин") && toScale.equals("Фаренгейт")) {
            return (temperature - 273.15) * 9 / 5 + 32;
        }

        return temperature;
    }
}