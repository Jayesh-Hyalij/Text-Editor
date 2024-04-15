import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import static java.io.BufferedReader.*;

public class TextEditor implements ActionListener {
    //Declaring properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;
    JTextArea textArea;

    JMenu file, edit;

    //file menu item
    JMenuItem newFile, openFile, saveFile;
    //edit menu item
    JMenuItem cut, copy, paste, selectAll, close;

    TextEditor (){
        //Initialize the frame
        frame = new JFrame();

        //Initialize the menubar
        menuBar = new JMenuBar();

        //Initialize textArea
        textArea = new JTextArea();

        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //Initialize menuItems to file
        newFile = new JMenuItem("New Window");
        openFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //add actionListener to file menuItems
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //add menuItem to file
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize menuItems to edit and add menuItems to edit add actionListener to edit menuItems
        cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);
        paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);
        selectAll = new JMenuItem("Select All");
        selectAll.addActionListener(this);
        edit.add(selectAll);
        close = new JMenuItem("Close");
        close.addActionListener(this);
        edit.add(close);

        //Set menuBar to frame
        frame.setJMenuBar(menuBar);
        //Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //add text area to panel
        panel.add(textArea, BorderLayout.CENTER);
        //create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //add scroll pane to panel
        panel.add(scrollPane);
        //add panel to frame
        frame.add(panel);

        //Set dimension of frame
        frame.setSize(400,400);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) / 2;
        frame.setTitle("Text Editor");
        frame.setLocation(x, y);
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            //performed cut operation
            textArea.cut();
        }
        if (actionEvent.getSource() == copy) {
            //performed copy operation
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            //performed paste operation
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            //performed selectAll operation
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            //performed close operation
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {
            //open file chooser
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\dell\\OneDrive\\Documents");
            int chooseOption = fileChooser.showOpenDialog(null);
            //if we have clicked on open button
            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                //getting selected file
                File file = fileChooser.getSelectedFile();
                //get the path of selected file
                String filePath = file.getPath();
                try{
                    //initiliaze file reader
                    FileReader fileReader = new FileReader(filePath);
                    //initilize BufferReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file line by line
                    while((intermediate = bufferedReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }
                    //set the output string to textArea
                    textArea.setText(output);
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile) {
            //initialize file picker
            JFileChooser fileChooser = new JFileChooser("C:\\Users\\dell\\OneDrive\\Documents");
            //get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //checked if we clicked save button
            if(chooseOption == JFileChooser.APPROVE_OPTION) {
                //create a new file with chosen directory path and file name
                File file = new File (fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    //initialize file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //initialize Buffer writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write contents of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}