package br.com.mhbpit.sov.activemodel.networking

import br.com.mhbpit.sov.activemodel.model.Repo
import retrofit2.Call
import retrofit2.http.GET
import java.util.List

interface RepoService {

    @GET("orgs/Google/repos")
    fun getRespository(): Call<List<Repo>>;
}