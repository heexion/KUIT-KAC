package com.konkuk.kuit_kac.core.util.context

import androidx.compose.runtime.Composable
import com.konkuk.kuit_kac.R
@Composable
fun String.toDrawable(): Int {
    val foodMap = mapOf(
        "곡류" to R.drawable.ic_grains,
        "과일류" to R.drawable.ic_fruits,
        "생선류" to R.drawable.ic_fish,
        "김치류" to R.drawable.ic_kimchi,
        "두류, 견과 및 종실류" to R.drawable.ic_beans_nuts_seeds,
        "면류" to R.drawable.ic_noodles,
        "만두류" to R.drawable.ic_dumplings,
        "밥류" to R.drawable.ic_rice,
        "볶음류" to R.drawable.ic_stirfry,
        "빵 및 과자" to R.drawable.ic_bread_snacks,
        "나물, 무침류" to R.drawable.ic_veggies_sides,
        "수,조,어,육류" to R.drawable.ic_meat,
        "유제품류 및 빙과류" to R.drawable.ic_dairy_icecream,
        "음료 및 차류" to R.drawable.ic_beverages_tea,
        "장류, 양념류" to R.drawable.ic_sauces_seasoning,
        "장아찌, 절임류" to R.drawable.ic_pickles,
        "전,적 및 부침류" to R.drawable.ic_pancakes_fritters,
        "젓갈류" to R.drawable.ic_jeotgal,
        "조림류" to R.drawable.ic_braised,
        "죽 및 스프류" to R.drawable.ic_porridge_soup,
        "찌개 및 전골류" to R.drawable.ic_stew_hotpot,
        "찜류" to R.drawable.ic_steamed,
        "채소, 해조류" to R.drawable.ic_veggies_seaweed,
        "튀김류" to R.drawable.ic_fried,
        "과자류, 빵류 또는 떡류" to R.drawable.ic_snacks_bread_ricecake,
        "기타식품류" to R.drawable.ic_misc_foods,
        "농산가공식품류" to R.drawable.ic_processed_agri,
        "당류" to R.drawable.ic_sugar,
        "동물성가공식품류" to R.drawable.ic_processed_animal,
        "두부, 묵류" to R.drawable.ic_tofu_jelly,
        "면류" to R.drawable.ic_noodles_p,
        "빙과류" to R.drawable.ic_icecream_p,
        "수산가공식품류" to R.drawable.ic_processed_seafood,
        "식용유지류" to R.drawable.ic_edible_oils,
        "식육가공품 및 육류" to R.drawable.ic_processed_meat,
        "알가공품류" to R.drawable.ic_processed_egg,
        "유가공품류" to R.drawable.ic_processed_dairy,
        "음료류" to R.drawable.ic_beverages_p,
        "잼류" to R.drawable.ic_jam,
        "조림류" to R.drawable.ic_braised_p,
        "조미식품류" to R.drawable.ic_seasoned_foods,
        "주류" to R.drawable.ic_alcohol,
        "즉석식품류" to R.drawable.ic_ready_to_eat,
        "코코아가공품류 및 초콜릿류" to R.drawable.ic_cocoa_chocolate,
        "특수영양식품" to R.drawable.ic_special_nutrition,
        "특수의료용도식품" to R.drawable.ic_medical_foods
    )
    return foodMap[this]?:R.drawable.ic_dumplings
}