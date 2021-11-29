object Setup {
  val DEAD = 0
  val ALIVE = 1
  // Create a 2D array given rows and columns
  def make2DArray(rows: Int, cols: Int): Array[Array[Int]] = {
    val grid = Array.ofDim[Int](rows , cols)
    for(i <- 0 until  rows ; j <- 0 until  cols){
      grid(i)(j) = 0
    }
    grid
  }


  // Display a matrix
  def display(grid: Array[Array[Int]]): Unit = {
    for(i <- 0 until grid.length){
        println(grid(i).mkString(" "))
      }
    println("\n")
  }

  // User clicks on a cell, if its alive it dies. If its dead, its comes to life.
  def cellInput(grid: Array[Array[Int]], y: Int, x: Int): Unit = {
    if(x > 0 && y > 0) {
      if (grid(x)(y) == 0) {
        grid(x)(y) = 1
      }
      else {
        grid(x)(y) = 0
      }
    }
  }
  def countNeighbors(grid: Array[Array[Int]],x: Int, y: Int): Int = {
    var sum: Int = 0
    for(i <- -1 until 2; j <- -1 until 2){
      sum += grid(x + i)(y + j)
    }
    sum = sum - grid(x)(y)
    sum
  }
  //print out
  def generateDisplay(grid: Array[Array[Int]], generations: Int): Unit = {
    for(i <- 0 until generations){
      var disp = grid
      display(disp)
      generateDisplay(run(disp), generations -1)
    }

  }
  def run(generation: Array[Array[Int]]): Array[Array[Int]] = {
    var nextGeneration: Array[Array[Int]] = make2DArray(generation.length,generation(0).length)
    for(i <- 0 until generation.length ; j<- 0 until generation(0).length ){
      val state: Int = generation(i)(j)
      //[EDGE CASES]
      if(i == 0 || j == 0|| i == generation(0).length - 1 || j == generation.length - 1){
          nextGeneration(i)(j) = generation(i)(j)
        }
      //[[GAME LOGIC]
      // If cell is dead and neighbors > 3, cell becomes alive otherwise stays dead.
      // If cell is alive, and neignbors == 2 or 3, lives on to next generation.
      // If cell is alive and neighbors < 2 or neighbors > 3, dies.
      else{
      var neighbors: Int = countNeighbors(generation, i,j)
      if(state == DEAD && neighbors == 3){
        nextGeneration(i)(j) = ALIVE
      }
      if(state == ALIVE && neighbors < 2 || state == ALIVE && neighbors > 3){
        nextGeneration(i)(j) = DEAD
       }
      else if( state == ALIVE && neighbors == 2 || state == ALIVE && neighbors == 3 ){
        nextGeneration(i)(j) = ALIVE
      }
     }
    }
    print("\n")
    nextGeneration
  }
  def allZero(grid: Array[Array[Int]]): Boolean = {
    var count = 0
    for(i <- 0 until grid.length;  j <- 0 until grid(0).length) {
      count += grid(i)(j)
    }
    if(count > 0 ){
      false
    }
    else{
      true
    }
  }
  def displayRun(grid: Array[Array[Int]]): Unit = {
    var current_generation = grid
    while(!allZero(current_generation)){
      display(current_generation)
      Thread.sleep(1200)
      current_generation = run(current_generation)
    }
  }



  def main(args: Array[String]): Unit = {

    var grid = make2DArray(rows = 10, cols = 10)
    cellInput(grid, 2, 4)
    cellInput(grid, 3, 4)
    cellInput(grid, 4, 4)
    cellInput(grid,4,3)
    cellInput(grid,3,2)

    displayRun(grid)

  }
}
