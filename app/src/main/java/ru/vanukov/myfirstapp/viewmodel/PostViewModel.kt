package ru.vanukov.myfirstapp.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ru.vanukov.myfirstapp.dto.Post
import ru.vanukov.myfirstapp.repository.PostRepository
import ru.vanukov.myfirstapp.repository.PostRepositoryInMemoryImpl


class PostViewModel : ViewModel() {

    // Создаем экземпляр репозитория
    private val repository: PostRepository = PostRepositoryInMemoryImpl()

    // Данные, доступные для наблюдения
    val data: LiveData<Post> = repository.get()

    // Методы для вызова из Activity
    fun like() = repository.like()
    fun share() = repository.share()
    fun increaseViews() = repository.increaseViews()
}
