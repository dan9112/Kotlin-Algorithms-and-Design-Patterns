package structures.linked_list.singly_linked_list.circular

open class DanilCircularSingleLinkedList<T> : CircularSingleLinkedList<T> {
    protected val data = CurrentNodeLink<T>()
    protected var mutableSize = 0
    final override val size
        get() = mutableSize

    override fun addNext(item: T, index: Int) {
        if (size == 0) {
            data.link = DanilNode(item)
        } else {
            var current = data.link!!
            var i = 0
            while (i < index % size) {
                i++
                current = current.next
            }
            current.next = DanilNode(item, current.next)
        }
        mutableSize++
    }

    override fun addNext(item: T) {
        if (size == 0) {
            data.link = DanilNode(item)
        } else {
            var current = data.link!!
            var i = 1
            while (i < size) {
                i++
                current = current.next
            }
            current.next = DanilNode(item, current.next)
        }
        mutableSize++
    }

    override fun addPrevious(item: T, index: Int) {
        if (size == 0) {
            data.link = DanilNode(item)
        } else {
            var current = data.link!!
            var i = 0
            var localIndex = index
            while (localIndex - 1 < -size) {
                localIndex += size
            }
            while (i < (size + localIndex - 1) % size) {
                i++
                current = current.next
            }
            current.next = DanilNode(item, current.next)
        }
        mutableSize++
    }

    override fun remove(index: Int) = if (isEmpty()) {
        false
    } else {
        if (size == 1) {
            data.link = null
        } else {
            var current = data.link!!
            var i = 0
            while (i < index % size) {
                i++
                current = current.next
            }
            current.value = current.next.value.apply {
                current.next = current.next.next
            }
        }
        mutableSize--
        true
    }

    override fun remove(item: T) = if (isEmpty()) {
        false
    } else {
        var current = data.link!!
        var i = 0
        var deleted = false
        while (i < size && !deleted) {
            i++
            if (current.value == item) {
                if (size == 1) data.link = null
                else current.value = current.next.value.apply {
                    current.next = current.next.next
                    deleted = true
                }
                mutableSize--
            } else current = current.next
        }
        deleted
    }

    final override fun isEmpty() = size == 0

    final override val current
        get() = data.link?.value ?: throw IllegalArgumentException()

    override fun asList() = with(receiver = mutableListOf<T>()) {
        data.link?.let {
            var current = it
            do {
                add(current.value)
                current = current.next
            } while (current != it)
        }
        toList()
    }

    override fun goForward(steps: Int) = if (size == 0) throw IllegalArgumentException()
    else {
        var i = 0
        var current = data.link!!
        while (i < steps % size) {
            i++
            current = current.next
        }
        data.link = current
    }

    override fun goBack(steps: Int) = if (size == 0) throw IllegalArgumentException()
    else {
        var i = 0
        var current = data.link!!
        while (i < size - steps % size) {
            i++
            current = current.next
        }
        data.link = current
    }

    protected interface Node<T> {
        var value: T
        var next: Node<T>
    }

    protected class DanilNode<T>(override var value: T) : Node<T> {
        constructor(value: T, next: Node<T>) : this(value) {
            this.next = next
        }

        override var next: Node<T> = this
    }

    protected class CurrentNodeLink<T>(var link: Node<T>? = null)
}
