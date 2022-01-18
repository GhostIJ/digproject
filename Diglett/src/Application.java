import nl.saxion.app.CsvReader;
import nl.saxion.app.SaxionApp;

import java.awt.*;
import java.util.Arrays;
import java.io.File; //File class to create new files
import java.io.FileWriter; //FileWriter class to write to files
import java.io.IOException; //IOException class to handle errors

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 1072, 680);
    }

    int rows = 13+2;
    int column = 10+2;
    int randomMinerals;
    int clearedMinerals;
    int[] newItems = new int[9]; //coal, iron, copper, tin, sapphire, ruby, emerald, diamond, holy stone
    Color background = SaxionApp.createColor(212, 136, 198);
    boolean runAfterLoadSave = true;
    boolean pickaxe = true;

    int[] inventory = new int[26];
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
    holy stone (24)
    pickaxe level (25)
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

            //Achtergrond kleur, border uit en tekst kleur
            SaxionApp.setBackgroundColor(background);
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.WHITE);

            //titel
            SaxionApp.drawBorderedText("Titel", 50, 50, 100);

            //keuzes in menu
            SaxionApp.drawBorderedText("1 New Game", 50, 250, 35);
            SaxionApp.drawBorderedText("2 Load Game", 50, 310, 35);
            SaxionApp.drawBorderedText("3 Exit Game", 50, 370, 35);

            //made by list
            SaxionApp.drawBorderedText("Made By: ", 760, 575, 20);
            SaxionApp.drawBorderedText("Anton Vorderman", 850, 575, 20);
            SaxionApp.drawBorderedText("Ilse Jansen", 850, 600, 20);
            SaxionApp.drawBorderedText("Sterre Liedewij", 850, 625, 20);
            SaxionApp.drawBorderedText("Jeroen Groen in't Woud",850, 650, 20);

            int MenuChoice = SaxionApp.readChar();

            if (MenuChoice == '1') {    //New Game
                SaxionApp.clear();
                Arrays.fill(inventory, 0);
                GameMenu();


            } else if (MenuChoice == '2') {     //Load game
                drawLoadSave(true);
                if(runAfterLoadSave){
                    GameMenu();
                }

            } else if (MenuChoice == '3') {     //Exit
                SaxionApp.clear();
                SaxionApp.drawBorderedText("Thank You for Playing", 171, 275, 75);
                SaxionApp.drawBorderedText("We hope to see you again", 396, 350, 25);
                menuRunning = false;
            } else if (MenuChoice == 't') {     //Test
                boolean blacksmithRunning = true;
                while (blacksmithRunning){
                    DrawBlacksmith();
                    blacksmithRunning = BlacksmithClick();
                }

            }
        }
    }

    /*
    Load en save
    */
    public void drawLoadSave(boolean isLoad){
        boolean selectLoadSave = true;
        while (selectLoadSave){
            SaxionApp.clear();

            //achtergrond kleur en tekst kleur
            SaxionApp.setBackgroundColor(background);
            SaxionApp.setBorderSize(4);
            SaxionApp.setBorderColor(Color.white);
            SaxionApp.setFill(background);

            SaxionApp.drawRectangle(211, 125, 650, 100);
            SaxionApp.drawRectangle(211, 275, 200, 200);
            SaxionApp.drawRectangle(436, 275, 200, 200);
            SaxionApp.drawRectangle(661, 275, 200, 200);
            SaxionApp.drawRectangle(5, 625 , 165, 50);

            //design tekst
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.white);

            //tekst in boxes
            if(isLoad){
                SaxionApp.drawBorderedText("Load File", 321, 135, 100);
            }
            else {
                SaxionApp.drawBorderedText("Save File", 321, 135, 100);
            }

            SaxionApp.drawBorderedText("1", 261, 305, 175);
            SaxionApp.drawBorderedText("2", 486, 305, 175);
            SaxionApp.drawBorderedText("3", 711, 305, 175);
            SaxionApp.drawBorderedText("0 Back", 10, 630, 50);

            if(isLoad){
                selectLoadSave = loadFromFile();
            }
            else {
                selectLoadSave = saveToFile();
            }
        }
    }

    public boolean saveToFile(){
        boolean savingData = true;
        while (savingData){
            int fileNumber = 0;
            boolean saving = true;
            while (saving){
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
                    case '0' -> {
                        saving = false;
                        savingData = false;
                    }
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
                            SaxionApp.pause();
                            SaxionApp.removeLastPrint();
                            f.printStackTrace();
                        }

                        SaxionApp.printLine();
                        SaxionApp.printLine("Data saved to save file \""+fileNumber+"\". Press any key to continue.");
                        SaxionApp.readChar();
                        SaxionApp.removeLastPrint();
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
                                SaxionApp.pause();
                                SaxionApp.removeLastPrint();
                                f.printStackTrace();
                            }

                            SaxionApp.printLine();
                            SaxionApp.printLine("Data saved to save file \""+fileNumber+"\". Press any key to continue.");
                            SaxionApp.readChar();
                            SaxionApp.removeLastPrint();
                            savingData = false;
                        }
                        else{
                            SaxionApp.printLine("Data wasn't saved. Press any key to continue.");
                            SaxionApp.readChar();
                            SaxionApp.removeLastPrint();
                        }
                    }
                }
                catch (IOException e) {
                    SaxionApp.printLine();
                    SaxionApp.printLine("An error occured while creating the file.");
                    SaxionApp.pause();
                    SaxionApp.removeLastPrint();
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean loadFromFile(){
        int fileNumber = 0;
        boolean loadingData = true;
        while(loadingData){
            boolean loading = true;
            while(loading){
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
                    case '0' -> {
                        loading = false;
                        loadingData = false;
                        runAfterLoadSave = false;
                    }
                }
            }
            if(fileNumber != 0){

                String filename = "Savefile"+fileNumber+".csv";
                File tempFile = new File(filename);
                if(tempFile.exists()){
                    CsvReader readFile = new CsvReader(filename);
                    readFile.setSeparator(',');
                    readFile.loadRow();
                    for(int i = 0; i<inventory.length; i++){
                        inventory[i] = readFile.getInt(i);
                    }
                    loadingData = false;
                    SaxionApp.printLine("File loaded successfully! Press any key to continue.");
                    SaxionApp.readChar();
                }
                else {
                    SaxionApp.printLine("There wasn't a file loaded in slot "+fileNumber+". Press any key to continue.");
                    SaxionApp.readChar();
                    SaxionApp.removeLastPrint();
                }
            }
        }
        return false;
    }
    /*
    Load en save einde
    */

    /*
    Blacksmith
    */

    public void DrawBlacksmith(){
        SaxionApp.clear();

        SaxionApp.setBackgroundColor(background);
        SaxionApp.setBorderSize(4);
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.setFill(background);

        SaxionApp.drawRectangle(25, 75, 680, 100);
        SaxionApp.drawRectangle(25, 185, 680, 75);
        SaxionApp.drawRectangle(25, 270, 680, 75);
        SaxionApp.drawRectangle(5, 625, 165, 50);

        //design tekst
        SaxionApp.turnBorderOff();
        SaxionApp.setFill(Color.white);

        SaxionApp.drawBorderedText("Blacksmith", 40, 92, 75);
        SaxionApp.drawBorderedText("1 Smelting",40, 199, 55);
        SaxionApp.drawBorderedText("2 Smithing",40, 284, 55);
        SaxionApp.drawBorderedText("0 Back", 10, 630, 50);
    }

    public boolean BlacksmithClick(){
        switch(SaxionApp.readChar()){
            case '1':
                boolean smeltingRunning = true;
                while (smeltingRunning){
                    DrawSmelting();
                    smeltingRunning = SmeltingClick();
                }
                break;
            case '2':
                boolean smithingRunning = true;
                while (smithingRunning){
                    DrawSmithing();
                    smithingRunning = SmithingClick();
                }
                break;
            case '0':
                return false;
        }
        return true;
    }

    public void DrawSmelting(){
        SaxionApp.clear();

        //achtergrond kleur en tekst kleur
        SaxionApp.setBackgroundColor(background);
        SaxionApp.setBorderSize(4);
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.setFill(background);

        SaxionApp.drawRectangle(25, 75, 680, 100);
        SaxionApp.drawRectangle(25, 185, 680, 75);
        SaxionApp.drawRectangle(25, 270, 680, 75);
        SaxionApp.drawRectangle(25, 355, 680, 75);
        SaxionApp.drawRectangle(25, 440, 680, 75);
        SaxionApp.drawRectangle(5, 625, 165, 50);

        //design tekst
        SaxionApp.turnBorderOff();
        SaxionApp.setFill(Color.white);

        SaxionApp.drawBorderedText("Smelting", 40, 92, 75);
        SaxionApp.drawBorderedText("1 Iron Ingot",40, 199, 55);
        SaxionApp.drawBorderedText("2 Copper Ingot",40, 284, 55);
        SaxionApp.drawBorderedText("3 Tin Ingot",40, 369, 55);
        SaxionApp.drawBorderedText("4 Bronze Ingot",40, 454, 55);

        //Draw coal & coal values
        for(int i = 0; i<=255; i+=85){ //loop voor al het coal, steeds 85 pixels lager (op de y-as elke keer +85)
            SaxionApp.drawImage("Graphics/Coal.png",410, 192+i, 60, 60);
            if(inventory[0]-inventory[1] < 1){
                SaxionApp.setFill(Color.red);
            }
            SaxionApp.drawBorderedText("1",465,203+i,50);
            SaxionApp.setFill(Color.white);
        }

        //Draw iron, copper and tin ores and values.
        int j = 2;
        for(int i = 0; i<=170; i+=85){
            switch (j) {
                case 2 -> SaxionApp.drawImage("Graphics/IronOre.png", 500, 190 + i, 70, 70);
                case 4 -> SaxionApp.drawImage("Graphics/CopperOre.png", 500, 190 + i, 70, 70);
                case 6 -> SaxionApp.drawImage("Graphics/TinOre.png", 500, 190 + i, 70, 70);
            }
            if(inventory[j]-inventory[j+1] < 3){
                SaxionApp.setFill(Color.red);
            }
            SaxionApp.drawBorderedText("3",560,203+i,50);
            SaxionApp.setFill(Color.white);

            j+=2;
        }

        //Draw Copper and tin ingots and their values for bronze
        SaxionApp.drawImage("Graphics/CopperIngot.png",500, 445, 70, 70);
        if(inventory[18]-inventory[19] < 3){
            SaxionApp.setFill(Color.red);
        }
        SaxionApp.drawBorderedText("3",560,458,50);
        SaxionApp.setFill(Color.white);


        SaxionApp.drawImage("Graphics/TinIngot.png",600, 445, 70, 70);
        if(inventory[19]-inventory[20] < 1){
            SaxionApp.setFill(Color.red);
        }
        SaxionApp.drawBorderedText("1",660,458,50);
        SaxionApp.setFill(Color.white);

        //inventory draw
        SaxionApp.drawBorderedText("Inventory", 720, 92, 60);
        SaxionApp.drawImage("Graphics/IronIngot.png", 705, 173, 100, 100);
        SaxionApp.drawImage("Graphics/CopperIngot.png", 705, 258, 100, 100);
        SaxionApp.drawImage("Graphics/TinIngot.png", 705, 343, 100, 100);
        SaxionApp.drawImage("Graphics/BronzeIngot.png", 705, 428, 100, 100);

        SaxionApp.drawImage("Graphics/Coal.png", 840, 173, 100, 100);
        SaxionApp.drawImage("Graphics/IronOre.png", 840, 258, 100, 100);
        SaxionApp.drawImage("Graphics/CopperOre.png", 840, 343, 100, 100);
        SaxionApp.drawImage("Graphics/TinOre.png", 840, 428, 100, 100);
        int a = 16;
        int b = 0;
        for(int i = 199; i<=454; i+=85){
            if(inventory[a]-inventory[a+1] > 99){
                SaxionApp.drawBorderedText("99",790, i, 50);
            }
            else {
                SaxionApp.drawBorderedText(String.valueOf(inventory[a]-inventory[a+1]),790, i, 50);
            }
            if(inventory[b]-inventory[b+1] > 99){
                SaxionApp.drawBorderedText("99",925, i, 50);
            }
            else {
                SaxionApp.drawBorderedText(String.valueOf(inventory[b]-inventory[b+1]),925, i, 50);
            }
            a+=2;
            b+=2;
        }
        SaxionApp.drawBorderedText("0 Back", 10, 630, 50);
    }

    public boolean SmeltingClick(){
        switch(SaxionApp.readChar()){
            case '1':
                if(inventory[0]-inventory[1] >= 1 && inventory[2]-inventory[3] >= 3){
                    inventory[1]++;
                    inventory[3]+=3;
                    inventory[16]+=3;
                }
                break;
            case '2':
                if(inventory[0]-inventory[1] >= 1 && inventory[4]-inventory[5] >= 3){
                    inventory[1]++;
                    inventory[5]+=3;
                    inventory[18]+=3;
                }
                break;
            case '3':
                if(inventory[0]-inventory[1] >= 1 && inventory[6]-inventory[7] >= 3){
                    inventory[1]++;
                    inventory[7]+=3;
                    inventory[20]+=3;
                }
                break;
            case '4':
                if(inventory[0]-inventory[1] >= 1 && inventory[18]-inventory[19] >= 3 && inventory[20]-inventory[21] >=1){
                    inventory[1]++;
                    inventory[19]+=3;
                    inventory[21]+=1;
                    inventory[22]+=4;
                }
                break;
            case '0':
                return false;
        }
        return true;
    }

    public void DrawSmithing(){
        SaxionApp.clear();

        //achtergrond kleur en tekst kleur
        SaxionApp.setBackgroundColor(background);
        SaxionApp.setBorderSize(4);
        SaxionApp.setBorderColor(Color.white);
        SaxionApp.setFill(background);

        SaxionApp.drawRectangle(25, 75, 680, 100);
        SaxionApp.drawRectangle(25, 185, 680, 75);
        SaxionApp.drawRectangle(25, 270, 680, 75);
        SaxionApp.drawRectangle(25, 355, 680, 75);
        SaxionApp.drawRectangle(5, 625, 165, 50);

        //design tekst
        SaxionApp.turnBorderOff();
        SaxionApp.setFill(Color.white);

        SaxionApp.drawBorderedText("Smithing", 40, 92, 75);
        SaxionApp.drawBorderedText("1 Iron pickaxe",40, 199, 55);
        SaxionApp.drawBorderedText("2 Bronze pickaxe",40, 284, 55);
        SaxionApp.drawBorderedText("3 Diamond pickaxe",40, 369, 55);

        SaxionApp.drawImage("Graphics/IronIngot.png", 510, 190, 70, 70);
        SaxionApp.drawImage("Graphics/BronzeIngot.png", 510, 275, 70, 70);
        SaxionApp.drawImage("Graphics/Diamond.png", 510, 360, 70, 70);

        if((inventory[16]-inventory[17]) < 10){ //iron ingot
            SaxionApp.setFill(Color.red);
        }
        SaxionApp.drawBorderedText("10", 570, 203, 50);
        SaxionApp.setFill(Color.white);

        if((inventory[22]-inventory[23]) < 10){ //bronze ingot
            SaxionApp.setFill(Color.red);
        }
        SaxionApp.drawBorderedText("10", 570, 288, 50);
        SaxionApp.setFill(Color.white);

        if((inventory[14]-inventory[15]) < 3){ //diamond
            SaxionApp.setFill(Color.red);
        }
        SaxionApp.drawBorderedText("3", 570, 373, 50);
        SaxionApp.setFill(Color.white);

        //inventory draw
        SaxionApp.drawBorderedText("Inventory", 720, 92, 60);
        SaxionApp.drawImage("Graphics/IronIngot.png", 705, 173, 100, 100);
        SaxionApp.drawImage("Graphics/BronzeIngot.png", 705, 258, 100, 100);
        SaxionApp.drawImage("Graphics/Diamond.png", 705, 343, 100, 100);

        if(inventory[16]-inventory[17] > 99){
            SaxionApp.drawBorderedText("99", 790, 199, 50);
        }
        else{
            SaxionApp.drawBorderedText(String.valueOf(inventory[16]-inventory[17]),790, 199, 50);
        }
        if(inventory[22]-inventory[23] > 99){
            SaxionApp.drawBorderedText("99", 790, 284, 50);
        }
        else{
            SaxionApp.drawBorderedText(String.valueOf(inventory[22]-inventory[23]),790, 284, 50);
        }
        if(inventory[14]-inventory[15] > 99){
            SaxionApp.drawBorderedText("99", 790, 369, 50);
        }
        else{
            SaxionApp.drawBorderedText(String.valueOf(inventory[14]-inventory[15]),790, 369, 50);
        }

        SaxionApp.drawBorderedText("0 Back", 10, 630, 50);
    }

    public boolean SmithingClick(){
        switch(SaxionApp.readChar()){
            case '1':
                if(inventory[16]-inventory[17] >= 10){
                    inventory[25] = 1;
                    inventory[17]+=10;
                }
                break;
            case '2':
                if(inventory[22]-inventory[23] >= 10){
                    inventory[25] = 2;
                    inventory[23]+=10;
                }
                break;
            case '3':
                if(inventory[14]-inventory[15] >= 3){
                    inventory[25] = 1;
                    inventory[17]+=3;
                }
                break;
            case '0':
                return false;
        }
        return true;
    }

    /*
    Mining levels
    */
    public void GameMenu() {

        boolean SelectGameMenu = true;
        while (SelectGameMenu) {
            SaxionApp.clear();

            SaxionApp.setBackgroundColor(background);
            SaxionApp.setBorderSize(4);
            SaxionApp.setBorderColor(Color.white);
            SaxionApp.setFill(background);

            SaxionApp.drawRectangle(50, 50, 550, 105);
            SaxionApp.drawRectangle(50, 180, 550, 105);
            SaxionApp.drawRectangle(50, 310, 550, 105);
            SaxionApp.drawRectangle(50, 440, 550, 105);

            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.white);

            SaxionApp.drawBorderedText("1", 60, 65, 90);
            SaxionApp.drawBorderedText("2", 60, 195, 90);
            SaxionApp.drawBorderedText("3", 60, 325, 90);
            SaxionApp.drawBorderedText("4", 60, 455, 90);
            SaxionApp.drawBorderedText("Mines", 150, 65, 90);
            SaxionApp.drawBorderedText("Inventory", 150, 190, 90);
            SaxionApp.drawBorderedText("Blacksmith", 150, 325, 90);
            SaxionApp.drawBorderedText("Options", 150, 450, 90);


            int GameMenuChoice = SaxionApp.readChar();

            if (GameMenuChoice == '1') { //Mines
                SelectLevel();
            } else if (GameMenuChoice == '2') { //Inventory

            } else if (GameMenuChoice == '3') { //Blacksmith
                boolean blacksmithRunning = true;
                while (blacksmithRunning) {
                    DrawBlacksmith();
                    blacksmithRunning = BlacksmithClick();
                }
            } else if (GameMenuChoice == '4') { //Options
                Options();
                SelectGameMenu = false;
            } /*else if (GameMenuChoice == '0') { //exit game test
                SelectGameMenu = false;
            }*/
        }
    }

    public void Options() {

        boolean OptionsRunning = true;
        while (OptionsRunning) {
            SaxionApp.clear();

            SaxionApp.setBackgroundColor(background);
            SaxionApp.setBorderSize(4);
            SaxionApp.setBorderColor(Color.white);
            SaxionApp.setFill(background);

            SaxionApp.drawRectangle(316, 25, 440, 135);
            SaxionApp.drawRectangle(381, 225, 310, 75);
            SaxionApp.drawRectangle(381, 325, 310, 75);
            SaxionApp.drawRectangle(381, 425, 310, 75);
            SaxionApp.drawRectangle(436, 600, 175, 50);

            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.white);

            SaxionApp.drawBorderedText("Options", 326, 35, 120);
            SaxionApp.drawBorderedText("1", 386, 240, 50);
            SaxionApp.drawBorderedText("Save Game", 426, 240, 50);
            SaxionApp.drawBorderedText("2", 386, 340, 50);
            SaxionApp.drawBorderedText("Stats", 436, 340, 50);
            SaxionApp.drawBorderedText("3", 386, 440, 50);
            SaxionApp.drawBorderedText("Quit Game", 436, 440, 50);
            SaxionApp.drawBorderedText("0 Back", 446, 605, 50);

            int OptionChoice = SaxionApp.readChar();

            if (OptionChoice == '1') { //Save game
                drawLoadSave(false);
            } else if (OptionChoice =='2') { //Stats

            } else if (OptionChoice == '3') { //Quit game
                OptionsRunning = false;

            } else if (OptionChoice == '0') { //Back
                OptionsRunning = false;
                GameMenu();
            }
        }
    }

    public void SelectLevel() {

        //Select level functionaliteit
        boolean SelectLevelRunning = true;
        while (SelectLevelRunning) {
            SaxionApp.clear();

            //achtergrond kleur en tekst kleur
            SaxionApp.setBackgroundColor(background);
            SaxionApp.setBorderSize(4);
            SaxionApp.setBorderColor(Color.white);
            SaxionApp.setFill(background);

            //randen rondom boxes
            SaxionApp.drawRectangle(211, 100, 650, 100);
            SaxionApp.drawRectangle(211, 225, 125,125);
            SaxionApp.drawRectangle(386, 225, 125, 125);
            SaxionApp.drawRectangle(561, 225, 125, 125);
            SaxionApp.drawRectangle(736, 225, 125, 125);
            SaxionApp.drawRectangle(5, 625 , 165, 50);
            SaxionApp.drawRectangle(211, 375, 125 , 125);
            SaxionApp.drawRectangle(386, 375, 125, 125);
            SaxionApp.drawRectangle(561, 375, 125, 125);
            SaxionApp.drawRectangle(736, 375, 125, 125);

            //design tekst
            SaxionApp.turnBorderOff();
            SaxionApp.setFill(Color.white);

            //tekst in boxes
            SaxionApp.drawBorderedText("Select Mine", 261, 110, 100);
            SaxionApp.drawBorderedText("1" , 238, 235, 130);
            SaxionApp.drawBorderedText("2", 413, 235, 130);
            SaxionApp.drawBorderedText("3" , 588, 235, 130);
            SaxionApp.drawBorderedText("4", 763, 235, 130);
            SaxionApp.drawBorderedText("5", 238, 385, 130);
            SaxionApp.drawBorderedText("6", 413, 385, 130);
            SaxionApp.drawBorderedText("7", 588, 385, 130);
            SaxionApp.drawBorderedText("8", 763, 385, 130);
            SaxionApp.drawBorderedText("0 Back", 10, 630, 50);


            int Levelchoice = SaxionApp.readChar();

            if (Levelchoice == '1') { //mine 1
                int level = 1;
                createLevel(level);

            } else if (Levelchoice == '2') { //mine 2
                int level = 2;
                createLevel(level);

            } else if (Levelchoice == '3') { // mine 3
                int level = 3;
                createLevel(level);

            } else if (Levelchoice == '4') { // mine 4
                int level = 4;
                createLevel(level);

            } else if (Levelchoice == '5') { // mine 5
                int level = 5;
                createLevel(level);

            } else if (Levelchoice == '6') { // mine 6
                int level = 6;
                createLevel(level);

            } else if (Levelchoice == '7') { // mine 7
                int level = 7;
                createLevel(level);

            } else if (Levelchoice == '8') { // mine 8
                int level = 8;
                createLevel(level);

            } else if (Levelchoice == '9') {
                int level = 9;
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
                    case 3 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 11));
                        if (rock == 1) {
                            newValue.rocks = 1; //10%
                        } else if (rock >= 2 && rock < 5) {
                            newValue.rocks = 2; //30%
                        } else if (rock >= 5 && rock < 9) {
                            newValue.rocks = 3; //40%
                        } else {
                            newValue.rocks = 4; //20%
                        }
                    }
                    case 4 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 11));
                        if (rock == 1 || rock == 2) {
                            newValue.rocks = 2; //20%
                        } else if (rock >= 3 && rock < 6) {
                            newValue.rocks = 3; //30%
                        } else {
                            newValue.rocks = 4; //50%
                        }
                    }
                    case 5 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 101));
                        if (rock >= 1 && rock < 11) {
                            newValue.rocks = 2; //10%
                        } else if (rock >= 11 && rock < 31) {
                            newValue.rocks = 3; //20%
                        } else if (rock >= 31 && rock < 76) {
                            newValue.rocks = 4; //45%
                        } else {
                            newValue.rocks = 5; //25%
                        }
                    }
                    case 6 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 101));
                        if (rock >= 1 && rock < 26) {
                            newValue.rocks = 3; //25%
                        } else if (rock >= 26 && rock < 66) {
                            newValue.rocks = 4; //40%
                        } else {
                            newValue.rocks = 5; //35%
                        }
                    }
                    case 7 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 101));
                        if (rock >= 1 && rock < 11) {
                            newValue.rocks = 3; //10%
                        } else if (rock >= 11 && rock < 36) {
                            newValue.rocks = 4; //25%
                        } else if (rock >= 36 && rock < 71) {
                            newValue.rocks = 5; //35%
                        } else {
                            newValue.rocks = 6; //30%
                        }
                    }
                    case 8 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 11));
                        if (rock == 1) {
                            newValue.rocks = 4; //10%
                        } else if (rock == 2 || rock == 3) {
                            newValue.rocks = 5; //20%
                        } else {
                            newValue.rocks = 6; //70%
                        }
                    }
                    case 9 -> {
                        rock = (SaxionApp.getRandomValueBetween(1, 11));
                        if (rock == 1) {
                            newValue.rocks = 5; //10%
                        } else {
                            newValue.rocks = 6; //90%
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
            int holyChance = 0;

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
                    tinChance = 8; //8%
                    copperChance = 32; //24%
                    ironChance = 64; //32%
                    coalChance = 100; //36%
                }
                case 4 -> {
                    tinChance = 15; //15%
                    copperChance = 60; //45%
                    ironChance = 100; //40%
                }
                case 5 -> {
                    sapphireChance = 8; //8%
                    tinChance = 43; //35%
                    copperChance = 73; //30%
                    ironChance = 100; //27%
                }
                case 6 -> {
                    rubyChance = 6; //6%
                    sapphireChance = 24; //18%
                    tinChance = 59; //35%
                    copperChance = 100; //31%
                }
                case 7 -> {
                    emeraldChance = 5; //5%
                    rubyChance = 21; //16%
                    copperChance = 45; //24%
                    ironChance = 100; //55%
                }
                case 8 -> {
                    diamondChance = 3; //3%
                    emeraldChance = 15; //12%
                    tinChance = 45; //30%
                    coalChance = 100; //55%
                }
                case 9 -> {
                    holyChance = 1; //1%
                    diamondChance = 7; //6%
                    tinChance = 46; //40%
                    ironChance = 100; //54%
                }
            }

            if(grid[randomX][randomY].minerals.equals("x") && grid[randomX+1][randomY].minerals.equals("x") && grid[randomX][randomY+1].minerals.equals("x") && grid[randomX+1][randomY+1].minerals.equals("x")){ //check if all of the spaces are empty
                int randomMineral = SaxionApp.getRandomValueBetween(1, 101);
                if(randomMineral <= holyChance){
                    grid[randomX][randomY].minerals = "HolyStone1";
                    grid[randomX+1][randomY].minerals = "HolyStone2";
                    grid[randomX][randomY+1].minerals = "HolyStone3";
                    grid[randomX+1][randomY+1].minerals = "HolyStone4";
                }
                else if(randomMineral <= diamondChance){
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
        for(int row = 1; row < grid.length-1; row+=1) {
            for (int col = 1; col < grid[row].length - 1; col+=1) {
                SaxionApp.drawImage("Graphics/Background.png", (row - 1) * 64, (col - 1) * 64, 64, 64);
            }
        }
        for(int row = 1; row < grid.length-1; row++){
            for(int col = 1; col < grid[row].length-1; col++){

                if(grid[row][col].minerals.equals("Iron1")){
                    SaxionApp.drawImage("Graphics/IronOre.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Coal1")){
                    SaxionApp.drawImage("Graphics/Coal.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Diamond1")){
                    SaxionApp.drawImage("Graphics/Diamond.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Copper1")){
                    SaxionApp.drawImage("Graphics/CopperOre.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Tin1")){
                    SaxionApp.drawImage("Graphics/TinOre.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Emerald1")){
                    SaxionApp.drawImage("Graphics/Emerald.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Ruby1")){
                    SaxionApp.drawImage("Graphics/Ruby.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("Sapphire1")){
                    SaxionApp.drawImage("Graphics/Sapphire.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals.equals("HolyStone1")){
                    SaxionApp.drawImage("Graphics/HolyStone.png",(row-1)*64,(col-1)*64,128,128);
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
        DrawLoadingbalk();
        DrawSideButtons(grid);
    }

    public void selectAndClick(Mine[][] grid) {
        int[] coords = new int[2];
        coords[0] = (int) ((rows / 2) + 0.5); //is 15/2 = 7.5+0.5 = 8-1 = 7
        coords[1] = (int) ((column / 2) + 0.5) - 1; //is 12/2 = 6+0.5 = 6.5-1 = 5.5(afgerond 5)
        SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0] - 1) * 64, (coords[1] - 1) * 64, 64, 64);
        int Mined = 0;
        boolean IsLoadingbalkFull = false;

        while (checkMinerals(grid) != 0 && !IsLoadingbalkFull) {
            char inputC = SaxionApp.readChar();
            switch (inputC) {
                case 'a':       //naar links
                    if (coords[0] > 1) {
                        coords[0]--;
                    }
                    break;
                case 'd':       //naar rechts
                    if (coords[0] < rows - 2) {
                        coords[0]++;
                    }
                    break;
                case 'w':       //naar boven
                    if (coords[1] > 1) {
                        coords[1]--;
                    }
                    break;
                case 's':       //naar onderen
                    if (coords[1] < column - 2) {
                        coords[1]++;
                    }
                    break;
                case 'e':       //gebruik pickaxe
                    if (pickaxe) {
                        grid[coords[0]][coords[1]].rocks -= 2;
                        grid[coords[0] - 1][coords[1]].rocks--;
                        grid[coords[0]][coords[1] - 1].rocks--;
                        grid[coords[0] + 1][coords[1]].rocks--;
                        grid[coords[0]][coords[1] + 1].rocks--;
                        Mined++;
                    } else {
                        grid[coords[0]][coords[1]].rocks -= 2;
                        grid[coords[0] - 1][coords[1]].rocks -= 2;
                        grid[coords[0]][coords[1] - 1].rocks -= 2;
                        grid[coords[0] + 1][coords[1]].rocks -= 2;
                        grid[coords[0]][coords[1] + 1].rocks -= 2;
                        grid[coords[0] - 1][coords[1] - 1].rocks--;
                        grid[coords[0] - 1][coords[1] + 1].rocks--;
                        grid[coords[0] + 1][coords[1] - 1].rocks--;
                        grid[coords[0] + 1][coords[1] + 1].rocks--;
                        Mined += 2;
                    }

                    drawGrid(grid);
                    LoadingbalkUpdate(inputC, Mined);
                    if  (Mined >= 41) {
                        IsLoadingbalkFull = true;
                    }
                    SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0] - 1) * 64, (coords[1] - 1) * 64, 64, 64);
                    break;
                case 'q':
                    pickaxe = !pickaxe;
                    drawGrid(grid);
                    LoadingbalkUpdate(inputC, Mined);
                    SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0] - 1) * 64, (coords[1] - 1) * 64, 64, 64);

            }
            drawSelect(coords);
        }
    }

    public void drawSelect(int[] coords){
        SaxionApp.removeLastDraw();
        SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0]-1)*64, (coords[1]-1)*64, 64, 64);
    }

    public int checkMinerals(Mine[][] grid){
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
                    case "HolyStone1":
                        if (grid[row][col].cleared && grid[row + 1][col].cleared && grid[row][col + 1].cleared && grid[row + 1][col + 1].cleared) {
                            clearedMinerals++;
                            newItems[8]++;
                        }
                        break;
                }
            }
        }
        return randomMinerals - clearedMinerals;
    }

    public void LoadingbalkUpdate(char inputC, int Mined) {
        if (inputC == 'e' || inputC == 'q') {
            SaxionApp.setFill(Color.green);
            SaxionApp.drawRectangle(5, 645, 2 + (20 * Mined), 30);
        }
    }

    public void DrawLoadingbalk() {
        SaxionApp.setFill(Color.green);
        SaxionApp.drawRectangle(5, 645, 2, 30);
    }

    public void DrawSideButtons(Mine[][] grid){
        switch (checkMinerals(grid)) {
            case 0 -> SaxionApp.drawImage("Graphics/Counter0.png", 842, 10, 220, 220);
            case 1 -> SaxionApp.drawImage("Graphics/Counter1.png", 842, 10, 220, 220);
            case 2 -> SaxionApp.drawImage("Graphics/Counter2.png", 842, 10, 220, 220);
            case 3 -> SaxionApp.drawImage("Graphics/Counter3.png", 842, 10, 220, 220);
            case 4 -> SaxionApp.drawImage("Graphics/Counter4.png", 842, 10, 220, 220);
            case 5 -> SaxionApp.drawImage("Graphics/Counter5.png", 842, 10, 220, 220);
        }

        SaxionApp.setFill(Color.white);
        SaxionApp.drawBorderedText("Switch: Q",842, 250, 50);

        if(pickaxe){
            switch (inventory[25]) {
                case 0 -> SaxionApp.drawImage("Graphics/Pickaxe.png", 842, 290, 220, 220);
                case 1 -> SaxionApp.drawImage("Graphics/IronPickaxe.png", 842, 290, 220, 220);
                case 2 -> SaxionApp.drawImage("Graphics/BronzePickaxe.png", 842, 290, 220, 220);
                case 3 -> SaxionApp.drawImage("Graphics/DiamondPickaxe.png", 842, 290, 220, 220);
            }
        }
        else {
            switch (inventory[25]) {
                case 0 -> SaxionApp.drawImage("Graphics/Hammer.png", 842, 290, 220, 220);
                case 1 -> SaxionApp.drawImage("Graphics/IronHammer.png", 842, 290, 220, 220);
                case 2 -> SaxionApp.drawImage("Graphics/BronzeHammer.png", 842, 290, 220, 220);
                case 3 -> SaxionApp.drawImage("Graphics/DiamondHammer.png", 842, 290, 220, 220);
            }
        }
        SaxionApp.drawBorderedText("Break: E",842, 510, 50);
        SaxionApp.drawBorderedText("Move: WASD",842, 570, 35);
    }
    /*
    Mining levels einde
    */
}