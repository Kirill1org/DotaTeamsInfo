package bonch.dev.networking.networking

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitFactory {

    const val BASE_URL = "https://api.opendota.com/"

    fun makeRetrofitService(): RetrofitService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create().withNullSerialization())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

}