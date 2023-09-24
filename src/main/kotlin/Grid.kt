class Grid(rows: Int, columns: Int) {
    val rows : Int
    val columns : Int
    private val value = Array(rows) { Array(columns) { 0 } }

    init {
        this.rows = rows
        this.columns = columns
    }

    operator fun get(row: Int, col: Int): Int {
        if (row in 0..<rows && col in 0..<columns) {
            return value[row][col]
        }

        throw IndexOutOfBoundsException("Invalid row or column index")
    }

    operator fun set(row: Int, col: Int, newValue: Int) {
        if (row in 0..<rows && col in 0..<columns) {
            value[row][col] = newValue
            return
        }

        throw IndexOutOfBoundsException("Invalid row or column index")
    }

    fun copy(): Grid {
        val grid = Grid(rows, columns)

        for (row in 0..<rows) for (col in 0..<columns)
            grid[row, col] = this[row, col]

        return grid
    }
}
