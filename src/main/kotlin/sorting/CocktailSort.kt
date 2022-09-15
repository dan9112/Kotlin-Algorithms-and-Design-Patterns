package sorting

fun <T : Comparable<T>> MutableList<T>.cocktailSort() {
    var rightBorder = size - 1
    var leftBorder = 0

    fun swapIfNeedGoUp(): Boolean {
        var swapped = false
        val localRightBorder = rightBorder
        for (index in leftBorder until localRightBorder) {
            if (this[index] > this[index + 1]) {
                this[index] = this[index + 1].also { this[index + 1] = this[index] }
                rightBorder = index
                swapped = true
            }
        }
        return swapped
    }

    fun swapIfNeedGoDown(): Boolean {
        var swapped = false
        val localLeftBorder = leftBorder
        for (index in rightBorder downTo localLeftBorder) {
            if (this[index] > this[index + 1]) {
                this[index] = this[index + 1].also { this[index + 1] = this[index] }
                leftBorder = index + 1
                swapped = true
            }
        }
        return swapped
    }

    do {
        val swapped = swapIfNeedGoUp() && swapIfNeedGoDown()
    } while (swapped)
}

fun <T : Comparable<T>> Array<T>.cocktailSort() {
    var rightBorder = size - 1
    var leftBorder = 0

    fun swapIfNeedGoUp(): Boolean {
        var swapped = false
        val localRightBorder = rightBorder// for
        for (index in leftBorder until localRightBorder) {
            if (this[index] > this[index + 1]) {
                this[index] = this[index + 1].also { this[index + 1] = this[index] }
                rightBorder = index
                swapped = true
            }
        }
        return swapped
    }

    fun swapIfNeedGoDown(): Boolean {
        var swapped = false
        val localLeftBorder = leftBorder
        for (index in rightBorder downTo localLeftBorder) {
            if (this[index] > this[index + 1]) {
                this[index] = this[index + 1].also { this[index + 1] = this[index] }
                leftBorder = index + 1
                swapped = true
            }
        }
        return swapped
    }

    do {
        val swapped = swapIfNeedGoUp() && swapIfNeedGoDown()
    } while (swapped)
}
