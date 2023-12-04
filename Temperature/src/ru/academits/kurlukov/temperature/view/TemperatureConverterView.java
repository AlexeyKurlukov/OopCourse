package ru.academits.kurlukov.temperature.view;

import ru.academits.kurlukov.temperature.model.TemperatureConverterModel;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TemperatureConverterView {
    private static TemperatureConverterModel model;

    public TemperatureConverterView(TemperatureConverterModel model) {
        TemperatureConverterView.model = model;
    }

    public static void createAndShowGUI() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Преобразователь температур");
            Image iconImage = Toolkit.getDefaultToolkit().getImage("Temperature\\icon_image.png");
            frame.setIconImage(iconImage);

            frame.setSize(350, 220);
            frame.setMinimumSize(new Dimension(220, 140));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(4, 2));

            JTextField inputTextField = new JTextField();
            String[] temperatureScales = {"Цельсий", "Фаренгейт", "Кельвин"};
            JComboBox<String> fromScaleComboBox = new JComboBox<>(temperatureScales);
            JComboBox<String> toScaleComboBox = new JComboBox<>(temperatureScales);
            JTextField resultTextField = new JTextField();
            resultTextField.setEditable(false);

            panel.add(new JLabel("Температура:"));
            panel.add(inputTextField);
            panel.add(new JLabel("Из:"));
            panel.add(fromScaleComboBox);
            panel.add(new JLabel("В:"));
            panel.add(toScaleComboBox);
            panel.add(new JLabel("Результат:"));
            panel.add(resultTextField);

            JButton convertButton = new JButton("Перевести");
            convertButton.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(inputTextField.getText());
                    String fromScale = Objects.requireNonNull(fromScaleComboBox.getSelectedItem()).toString();
                    String toScale = Objects.requireNonNull(toScaleComboBox.getSelectedItem()).toString();
                    double result = model.convertTemperature(temperature, fromScale, toScale);
                    resultTextField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Ошибка ввода. Введите число.", "Сообщение", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.add(panel, BorderLayout.CENTER);
            frame.add(convertButton, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }
}