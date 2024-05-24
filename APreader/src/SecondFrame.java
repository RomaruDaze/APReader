import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SecondFrame extends JFrame {
    private JTextField filePathField, destinationField;
    private JLabel resultLabel;
    private JLabel loadingLabel;

    public SecondFrame() {
        setTitle("AP Reader V.1.0.3");
        setSize(600, 600);
        setMinimumSize(new Dimension(400, 300));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setBackground(Color.CYAN);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1, 0, 0)); // Changed to 4 rows

        int marginSize = 10;

        // File Panel
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
        clearButton1.setBorder(new EmptyBorder(5, 10, 5, 10));
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

        // Destination Panel
        JPanel destinationPanel = new JPanel();
        destinationPanel.setLayout(new BorderLayout());
        JPanel destinationPathPanel = new JPanel(new BorderLayout(5, 5));
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
        clearButton2.setBorder(new EmptyBorder(5, 10, 5, 10));
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
        
        
        topPanel.add(filePanel);
        topPanel.add(destinationPanel);
        add(topPanel, BorderLayout.NORTH);


//SECOND PANEL

        JPanel secondPanel = new JPanel();
        secondPanel.setLayout(new GridLayout(1, 1, 0, 0)); 


        // Custom Text Panel (Multi-line text area)
        JPanel customTextPanel = new JPanel(new BorderLayout());
        JPanel customTextAreaPanel = new JPanel(new BorderLayout(5, 5));

        JLabel customTextLabel = new JLabel("コマンドライン:");
        customTextLabel.setFont(new Font("MS Gothic", Font.BOLD, 24));
        JTextArea customTextArea = new JTextArea(3, 20);
        customTextArea.setFont(new Font("MS Gothic", Font.BOLD, 24));

        JScrollPane customScrollPane = new JScrollPane(customTextArea);


        customTextAreaPanel.add(customScrollPane);

        // Add components to panels
        customTextPanel.add(customTextLabel, BorderLayout.NORTH);
        customTextPanel.add(customTextAreaPanel, BorderLayout.CENTER);



        // Add components to panels
        filePathPanel.add(filePathField, BorderLayout.CENTER);
        JPanel fileButtonPanel = new JPanel();
        fileButtonPanel.add(browseButton);
        fileButtonPanel.add(clearButton1);
        filePathPanel.add(fileButtonPanel, BorderLayout.EAST);
        filePanel.add(fileLabel, BorderLayout.NORTH);
        filePanel.add(filePathPanel, BorderLayout.CENTER);

        destinationPathPanel.add(destinationField, BorderLayout.CENTER);
        JPanel destinationButtonPanel = new JPanel();
        destinationButtonPanel.add(destinationBrowseButton);
        destinationButtonPanel.add(clearButton2);
        destinationPathPanel.add(destinationButtonPanel, BorderLayout.EAST);
        destinationPanel.add(destinationLabel, BorderLayout.NORTH);
        destinationPanel.add(destinationPathPanel, BorderLayout.CENTER);


        // Set borders
        filePanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));
        destinationPathPanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));
        customTextPanel.setBorder(new EmptyBorder(marginSize, marginSize, marginSize, marginSize));

        // Add panels to the top panel

        secondPanel.add(customTextPanel); // Added customTextPanel

        add(secondPanel, BorderLayout.CENTER);

        
//THIRD PANEL
        JPanel thirdPanel = new JPanel();
        thirdPanel.setLayout(new GridLayout(2, 1, 0, -50)); // Changed to 4 rows

        JPanel labelPanel = new JPanel();
        
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("MS Gothic", Font.BOLD, 14));
        resultLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        loadingLabel = new JLabel("");
        loadingLabel.setFont(new Font("MS Gothic", Font.BOLD, 14));
        loadingLabel.setBorder(new EmptyBorder(0, 20, 0, 0));


        JPanel buttonPanel = new JPanel();

        JButton submitButton = new JButton("実行");

        submitButton.setFont(new Font("MS Gothic", Font.BOLD, 70));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBackground(new Color(0, 190, 0));
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        submitButton.addActionListener(e -> {
            String filePath = filePathField.getText();
            String destinationPath = destinationField.getText();
            String customText = customTextArea.getText(); // Get the multi-line custom text
            clearSubmitMessage();
            setLoadingMessage("Compiling...");
            
            new Thread(() -> {
                runPythonScript(filePath, customText, destinationPath);
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

        setVisible(true);

        JButton saveButton = new JButton("保存");  // Create the "Save" button
        saveButton.setFont(new Font("MS Gothic", Font.BOLD, 70));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(new Color(0, 0, 190));
        saveButton.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        saveButton.addActionListener(e -> {
            String filePath = filePathField.getText();
            String destinationPath = destinationField.getText();
            runSavePythonScript(filePath, destinationPath);
        });


        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        labelPanel.add(loadingLabel);
        labelPanel.add(resultLabel);
        thirdPanel.add(labelPanel, BorderLayout.BEFORE_LINE_BEGINS);

        buttonPanel.add(submitButton,BorderLayout.SOUTH);
        buttonPanel.add(saveButton,BorderLayout.SOUTH); 
        thirdPanel.add(buttonPanel);
        
        add(thirdPanel, BorderLayout.SOUTH);

        

    }
//FINAL

    private void extractHostAndPass(String selectedFile) {
        // Implement your code here
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

    private String runPythonScript(String filePath, String customText, String destinationPath) {
        try {
            String pythonScript = "src/python/telnet_text.py"; // Update the path to your Python script

            ProcessBuilder pb = new ProcessBuilder("python", pythonScript, filePath, destinationPath, customText);
            pb.environment().put("PYTHONPATH", pythonScript);

            pb.inheritIO();

            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                resultLabel.setText("Python script executed successfully: " + pythonScript);
            } else {
                resultLabel.setText("Python script encountered an error.");
            }

            return pythonScript;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void runSavePythonScript(String filePath, String destinationPath) {
        try {
            String pythonScript = "src/python/save.py"; // Update the path to your "save.py" Python script
    
            ProcessBuilder pb = new ProcessBuilder("python",  pythonScript, filePath, destinationPath);
            pb.environment().put("PYTHONPATH", pythonScript);
    
            pb.inheritIO();
    
            Process process = pb.start();
            int exitCode = process.waitFor();
    
            if (exitCode == 0) {
                resultLabel.setText("Save Complete");
            } else {
                resultLabel.setText("Python script encountered an error.");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SecondFrame();
        });
    }
}





