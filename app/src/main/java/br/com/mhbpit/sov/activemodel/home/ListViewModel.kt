package br.com.mhbpit.sov.activemodel.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.mhbpit.sov.activemodel.model.Repo
import br.com.mhbpit.sov.api.RepoApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.List


/*
https://medium.com/collabcode/consumindo-api-rest-no-android-com-retrofit-em-kotlin-parte-2-62d40b49f8be
https://medium.com/collabcode/consumindo-api-rest-no-android-com-retrofit-em-kotlin-parte-3-c23adba08095
https://medium.com/collabcode/consumindo-api-rest-no-android-com-retrofit-em-kotlin-parte-4-c60683ff0ee8
 */
class ListViewModel : ViewModel() {

    val repos :MutableLiveData<List<Repo>> = MutableLiveData();
    val repoLoadError :MutableLiveData<Boolean> = MutableLiveData();
    val loading :MutableLiveData<Boolean> = MutableLiveData();
    var repoCall: Call<List<Repo>>? = null;


    fun getRepos(): LiveData<List<Repo>> {
        return repos;
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError;
    }

    fun getLoading(): LiveData<Boolean> {
        return loading;
    }

    fun fetchRepos() {
        loading.value = true;
        repoCall = RepoApi.repoService().getRespository();

        repoCall!!.enqueue(object : Callback<List<Repo>> {

            override fun onFailure(call: Call<List<Repo>>?, t: Throwable?) {
                Log.e(javaClass.simpleName, "Error loading repos", t)
                repoLoadError.value = true;
                loading.value = false;
                repoCall = null;
            }

            override fun onResponse(call: Call<List<Repo>>?, response: Response<List<Repo>>?) {
                repoLoadError.value = false;
                repos.value = response!!.body();
                loading.value = false;
                repoCall = null;
            }

        })
    }

    override fun onCleared() {
        if ( repoCall != null) {
            repoCall!!.cancel();
            repoCall = null;
        }
    }
}