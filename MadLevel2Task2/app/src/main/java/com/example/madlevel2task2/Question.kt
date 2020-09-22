package com.example.madlevel2task2

data class Question(
    var questionText: String,
    var questionAnswer: Boolean
) {
    companion object{
        val QUESTION_TEXTS = arrayOf(
            "1 + 1 = 2",
            "Een olifant heeft een lange slurf",
            "Een mens heeft 10 vingers",
            "In de ruimte is er geen zuurstof",
            "Er zijn auto's die sneller gaan dan het licht",
            "Mensheid is goed voor onze planeet",
            "Van 3 bier op een dag word je slimmer",
            "Wit en zwart zijn een kleur"
        )

        val QUESTION_ANSWERS = arrayOf(
            true,
            true,
            true,
            true,
            false,
            false,
            false,
            false,
            false
        )
    }
}