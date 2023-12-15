package com.sendiko.daggerhiltretrofitcompose.network.responses

import com.google.gson.annotations.SerializedName

data class GetConcertsResponse(

	@field:SerializedName("data")
	val data: List<Concert?>? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class Concert(

	@field:SerializedName("concert_time")
	val concertTime: String? = null,

	@field:SerializedName("stage")
	val stage: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("concert_id")
	val concertId: String? = null,

	@field:SerializedName("concert_address")
	val concertAddress: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("concert_name")
	val concertName: String? = null,

	@field:SerializedName("name_of_artist")
	val nameOfArtist: String? = null,

	@field:SerializedName("concert_date")
	val concertDate: String? = null,

	@field:SerializedName("seat_capacity")
	val seatCapacity: Int? = null
)
