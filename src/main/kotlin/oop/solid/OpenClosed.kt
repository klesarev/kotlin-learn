package oop.solid

import io.restassured.RestAssured
import io.restassured.http.ContentType
import kotlin.random.Random

fun main(){
    val dataController = JsonController( JsonPlaceholderServiceImpl() )
    dataController.printPost(1)

    val testController = JsonController( DummyJsonServiceImpl() )
    testController.printPost(1)
}

class JsonController(private val service: JsonRestService) {
    fun printPost(postId: Int = 1) {
        println(service.getPost(postId))
    }
}

interface JsonRestService {
    val baseUrl: String
    fun getPost(postId: Int): PostModel
}

class JsonPlaceholderServiceImpl: JsonRestService {
    
    override val baseUrl: String = "https://jsonplaceholder.typicode.com/"
    override fun getPost(postId: Int): PostModel {
        return RestAssured.given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("${baseUrl}posts/$postId")
            .then()
            .extract().response().body.`as`(PostModel::class.java)
    }
}

class DummyJsonServiceImpl: JsonRestService {

    override val baseUrl: String = "localhost:8080"
    override fun getPost(postId: Int): PostModel {
        return PostModel(
            userId = Random.nextInt(0, 10),
            id = postId,
            title = "Mock Title",
            body = "The some text from mock class"
        )
    }
}

data class PostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)
