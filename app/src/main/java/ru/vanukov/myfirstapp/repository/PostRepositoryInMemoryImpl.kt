package ru.vanukov.myfirstapp.repository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.vanukov.myfirstapp.R
import ru.vanukov.myfirstapp.dto.Post
class PostRepositoryInMemoryImpl : PostRepository {

    // Теперь это список, а не один пост
    private var posts = listOf(
        Post(
            id = 1,
            author = "Уютное убежище",
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
            content = "Вышла новая книга! Наконец то дождались продолжения Гарри Потера",
            published = "22 мая в 10:15",
            likedByMe = false,
            likes = 342,
            shares = 89,
            views = 2300
        ),
        Post(


            id = 3,
            author = "Уютное убежище",
            content = "Скоро начнётся распродажа книг на нашем сайт, успейте купить по скидке книги.",
            published = "23 мая в 09:42",
            likedByMe = true,
            likes = 1250,
            shares = 420,
            views = 8900
        ),
        Post(
            id = 4,
            author = "Уютное убежище",
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
}

