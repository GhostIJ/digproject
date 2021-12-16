import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.Arrays;
import java.io.File; //File class to create new files
import java.io.FileWriter; //FileWriter class to write to files
import java.io.IOException; //IOException class to handle errors

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 832, 670);
    }

    int rows = 13+2;
    int column = 10+2;
    int randomMinerals;
    int clearedMinerals;
    int[] newItems = new int[8]; //coal, iron, copper, tin, sapphire, ruby, emerald, diamond
    Color background = SaxionApp.createColor(212, 136, 198);

    int[] inventory = new int[24];
    /*
    coalget, coalused, (0 1)
    ironoreget, ironoreused, (2 3)
    copperoreget, copperoreused, (4 5)
    tinoreget, tinoreused, (6 7)
    sapphireget, sapphireused, (8 9)
    rubyget, rubyused, (10 11)
    emeraldget, emeraldused, (12 13)
    diamondget, diamondused (14 15)
    ironbarget, ironbarused, (16 17)
    copperbarget, copperbarused, (18 19)
    tinbarget, tinbarused, (20 21)
    bronzebarget, bronzebarused, (22 23)
    */


    public void run() {
        // Your code goes here!
        MainMenu();
    }


    public void MainMenu() {

        //menu functionaliteit
        boolean menuRunning = true;
        while (menuRunning){
            SaxionApp.clear();
            SaxionApp.resize(1000, 530); //Resize scherm voor main menu

            //Achtergrond kleur, border uit en tekst kleur
            SaxionApp.setBackgroundColor(background);
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.WHITE);

            //titel
            SaxionApp.drawBorderedText("Titel", 50, 50, 100);

            //keuzes in menu
            SaxionApp.drawBorderedText("1. New Game", 50, 200, 25);
            SaxionApp.drawBorderedText("2. Load Game", 50, 250, 25);
            SaxionApp.drawBorderedText("3. Exit", 50, 300, 25);

            //made by list
            SaxionApp.drawBorderedText("Made By: ", 690, 400, 20);
            SaxionApp.drawBorderedText("Anton Vorderman", 780, 400, 20);
            SaxionApp.drawBorderedText("Ilse Jansen", 780, 425, 20);
            SaxionApp.drawBorderedText("Sterre Liedewij", 780, 450, 20);
            SaxionApp.drawBorderedText("Jeroen Groen in't Woud",780, 475, 20);

            int MenuChoice = SaxionApp.readChar();

            if (MenuChoice == '1') {    //New Game
                SaxionApp.clear();
                Arrays.fill(inventory, 0);
                SelectLevel();


            } else if (MenuChoice == '2') {     //Load game
                SaxionApp.resize(832, 670);
                loadFromFile();

            } else if (MenuChoice == '3') {     //Exit
                SaxionApp.clear();
                SaxionApp.drawBorderedText("Thank You for Playing", 125, 200, 75);
                SaxionApp.drawBorderedText("We hope to see you again", 350, 275, 25);
                menuRunning = false;
            }
        }
    }

    public void SelectLevel() {

        //Select level functionaliteit
        boolean SelectLevelRunning = true;
        while (SelectLevelRunning) {
            SaxionApp.clear();

            SaxionApp.resize(1000, 530); //Resize scherm voor Level select

            //achtergrond kleur en tekst kleur
            SaxionApp.setBackgroundColor(background);
            SaxionApp.setBorderSize(4);
            SaxionApp.setBorderColor(Color.white);
            SaxionApp.setFill(background);

            //randen rondom boxes
            SaxionApp.drawRectangle(165, 50, 650, 100);
            SaxionApp.drawRectangle(165, 175, 125,125);
            SaxionApp.drawRectangle(340, 175, 125, 125);
            SaxionApp.drawRectangle(515, 175, 125, 125);
            SaxionApp.drawRectangle(690, 175, 125, 125);
            SaxionApp.drawRectangle(5, 475 , 165, 50);
            SaxionApp.drawRectangle(165, 325, 125 , 125);
            SaxionApp.drawRectangle(340, 325, 125, 125);
            SaxionApp.drawRectangle(515, 325, 125, 125);
            SaxionApp.drawRectangle(690, 325, 125, 125);
            //SaxionApp.drawRectangle(840, 475, 155, 50);

            //design tekst
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.white);

            //tekst in boxes
            SaxionApp.drawBorderedText("Select Mine", 215, 60, 100);
            SaxionApp.drawBorderedText("1" , 192, 185, 130);
            SaxionApp.drawBorderedText("2", 367, 185, 130);
            SaxionApp.drawBorderedText("3" , 542, 185, 130);
            SaxionApp.drawBorderedText("4", 717, 185, 130);
            SaxionApp.drawBorderedText("5", 192, 335, 130);
            SaxionApp.drawBorderedText("6", 367, 335, 130);
            SaxionApp.drawBorderedText("7", 542, 335, 130);
            SaxionApp.drawBorderedText("8", 717, 335, 130);
            // SaxionApp.drawBorderedText("9.Next", 845, 480, 50);
            SaxionApp.drawBorderedText("0.Back", 10, 480, 50);


            int Levelchoice = SaxionApp.readChar();

            if (Levelchoice == '1') { //mine 1
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 1;
                createLevel(level);

            } else if (Levelchoice == '2') { //mine 2
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 2;
                createLevel(level);

            } else if (Levelchoice == '3') { // mine 3
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 3;
                createLevel(level);

            } else if (Levelchoice == '4') { // mine 4
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 4;
                createLevel(level);

            } else if (Levelchoice == '5') { // mine 5
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 5;
                createLevel(level);

            } else if (Levelchoice == '6') { // mine 6
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 6;
                createLevel(level);

            } else if (Levelchoice == '7') { // mine 7
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 7;
                createLevel(level);

            } else if (Levelchoice == '8') { // mine 8
                SaxionApp.resize(832, 640);
                SaxionApp.setBackgroundColor(SaxionApp.createColor(141, 141, 141));
                int level = 8;
                createLevel(level);

            } else if (Levelchoice == '0') {
                SelectLevelRunning = false;
            }
        }

    }

    public void createLevel(int level){

        //Play level
        Mine[][] grid = createGrid(level);
        grid = addMinerals(grid, level);
        drawGrid(grid);
        selectAndClick(grid);

        //voeg items toe aan inventory
        for(int i = 0; i<newItems.length; i++){
            inventory[(i*2)] = inventory[(i*2)] + newItems[i];
        }
        saveToFile();

        /*
        SaxionApp.clear();
        for (int j : inventory) {
            SaxionApp.printLine(j);
        }
        */
    }

    public Mine[][] createGrid(int level){
        Mine[][] grid = new Mine[rows][column]; //create grid

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){ //for loops to go through the grid and fill it
                Mine newValue = new Mine();
                int rock;

                switch (level) { //set rocks based on level
                    case 1 -> {             //level 1
                        rock = (SaxionApp.getRandomValueBetween(1, 5));
                        if (rock == 1) {
                            newValue.rocks = 1; //25%
                        } else {
                            newValue.rocks = 2; //75%
                        }
                    }
                    case 2 -> {             //level 2
                        rock = (SaxionApp.getRandomValueBetween(1, 5));
                        if (rock == 1) {
                            newValue.rocks = 1; //25%
                        } else if (rock == 2 || rock == 3) {
                            newValue.rocks = 2; //50%
                        } else {
                            newValue.rocks = 3; //25%
                        }
                    }
                }

                newValue.minerals = "x";
                newValue.cleared = false;
                grid[row][col] = newValue;
            }
        }
        return grid;
    }

    public Mine[][] addMinerals(Mine[][] grid, int level){

        randomMinerals = SaxionApp.getRandomValueBetween(3,6); //Amount of materials
        for(int x = 0; x < randomMinerals; x++){

            int randomX = SaxionApp.getRandomValueBetween(1, rows-2);
            int randomY = SaxionApp.getRandomValueBetween(1, column-2); //locatie op de grid

            int coalChance = 0;
            int ironChance = 0;
            int copperChance = 0;
            int tinChance = 0;
            int sapphireChance = 0;
            int rubyChance = 0;
            int emeraldChance = 0;
            int diamondChance = 0;

            switch (level) {
                case 1 -> { //in procenten
                    ironChance = 20; //20%
                    coalChance = 100; //80%
                }
                case 2 -> { //in procenten
                    copperChance = 10; //10%
                    ironChance = 40; //30%
                    coalChance = 100; //60%
                }
                case 3 -> {
                    tinChance = 8;
                    copperChance = 32;
                    ironChance = 64;
                    coalChance = 100;
                }
                case 4 -> {
                    tinChance = 15;
                    copperChance = 60;
                    ironChance = 100;
                }
            }

            if(grid[randomX][randomY].minerals.equals("x") && grid[randomX+1][randomY].minerals.equals("x") && grid[randomX][randomY+1].minerals.equals("x") && grid[randomX+1][randomY+1].minerals.equals("x")){ //check if all of the spaces are empty
                int randomMineral = SaxionApp.getRandomValueBetween(1, 101);
                if(randomMineral <= diamondChance){
                    grid[randomX][randomY].minerals = "Diamond1";
                    grid[randomX+1][randomY].minerals = "Diamond2";
                    grid[randomX][randomY+1].minerals = "Diamond3";
                    grid[randomX+1][randomY+1].minerals = "Diamond4";
                }
                else if(randomMineral <= emeraldChance){
                    grid[randomX][randomY].minerals = "Emerald1";
                    grid[randomX+1][randomY].minerals = "Emerald2";
                    grid[randomX][randomY+1].minerals = "Emerald3";
                    grid[randomX+1][randomY+1].minerals = "Emerald4";
                }
                else if(randomMineral <= rubyChance){
                    grid[randomX][randomY].minerals = "Ruby1";
                    grid[randomX+1][randomY].minerals = "Ruby2";
                    grid[randomX][randomY+1].minerals = "Ruby3";
                    grid[randomX+1][randomY+1].minerals = "Ruby4";
                }
                else if(randomMineral <= sapphireChance){
                    grid[randomX][randomY].minerals = "Sapphire1";
                    grid[randomX+1][randomY].minerals = "Sapphire2";
                    grid[randomX][randomY+1].minerals = "Sapphire3";
                    grid[randomX+1][randomY+1].minerals = "Sapphire4";
                }
                else if(randomMineral <= tinChance){
                    grid[randomX][randomY].minerals = "Tin1";
                    grid[randomX+1][randomY].minerals = "Tin2";
                    grid[randomX][randomY+1].minerals = "Tin3";
                    grid[randomX+1][randomY+1].minerals = "Tin4";
                }
                else if(randomMineral <=copperChance){
                    grid[randomX][randomY].minerals = "Copper1";
                    grid[randomX+1][randomY].minerals = "Copper2";
                    grid[randomX][randomY+1].minerals = "Copper3";
                    grid[randomX+1][randomY+1].minerals = "Copper4";
                }
                else if(randomMineral <= ironChance){
                    grid[randomX][randomY].minerals = "Iron1";
                    grid[randomX+1][randomY].minerals = "Iron2";
                    grid[randomX][randomY+1].minerals = "Iron3";
                    grid[randomX+1][randomY+1].minerals = "Iron4";
                }
                else if(randomMineral <= coalChance){
                    grid[randomX][randomY].minerals = "Coal1";
                    grid[randomX+1][randomY].minerals = "Coal2";
                    grid[randomX][randomY+1].minerals = "Coal3";
                    grid[randomX+1][randomY+1].minerals = "Coal4";
                }
            }
            else{
                x--;
            }
        }
        return grid;
    }

    public void drawGrid(Mine[][] grid){
        SaxionApp.clear();
        for(int row = 1; row < grid.length-1; row++){
            for(int col = 1; col < grid[row].length-1; col++){

                if(grid[row][col].minerals.equals("Iron1")){
                    SaxionApp.drawImage("Graphics/IronOre.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Coal1")){
                    SaxionApp.drawImage("Graphics/Coal.png",(row-1)*64,(col-1)*64,128,128);
                }

                if(grid[row][col].rocks == 6){
                    SaxionApp.drawImage("Graphics/Steen6.png",(row-1)*64,(col-1)*64,64,64);
                }
                else if(grid[row][col].rocks == 5){
                    SaxionApp.drawImage("Graphics/Steen5.png",(row-1)*64,(col-1)*64,64,64);
                }
                else if(grid[row][col].rocks == 4){
                    SaxionApp.drawImage("Graphics/Steen4.png",(row-1)*64,(col-1)*64,64,64);
                }
                else if(grid[row][col].rocks == 3){
                    SaxionApp.drawImage("Graphics/Steen3.png",(row-1)*64,(col-1)*64,64,64);
                }
                else if(grid[row][col].rocks == 2){
                    SaxionApp.drawImage("Graphics/Steen2.png",(row-1)*64,(col-1)*64,64,64);
                }
                else if(grid[row][col].rocks == 1){
                    SaxionApp.drawImage("Graphics/Steen1.png",(row-1)*64,(col-1)*64,64,64);
                }
                else if(grid[row][col].rocks <= 0){
                    grid[row][col].cleared = true;
                }
            }
        }
    }

    public void selectAndClick(Mine[][] grid){
        int[] coords = new int[2];
        coords[0] = (int)((rows/2)+0.5); //is 15/2 = 7.5+0.5 = 8-1 = 7
        coords[1] = (int)((column/2)+0.5)-1; //is 12/2 = 6+0.5 = 6.5-1 = 5.5(afgerond 5)
        SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0]-1)*64, (coords[1]-1)*64, 64, 64);
        boolean pickaxe = true;

        while(!checkMinerals(grid)){
            char inputC = SaxionApp.readChar();
            switch (inputC){
                case 'a':       //naar links
                    if(coords[0]>1){
                        coords[0]--;
                    }
                    break;
                case 'd':       //naar rechts
                    if(coords[0]<rows-2){
                        coords[0]++;
                    }
                    break;
                case 'w':       //naar boven
                    if(coords[1]>1){
                        coords[1]--;
                    }
                    break;
                case 's':       //naar onderen
                    if(coords[1]<column-2){
                        coords[1]++;
                    }
                    break;
                case 'e':       //gebruik pickaxe
                    if(pickaxe){
                        grid[coords[0]][coords[1]].rocks-=2;
                        grid[coords[0]-1][coords[1]].rocks--;
                        grid[coords[0]][coords[1]-1].rocks--;
                        grid[coords[0]+1][coords[1]].rocks--;
                        grid[coords[0]][coords[1]+1].rocks--;
                    }
                    else{
                        grid[coords[0]][coords[1]].rocks-=2;
                        grid[coords[0]-1][coords[1]].rocks-=2;
                        grid[coords[0]][coords[1]-1].rocks-=2;
                        grid[coords[0]+1][coords[1]].rocks-=2;
                        grid[coords[0]][coords[1]+1].rocks-=2;
                        grid[coords[0]-1][coords[1]-1].rocks--;
                        grid[coords[0]-1][coords[1]+1].rocks--;
                        grid[coords[0]+1][coords[1]-1].rocks--;
                        grid[coords[0]+1][coords[1]+1].rocks--;
                    }

                    drawGrid(grid);
                    SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0]-1)*64, (coords[1]-1)*64, 64, 64);
                    break;
                case 'q':
                    pickaxe = !pickaxe;
            }
            drawSelect(coords);
        }
    }

    public void drawSelect(int[] coords){
        SaxionApp.removeLastDraw();
        SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0]-1)*64, (coords[1]-1)*64, 64, 64);
    }

    public boolean checkMinerals(Mine[][] grid){
        Arrays.fill(newItems, 0);
        clearedMinerals = 0;
        for(int row = 1; row < grid.length-1; row++) {
            for (int col = 1; col < grid[row].length - 1; col++) {

                switch (grid[row][col].minerals) {
                    case "Coal1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[0]++;
                        }
                        break;
                    case "Iron1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[1]++;
                        }
                        break;
                    case "Copper1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[2]++;
                        }
                        break;
                    case "Tin1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[3]++;
                        }
                        break;
                    case "Sapphire1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[4]++;
                        }
                        break;
                    case "Ruby1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[5]++;
                        }
                        break;
                    case "Emerald1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[6]++;
                        }
                        break;
                    case "Diamond1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[7]++;
                        }
                        break;
                }
            }
        }
        return clearedMinerals == randomMinerals;
    }

    public void saveToFile(){

        SaxionApp.clear();

        boolean savingData = true;
        while (savingData){
            int fileNumber = 0;
            boolean saving = true;
            while (saving){
                SaxionApp.printLine("Which file do you want to save in?(1,2 or 3. Press E to exit)");
                switch (SaxionApp.readChar()) {
                    case '1' -> {
                        fileNumber = 1;
                        saving = false;
                    }
                    case '2' -> {
                        fileNumber = 2;
                        saving = false;
                    }
                    case '3' -> {
                        fileNumber = 3;
                        saving = false;
                    }
                    case 'e' -> {
                        saving = false;
                        savingData = false;
                    }
                    default -> SaxionApp.printLine("Please choose 1, 2, 3 or E");
                }
            }
            if(fileNumber != 0){
                String filename = "Savefile"+fileNumber+".csv";
                try {
                    File Save = new File(filename);
                    if(Save.createNewFile()) { //wanneer er nog geen save bestand was
                        try {
                            FileWriter SaveWrite = new FileWriter(filename);
                            StringBuilder toWrite = new StringBuilder();
                            for(int i = 0; i < inventory.length; i++){
                                if(i == inventory.length-1){
                                    toWrite.append(inventory[i]);
                                }
                                else {
                                    toWrite.append(inventory[i]).append(",");
                                }
                            }
                            SaveWrite.write(String.valueOf(toWrite));
                            SaveWrite.close();
                        }
                        catch (IOException f){
                            SaxionApp.printLine();
                            SaxionApp.printLine("An error occured while trying to save your data");
                            f.printStackTrace();
                        }

                        SaxionApp.printLine();
                        SaxionApp.printLine("Data saved to save file \""+fileNumber+"\"");
                        savingData = false;
                    }
                    else { //wanneer er al een save bestand bestaat
                        SaxionApp.printLine();
                        SaxionApp.printLine("Save file is already in use");
                        SaxionApp.printLine("Do you want to overwrite the file? (y/n)");
                        if(SaxionApp.readChar() == 'y'){
                            try {
                                FileWriter SaveWrite = new FileWriter(filename);
                                StringBuilder toWrite = new StringBuilder();
                                for(int i = 0; i < inventory.length; i++){
                                    if(i == inventory.length-1){
                                        toWrite.append(inventory[i]);
                                    }
                                    else {
                                        toWrite.append(inventory[i]).append(",");
                                    }
                                }
                                SaveWrite.write(String.valueOf(toWrite));
                                SaveWrite.close();
                            }
                            catch (IOException f){
                                SaxionApp.printLine();
                                SaxionApp.printLine("An error occured while trying to save your data");
                                f.printStackTrace();
                            }

                            SaxionApp.printLine();
                            SaxionApp.printLine("Data saved to save file \""+fileNumber+"\"");
                            savingData = false;
                        }
                        else{
                            SaxionApp.printLine("Data wasn't saved");
                        }
                    }
                }
                catch (IOException e) {
                    SaxionApp.printLine();
                    SaxionApp.printLine("An error occured while creating the file.");
                    e.printStackTrace();
                }
            }
        }
        SaxionApp.printLine("Press any key to continue");
        SaxionApp.readChar();
    }

    public void loadFromFile(){

        SaxionApp.clear();

        int fileNumber = 0;
        boolean loadingData = true;
        while(loadingData){
            boolean loading = true;
            while(loading){
                SaxionApp.printLine("Which file do you want to load from?(1,2 or 3. Press E to exit)");
                switch (SaxionApp.readChar()){
                    case '1' -> {
                        fileNumber = 1;
                        loading = false;
                    }
                    case '2' -> {
                        fileNumber = 2;
                        loading = false;
                    }
                    case '3' -> {
                        fileNumber = 3;
                        loading = false;
                    }
                    case 'e' -> {
                        loading = false;
                        loadingData = false;
                    }
                    default -> SaxionApp.printLine("Please choose 1, 2, 3 or E");
                }
            }
            if(fileNumber != 0){
                String filename = "Savefile"+fileNumber+".csv";
                CsvReader readFile = new CsvReader(filename);
                readFile.setSeparator(',');
                readFile.loadRow();
                for(int i = 0; i<inventory.length; i++){
                    inventory[i] = readFile.getInt(i);
                }
                loadingData = false;
            }
        }
        SaxionApp.printLine("Current amount of coal is: "+inventory[0]);
        SaxionApp.pause();
    }
}
