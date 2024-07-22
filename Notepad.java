import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;


// Main class implementing ActionListener to handle menu actions
public class Notepad implements ActionListener{
  JFrame window;// Main window of the application
  JTextArea textArea;// Text area for writing and editing text
  JScrollPane scrollPane; // Scroll pane to add scrolling functionality to the text area
  JMenuBar menubar;// Menu bar to hold menus
  JMenu menuFile,menuEdit;// Menus for file and edit operations
  JMenuItem iNew,iOpen,iSave,iSaveAs,iExit;//Menue Item for File
  Fuctionalities fuc = new Fuctionalities(this);// Instance of functionalities class to handle file operations
   // Constructor to initialize the Notepad application
   public Notepad() {
    createWindow(); // Method to create the main window
    createTextaArea(); // Method to create the text area
    createMenueBar(); // Method to create the menu bar
    createFileMenue(); // Method to create the file menu
    window.setVisible(true); // Make the window visible
    
  }
  public static void main(String []arg){
    new Notepad();// Create an instance of Notepad
  }
    // Method to create the main window
  public void createWindow(){
    window = new JFrame("Notepad");
    window.setSize(500, 300);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  // Method to create the text area with scroll functionality
  public void createTextaArea(){
    textArea = new JTextArea();
    scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    window.add(scrollPane);
   
  }
    // Method to create the menu bar
  public void createMenueBar(){
    menubar = new JMenuBar();
    window.setJMenuBar(menubar);
   
    menuFile = new JMenu("File");
    menubar.add(menuFile);
  }
    // Method to create the file menu and add menu items
  public void createFileMenue(){
      iNew = new JMenuItem("New");
      iNew.addActionListener(this);
      iNew.setActionCommand("New");
      menuFile.add(iNew);

      iOpen = new JMenuItem("Open");
      iOpen.addActionListener(this);
      iOpen.setActionCommand("Open");
      menuFile.add(iOpen);

      iSave = new JMenuItem("Save");
      iSave.addActionListener(this);
      iSave.setActionCommand("Save");
      menuFile.add(iSave);

      iSaveAs = new JMenuItem("Save As");
      iSaveAs.addActionListener(this);
      iSaveAs.setActionCommand("Save As");
      menuFile.add(iSaveAs);

      iExit = new JMenuItem("Exit");
      iExit.addActionListener(this);
      iExit.setActionCommand("Exit");
      menuFile.add(iExit);
  }
  public void createEditMenue(){

  }
  // Method to handle menu actions
  public void actionPerformed(ActionEvent e) {
    String comnd = e.getActionCommand();

    switch (comnd) {
        case "New": fuc.newFile();break;
        case "Open": fuc.open();break;
        case "Save As": fuc.saveAs();break;
        case "Save": fuc.save();break;
        case "Exit": System.exit(0);
        break;
    
        default:
            break;
    }
}
}
// Class to handle file operations
class Fuctionalities{
    Notepad ntp;
    String FileName;
    String fileAddress;
    public Fuctionalities( Notepad ntp){
        this.ntp = ntp;
    }
     // Method to create a new file
    public void newFile(){
        ntp.textArea.setText("");
        ntp.window.setTitle("New");
        FileName = null;
        fileAddress = null;
    }
    // Method to open an existing file
    public void open(){
        FileDialog fd = new FileDialog(ntp.window,"Open",FileDialog.LOAD);
        fd.setVisible(true);
        if(fd.getFile() != null){
          FileName = fd.getFile();
          fileAddress = fd.getDirectory();
          ntp.window.setTitle(FileName);
        }
        try{
              BufferedReader br = new BufferedReader(new FileReader(fileAddress+FileName));
              ntp.textArea.setText("");
              String line = null;
              while((line = br.readLine())!=null){
                         ntp.textArea.append(line+"\n");
              }
        }catch(Exception a){
                System.out.println("file is not exist or "+a);        }
               
    }  
    // Method to save the current file
    public void save(){
       if(FileName == null){
             saveAs();
       }
       else{
        try{
            FileWriter fw = new FileWriter(FileName+fileAddress);
            fw.write(ntp.textArea.getText());
            ntp.window.setTitle(FileName);
            fw.close();
        }catch(Exception e){
            System.out.println("Somthing went wron! or "+e);
        }
       }
    }
    // Method to save the file with a new name
    public void saveAs(){
        FileDialog fd = new FileDialog(ntp.window,"Save",FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            FileName = fd.getFile();
            fileAddress = fd.getDirectory();
            ntp.window.setTitle(FileName);
        }
        try{
              FileWriter fw = new FileWriter(FileName + fileAddress);
              fw.write(ntp.textArea.getText());
              fw.close();

        }catch (Exception e){
            System.out.println("Somthing went wron! or "+e);
        }
    }
}