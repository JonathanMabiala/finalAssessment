package finalAssessment;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.TreeSet;

public class App {
	 private File file;
	    private File[] fileList;
	    private Path path = Paths.get(".").toAbsolutePath().normalize();
	    private final String directory = "/APP_FILES/";
	    private static Scanner scanner;



	    public String getPath(){ // Returns the path of a file
	       return path.toString() + directory;
	    }

	    public void  getFileList() { // Fetches and lists all files in an ascending order.
	    	TreeSet<String> sortedList = new TreeSet<>();
	        file = new File(this.path.toString()+ directory);
	        fileList = file.listFiles();
	        if (fileList != null) {
	            if(fileList.length != 0){
	                for(File list: fileList )
	                    sortedList.add(list.getName());
	                System.out.println("---------------------------- File List ---------------------------------------");
	                for (String value: sortedList) {
	                    if(value.equals("App.java") || value.equals("Main.java") )
	                        continue;
	                    System.out.println("> " + value);
	                }
	            }else {
	                System.out.println("---------------------------- File List ---------------------------------------");
	                
	                System.out.println("                  NO FILES FOUND IN THE DIRECTORY                             ");

	            }
	            System.out.println("---------------------- *** End Of File List *** ------------------------------");
	            menu();
	        }

	    }

	    public void createFile(){ // Creates a file in the directory
	        System.out.println("Type in the file's name to be created with its extension(.txt, .png, etc) and hit 'enter'.");
	        scanner= new Scanner(System.in);
	        String name = scanner.nextLine();
	        Path createPath = Paths.get(path.toString()+ directory +name);
	        try {
	            // Create the empty file with default permissions, etc.
	            Files.createFile(createPath);
	            getFileList();
			    fileOperations();
	        } catch (FileAlreadyExistsException x) {
	            System.err.format("file named %s" +
	                " already exists%n", file);
	            getFileList();
			    fileOperations();
	        } catch (IOException x) {
	            // Some other sort of failure, such as permissions.
	            System.err.format("createFile error: %s%n", x);
	            getFileList();
			    fileOperations();
	        }
	    }

	    public void removeElement() { // Removes a specified file
	        System.out.println("Type in the file's name to be deleted then hit 'enter' / type 'cancel' to return to Home.");
	        scanner = new Scanner(System.in);
	        String name = scanner.nextLine();
	        Path delPath = Paths.get(path.toString()+ directory +name);
	        
	        try {
			    Files.delete(delPath);
			    System.out.println("Deleted the file: " + name);
	            getFileList();
			    fileOperations();
			} catch (NoSuchFileException x) {
			    System.err.format("%s: no such" + " file or directory%n", delPath);
			    getFileList();
			    fileOperations();
			} catch (DirectoryNotEmptyException x) {
			    System.err.format("%s not empty%n", delPath);
			    getFileList();
			    fileOperations();
			} catch (IOException x) {
			    // File permission problems are caught here.
			    System.err.println(x);
			    getFileList();
			    fileOperations();
			}
	                
	                   
	            
	        
	    }

	    public void search(){
	        System.out.println("Type a file's name with its extension(.txt, .png, etc) then hit 'enter' / type 'cancel' to return to Home.");
	        scanner = new Scanner(System.in);
	        String name = scanner.nextLine();
	        if(name.equals("cancel") ) {
	            menu();
	        }else {
	        file = new File(path + directory + name);

	        if(file.exists()){
	            System.out.println("--------------------------------  Found File  -------------------------------");
	            System.out.println(file.getName());
	            System.out.println("---------------------------- **** Found File **** ---------------------------");
	            fileOperations();
	        }else{
	            System.out.println("--------------------------------  File Not Found  ---------------------------");
	            System.out.println("No element named " + name);
	            System.out.println("---------------------------- **** File Not Found **** -----------------------");
	            search();
	        }
	        }
	    }


	    public void load(){
	        System.out.println(" --------------------------------------------------------------------------------------");
	        System.out.println(" --------------------------------------------------------------------------------------");
	        System.out.println("|                                  Welcome to LockedMe                                 |");
	        System.out.println("|                                 Developed by Jonathan                                |");
	        System.out.println(" --------------------------------------------------------------------------------------");
	        System.out.println(" --------------------------------------------------------------------------------------");
	        System.out.println("");
	        System.out.println("");
	        try {
	            menu();
	        }catch ( Exception e){
	            System.out.println("Bad input try again");
	            menu();
	        }


	    }


	    public void menu(){

	        System.out.println("1) List all files in the current directory");
	        System.out.println("2) File Operations (Add , Delete and Search)   ");
	        System.out.println("3) Home  ");
	        System.out.println("4) Exit  ");

	        scanner = new Scanner(System.in);
	        int option = scanner.nextInt();

	        switch (option){
	            case 1 :
	                getFileList();
	                break;
	            case 2 :
	                    fileOperations();
	                break;
	            case 3:
	                load();
	            case 4 :
	                System.exit(0);
	            default:
	                System.out.println("Option does not exist.");
	                menu();
	        }
	    }

	    public void fileOperations(){
	        try {
	            System.out.println("1) Add file to the current Directory");
	            System.out.println("2) Delete a file ");
	            System.out.println("3) Search a file ");
	            System.out.println("4) Home  ");
	            System.out.println("5) Exit  ");

	            scanner = new Scanner(System.in);
	            int options = scanner.nextInt();

	            switch (options){
	                case 1 :
	                    createFile();
	                    break;
	                case 2 :
	                    removeElement();
	                    break;
	                case 3 :
	                    search();
	                    break;
	                case 4 :
	                    load();
	                    break;
	                case 5 :
	                    System.exit(0);
	                    break;
	                default:
	                    System.out.println("Option does not exist");
	                    fileOperations();

	            }
	        }catch (Exception e){
	            System.out.println("Bad input try again..");
	            fileOperations();
	        }


	    }
}
