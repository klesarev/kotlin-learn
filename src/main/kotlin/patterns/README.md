

# Список основных паттернов

## Singleton
Это порождающий паттерн проектирования, который гарантирует, что у класса есть только один
экземпляр, и предоставляет к нему глобальную точку доступа.

:hammer_and_wrench: Используется в основном в качестве класса-connection к базе данных, для чтения настроек или же
хранения конфигурации. Самый простой паттерн!

```kotlin
object Single {
    val name: String = "Singleton"
}
```
А теперь приведу минимальный пример на Java. Тут обязательно нужно помечать конструктор как приватный,
чтобы избежать создания инстанса)
```java
public class Singleton {
    private Singleton(){}
    public static final Singleton INSTANCE = new Singleton();
}
```

## Builder
Это порождающий паттерн проектирования, который позволяет создавать сложные объекты пошагово.
Применить в автотестах можно в качестве файла для создания конфигурации.


## Observer
text will be soon...

## Factory
Фабрика — это порождающий паттерн проектирования, который определяет общий интерфейс 
для создания объектов в суперклассе, позволяя подклассам изменять тип создаваемых объектов.

В отличие от java, kotlin позволяет немного прокачать ее, благодаря sealed классам и 
меньшему объему кода. 

Ниже мы рассмотрим 3 примера реализации паттерна "Фабрика" - классический, с sealed-классами и 
со статическим методом.

### Kotlin classic Factory
coming soon...
```kotlin
interface ICurrency {
    val symbol: String get() = ""
    val code: String get() = ""
}

data class Euro(
    override val symbol: String = "€",
    override val code: String = "EUR"
): ICurrency

data class UnitedStatesDollar(
    override val symbol: String = "$",
    override val code: String = "USD"
): ICurrency

enum class Country {
    UnitedStates, Spain, UK, Greece
}

class CurrencyFactory private constructor(){
    companion object {
        fun create(country: Country): ICurrency {
            return when (country) {
                Country.Spain, Country.Greece -> Euro()
                Country.UnitedStates -> UnitedStatesDollar()
                else -> error("enter the currency code")
            }
        }
    }
}
```


### Kotlin sealed class Factory
Классическая Фабрика конечно хороша, но реализация при помощи sealed-классов/интерфейса интереснее.  

Разберем на простом примере. Создадим фабрику, которая будет возвращать разные классы баз данных, 
в зависимости от переданных параметров. Для чего это нужно? К примеру, в проекте используется несколько баз-данных и 
у каждой могут быть свои параметры - хост, порт, логин, пароль и т.д. Если база одна - то можно обойтись и
без фабрики, а что если добавятся одна, две, три...?

Создадим интерфейс Database, который будет суперклассом для остальных классов баз данных. Зададим свойство - config,
в котором будем хранить наши параметры.
```kotlin
interface Database {
    val config: DBConfig
}
```
Отлично. Теперь накидаем два класса баз данных - MySql и MongoDB. Соответственно, свойству config 
присвоим тип DBConfig.
```kotlin
data class MySql(override val config: MySqlConfig): Database
class MongoDB(override val config: MongoDBConfig): Database
```
Теперь sealed интерфейс с конфигурацией. Вместо sealed interface
можно использовать sealed class. Документация по ним [лежит тут](https://kotlinlang.org/docs/sealed-classes.html#sealed-classes-and-when-expression)
```kotlin
sealed interface DBConfig {
    val params: Map<String, Any>
    val host: String get() = Default.host
    val port: Int get() = Default.port
    
    companion object Default {
        const val host: String = "8.8.8.8"
        const val port: Int = 8080
    }
}
```
В companion object добавим параметры по-умолчанию. Теперь напишем классы-конфигурации для баз данных.
```kotlin
class MySqlConfig(
    override val host: String = DBConfig.host,
    override val port: Int = DBConfig.port,
    override val params: Map<String, Any>
): DBConfig

class MongoDBConfig (
    override val host: String = DBConfig.host,
    override val port: Int = DBConfig.port,
    override val params: Map<String, Any>
): DBConfig
```
Как обычно, чтобы иметь возможность переопределять свойства родительского класса, нужно добавить модификатор _override._ 

Большая часть свойств будет храниться в переменной params, которая имеет тиа _Map<String, Any>,_ где значение ключа
может быть любым - _Int, Boolean, String_ или же еще одна _Map_.

Кстати, классы могут быть как вложены в основной sealed-класс, так и находиться в одном файле/пакете, как в нашем случае.

Теперь настало время реализовать фабрику. Берем объект DatabaseFactory и создаем ему один метод-создатель
_create()_, который на вход будет принимать нашу конфигурацию и в зависимости от ее типа возвращать нужный класс 
базы данных, с переданной в него конфигурацией.
```kotlin
object DatabaseFactory {
    fun create(config: DBConfig): Database {
        return when(config) {
            is MySqlConfig -> MySql(config)
            is MongoDBConfig -> MongoDB(config)
            else -> error("error")
        }
    }
}
```
Попробуем фабрику в деле
```kotlin
val db = DatabaseFactory.create(
    MySqlConfig(port = 4371, host = "192.168.1.1", params = mapOf(
        "name" to "SQL Base"
    ))
)

println(db)
// MySql(config=MySqlConfig(host=192.168.1.1, port=4371, params={name=SQL Base}))
```
Работает. Фабрику можно улучшить, сделав при ошибке в блоке when вывод дефолтной конфигурации - 
например класс DefaultConfig. Соответственно, сменив "базы" на "драйвера" можно получить гибкую
фабрику по созданию драйверов с кастомными параметрами для QA проекта.


### Kotlin Factory Static Method
Еще один способ создать фабрику при помощи Kotlin. Данный фабричный метод возвращает 
объект с настройками, в зависимости от тега - chrome, firefox.

В отличие от классической реализации (java-style) отличается лаконичностью, и лучшей 
читаемостью.
```kotlin
// Static Method
class StaticFactory private constructor(){
    companion object {
        fun getOption(tag: String, vararg opts: String = arrayOf("")): AbstractDriverOptions<*> {
            return when (tag.lowercase()) {
                "chrome" -> ChromeOptions().addArguments(opts.toList())
                "firefox" -> FirefoxOptions().addArguments(opts.toList())
                else -> error("incorrect select tag")
            }
        }
    }
}
```
Итак, в примере выше видим, что у нас ограничен доступ к конструктору StaticFactory при помощи _**private constructor()**,_ так как
нам не нужно создавать много экземпляров класса. Это своего рода микс singleton + factory.

В классе StaticFactory у нас есть companion object, который содержит всего 1 метод - _getOption(),_ 
возвращающий в зависимости от переданного в него тега объект с настройками, 
которые мы передаем в виде набора строк вторым параметром - _vararg opts: String = arrayOf("")_.
 По умолчанию массив со строками пуст. В случае, если мы передадим некорректный тег - метод вернет нам
объект Nothing.


## State
Паттерн State? или Состояние - это поведенческий паттерн проектирования,
который позволяет объектам менять поведение
в зависимости от своего состояния. Извне создаётся впечатление, что изменился класс объекта.
