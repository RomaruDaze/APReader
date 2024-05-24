import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.io.IOException;

public class MainFrame extends JFrame {
    private final Font mainFont = new Font("MS Gothic", Font.BOLD, 18);
    private JComboBox<String> optionsComboBox;
    private JTextField filePathField, destinationField;
    private JLabel resultLabel;
    private JLabel loadingLabel;

    public MainFrame() {
        setTitle("AP Reader V.1.1.2");
        setSize(600, 570);
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        int marginSize = 10; 

        
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(3, 1, 0, 0));
        topPanel.setBackground(Color.CYAN);


        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel optionLabel = new JLabel("タイプ");
        optionLabel.setFont(new Font("MS Gothic", Font.BOLD, 30));
        optionsComboBox = new JComboBox<>(new String[] { "","FAT", "FIT", "CISCO" });
        optionsComboBox.setFont(new Font("MS Gothic", Font.BOLD, 20));
        typePanel.add(optionLabel);
        typePanel.add(optionsComboBox);
        typePanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));
        typePanel.setBackground(null);



        JPanel filePanel = new JPanel();
        filePanel.setLayout(new BorderLayout());
        JPanel filePathPanel = new JPanel(new BorderLayout(5, 5));
        JLabel fileLabel = new JLabel("データファイル{.csv}");
        fileLabel.setFont(new Font("MS Gothic", Font.BOLD, 24));
        filePathField = new JTextField();
        filePathField.setFont(new Font("MS Gothic", Font.BOLD, 24));

        


        JButton browseButton = new JButton("探す");
        JButton clearButton1 = new JButton("削除");
        clearButton1.setFont(new Font("MS Gothic", Font.BOLD, 24));
        clearButton1.setForeground(Color.WHITE);
        clearButton1.setBackground(Color.RED);
        clearButton1.setBorder(new LineBorder(Color.RED, 4));
        clearButton1.setBorder(new EmptyBorder(5,10,5,10));
        clearButton1.setOpaque(true);
        browseButton.setFont(new Font("MS Gothic", Font.BOLD, 24));
        


        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFile = fileChooser.getSelectedFile().getAbsolutePath();
                filePathField.setText(selectedFile);
                extractHostAndPass(selectedFile);
            }
        });

        clearButton1.addActionListener(e -> {
            filePathField.setText("");
        });



        JPanel destinationPanel = new JPanel();
        destinationPanel.setLayout(new BorderLayout());
        destinationPanel.setBackground(Color.CYAN);

        JPanel destinationPathPanel = new JPanel(new BorderLayout(5, 5));
        destinationPathPanel.setBackground(Color.CYAN);

        JLabel destinationLabel = new JLabel("出力場所:");
        destinationLabel.setFont(new Font("MS Gothic", Font.BOLD, 24));
        destinationField = new JTextField();
        destinationField.setFont(new Font("MS Gothic", Font.BOLD, 24));
        JButton destinationBrowseButton = new JButton("探す");
        JButton clearButton2 = new JButton("削除");
        clearButton2.setFont(new Font("MS Gothic", Font.BOLD, 24));
        clearButton2.setForeground(Color.WHITE);
        clearButton2.setBackground(Color.RED);
        clearButton2.setBorder(new LineBorder(Color.RED, 4));
        clearButton2.setBorder(new EmptyBorder(5,10,5,10));
        clearButton2.setOpaque(true);
        destinationBrowseButton.setFont(new Font("MS Gothic", Font.BOLD, 24));



        destinationBrowseButton.addActionListener(e -> {
            JFileChooser folderChooser = new JFileChooser();
            folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = folderChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFolder = folderChooser.getSelectedFile().getAbsolutePath();
                destinationField.setText(selectedFolder);
            }
        });

        typePanel.setBorder(new EmptyBorder(40, marginSize, marginSize, marginSize));
        filePathPanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));
        filePanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));
        destinationPathPanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));
        destinationPanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));

        typePanel.setBackground(Color.CYAN);
        filePathPanel.setBackground(Color.CYAN);
        filePanel.setBackground(Color.CYAN);
        destinationPathPanel.setBackground(Color.CYAN);
        destinationPanel.setBackground(Color.CYAN);

        topPanel.add(typePanel);
        filePathPanel.add(filePathField, BorderLayout.CENTER);
        JPanel fileButtonPanel = new JPanel();
        fileButtonPanel.setBackground(Color.CYAN);

        fileButtonPanel.add(browseButton);
        fileButtonPanel.add(clearButton1);
        filePathPanel.add(fileButtonPanel, BorderLayout.EAST);
        filePanel.add(fileLabel, BorderLayout.NORTH);
        filePanel.add(filePathPanel, BorderLayout.CENTER);

        destinationPathPanel.add(destinationField, BorderLayout.CENTER);
        JPanel destinationButtonPanel = new JPanel();
        destinationButtonPanel.setBackground(Color.CYAN);

        destinationButtonPanel.add(destinationBrowseButton);
        destinationButtonPanel.add(clearButton2);
        destinationPathPanel.add(destinationButtonPanel, BorderLayout.EAST);
        destinationPanel.add(destinationLabel, BorderLayout.NORTH);
        destinationPanel.add(destinationPathPanel, BorderLayout.CENTER);

        topPanel.add(filePanel);
        topPanel.add(destinationPanel);

        add(topPanel, BorderLayout.NORTH);


        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(Color.CYAN);

        resultLabel = new JLabel();
        resultLabel.setFont(mainFont);
        labelPanel.add(resultLabel, BorderLayout.CENTER);

        loadingLabel = new JLabel("");
        loadingLabel.setFont(new Font("MS Gothic", Font.BOLD, 30));
        loadingLabel.setBorder(new EmptyBorder(0, 20, 0, 0));
        labelPanel.add(loadingLabel, BorderLayout.CENTER);
        add(labelPanel);

        JButton submitButton = new JButton("実行");

        submitButton.setFont(new Font("MS Gothic", Font.BOLD, 70));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(0, 190, 0));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));


        

        submitButton.addActionListener(e -> {
            String selectedOption = (String) optionsComboBox.getSelectedItem();
            String filePath = filePathField.getText();
            String destinationPath = destinationField.getText();
            clearSubmitMessage();
            setLoadingMessage("Compiling...");

            new Thread(() -> {
                runPythonScript(selectedOption, filePath, destinationPath);
                clearLoadingMessage();

                int delay = 5000; // Delay in milliseconds (e.g., 5 seconds)
                Timer timer = new Timer(delay, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        resultLabel.setVisible(false);
                        ((Timer) e.getSource()).stop();
                    }
                });

                timer.setRepeats(false);
                timer.start();
            }).start();

            
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.setBackground(Color.CYAN);

        setVisible(true);
    }

    private void extractHostAndPass(String selectedFile) {

    }

    private void setLoadingMessage(String message) {
        loadingLabel.setText(message);
        loadingLabel.repaint();
    }

    private void clearLoadingMessage() {
        loadingLabel.setText("");
    }

    private void clearSubmitMessage() {
        loadingLabel.repaint();
        loadingLabel.setText("");
        resultLabel.repaint();
        resultLabel.setText("");
    }

    private String runPythonScript(String option, String filePath, String destinationPath) {
        try {
            String pythonExecutable = "";
            String pythonScript = "";
            if ("FIT".equalsIgnoreCase(option)) {
                pythonExecutable = "src/python/telnet_fit.py";
                pythonScript = "telnet_fit.py";
            } else if ("FAT".equalsIgnoreCase(option)) {
                pythonExecutable = "src/python/telnet_fat.py";
                pythonScript = "telnet_fat.py";
            } else if ("CISCO".equalsIgnoreCase(option)) {
                pythonExecutable = "src/python/telnet_cisco.py";
                pythonScript = "telnet_ciscot.py";
            } else {
                resultLabel.setText("タイプを選択してください");
                resultLabel.setFont(new Font("MS Gothic", Font.PLAIN, 20));
                resultLabel.setForeground(Color.RED);
                return null;
            }

    
            ProcessBuilder pb = new ProcessBuilder("python", pythonExecutable, pythonScript, filePath, destinationPath);
            pb.environment().put("PYTHONPATH", pythonScript);
    
            pb.inheritIO();
    
            Process process = pb.start();
            int exitCode = process.waitFor();
    
            if (exitCode == 0) {
                resultLabel.setText("Python script executed successfully" + pythonScript);
            } else {
                resultLabel.setText("Python script encountered an error.");
            }
    
            return pythonExecutable;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
