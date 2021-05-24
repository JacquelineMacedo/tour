package com.ascm.tour.entities

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING
import java.util.*

data class RespostaJson(val message: String, @JsonFormat(shape = STRING, pattern = "dd/mm/yyyy") val date: Date)
