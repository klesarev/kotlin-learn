package qa

import io.qameta.allure.Allure

// обертка для Allure Step
fun step(name: String?, runnable: ()-> Unit ) =
    Allure.step(name, runnable)
