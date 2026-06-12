import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Janna Willemsen
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;

    }

    public void act() {
        if (canLayEgg()) {
            layEgg();
        }
        if (borderAhead()) {
            turnRight();
            turnRight();// draai ??n keer
        } else {
            move();     // anders lopen
        }
    }
    
    /**
     * Returns the number of eggs Dodo has hatched so far.
     * 
     * @return int number of eggs hatched by Dodo
     */
    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    /**
     * Move one cell forward in the current direction.
     * 
     * <P> Initial: Dodo is somewhere in the world
     * <P> Final: If possible, Dodo has moved forward one cell
     *
     */
    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    /**
     * Hatches the egg in the current cell by removing
     * the egg from the cell.
     * Gives an error message if there is no egg
     * 
     * <p> Initial: Dodo is somewhere in the world. There is an egg in Dodo's cell.
     * <p> Final: Dodo is in the same cell. The egg has been removed (hatched).     
     */    
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }

    /**
     * Test if Dodo can lay an egg.
     *          (there is not already an egg in the cell)
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can lay an egg (no egg there)
     *                 false if Dodo can't lay an egg
     *                      (already an egg in the cell)
     */

    public boolean canLayEgg( ){
        if(onEgg() ) {
            return false;
            //E
        }else{
            return true;
        }
    }
    
    public void turn180() {
        turnRight();
        turnRight();
    }
    
    /**
     * Move given number of cells forward in the current direction.
     * 
     * <p> Initial:   
     * <p> Final:  
     * 
     * @param   int distance: the number of steps made
     */
    public void jump( int distance ) {
        int nrStepsTaken = 0;               // set counter to 0
        while ( nrStepsTaken < distance ) { // check if more steps must be taken  
            move();                         // take a step   
            nrStepsTaken++;                 // increment the counter
            System.out.println("moved");
        }
    }
    
    /**
     * Walks to edge of the world printing the coordinates at each step
     * 
     * <p> Initial: Dodo is on West side of world facing East.
     * <p> Final:   Dodo is on East side of world facing East.
     *              Coordinates of each cell printed in the console.
     */

    public void walkToWorldEdgePrintingCoordinates( ){
        while( ! borderAhead() ){
            // print coordinates
            printCoordinates(getX(), getY());
            move();
        }
    }
    
    /**
     * Test if Dodo can move forward, (there are no obstructions
     *    or end of world in the cell in front of her).
     * 
     * <p> Initial: Dodo is somewhere in the world
     * <p> Final:   Same as initial situation
     * 
     * @return boolean true if Dodo can move (no obstructions ahead)
     *                 false if Dodo can't move
     *                      (an obstruction or end of world ahead)
     */
    public boolean canMove() {
        if ( fenceAhead() ){
            return false;
        } else {
            return true;
        }
    }
    
    public void WalkToWorldEdgeClimbOverFence()
    {
        while(!borderAhead())
        {
            
            if (fenceAhead())
            { 
                turnLeft();
                move();
                turnRight();
                move(2);
                turnRight();
                move();
                turnLeft(); 
            }
            else
            {
                move();
            }
        }
    }
    
    public void grainAhead(){
        while(!borderAhead())
        {
            if (onGrain())
            { 
                pickUpGrain();
            }
            else
            {
                move();
            }
            
        }
    }
    
    public void gotoEgg() 
    {
        while(!onEgg())
        {
            move(); 
        }
    }
    
    public void goBackToStartOfRowAndFaceBack() {
        turn180();
        walkToWorldEdgePrintingCoordinates();
        turn180();
    }
    
    public void pickUpGrainsAndPrintCoordinates() {
        while(!borderAhead())
        {
            if (onGrain())
            { 
                pickUpGrain();
                printCoordinates(getX(), getY());
            }
            else
            {
                move();
            }
            
        }
    }
    
    public void stepOneCellBackwards() {
        turn(180);
        move(1);
        turn(180);
    }
    
    public void worldEmptyNestsTopRow() {
        while(!borderAhead())
        {
            if (onNest())
            { 
                canLayEgg();
                layEgg();
            }
            else
            {
                move();
            }
            
        }
    }
    
    public void walkAroundFencedArea() {
        while (!onEgg()) {
            move();
             if (!fenceOnRight()) { 
            move();
            } else {
            move();               
            }
        }
    }
    
    public void eggTrailToNest() {
        if(onNest()) {
            System.out.println("arrived");
        }

        else if (onEgg()) {
            move(); 
        }

        else if (cornerAhead()) {
            turnTowardsEgg();
            move();
        }

        else {
            gotoEgg(); 
        }
    }
    
    public void worldMazeLevel1() {
        
    }
    
    public void worldMazeLevel2() {
        
    }
    
    public void tijdelijkeWaardeEi() {
        
    }
    
    public void faceEast() {
        
    }
    
    public void GetDirection() {
        
    }
    
    public void goToLocation(int x, int y) {
        setLocation(x, y);
    }
    
    public void validCoordinates(int x, int y) {
        
    }
    
    public int countEggsInRow(int y) {
        int count = 0;
        int width = getWorld().getWidth();
        for (int x = 0; x < width; x++) {
            goToLocation(x, y);
            if (onEgg()) {
                count++;
            }
        }
        return count;
    }

    public void layTrailofEggs( int n ) {
        int nrStepsTaken = 0;               
        while ( nrStepsTaken < n ) {   
            move();   
            layEgg();
            nrStepsTaken++;                 
        }
    }
    
    public void countAllEggsInWorld() {
        int total = 0;
        int height = getWorld().getHeight();

        for (int y = 0; y < height; y++) {
            total += countEggsInRow(y);
        }

        System.out.println("Totaal aantal eieren: " + total);
    }
    
    public void findRowWithMostEggs() {
        int maxEggs = 0;            
        int rowWithMostEggs = 0;     
        int height = getWorld().getHeight();

        for (int y = 0; y < height; y++) {
            int eggsInRow = countEggsInRow(y); 
            if (eggsInRow > maxEggs) {         
                maxEggs = eggsInRow;           
                rowWithMostEggs = y;           
            }
        }
        System.out.println("Rij met meeste eieren: " + rowWithMostEggs);
        goToLocation(0, 0);  
    }
    
    public void eggMonument() {
        
    }
    
    public void stevigMonument() {
        
    }
    
    public void eggPiramide() {
        
    }
    
    public void gemiddeldeAantalEggsPerRow() {
        
    }
    
    
    
    
        public boolean fenceOnRight() {
        turnRight();          // tijdelijk naar rechts kijken
        boolean hek = borderAhead();  
        return hek;
    }
    
    public boolean cornerAhead() {
        turnLeft();
        boolean eggLeft = onEgg();
        turnRight(); 
        
        turnRight();
        boolean eggRight = onEgg();
        turnLeft();

        return eggLeft || eggRight;
    }

    public void turnTowardsEgg() {
        turnLeft();
        if (onEgg()) {
            return; // we staan al goed
        }
        turnRight(); 
        turnRight();
        if (onEgg()) {
            return;
        }
        turnLeft(); 
    }
    
    public void printCoordinates(int x, int y) {
        System.out.println("Co?rdinaten: (" + x + ", " + y + ")");
    }
}  