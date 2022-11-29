## Список задач
### Верните повторяющиеся элементы из списка
Дан список (1,2,3,6,2,14,156,14), верните повторяющиеся элементы из списка
```kotlin
fun <T> repeatElements(list: List<T>): Set<T> {
    val buffer = mutableSetOf<T>()
    
    return list.filter {element->
        !buffer.add(element)
    }.toSet()
}

// [2, 14]
```
Кроме того, данную задачу можно решить через метод count.
```kotlin
fun repeatElementsListTwo(list: List<Int>): List<Int> {
    return list.filter { element ->
            list.count{ it == element } > 1
        }.distinct()
}

// [2, 14]
```
А вот еще решение, и так же с примененеим _**count()**_
```kotlin
val result = digits.groupBy { it }
        .filter { it.value.count() > 1 }
        .map { it.key }

// [2, 14]
```
P.S. Лично встречал эту задачу на 2 собесах.

### Количество повторений каждого символа в строке(массиве)
Итак, дана строка вида _**aabbbccddddeefffff**_. Нужно посчитаь количество элементов каждого символа,
и вывести рядом с ним. Пример - _**a2b3c2d4e2f5**_
```kotlin
val result = str.groupingBy { it }.eachCount()
        .toList()
        .map { "${it.first}${it.second}" }
        .joinToString("")

println(result) // выведет a2b3c2d4e2f5
```
### Перевернуть строку
Одна из самых распространненых задач и довольно простая. В Kotlin решается 
в одну строку.  

Итак, дана строка - _keep calm and coding_, нужно написать метод, который будет возвращать реверсивный текст - 
_gnidoc dna mlac peek_
```kotlin
val str = "keep calm and coding"
str.reversed() // gnidoc dna mlac peek
```
Вулая! Но скорее всего на собесе это не прокатит. Поэтому напишем чуть больше кода...
```kotlin
fun strReverse(text: String): String {
    val size = text.length - 1
    
    return text.mapIndexed { index, _ ->
        text[size - index]
    }.joinToString("")
}
```
Стало определенно лучше - уже начали проглядываться зачатки логики. Чтобы "добить" интервьюера, можно написать это 
в виде функции расширения для класса String.
```kotlin
fun String.reverse(): String {
    return this.mapIndexed { index, _ ->
        this[(this.length - 1) - index]
    }.joinToString("")
}
```
