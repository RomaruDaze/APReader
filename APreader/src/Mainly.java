import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Mainly {

    private static void createAndShowGUI() {

        //フレームを作成するコード
        JFrame frame = new JFrame("AP Reader 1.0.6");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 270); 
        frame.setBackground(Color.GREEN.darker());
        
        //パネルを作成するコード
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));  
        panel.setBackground(Color.RED);

        //ラベル・文字を作成するコード
        JLabel welcomeLabel = new JLabel("Welcome to AP Reader");
        welcomeLabel.setFont(new Font("Impact", Font.PLAIN, 30)); 
        welcomeLabel.setForeground(new Color(204, 204, 0));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
        JLabel copyrightLabel = new JLabel("Made by Roger Marvin");
        copyrightLabel.setFont(new Font("Arno Pro", Font.BOLD, 14));
        copyrightLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 

        //お任せモードのボタン
        JButton button1 = new JButton("お任せモード");
        button1.setFont(new Font("MS Gothic", Font.BOLD, 16));  
        button1.setAlignmentX(Component.CENTER_ALIGNMENT); 
        button1.setBorder(BorderFactory.createEmptyBorder(10, 55, 10, 55));
        button1.setBackground(Color.GREEN.darker());
        button1.setForeground(Color.WHITE);
        
        //お任せモードのボタンを押すときのコード
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    runBatchFile("C:\\Users\\USER\\Desktop\\Java\\APreader\\auto.bat");
                }).start();
            }
        });

        //マニュアルモードのボタン
        JButton button2 = new JButton("マニュアルモード");
        button2.setFont(new Font("MS Gothic", Font.BOLD, 16)); 
        button2.setAlignmentX(Component.CENTER_ALIGNMENT); 
        button2.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        button2.setBackground(Color.BLUE);
        button2.setForeground(Color.WHITE);
        
        //マニュアルモードのボタンを押すときのコード
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    runBatchFile("C:\\Users\\USER\\Desktop\\Java\\APreader\\manual.bat");
                }).start();
            }
        });

        //フレーム・パネルのビジュアルを設定するコード
        panel.add(Box.createVerticalStrut(20)); 
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(20));
        panel.add(button1);
        panel.add(Box.createVerticalStrut(10)); 
        panel.add(button2);
        panel.add(Box.createVerticalStrut(30)); 
        panel.add(copyrightLabel);

        frame.add(panel);
        frame.setVisible(true);
    }


//BATファイルを起動するコード
    private static void runBatchFile(String batchFilePath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", batchFilePath);

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Batch file executed successfully");
            } else {
                System.err.println("Batch file encountered an error.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
}
