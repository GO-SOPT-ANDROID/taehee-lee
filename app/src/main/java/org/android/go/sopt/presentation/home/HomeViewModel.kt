package org.android.go.sopt.presentation.home

import androidx.lifecycle.ViewModel
import org.android.go.sopt.R
import org.android.go.sopt.model.Animal

class HomeViewModel : ViewModel() {

    val mockAnimalList = listOf<Animal>(

        Animal(
            image = R.drawable.ic_tibet,
            animal = "티벳여우",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_seal_1,
            animal = "담배 피는 물개",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_seal_2,
            animal = "파란 물개",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_owl,
            animal = "부엉이",
            species = "조류"
        ),
        Animal(
            image = R.drawable.ic_dog,
            animal = "웃는 개",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_kjs,
            animal = "지친 김준서",
            species = "파충류"
        ),
        Animal(
            image = R.drawable.ic_iron_man,
            animal = "아이언맨",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_toruk,
            animal = "토루크",
            species = "파충류"
        ),
        Animal(
            image = R.drawable.ic_tibet,
            animal = "티뱃 여우",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_seal_1,
            animal = "담배 피는 물개",
            species = "포유류"
        ),
        Animal(
            image = R.drawable.ic_seal_2,
            animal = "파란 물개",
            species = "포유류"
        )
    )
}