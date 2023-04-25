package com.example.mapsbasics2.common.entities

data class Artist(val id: String = "",
                  var name:String = "",
                  var surnames:String = "",
                  val photoUrl:String ="",
                  var birthLocation: BirthLocation = BirthLocation())
{
    fun getFullName(): String = "{$name $surnames}"

    override fun toString(): String {
        return "Name: $name\nSurnames: $surnames\n${birthLocation.customFormatDetails()}"
    }

}

