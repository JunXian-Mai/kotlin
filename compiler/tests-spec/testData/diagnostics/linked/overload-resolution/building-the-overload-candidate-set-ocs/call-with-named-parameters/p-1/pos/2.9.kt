// !LANGUAGE: +NewInference
// !DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -NOTHING_TO_INLINE
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 0.1-280
 * PLACE: overload-resolution, building-the-overload-candidate-set-ocs, call-with-named-parameters -> paragraph 1 -> sentence 2
 * RELEVANT PLACES: overload-resolution, building-the-overload-candidate-set-ocs, call-with-named-parameters -> paragraph 2 -> sentence 1
 * NUMBER: 9
 * DESCRIPTION: Explicit receiver:  Top-level non-extension functions: Callables declared in the same package
 */

// FILE: TestCase.kt
// TESTCASE NUMBER: 1
package testsCase1
import libPackageCase1.*

fun case1() {
    <!DEBUG_INFO_AS_CALL("fqName: testsCase1.listOf; typeCall: function")!>listOf(elements1= arrayOf(1))<!>
}

// FILE: Lib.kt
package libPackageCase1

public fun <T> listOf(vararg elements1: T): List<T> = TODO()

// FILE: LibtestsPack.kt
package testsCase1

public fun <T> listOf(vararg elements1: T): List<T> = TODO()

// FILE: TestCase.kt
// TESTCASE NUMBER: 2
package testsCase2
import libPackageCase2.*

fun case2() {
    <!DEBUG_INFO_AS_CALL("fqName: testsCase2.listOf; typeCall: function")!>listOf(elements1= arrayOf(1))<!>
}

// FILE: Lib.kt
package libPackageCase2

public fun <T> listOf(vararg elements1: T): List<T> = TODO()

// FILE: LibtestsPack.kt
package testsCase2
import testsCase2.*

public val listOf: A
    get() = A()

class A {
    operator fun <T>invoke(vararg elements1: T): List<T> = TODO()
}

public fun <T> listOf(vararg elements1: T): List<T> = TODO()


// FILE: TestCase.kt
// TESTCASE NUMBER: 3
package testsCase3
import libPackageCase3.*

fun case3() {
    <!DEBUG_INFO_AS_CALL("fqName: testsCase3.A.invoke; typeCall: variable&invoke")!>listOf(elements1= arrayOf(1))<!>
}

// FILE: Lib.kt
package libPackageCase3

public fun <T> listOf(vararg elements1: T): List<T> = TODO()

// FILE: LibtestsPack.kt
package testsCase3
import testsCase3.*

public val listOf: A
    get() = A()

class A {
    operator fun <T>invoke(vararg elements1: T): List<T> = TODO()
}

private fun <T> listOf(vararg elements1: T): List<T> = TODO()

