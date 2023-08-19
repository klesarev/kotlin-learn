package core

fun main(vararg args: String) {

    val user = User("Josh", "1987-11-08")
}


data class User (
    val name: String,
    @AllowRegex("\\d{4}-\\d{2}-\\d{2}")
    val birthDate: String
) {
    init {
        val fields = this::class.java.declaredFields

        fields.forEach { field->
            field.annotations.forEach { annotation ->
                if (field.isAnnotationPresent(AllowRegex::class.java)) {
                    val regex = field.getAnnotation(AllowRegex::class.java)?.regex
                    if (regex?.toRegex()?.matches(birthDate) == false) {
                        throw IllegalArgumentException("Birthday is not a valid date: $birthDate")
                    }
                }
            }
        }
    }

}

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class AllowRegex(val regex: String)


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class Path(val url: String)

