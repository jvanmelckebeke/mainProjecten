package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jari Van Melckebeke
 * @version 0.1
 */
public class Gui {
    //special vars
    private GridBagConstraints constraints = new GridBagConstraints();
    //end of special vars
    //Gui vars
    //basic
    private JFrame frame = new JFrame("Physics laws");
    private JPanel mainPanel = new JPanel();
    private JPanel inputPanel = new JPanel(new GridBagLayout());
    //variable components
    private JComboBox<String> mirrorSortComboBox = new JComboBox<>(new String[]{"flat", "hollow", "rounded"});
    private JComboBox<String> substanceComboBox1 = new JComboBox<>(SubstanceDatabase.getSubstances());
    private JComboBox<String> substanceComboBox2 = new JComboBox<>(SubstanceDatabase.getSubstances());
    private JSpinner angleSpinnerMirror = new JSpinner(new SpinnerNumberModel(0, 0, 360, 0.1));
    private JSpinner indicentRayAngleRefraction = new JSpinner(new SpinnerNumberModel(0, 0, 180, 0.1));
    private JSpinner refractedRayAngleRefraction = new JSpinner(new SpinnerNumberModel(0, 0, 180, 0.1));
    private JLabel maxAngle = new JLabel(Calculator.getMaxAngle(substanceComboBox1.getSelectedIndex(), substanceComboBox2.getSelectedIndex()));
    //end of Gui vars

    public Gui() {
        panelSetup();
        addComponents();
        setupActionListeners();
    }

    private GridBagConstraints setGridBagPlace(int x, int y) {
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.5;
        constraints.weightx = 0.5;
        constraints.gridx = x;
        constraints.gridy = y;
        return constraints;
    }

    private void addComponents() {
        frame.add(mainPanel, BorderLayout.LINE_START);
        frame.add(inputPanel, BorderLayout.LINE_END);
        inputPanel.add(new JLabel("mirror laws"), setGridBagPlace(0, 0));
        inputPanel.add(new JLabel("sort: "), setGridBagPlace(0, 1));
        inputPanel.add(mirrorSortComboBox, setGridBagPlace(1, 1));
        inputPanel.add(new JLabel("angle: "), setGridBagPlace(0, 2));
        inputPanel.add(angleSpinnerMirror, setGridBagPlace(1, 2));
        inputPanel.add(new JLabel(" "), setGridBagPlace(0, 3));
        inputPanel.add(new JLabel("laws of refraction"), setGridBagPlace(0, 4));
        inputPanel.add(new JLabel("substance 1: "), setGridBagPlace(0, 5));
        inputPanel.add(substanceComboBox1, setGridBagPlace(1, 5));
        inputPanel.add(new JLabel("substance 2: "), setGridBagPlace(0, 6));
        inputPanel.add(substanceComboBox2, setGridBagPlace(1, 6));
        inputPanel.add(new JLabel("indicent ray: "), setGridBagPlace(0, 7));
        inputPanel.add(indicentRayAngleRefraction, setGridBagPlace(1, 7));
        inputPanel.add(new JLabel("refracted ray: "), setGridBagPlace(0, 8));
        inputPanel.add(refractedRayAngleRefraction, setGridBagPlace(1, 8));
        inputPanel.add(new JLabel("total internal reflection angle: "), setGridBagPlace(0, 9));
        inputPanel.add(maxAngle, setGridBagPlace(1, 9));
    }

    private void setupActionListeners() {
        substanceComboBox1.addActionListener(e -> maxAngle.setText(Calculator.getMaxAngle(substanceComboBox1.getSelectedIndex(), substanceComboBox2.getSelectedIndex())));
        substanceComboBox2.addActionListener(e -> maxAngle.setText(Calculator.getMaxAngle(substanceComboBox1.getSelectedIndex(), substanceComboBox2.getSelectedIndex())));
        mirrorSortComboBox.addActionListener(e -> new SetupMirrorScene(mirrorSortComboBox.getSelectedIndex(), (Double) angleSpinnerMirror.getValue()));
        angleSpinnerMirror.addChangeListener(e -> new SetupMirrorScene(mirrorSortComboBox.getSelectedIndex(), (Double) angleSpinnerMirror.getValue()));
        indicentRayAngleRefraction.addChangeListener(e -> new SetupRefractionScene((Double) indicentRayAngleRefraction.getValue(), substanceComboBox1.getSelectedIndex(), substanceComboBox2.getSelectedIndex()));
        refractedRayAngleRefraction.addChangeListener(e -> new SetupRefractionScene((Double) refractedRayAngleRefraction.getValue(), substanceComboBox1.getSelectedIndex(), substanceComboBox2.getSelectedIndex()));
    }

    private void panelSetup() {
        frame.setSize(1920, 1080);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        inputPanel.setBorder(BorderFactory.createTitledBorder("input"));
        inputPanel.setPreferredSize(new Dimension(576, 1080));
    }

}
