package LambdaExpression.ThreadusingLambda;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWIndow {
    public static void main(String[] args) {
        // window : object JFrame
        JFrame frame = new JFrame("My Window");
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());


        // create button and add frame
        JButton button = new JButton("Click me ");
        // Interface using anonymous class
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button click ");
                JOptionPane.showMessageDialog(null, "hey button click");
            }
        });
        //Interface using lambada
        button.addActionListener((ActionEvent actionEvent) -> {
            System.out.println("Button click ");
            JOptionPane.showMessageDialog(null, "hey button click");
        });
        frame.add(button);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
