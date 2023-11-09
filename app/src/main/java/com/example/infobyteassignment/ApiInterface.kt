package com.example.infobyteassignment

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("/macros/echo?user_content_key=XAPHXH_2IkMEALz6V5SHu8D4nLQOTrRyfC5iVPIMVpmlTkv7U7UjvNPkpr3q55CzciCS2vm7U83YUIY5CjP6WBkrDEa1b7EDm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnLLaPLSR1orrviIfdqbMVqnezuh_s26yzbzP1_QfvmA2sWZRCCTUDlAxB98yUCUmFTQrJMlj5zDWXRL6VL37MTTcpTNo-7Iq1kALmaJ23tE48xTm37iX3Fs&lib=MSKxoTD8KE-03f2YxPJKn5sQfnqTZGV-_")
    fun getProductData() : Call<MyData>
}
