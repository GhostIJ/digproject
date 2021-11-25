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
                newValue.rocks = (SaxionApp.getRandomValueBetween(1,4)*2 ); //set Rocks
                int randomValue = SaxionApp.getRandomValueBetween(1,101); //set Minerals

                if(randomValue >= 1 && randomValue <= 14){
                    newValue.minerals = "Coal";
                }
                else if(randomValue >= 15 && randomValue <= 20){
                    newValue.minerals = "Iron";
                }
            }
        }
        return grid;
    }
    public void drawGrid(Mine[][] grid){
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid[row].length; col++){
                SaxionApp.drawImage("Graphics/Steen6.png",row*64,col*64,64,64);
            }
        }
    }
}
