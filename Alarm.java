import java.time.LocalTime;
import javax.swing.JOptionPane;

public class Alarm {

    private int hour;
    private int minute;
    private boolean alarmOn = false;

    public void setAlarm(int h, int m) {
        hour = h;
        minute = m;
        alarmOn = true;
    }

    public void checkAlarm(LocalTime time) {

        if (alarmOn) {

            if (time.getHour() == hour &&
                    time.getMinute() == minute &&
                    time.getSecond() == 0) {

                JOptionPane.showMessageDialog(null,
                        "Alarm! Wake Up!");

                alarmOn = false;
            }
        }

    }

}