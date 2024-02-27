package com.tutorials.compose_project.data


val ListOfGyms = listOf<Gym>(
    Gym(1, "FitZone", "123 Fitness Street, Maadi, Cairo, Egypt"),
    Gym(2, "PowerGym", "456 Strength Avenue, Smouha, Alexandria, Egypt"),
    Gym(3, "EliteFitness", "789 Wellness Lane, Karnak, Luxor, Egypt"),
    Gym(4, "FlexFit", "101 Flexibility Boulevard, Elephantine, Aswan, Egypt"),

    )

data class Gym(var id: Int, val name: String, val place: String, var isFavorite: Boolean = false)
