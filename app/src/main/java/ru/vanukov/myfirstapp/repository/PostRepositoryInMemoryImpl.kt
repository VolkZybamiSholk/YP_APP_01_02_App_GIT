package ru.vanukov.myfirstapp.repository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vanukov.myfirstapp.R
import ru.vanukov.myfirstapp.dto.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostRepositoryInMemoryImpl : PostRepository {

    // Счетчик для генерации ID
    private var nextId = 5L

    // Текущий пользователь (для демонстрации)
    private val currentUserId = 1L
    private val currentUserName = "Я"

    private var posts = listOf(
        Post(
            id = 1,
            author = "Уютное убежище",
            authorId = 2,
            content = "Иногда самое лучшее место в мире находится между страниц любимой книги. Дождь за окном, плед, горячий чай и история, которая не отпускает до глубокой ночи.\\nА какая книга согревает вас этой осенью? Делитесь в комментариях!",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 999,
            shares = 25,
            views = 5700
        ),
        Post(
            id = 2,
            author = "Уютное убежище",
            authorId = 3,
            content = "Вышла новая книга! Наконец то дождались продолжения Гарри Потера", published = "22 мая в 10:15",
            likedByMe = false,
            likes = 342,
            shares = 89,
            views = 2300
        ),
        Post(
            id = 3,
            author = "Уютное убежище",
            authorId = 4,
            content = "Скоро начнётся распродажа книг на нашем сайт, успейте купить по скидке книги.",  published = "23 мая в 09:42",
            likedByMe = true,
            likes = 1250,
            shares = 420,
            views = 8900
        ),
        Post(
            id = 4,
            author = "Уютное убежище",
            authorId = 5,
            content = "Анонсированы новые книги для заказа на нашем сайте. От наших авторов.",
            published = "20 мая в 20:00",
            likedByMe = false,
            likes = 5678,
            shares = 1234,
            views = 45000
        )
    )

    private val _data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = _data

    override fun likeById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(
                    likedByMe = !post.likedByMe,
                    likes = if (post.likedByMe) post.likes - 1 else post.likes + 1
                )
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun shareById(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(shares = post.shares + 1)
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun increaseViews(id: Long) {
        posts = posts.map { post ->
            if (post.id == id) {
                post.copy(views = post.views + 1)
            } else {
                post
            }
        }
        _data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            // Создание нового поста
            val newPost = post.copy(
                id = nextId++,
                author = currentUserName,
                authorId = currentUserId,
                published = formatDate(Date()),
                likedByMe = false,
                likes = 0,
                shares = 0,
                views = 0
            )
            posts = listOf(newPost) + posts
        } else {
            // Обновление существующего поста
            posts = posts.map { existingPost ->
                if (existingPost.id == post.id) {
                    // Сохраняем автора, дату и счетчики, обновляем только контент
                    existingPost.copy(content = post.content)
                } else {
                    existingPost
                }
            }
        }
        _data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        _data.value = posts
    }

    private fun formatDate(date: Date): String {
        val format = SimpleDateFormat("d MMM в HH:mm", Locale("ru"))
        return format.format(date)
    }
}

