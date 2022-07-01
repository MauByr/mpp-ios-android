package com.jetbrains.handson.mpp.mobile.dataObjects

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

typealias NLC = String
typealias CRS = String
typealias DateTimeString = String
typealias LegDetails = JsonObject
typealias PricingDetailBreakdown = JsonObject
typealias DiscountInfo = JsonObject
typealias TicketCategory = String
typealias TicketClass = String
typealias UpgradeType = String

@Serializable
data class StationList(
    val stations: List<StationInfo>
)

@Serializable
data class StationInfo(
    val id: Int? = null,
    val name: String? = null,
    val aliases: List<String>? = null,
    val crs: CRS? = null,
    val nlc: NLC? = null,
    val latitude: Double? = null,
    val longitude: Double? = null,
    val isGroupStation: Boolean? = null,
    val isSilverSeekStation: Boolean?

)

@Serializable
data class FareResponse(
    val numberOfAdults: Int,
    val numberOfChildren: Int,
    val outboundJourneys: List<Journey>,
    val nextOutboundQuery: String,
    val previousOutboundQuery: String,
    val nextInboundQuery: String? = null,
    val previousInboundQuery: String? = null,
    val bookingMessages: BookingMessages
)

@Serializable
data class BookingMessages(
    val messageCentreTitle: String? = null,
    val doNotShowAgainText: String? = null,
    val messages: List<BookingMessage>
)

@Serializable
data class BookingMessage(
    val title: String,
    val message: String,
    val messageDismissText: String? = null,
    val showBookingFormOnDismiss: Boolean
)

@Serializable
data class Journey(
    val journeyOptionToken: String,
    val journeyId: String,
    val originStation: Station,
    val destinationStation: Station,
    val departureTime: DateTimeString,
    val arrivalTime: DateTimeString,
    val arrivalRealTime: DateTimeString? = null,
    val departureRealTime: DateTimeString? = null,
    val status: TrainStatus,
    val primaryTrainOperator: TrainOperator,
    val legs: List<LegDetails>,
    val tickets: List<TicketOption>,
    val journeyDurationInMinutes: Double,
    val isFastestJourney: Boolean,
    val bulletins: List<Bulletin>,
    val stationMessages: List<StationMessage>,
    val isEligibleForLoyalty: Boolean
)

@Serializable
data class TicketOption(
    val ticketOptionToken: String,
    val name: String,
    val description: String,
    val priceInPennies: Int,
    val pricingItem: PricingItem,
    val ticketType: TicketType,
    val ticketClass: TicketClass,
    val ticketCategory: TicketCategory,
    val numberOfTickets: Int,
    val ticketsRemaining: Int? = null,
    val fareId: String,
    val ftot: String? = null,
    val fareSignature: String? = null,
    val upgradeDetails: UpgradeDetails? = null,
    val isValidForLoyaltyCredit: Boolean,
    val isValidForAdr: Boolean

)

@Serializable
data class PricingItem(
    val subTotalInPennies: Int,
    val breakdown: List<PricingDetailBreakdown>,
    val addOns: List<AddOn> = emptyList(),
    val discounts: DiscountInfo? = null
)

@Serializable
data class AddOn(
    val name: String? = null,
    val count: Int? = null,
    val costInPennies: Int?
)

@Serializable
data class UpgradeDetails(
    val upgradeFareId: String? = null,
    val type: UpgradeType? = null,
    val fareDifferenceInPennies: Int
)

@Serializable
data class Bulletin(
    val id: Int,
    val title: String? = null, //marked as required in spec, but not always sent
    val description: String,
    val category: String? = null,
    val url: String? = null,
    val severity: String?
)

@Serializable
data class StationMessage(
    val severity: String,
    val category: String,
    val message: String
)

@Serializable
data class TrainOperator(
    val code: String,
    val name: String
)

@Serializable
enum class TicketType {
    @SerialName("single")
    Single,

    @SerialName("return")
    Return
}

@Serializable
enum class TrainStatus {
    normal, delayed, cancelled, fully_reserved
}


@Serializable
data class Station(
    val displayName: String,
    val crs: String,
    val nlc: String
)
