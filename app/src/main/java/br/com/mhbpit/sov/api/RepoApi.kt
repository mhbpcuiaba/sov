package br.com.mhbpit.sov.api

import br.com.mhbpit.sov.activemodel.networking.RepoService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RepoApi {



    companion object {
        private val retrofit = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        @JvmStatic
        fun repoService(): RepoService {
            return retrofit.create(RepoService::class.java)
        }
    }

}