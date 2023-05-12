package util

fun intSqrt(n: Int): Int {
    if (n <= 1) {
        return n
    }

    var x0 = n.shr(1)
    var x1 = (x0 + n / x0).shr(1)

    while (x1 < x0) {
        x0 = x1
        x1 = (x0 + n / x0).shr(1)
    }

    return x0
}
