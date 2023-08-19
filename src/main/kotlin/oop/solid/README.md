## SOLID Kotlin Principles

### Single Responsibility

### Open Closed
Принцип открытости-закрытости - принцип объектно-ориентированного программирования, устанавливающий следующее положение: 
«программные сущности (классы, модули, функции и т. п.) должны быть открыты для расширения, но закрыты для изменения»; 
это означает, что такие сущности могут позволять менять свое поведение без изменения их исходного кода.

В качестве примера возьмем контроллер, который пользуется внешним сервисом и получая json генерирует из него класс. 
Пусть это будет - https://jsonplaceholder.typicode.com/

У нас есть модель (дата-класс) - PostModel, которая позволяет хранить в нем полученные данные.
```kotlin
data class PostModel(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
)
```
Достает данные для нашего приложения класс JsonPlaceholderService (метод getPost) из вышеуказанного сервиса (при помощи 
библиотеки [RestAssured](https://rest-assured.io/)) по адресу - https://jsonplaceholder.typicode.com/posts/$postId, 
где $postId идентификатор нашего поста.
```kotlin
class JsonPlaceholderService {
    
    val baseUrl: String = "https://jsonplaceholder.typicode.com/"
    
    fun getPost(postId: Int): PostModel {
        return RestAssured.given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("${baseUrl}posts/$postId")
            .then()
            .extract().response().body.`as`(PostModel::class.java)
    }
    
}
```
...а контроллер получая их, обрабатывает и выводит на печать. 
```kotlin
class JsonController(private val service: JsonPlaceholderService) {
    
    fun printPost(postId: Int = 1) {
        println(service.getPost(postId))
    }
    
}
```
Неплохо, но вот настало время для интеграционного тестирования, и нам нужно отключить внешний сервис, или же
получать данные из другого места. Для этого потребуется поменять текущую реализацию JsonPlaceholderService. 
Но! В чистой архитектуре Дядя Боб не рекомендовал такой подход, поскольку нарушается Принцип открытости-закрытости 
— старый код не должен меняться. К тому же, вдруг начальству вновь понадобится {JSON} Placeholder Service?

Как решить проблему и не трогать старый код? При помощи интерфейса Карл! Создадим interface JsonRestService, который будет
содержать свойство _baseUrl_ для ссылки на внешний источник, и метод _getPost()_
```kotlin
interface JsonRestService {
    
    val baseUrl: String
    fun getPost(postId: Int): PostModel
    
}
```
Теперь изменим JsonPlaceholderService - унаследуемся от JsonRestService и переопределим метод и свойство из интерфейса, 
а также добавим еще один класс - DummyJsonServiceImpl, который будет "стучаться" на localhost и возвращать класс 
PostModel с тестовыми данными.
```kotlin
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
```
Осталось немного изменить JsonController и можно пользоваться, меняя по необходимости источник и способ получения данных

```kotlin
fun main(){
    
    val dataController = JsonController( JsonPlaceholderServiceImpl() )
    dataController.printPost(1)

    val testController = JsonController( DummyJsonServiceImpl() )
    testController.printPost(2)
    
}

class JsonController(private val service: JsonRestService) {
    
    fun printPost(postId: Int = 1) {
        println(service.getPost(postId))
    }
    
}
```

Далее, если захотим добавить ещё одну реализацию (внешний сервис), нам не придётся менять существующий код. Таким образом, 
достигается главная цель Принципа Открытости-Закрытости — сделать систему легко-расширяемой и обезопасить её от влияния изменений.


### Liskov Substitution

### Interface Segregation

### Dependency Inversion

**Какие есть способы внедрения зависимостей (DI)?**
- Внедрение зависимости через конструктор (Constructor Injection). Этот способ мы рассмотрели выше. 
В этом способе нужно передать необходимую зависимость в конструктор.
- Внедрение зависимости через поле. (Field Injection (or Setter Injection)). На примере android, некоторые классы 
из Android framework такие как Activity или Fragments создаются системой, так что первый способ использовать нельзя. 
Внедрение зависимости через поле позволяет передать зависимость уже после того как класс будет создан. 
Примерно это будет выглядеть вот так:
```kotlin
class Car {
    lateinit var engine: Engine
    fun start() {
        engine.start()
    }
}
fun main(args: Array) {
    val car = Car()
    car.engine = Engine()
    car.start()
}
```
- Использование DI-фреймворка. Вручную описывать для большого приложения все зависимости будет очень сложно и громоздко,
поэтому для больших приложений рекомендуется использовать DI-фреймворки, генерирующие зависимости самостоятельно. 
Одним из таких фреймворков является Koin.