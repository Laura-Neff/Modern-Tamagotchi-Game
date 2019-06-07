package Tomogatchi;

import javax.swing.*;
import java.awt.*;

public class ComboBox extends JFrame {
    private String [] petChoices = {"Dog", "Parrot", "Stitch"};
    private JComboBox petChoicesList = new JComboBox(petChoices);
    JLabel lblText = new JLabel();

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "What kind of pet would you like to adopt? You can choose a dog, a parrot, Stitch, or a snail.");

    }

    public ComboBox() {
        ComboBox petChooser = new ComboBox();
        petChooser.setVisible(true);
        setLayout(new FlowLayout());
        setSize(400,400);
        setTitle("Choose your pet!");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        petChoicesList.setSelectedIndex(0);
        add(petChoicesList);
        add(lblText);


    }

}
