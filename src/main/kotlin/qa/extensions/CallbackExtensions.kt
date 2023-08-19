package qa.extensions

import io.qameta.allure.TmsLink
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

enum class TestCaseStatus(val id: Int) {
    PASSED(1), FAILED(5)
}

class TestRailExtension : AfterTestExecutionCallback, AfterAllCallback,
    BeforeAllCallback {

    private val host = "https://rails.yourcompany.tech/index.php?/api/v2"
    private val setOfCases = HashMap<String, Int>()
    private var testRunID = 91
    private val projectId = 2
    private val localDatetime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM"))

    override fun afterTestExecution(context: ExtensionContext) {
        val annotation: TmsLink? = context.element.get().getAnnotation(TmsLink::class.java)
        val caseStatus = when (!context.executionException.isPresent) {
            true -> TestCaseStatus.PASSED.id
            else -> TestCaseStatus.FAILED.id
        }
        annotation?.let { setOfCases[it.value] = caseStatus }
    }

    private fun setCaseStatus(caseStatus: Int, caseId: String) {
        RestAssured.given()
            .auth().preemptive().basic("sdet@yourcompany.tech", "qwerty7c1")
            .contentType(ContentType.JSON)
            .body(hashMapOf("status_id" to caseStatus))
            .post("$host/add_result_for_case/$testRunID/$caseId")
    }

    private fun createTestRun(projectId: Int, testCases: List<Int>): Int {

        val requestBody = hashMapOf(
            "name" to "New test run $localDatetime",
            "include_all" to false,
            "case_ids" to testCases
        )

        val response = RestAssured.given()
            .auth().preemptive().basic("sdet@yourcompany.tech", "qwerty7c1")
            .contentType(ContentType.JSON)
            .body(requestBody)
            .post("$host/add_run/$projectId")

        return response.body.jsonPath().getString("id").toInt()
    }

    override fun afterAll(context: ExtensionContext?) {
        setOfCases.forEach { (caseId, caseStatus) ->
            setCaseStatus(caseStatus, caseId)
        }
    }

    override fun beforeAll(context: ExtensionContext) {
        val cases = arrayListOf<Int>()
        context.requiredTestClass.declaredMethods.forEach { method ->
            cases.addAll(
                method.annotations.filterIsInstance<TmsLink>().map { it.value.toInt() }
            )
        }

        if (cases.isNotEmpty()) {
            testRunID = createTestRun(projectId, cases)
        }
    }

}