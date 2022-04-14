package com.kaimiya.gaurav.fidoointerviewtask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaimiya.gaurav.fidoointerviewtask.model.Post
import com.kaimiya.gaurav.fidoointerviewtask.model.PostList
import com.kaimiya.gaurav.fidoointerviewtask.repository.MainRepository
import kotlinx.coroutines.*
import retrofit2.Response

class MainActivityViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val posts = MutableLiveData<List<Post>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()

    fun getPostList() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = mainRepository.getPostList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    posts.postValue(response.body()?.data)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    /*fun getPostList() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = mainRepository.getPostList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    posts.postValue(response.body()?.data)
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }*/

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}