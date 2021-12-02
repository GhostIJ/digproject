import nl.saxion.app.SaxionApp;

public class Application implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new Application(), 832, 670);
    }

    public void run() {
        // Your code goes here!
        Mine[][] grid = createGrid();
        drawGrid(grid);
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
                    SaxionApp.drawImage("Graphics/Steen6.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 4){
                    SaxionApp.drawImage("Graphics/Steen5.png",row*64,col*64,64,64);
                }
                else if(grid[row][col].rocks == 3){
                    SaxionApp.drawImage("Graphics/Steen6.png",row*64,col*64,64,64);
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
