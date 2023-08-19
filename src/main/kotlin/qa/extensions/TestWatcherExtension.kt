package qa.extensions

import io.qameta.allure.TmsLink
import io.restassured.RestAssured
import io.restassured.http.ContentType
import io.restassured.response.Response
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.api.extension.TestWatcher
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.HashMap

class TestWatcherExtension : TestWatcher, BeforeAllCallback, AfterAllCallback {

    private val host = "https://rails.yourcompany.tech/index.php?/api/v2"
    private val setOfCases = HashMap<String, Int>()
    private var testRunID = 91
    private val projectId = 2
    private val localDatetime = LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM"))

    private fun updateResult(body: HashMap<String, Any>, url: String): Response {
        return RestAssured.given()
            .auth().preemptive().basic("sdet@yourcompany.tech", "qwerty7c1")
            .contentType(ContentType.JSON)
            .body(body)
            .post(url)
    }

    private fun createTestRun(projectId: Int, testCases: List<Int>): Int {
        val requestBody = hashMapOf(
            "name" to "New test run $localDatetime",
            "include_all" to false,
            "case_ids" to testCases
        )
        val response = updateResult(requestBody, "$host/add_run/$projectId")
        return response.body.jsonPath().getString("id").toInt()
    }

    private fun setStatus(ctx: ExtensionContext, caseStatus: TestCaseStatus) {
        val annotation: TmsLink? = ctx.element.get().getAnnotation(TmsLink::class.java)
        annotation?.let { setOfCases[it.value] = caseStatus.id }
    }

    override fun testSuccessful(context: ExtensionContext) {
        setStatus(context, TestCaseStatus.PASSED)
    }

    override fun testFailed(context: ExtensionContext, cause: Throwable?) {
        setStatus(context, TestCaseStatus.FAILED)
    }

    override fun testAborted(context: ExtensionContext, cause: Throwable?) {
        setStatus(context, TestCaseStatus.RETEST)
    }

    override fun testDisabled(context: ExtensionContext, reason: Optional<String>?) {
        setStatus(context, TestCaseStatus.BLOCKED)
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

    override fun afterAll(context: ExtensionContext?) {
        setOfCases.forEach { (caseId, caseStatus) ->
            updateResult(
                hashMapOf("status_id" to caseStatus),
                "$host/add_result_for_case/$testRunID/$caseId"
            )
        }
    }
}