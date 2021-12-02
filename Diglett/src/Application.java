import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 832, 670);
    }

    public void run() {
        // Your code goes here!
        MainMenu();
    }

    public void MainMenu() {
        SaxionApp.clear();
        SaxionApp.resize(1000, 530); //Resize scherm voor main menu

        //Achtergrond kleur, border uit en tekst kleur
        SaxionApp.setBackgroundColor(SaxionApp.createColor(229, 190, 228));
        SaxionApp.turnBorderOff();
        SaxionApp.setFill(Color.WHITE);

        //titel
        SaxionApp.drawBorderedText("Titel", 50, 50, 100);

        //keuzes in menu
        SaxionApp.drawBorderedText("1. New Game", 50, 200, 25);
        SaxionApp.drawBorderedText("2. Load Save", 50, 250, 25);
        SaxionApp.drawBorderedText("3. Exit", 50, 300, 25);

        //made by list
        SaxionApp.drawBorderedText("Made By: ", 690, 400, 20);
        SaxionApp.drawBorderedText("Anton Vorderman", 780, 400, 20);
        SaxionApp.drawBorderedText("Ilse Jansen", 780, 425, 20);
        SaxionApp.drawBorderedText("Sterre Liedewij", 780, 450, 20);
        SaxionApp.drawBorderedText("Jeroen Groen in't Woud",780, 475, 20);

        //menu functionaliteit
        int MenuChoice = SaxionApp.readChar();

        if (MenuChoice == '1') {
            SaxionApp.resize(832, 670);
            Mine[][] grid = createGrid();
            grid = addMineralsLvl1(grid);
            drawGrid(grid);

        } else if (MenuChoice == '2') {
            SaxionApp.resize(832, 670);

        } else if (MenuChoice == '3') {
            SaxionApp.clear();
            SaxionApp.drawBorderedText("Thank You for Playing", 125, 200, 75);
            SaxionApp.drawBorderedText("We hope to see you again", 350, 275, 25);
        }
    }

    public Mine[][] createGrid(){
        int rows = 13;
        int column = 10;
        Mine[][] grid = new Mine[rows][column]; //create grid

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){ //for loops to go through the grid and fill it

                Mine newValue = new Mine();
                newValue.rocks = (SaxionApp.getRandomValueBetween(1,4)*2); //set Rocks
                newValue.minerals = "x";
                grid[row][col] = newValue;
            }
        }
        return grid;
    }

    public Mine[][] addMineralsLvl1(Mine[][] grid){
        int randomValue = SaxionApp.getRandomValueBetween(3,6); //Amount of materials
        for(int x = 0; x < randomValue; x++){
            int randomX = SaxionApp.getRandomValueBetween(0, 12);
            int randomY = SaxionApp.getRandomValueBetween(0, 9);
            if(grid[randomX][randomY].minerals.equals("x") && grid[randomX+1][randomY].minerals.equals("x") && grid[randomX][randomY+1].minerals.equals("x") && grid[randomX+1][randomY+1].minerals.equals("x")){ //check if all of the spaces are empty
                int randomMineral = SaxionApp.getRandomValueBetween(1, 11);
                if(randomMineral <=2){
                    grid[randomX][randomY].minerals = "Iron1";
                    grid[randomX+1][randomY].minerals = "Iron2";
                    grid[randomX][randomY+1].minerals = "Iron3";
                    grid[randomX+1][randomY+1].minerals = "Iron4";
                }
                else {
                    grid[randomX][randomY].minerals = "Coal1";
                    grid[randomX+1][randomY].minerals = "Coal2";
                    grid[randomX][randomY+1].minerals = "Coal3";
                    grid[randomX+1][randomY+1].minerals = "Coal4";
                }
            }
        }
        return grid;
    }

    public void drawGrid(Mine[][] grid){
        SaxionApp.clear();
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                if(grid[row][col].rocks == 6){
                    SaxionApp.drawImage("Graphics/Steen6.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 5){
                    SaxionApp.drawImage("Graphics/Steen5.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 4){
                    SaxionApp.drawImage("Graphics/Steen4.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 3){
                    SaxionApp.drawImage("Graphics/Steen3.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 2){
                    SaxionApp.drawImage("Graphics/Steen4.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 1){
                    SaxionApp.drawImage("Graphics/Steen6.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 0){
                    SaxionApp.drawImage("Graphics/Steen6.png",row*64,col*64,64,64);
                }

            }
        }
    }

}
