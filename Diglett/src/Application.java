import nl.saxion.app.SaxionApp;

import java.awt.*;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 832, 670);
    }

    int rows = 13+2;
    int column = 10+2;

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
        SaxionApp.drawBorderedText("2. Load Game", 50, 250, 25);
        SaxionApp.drawBorderedText("3. Exit", 50, 300, 25);

        //made by list
        SaxionApp.drawBorderedText("Made By: ", 690, 400, 20);
        SaxionApp.drawBorderedText("Anton Vorderman", 780, 400, 20);
        SaxionApp.drawBorderedText("Ilse Jansen", 780, 425, 20);
        SaxionApp.drawBorderedText("Sterre Liedewij", 780, 450, 20);
        SaxionApp.drawBorderedText("Jeroen Groen in't Woud",780, 475, 20);

        //menu functionaliteit
        int MenuChoice = SaxionApp.readChar();

        if (MenuChoice == '1') {    //New Game
            SaxionApp.resize(832, 640);
            Mine[][] grid = createGrid();
            grid = addMineralsLvl1(grid);
            drawGrid(grid);
            selectAndClick(grid);

        } else if (MenuChoice == '2') {     //Load game
            SaxionApp.resize(832, 670);

        } else if (MenuChoice == '3') {     //Exit
            SaxionApp.clear();
            SaxionApp.drawBorderedText("Thank You for Playing", 125, 200, 75);
            SaxionApp.drawBorderedText("We hope to see you again", 350, 275, 25);
        }
    }

    public Mine[][] createGrid(){
        Mine[][] grid = new Mine[rows][column]; //create grid

        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){ //for loops to go through the grid and fill it

                Mine newValue = new Mine();
                newValue.rocks = (SaxionApp.getRandomValueBetween(1,4)*2); //set Rocks
                newValue.minerals = "x";
                newValue.cleared = false;
                grid[row][col] = newValue;
            }
        }
        return grid;
    }

    public Mine[][] addMineralsLvl1(Mine[][] grid){
        int randomValue = SaxionApp.getRandomValueBetween(3,6); //Amount of materials
        for(int x = 0; x < randomValue; x++){
            int randomX = SaxionApp.getRandomValueBetween(1, rows-2);
            int randomY = SaxionApp.getRandomValueBetween(1, column-2);
            if(grid[randomX][randomY].minerals.equals("x") && grid[randomX+1][randomY].minerals.equals("x") && grid[randomX][randomY+1].minerals.equals("x") && grid[randomX+1][randomY+1].minerals.equals("x")){ //check if all of the spaces are empty
                int randomMineral = SaxionApp.getRandomValueBetween(1, 11);
                if(randomMineral <=3){
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

                if(grid[row][col].minerals == "Iron1"){
                    SaxionApp.drawImage("Graphics/Steen6.png",(row-1)*64,(col-1)*64,128,128);
                }
                else if(grid[row][col].minerals == "Coal1"){
                    SaxionApp.drawImage("Graphics/Steen4.png",(row-1)*64,(col-1)*64,128,128);
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

                }
            }
        }
    }

    public int[] selectAndClick(Mine[][] grid){
        int[] coords = new int[2];
        coords[0] = (int)((rows/2)+0.5); //is 15/2 = 7.5+0.5 = 8-1 = 7
        coords[1] = (int)((column/2)+0.5)-1; //is 12/2 = 6+0.5 = 6.5-1 = 5.5(afgerond 5)
        SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0]-1)*64, (coords[1]-1)*64, 64, 64);
        boolean running = true;
        boolean pickaxe = true;

        while(running){
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
                    if(pickaxe){
                        pickaxe = false;
                    }
                    else{
                        pickaxe = true;
                    }
            }
            drawSelect(coords);
        }

        return coords;
    }

    public void drawSelect(int[] coords){
        SaxionApp.removeLastDraw();
        SaxionApp.drawImage("Graphics/Crosshair.png", (coords[0]-1)*64, (coords[1]-1)*64, 64, 64);
    }

}
