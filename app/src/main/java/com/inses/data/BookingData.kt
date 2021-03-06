package com.inses.data

import java.io.Serializable

data class BookingData (
        var id:String,
        var profilePic:String,
        var customerId:String,
        var name:String,
        var area:String,
        var transactionData:String,
        var nearBy:String,
        var email:String,
        var serviceName:String,
        var serviceType:String,
        var image:String,
        var no:String,
        var perAmount:String,
        var totalAmount:String,
        var discount:String,
        var payable:String,
        var serviceDate:String,
        var serviceTime:String,
        var phone:String,
        var locality:String,
        var houseNo:String,
        var street:String,
        var orderDate:String,
        var orderTime:String,
        var status:String,
        var completed:Boolean,
        var completedDate:String,
        var completedTime:String,
        var completedBy:String,
        var stars:String,
        var review:String,
        var paymentReceived:Boolean

): Serializable