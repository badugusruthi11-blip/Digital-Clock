import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class ClockFrame extends JFrame {

    JLabel clockLabel;
    JButton alarmButton;
    JPanel panel;

    Alarm alarm = new Alarm();

    public ClockFrame() {

        setTitle("Digital Clock with Alarm");

        setSize(420, 220);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);


        Color backgroundColor = new Color(18, 18, 30);   // dark background
        Color buttonColor = new Color(0, 153, 255);      // blue button
        Color textColor = new Color(0, 255, 180);        // neon green text

        Font clockFont = new Font("Consolas", Font.BOLD, 42);
        Font buttonFont = new Font("Arial", Font.BOLD, 16);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(backgroundColor);


        clockLabel = new JLabel();
        clockLabel.setFont(clockFont);
        clockLabel.setForeground(textColor);
        clockLabel.setHorizontalAlignment(SwingConstants.CENTER);

        
        alarmButton = new JButton("SET ALARM");
        alarmButton.setFont(buttonFont);
        alarmButton.setBackground(buttonColor);
        alarmButton.setForeground(Color.WHITE);
        alarmButton.setFocusPainted(false);

    
        alarmButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

    
        panel.add(clockLabel, BorderLayout.CENTER);
        panel.add(alarmButton, BorderLayout.SOUTH);

        add(panel);

        
        alarmButton.addActionListener(e -> {

    String hour = JOptionPane.showInputDialog("Enter Hour (0-23)");
    String minute = JOptionPane.showInputDialog("Enter Minute (0-59)");


    if (hour == null || minute == null) {
        JOptionPane.showMessageDialog(this, "Alarm cancelled");
        return;
    }

    try {
        hour = hour.trim();
        minute = minute.trim();

        int h = Integer.parseInt(hour);
        int m = Integer.parseInt(minute);

        // validation
        if (h < 0 || h > 23 || m < 0 || m > 59) {
            JOptionPane.showMessageDialog(this, "Enter valid time!");
            return;
        }

        alarm.setAlarm(h, m);

        JOptionPane.showMessageDialog(this,
                "Alarm Set Successfully for " + h + ":" + String.format("%02d", m));

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this,
                "Please enter numbers only!");
    }

});

        
        Timer timer = new Timer(1000, e -> {

            LocalTime currentTime = LocalTime.now();

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("HH:mm:ss");

            clockLabel.setText(currentTime.format(formatter));

            alarm.checkAlarm(currentTime);

        });

        timer.start();

        setVisible(true);
    }
}