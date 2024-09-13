package com.baitent.vocabulity.ui

import android.content.Context
import android.content.SharedPreferences
import com.baitent.vocabulity.data.model.CardItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreferencesUtil(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("VocabularyPrefs", Context.MODE_PRIVATE)
    private val gson = Gson()


    fun getLearnedCards(): List<CardItem> {
        val json = sharedPreferences.getString("learnedCards", null)
        if (json.isNullOrEmpty()) return emptyList()
        val type = object : TypeToken<List<CardItem>>() {}.type
        return gson.fromJson(json, type)
    }
}
class Util {
        val englishWords: Map<String, Pair<String, String>> = mapOf(
            "apple" to ("elma" to "She ate an apple for breakfast."),
            "banana" to ("muz" to "Monkeys love eating bananas."),
            "orange" to ("portakal" to "I drank a glass of orange juice."),
            "grape" to ("üzüm" to "He picked a bunch of grapes from the vine."),
            "watermelon" to ("karpuz" to "We had slices of watermelon at the picnic."),
            "pear" to ("armut" to "She likes to eat pears in the summer."),
            "strawberry" to ("çilek" to "He bought fresh strawberries from the market."),
            "cherry" to ("kiraz" to "There’s a bowl of cherries on the table."),
            "peach" to ("şeftali" to "The peach was sweet and juicy."),
            "plum" to ("erik" to "Plums are in season right now."),
            "lemon" to ("limon" to "Add a slice of lemon to your tea."),
            "lime" to ("yeşil limon" to "She squeezed lime juice over the salad."),
            "blueberry" to ("yaban mersini" to "We made blueberry muffins for breakfast."),
            "raspberry" to ("ahududu" to "She garnished the cake with raspberries."),
            "pineapple" to ("ananas" to "I love pineapple on pizza."),
            "mango" to ("mango" to "Mangoes are delicious in smoothies."),
            "apricot" to ("kayısı" to "He ate dried apricots as a snack."),
            "fig" to ("incir" to "Figs are a popular fruit in Mediterranean cuisine."),
            "kiwi" to ("kivi" to "She sliced a kiwi for the fruit salad."),
            "papaya" to ("papaya" to "Papaya is often used in tropical fruit salads."),
            "avocado" to ("avokado" to "Avocado is great on toast."),
            "carrot" to ("havuç" to "Carrots are good for your eyesight."),
            "potato" to ("patates" to "We baked potatoes for dinner."),
            "onion" to ("soğan" to "He chopped onions for the soup."),
            "garlic" to ("sarımsak" to "Garlic adds flavor to many dishes."),
            "tomato" to ("domates" to "She made a fresh tomato salad."),
            "cucumber" to ("salatalık" to "Cucumber is very refreshing in summer."),
            "pepper" to ("biber" to "Add some pepper to the sauce."),
            "lettuce" to ("marul" to "She used lettuce in the sandwich."),
            "spinach" to ("ıspanak" to "Spinach is rich in iron."),
            "cabbage" to ("lahana" to "Cabbage is often used in coleslaw."),
            "broccoli" to ("brokoli" to "Broccoli is a healthy side dish."),
            "cauliflower" to ("karnabahar" to "She roasted cauliflower for dinner."),
            "eggplant" to ("patlıcan" to "They made grilled eggplant for lunch."),
            "zucchini" to ("kabak" to "Zucchini can be used in stir-fries."),
            "beetroot" to ("pancar" to "Beetroot is often used in salads."),
            "radish" to ("turp" to "Radish adds a nice crunch to salads."),
            "pumpkin" to ("balkabağı" to "We made pumpkin pie for dessert."),
            "corn" to ("mısır" to "He grilled corn on the cob."),
            "peas" to ("bezelye" to "Peas are a great source of protein."),
            "bean" to ("fasulye" to "They cooked beans with rice."),
            "lentil" to ("mercimek" to "Lentil soup is a common dish."),
            "chickpea" to ("nohut" to "She made hummus with chickpeas."),
            "oats" to ("yulaf" to "He had oatmeal for breakfast."),
            "rice" to ("pirinç" to "She served rice with curry."),
            "bread" to ("ekmek" to "Freshly baked bread smells amazing."),
            "butter" to ("tereyağı" to "She spread butter on the toast."),
            "cheese" to ("peynir" to "I love cheese on my pizza."),
            "milk" to ("süt" to "He poured milk into his coffee."),
            "yogurt" to ("yoğurt" to "She added yogurt to the smoothie."),
            "cream" to ("krema" to "The dessert was topped with whipped cream."),
            "egg" to ("yumurta" to "He fried eggs for breakfast."),
            "chicken" to ("tavuk" to "They grilled chicken for dinner."),
            "beef" to ("sığır eti" to "He ordered a beef steak."),
            "pork" to ("domuz eti" to "They made pork chops for lunch."),
            "fish" to ("balık" to "She cooked fish with lemon."),
            "shrimp" to ("karides" to "Shrimp is delicious in pasta."),
            "crab" to ("yengeç" to "Crab cakes are a popular seafood dish."),
            "lobster" to ("ıstakoz" to "Lobster is considered a delicacy."),
            "octopus" to ("ahtapot" to "Octopus is often served as an appetizer."),
            "salmon" to ("somon" to "Salmon is rich in omega-3 fatty acids."),
            "tuna" to ("ton balığı" to "They made a tuna salad for lunch."),
            "pasta" to ("makarna" to "He cooked pasta with tomato sauce."),
            "noodles" to ("erişte" to "She made stir-fried noodles."),
            "soup" to ("çorba" to "We had chicken soup for dinner."),
            "salad" to ("salata" to "She made a fresh garden salad."),
            "pizza" to ("pizza" to "Pizza is my favorite fast food."),
            "hamburger" to ("hamburger" to "They ordered hamburgers for lunch."),
            "sandwich" to ("sandviç" to "He packed a sandwich for lunch."),
            "cake" to ("pasta" to "She baked a chocolate cake."),
            "cookie" to ("kurabiye" to "The children loved the homemade cookies."),
            "chocolate" to ("çikolata" to "Chocolate is a great treat."),
            "ice cream" to ("dondurma" to "They had ice cream for dessert."),
            "sugar" to ("şeker" to "She added sugar to her tea."),
            "salt" to ("tuz" to "The recipe called for a pinch of salt."),
            "pepper" to ("biber" to "He seasoned the steak with pepper."),
            "oil" to ("yağ" to "She cooked the vegetables in olive oil."),
            "vinegar" to ("sirke" to "He dressed the salad with vinegar."),
            "honey" to ("bal" to "She drizzled honey over the pancakes."),
            "jam" to ("reçel" to "He spread jam on his toast.")
        )
}