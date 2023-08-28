package net.nejiwwkr.project_industrial.util.math

typealias int = Int

class Math {
    companion object {
        private fun factorial(x: int): int {
            var res : int = 1
            for (k in 1..x) res *= k
            return res
        }

        @JvmStatic
        fun arrangement(down: int, up: int): int = factorial(down) / factorial(up)

        @JvmStatic
        fun combination(down: int,up: int): int = arrangement(down,up) / arrangement(up,up)
    }
}

