package structures

import java.util.LinkedList
import java.util.Stack

/**
 * Простая реализация Бинарного Дерева
 *
 * @author Mr.Fox
 *
 * @property [BinaryTree.value] значение узла дерева
 * @property [BinaryTree.right] правый узел дерева
 * @property [BinaryTree.left] левый узел дерева
 *
 * @sample BFS обход дерева в ширину
 * @sample DFS обход дерева в глубину
 *
 * @constructor BinaryTree(значение, леввый узел, правый узел).
 */

class BinaryTree (
    val value: Int = 0,
    val left: BinaryTree? = null,
    val right: BinaryTree? = null,
)

fun DFS(root: BinaryTree) {
    val stack = Stack<BinaryTree>()
    var total = 0

    // add first element(root) to stack
    stack.push(root)

    while (stack.isNotEmpty()) {
        val node = stack.pop()
        total += node.value

        println("Current node -> ${node.value}")

        if (node.right != null) stack.push(node.right)
        if (node.left != null) stack.push(node.left)
    }

    println("total: $total")
}
fun BFS(root: BinaryTree) {
    val queue = LinkedList<BinaryTree>()
    var total = 0

    // add first element(root) to stack
    queue.push(root)

    while (queue.isNotEmpty()) {
        val node = queue.pop()
        total += node.value

        println("Current node -> ${node.value}")

        if (node.right != null) queue.push(node.right)
        if (node.left != null) queue.push(node.left)
    }

    println("total: $total")
}