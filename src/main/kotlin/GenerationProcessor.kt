import java.util.Random

class GenerationProcessor {
    private val random = lazy { Random() }

    fun nextGeneration(grid: Grid): Grid {
        val nextGrid = Grid(grid.rows, grid.columns)

        for (row in 0..<grid.rows) for (col in 0..<grid.columns) {
            val state = grid[row, col]
            val neighbours = countNeighbours(grid, col, row)

            if (state == 0 && neighbours == 3)
                nextGrid[row, col] = 1
            else if (state == 1 && (neighbours < 2 || neighbours > 3))
                nextGrid[row, col] = 0
            else
                nextGrid[row, col] = state
        }

        return nextGrid
    }

    fun addOrganisms(grid: Grid, count: Int): Grid {
        var currentCount = count
        val nextGrid = grid.copy()

        while (currentCount > 0) {
            val randomRow = random.value.nextInt(nextGrid.rows)
            val randomColumn = random.value.nextInt(nextGrid.columns)

            nextGrid[randomRow, randomColumn] = 1
            currentCount--
        }

        return nextGrid
    }

    private fun countNeighbours(grid: Grid, x: Int, y: Int): Int {
        var countAliveNeighbours = 0

        for (i in -1..1) for (j in -1..1) {
            val row = (y + i + grid.rows) % grid.rows
            val col = (x + j + grid.columns) % grid.columns

            countAliveNeighbours += grid[row, col]
        }

        countAliveNeighbours -= grid[y, x]

        return countAliveNeighbours
    }
}