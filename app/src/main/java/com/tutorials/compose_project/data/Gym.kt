package com.tutorials.compose_project.data


val ListOfGyms = listOf(
    Gym("FitZone", "123 Fitness Street, Maadi, Cairo, Egypt"),
    Gym("PowerGym", "456 Strength Avenue, Smouha, Alexandria, Egypt"),
    Gym("EliteFitness", "789 Wellness Lane, Karnak, Luxor, Egypt"),
    Gym("FlexFit", "101 Flexibility Boulevard, Elephantine, Aswan, Egypt"),

)

data class Gym(val name: String, val place: String)
