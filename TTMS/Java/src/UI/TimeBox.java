package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TimeBox extends JButton {
    private DateChooser dateChooser = null;
    private String preLabel = "";
    private String originalText = null;
    private SimpleDateFormat sdf = null;

    public TimeBox() {
        this(getNowDate());
    }

    public TimeBox(String dateString) {
        this();
        setText(getDefaultDateFormat(), dateString);
        initOriginalText(dateString);
    }

    public TimeBox(SimpleDateFormat df, String dateString) {
        this();
        setText(df, dateString);
        this.sdf = df;

        Date originalDate = null;
        try {
            originalDate = df.parse(dateString);
        } catch (ParseException ex) {
            originalDate = getNowDate();
        }
        initOriginalText(originalDate);
    }

    public TimeBox(Date date) {
        this("", date);
        initOriginalText(date);
    }

    public TimeBox(String preLabel, Date date) {
        if (preLabel != null) {
            this.preLabel = preLabel;
        }
        setDate(date);
        initOriginalText(date);

        setBorder(null);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        super.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateChooser == null) {
                    dateChooser = new DateChooser();
                }
                Point p = getLocationOnScreen();
                p.y = p.y + 30;
                dateChooser.showDateChooser(p);
            }
        });
    }

    private static Date getNowDate() {
        return Calendar.getInstance().getTime();
    }

    private static SimpleDateFormat getDefaultDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    /**
     * 寰楀埌褰撳墠浣跨敤鐨勬棩鏈熸牸寮忓寲鍣�
     * @return 鏃ユ湡鏍煎紡鍖栧櫒
     */
    public SimpleDateFormat getCurrentSimpleDateFormat(){
        if(this.sdf != null){
            return sdf;
        }else{
            return getDefaultDateFormat();
        }
    }


    //淇濆瓨鍘熷鏄棩鏈熸椂闂�
    private void initOriginalText(String dateString) {
        this.originalText = dateString;
    }

    //淇濆瓨鍘熷鏄棩鏈熸椂闂�
    private void initOriginalText(Date date) {
        this.originalText = preLabel + getDefaultDateFormat().format(date);
    }

    /** 
     * 寰楀埌褰撳墠璁板繂鐨勫師濮嬫棩鏈熸椂闂�
     * @return 褰撳墠璁板繂鐨勫師濮嬫棩鏈熸椂闂达紙鏈慨鏀瑰墠鐨勬棩鏈熸椂闂达級
     */
    public String getOriginalText() {
        return originalText;
    }

    // 瑕嗙洊鐖剁被鐨勬柟娉�
    @Override
    public void setText(String s) {
        Date date;
        try {
            date = getDefaultDateFormat().parse(s);
        } catch (ParseException e) {
            date = getNowDate();
        }
        setDate(date);
    }

    public void setText(SimpleDateFormat df, String s) {
        Date date;
        try {
            date = df.parse(s);
        } catch (ParseException e) {
            date = getNowDate();
        }
        setDate(date);
    }

    public void setDate(Date date) {
        super.setText(preLabel + getDefaultDateFormat().format(date));
    }

    public Date getDate() {
        String dateString = getText().substring(preLabel.length());
        try {
            SimpleDateFormat currentSdf = getCurrentSimpleDateFormat();
            return currentSdf.parse(dateString);
        } catch (ParseException e) {
            return getNowDate();
        }
    }

    /**  
     * 瑕嗙洊鐖剁被鐨勬柟娉曚娇涔嬫棤鏁�
     * @param listener 鍝嶅簲鐩戝惉鍣�
     */
    @Override
    public void addActionListener(ActionListener listener) {
    }

    /**
     * 鍐呴儴绫伙紝涓昏鏄畾涔変竴涓狫Panel锛岀劧鍚庢妸鏃ュ巻鐩稿叧鐨勬墍鏈夊唴瀹瑰～鍏ユ湰JPanel锛�
     * 鐒跺悗鍐嶅垱寤轰竴涓狫Dialog锛屾妸鏈唴閮ㄧ被瀹氫箟鐨凧Panel鏀惧叆JDialog鐨勫唴瀹瑰尯
     */
    private class DateChooser extends JPanel implements ActionListener, ChangeListener {

        int startYear = 1980; // 榛樿銆愭渶灏忋�戞樉绀哄勾浠�
        int lastYear = 2050; // 榛樿銆愭渶澶с�戞樉绀哄勾浠�
        int width = 500; // 鐣岄潰瀹藉害
        int height = 300; // 鐣岄潰楂樺害
        Color backGroundColor = Color.gray; // 搴曡壊
        // 鏈堝巻琛ㄦ牸閰嶈壊----------------//
        Color palletTableColor = Color.white; // 鏃ュ巻琛ㄥ簳鑹�
        Color todayBackColor = Color.orange; // 浠婂ぉ鑳屾櫙鑹�
        Color weekFontColor = Color.blue; // 鏄熸湡鏂囧瓧鑹�
        Color dateFontColor = Color.black; // 鏃ユ湡鏂囧瓧鑹�
        Color weekendFontColor = Color.red; // 鍛ㄦ湯鏂囧瓧鑹�
        // 鎺у埗鏉￠厤鑹�------------------//
        Color controlLineColor = Color.pink; // 鎺у埗鏉″簳鑹�
        Color controlTextColor = Color.black; // 鎺у埗鏉℃爣绛炬枃瀛楄壊
        Color rbFontColor = Color.white; // RoundBox鏂囧瓧鑹�
        Color rbBorderColor = Color.red; // RoundBox杈规鑹�
        Color rbButtonColor = Color.pink; // RoundBox鎸夐挳鑹�
        Color rbBtFontColor = Color.red; // RoundBox鎸夐挳鏂囧瓧鑹�
        JDialog dialog;
        JSpinner yearSpin;
        JSpinner monthSpin;
        JSpinner daySpin;
        JSpinner hourSpin;
        JSpinner minuteSpin;
        JSpinner secondSpin;
        JButton[][] daysButton = new JButton[6][7];

        DateChooser() {

            setLayout(new BorderLayout());
            setBorder(new LineBorder(backGroundColor, 2));
            setBackground(backGroundColor);

            JPanel topYearAndMonth = createYearAndMonthPanal();
            add(topYearAndMonth, BorderLayout.NORTH);
            JPanel centerWeekAndDay = createWeekAndDayPanal();
            add(centerWeekAndDay, BorderLayout.CENTER);
            JPanel buttonBarPanel = createButtonBarPanel();
            this.add(buttonBarPanel, java.awt.BorderLayout.SOUTH);
        }

        private JPanel createYearAndMonthPanal() {
            Calendar c = getCalendar();
            int currentYear = c.get(Calendar.YEAR);
            int currentMonth = c.get(Calendar.MONTH) + 1;
            int currentHour = c.get(Calendar.HOUR_OF_DAY);
            int currentMinute = c.get(Calendar.MINUTE);
            int currentSecond = c.get(Calendar.SECOND);

            JPanel result = new JPanel();
            result.setLayout(new FlowLayout());
            result.setBackground(controlLineColor);

            yearSpin = new JSpinner(new SpinnerNumberModel(currentYear, startYear, lastYear, 1));
            yearSpin.setPreferredSize(new Dimension(48, 20));
            yearSpin.setName("Year");
            yearSpin.setEditor(new JSpinner.NumberEditor(yearSpin, "####"));
            yearSpin.addChangeListener(this);
            result.add(yearSpin);

            JLabel yearLabel = new JLabel("年");
            yearLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            yearLabel.setForeground(controlTextColor);
            result.add(yearLabel);

            monthSpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 12, 1));
            monthSpin.setPreferredSize(new Dimension(35, 20));
            monthSpin.setName("Month");
            monthSpin.addChangeListener(this);
            result.add(monthSpin);

            JLabel monthLabel = new JLabel("月");
            monthLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            monthLabel.setForeground(controlTextColor);
            result.add(monthLabel);

            daySpin = new JSpinner(new SpinnerNumberModel(currentMonth, 1, 31, 1));
            daySpin.setPreferredSize(new Dimension(35, 20));
            daySpin.setName("Day");
            daySpin.addChangeListener(this);
            daySpin.setEnabled(false);
            daySpin.setToolTipText("请在下面的日历面板中进行选择哪一天");
            result.add(daySpin);

            JLabel dayLabel = new JLabel("日");
            dayLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            dayLabel.setForeground(controlTextColor);
            result.add(dayLabel);

            hourSpin = new JSpinner(new SpinnerNumberModel(currentHour, 0, 23, 1));
            hourSpin.setPreferredSize(new Dimension(35, 20));
            hourSpin.setName("Hour");
            hourSpin.addChangeListener(this);
            result.add(hourSpin);

            JLabel hourLabel = new JLabel("时");
            hourLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            hourLabel.setForeground(controlTextColor);
            result.add(hourLabel);

            minuteSpin = new JSpinner(new SpinnerNumberModel(currentMinute, 0, 59, 1));
            minuteSpin.setPreferredSize(new Dimension(35, 20));
            minuteSpin.setName("Minute");
            minuteSpin.addChangeListener(this);
            result.add(minuteSpin);

            JLabel minuteLabel = new JLabel("分");
            minuteLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            hourLabel.setForeground(controlTextColor);
            result.add(minuteLabel);

            secondSpin = new JSpinner(new SpinnerNumberModel(currentSecond, 0, 59, 1));
            secondSpin.setPreferredSize(new Dimension(35, 20));
            secondSpin.setName("Second");
            secondSpin.addChangeListener(this);
            result.add(secondSpin);

            JLabel secondLabel = new JLabel("秒");
            secondLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
            hourLabel.setForeground(controlTextColor);
            result.add(secondLabel);

            return result;
        }

        private JPanel createWeekAndDayPanal() {
            String colname[] = {"日", "一", "二", "三", "四", "五", "六"};
            JPanel result = new JPanel();
            // 璁剧疆鍥哄畾瀛椾綋锛屼互鍏嶈皟鐢ㄧ幆澧冩敼鍙樺奖鍝嶇晫闈㈢編瑙�
            result.setFont(new Font("寰蒋闆呴粦", Font.PLAIN, 18));
            result.setLayout(new GridLayout(7, 7));
            result.setBackground(Color.white);
            JLabel cell;

            for (int i = 0; i < 7; i++) {
                cell = new JLabel(colname[i]);
                cell.setHorizontalAlignment(JLabel.RIGHT);
                if (i == 0 || i == 6) {
                    cell.setForeground(weekendFontColor);
                } else {
                    cell.setForeground(weekFontColor);
                }
                result.add(cell);
            }

            int actionCommandId = 0;
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    JButton numberButton = new JButton();
                    numberButton.setBorder(null);
                    numberButton.setHorizontalAlignment(SwingConstants.RIGHT);
                    numberButton.setActionCommand(String.valueOf(actionCommandId));
                    numberButton.addActionListener(this);
                    numberButton.setBackground(palletTableColor);
                    numberButton.setForeground(dateFontColor);
                    if (j == 0 || j == 6) {
                        numberButton.setForeground(weekendFontColor);
                    } else {
                        numberButton.setForeground(dateFontColor);
                    }
                    daysButton[i][j] = numberButton;
                    result.add(numberButton);
                    actionCommandId++;
                }
            }

            return result;
        }

        /** 寰楀埌DateChooserButton鐨勫綋鍓峵ext锛屾湰鏂规硶鏄负鎸夐挳浜嬩欢鍖垮悕绫诲噯澶囩殑銆� */
        public String getTextOfDateChooserButton() {
            return getText();
        }

        /** 鎭㈠DateChooserButton鐨勫師濮嬫棩鏈熸椂闂磘ext锛屾湰鏂规硶鏄负鎸夐挳浜嬩欢鍖垮悕绫诲噯澶囩殑銆� */
        public void restoreTheOriginalDate() {
            String originalText = getOriginalText();
            setText(originalText);
        }

        private JPanel createButtonBarPanel() {
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            JButton ok = new JButton("确定");
            ok.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    //璁板繂鍘熷鏃ユ湡鏃堕棿
                    initOriginalText(getTextOfDateChooserButton());
                    //闅愯棌鏃ュ巻瀵硅瘽妗�
                    dialog.setVisible(false);
                }
            });
            panel.add(ok);

            JButton cancel = new JButton("取消");
            cancel.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    restoreTheOriginalDate();
                    dialog.setVisible(false);
                }
            });

            panel.add(cancel);
            return panel;
        }

        private JDialog createDialog(JDialog owner) {
            JDialog result = new JDialog(owner, "日期时间选择", true);
            result.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
            result.getContentPane().add(this, BorderLayout.CENTER);
//          result.pack();
            result.setSize(width, height);
            return result;
        }

        void showDateChooser(Point position) {
            JDialog owner = (JDialog) SwingUtilities.getWindowAncestor(TimeBox.this);
            if (dialog == null || dialog.getOwner() != owner) {
                dialog = createDialog(owner);
            }
            dialog.setLocation(getAppropriateLocation(owner, position));
            flushWeekAndDay();
            dialog.setVisible(true);
        }

        Point getAppropriateLocation(JDialog owner, Point position) {
            Point result = new Point(position);
            Point p = owner.getLocation();
            int offsetX = (position.x + width) - (p.x + owner.getWidth());
            int offsetY = (position.y + height) - (p.y + owner.getHeight());

            if (offsetX > 0) {
                result.x -= offsetX;
            }

            if (offsetY > 0) {
                result.y -= offsetY;
            }

            return result;
        }

        private Calendar getCalendar() {
            Calendar result = Calendar.getInstance();
            result.setTime(getDate());
            return result;
        }

        private int getSelectedYear() {
            return ((Integer) yearSpin.getValue()).intValue();
        }

        private int getSelectedMonth() {
            return ((Integer) monthSpin.getValue()).intValue();
        }

        private int getSelectedHour() {
            return ((Integer) hourSpin.getValue()).intValue();
        }

        private int getSelectedMinite() {
            return ((Integer) minuteSpin.getValue()).intValue();
        }

        private int getSelectedSecond() {
            return ((Integer) secondSpin.getValue()).intValue();
        }

        private void dayColorUpdate(boolean isOldDay) {
            Calendar c = getCalendar();
            int day = c.get(Calendar.DAY_OF_MONTH);
            c.set(Calendar.DAY_OF_MONTH, 1);
            int actionCommandId = day - 2 + c.get(Calendar.DAY_OF_WEEK);
            int i = actionCommandId / 7;
            int j = actionCommandId % 7;
            if (isOldDay) {
                daysButton[i][j].setForeground(dateFontColor);
            } else {
                daysButton[i][j].setForeground(todayBackColor);
            }
        }

        private void flushWeekAndDay() {
            Calendar c = getCalendar();
            c.set(Calendar.DAY_OF_MONTH, 1);
            int maxDayNo = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            int dayNo = 2 - c.get(Calendar.DAY_OF_WEEK);
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 7; j++) {
                    String s = "";
                    if (dayNo >= 1 && dayNo <= maxDayNo) {
                        s = String.valueOf(dayNo);
                    }
                    daysButton[i][j].setText(s);
                    dayNo++;
                }
            }
            dayColorUpdate(false);
        }

        /**
         * 閫夋嫨鏃ユ湡鏃剁殑鍝嶅簲浜嬩欢
         */
        @Override
        public void stateChanged(ChangeEvent e) {
            JSpinner source = (JSpinner) e.getSource();
            Calendar c = getCalendar();
            if (source.getName().equals("Hour")) {
                c.set(Calendar.HOUR_OF_DAY, getSelectedHour());
                setDate(c.getTime());
                return;
            }
            if (source.getName().equals("Minute")) {
                c.set(Calendar.MINUTE, getSelectedMinite());
                setDate(c.getTime());
                return;
            }
            if (source.getName().equals("Second")) {
                c.set(Calendar.SECOND, getSelectedSecond());
                setDate(c.getTime());
                return;
            }

            dayColorUpdate(true);

            if (source.getName().equals("Year")) {
                c.set(Calendar.YEAR, getSelectedYear());
            } else if (source.getName().equals("Month")) {
                c.set(Calendar.MONTH, getSelectedMonth() - 1);
            }
            setDate(c.getTime());
            flushWeekAndDay();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.getText().length() == 0) {
                return;
            }
            dayColorUpdate(true);
            source.setForeground(todayBackColor);
            int newDay = Integer.parseInt(source.getText());
            Calendar c = getCalendar();
            c.set(Calendar.DAY_OF_MONTH, newDay);
            setDate(c.getTime());
            daySpin.setValue(Integer.valueOf(newDay));
        }
    }

    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("测试");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new java.awt.BorderLayout());
        mainFrame.add(new TimeBox(), java.awt.BorderLayout.CENTER);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int w = mainFrame.getWidth();
        int h = mainFrame.getHeight();
        mainFrame.setLocation((width - w) / 2, (height - h) / 2);
        mainFrame.setVisible(true);
    }
}
