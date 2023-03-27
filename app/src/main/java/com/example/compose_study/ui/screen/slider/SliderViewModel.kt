package com.example.compose_study.ui.screen.slider

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.compose_study.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(
) : BaseViewModel() {

    var movieListResponse: List<Movies> by mutableStateOf(listOf())

    init {
        viewModelScope.launch {
            try {
                movieListResponse = listOf(
                    Movies(category = "Latest", name = "Coco", imageUrl = "https://www.howtodoandroid.com/images/coco.jpg", desc = "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"),
                    Movies(category = "Latest", name = "Terminator 2: Judgment Day 3D", imageUrl = "https://www.howtodoandroid.com/images/terminator_2.jpg", desc = "Similar to Cameron's Titanic 3D, Lightstorm Entertainment oversaw the work on the 3D version of Terminator 2, which took nearly a year to finish."),
                    Movies(category = "Latest", name = "Coco", imageUrl = "https://www.howtodoandroid.com/images/coco.jpg", desc = "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"),
                    Movies(category = "Latest", name = "Dunkirk", imageUrl = "https://www.howtodoandroid.com/images/dunkirk.jpg", desc = "Dunkirk is a 2017 war film written, directed, and co-produced by Christopher Nolan that depicts the Dunkirk evacuation of World War II. "),
                    Movies(category = "Latest", name = "Dunkirk", imageUrl = "https://www.howtodoandroid.com/images/dunkirk.jpg", desc = "Dunkirk is a 2017 war film written, directed, and co-produced by Christopher Nolan that depicts the Dunkirk evacuation of World War II. "),
                    Movies(category = "Latest", name = "Terminator 2: Judgment Day 3D", imageUrl = "https://www.howtodoandroid.com/images/terminator_2.jpg", desc = "Similar to Cameron's Titanic 3D, Lightstorm Entertainment oversaw the work on the 3D version of Terminator 2, which took nearly a year to finish."),
                )
            } catch (e: Exception) {
                Log.e("Error",e.toString())
            }
        }

    }
}

//[{
//    "category": "Latest",
//    "imageUrl": "https://www.howtodoandroid.com/images/coco.jpg",
//    "name": "Coco",
//    "desc": "Coco is a 2017 American 3D computer-animated musical fantasy adventure film produced by Pixar"
//},
//{
//    "category": "Latest",
//    "imageUrl": "https://www.howtodoandroid.com/images/terminator_2.jpg",
//    "name": "Terminator 2: Judgment Day 3D",
//    "desc": "Similar to Cameron's Titanic 3D, Lightstorm Entertainment oversaw the work on the 3D version of Terminator 2, which took nearly a year to finish."
//},
//{
//    "category": "Latest",
//    "imageUrl": "https://www.howtodoandroid.com/images/dunkirk.jpg",
//    "name": "Dunkirk",
//    "desc": "Dunkirk is a 2017 war film written, directed, and co-produced by Christopher Nolan that depicts the Dunkirk evacuation of World War II. "
//}
//]
