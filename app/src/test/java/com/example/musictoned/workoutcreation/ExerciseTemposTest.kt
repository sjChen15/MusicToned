package com.example.musictoned.workoutcreation;

import com.example.musictoned.workoutcreation.exerciseTempoMapping.getTempo
import org.junit.Test
import org.junit.Assert.*

class ExerciseTemposTest {
    @Test
    fun canGetTempo() {
       val tempo = getTempo("push-up")
        assertEquals(tempo,90)
    }
}

