package ru.vanukov.myfirstapp
import ru.vanukov.myfirstapp.util.FormatUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.vanukov.myfirstapp.databinding.ActivityMainBinding
import ru.vanukov.myfirstapp.dto.Post
import java.text.DecimalFormat
import androidx.activity.viewModels
import ru.vanukov.myfirstapp.viewmodel.PostViewModel
import ru.vanukov.myfirstapp.adapter.PostsAdapter





class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Создаем адаптер с обработчиками
        val adapter = PostsAdapter(
            onLikeClickListener = { post ->
                viewModel.likeById(post.id)
                Toast.makeText(this, "Лайк поста ${post.id}", Toast.LENGTH_SHORT).show()
            },
            onShareClickListener = { post ->
                viewModel.shareById(post.id)
                Toast.makeText(this, "Репост поста ${post.id}", Toast.LENGTH_SHORT).show()
            }
        )

        // Устанавливаем адаптер для RecyclerView
        binding.list.adapter = adapter

        // Наблюдаем за изменениями данных
        viewModel.data.observe(this) { posts ->
            // submitList сам вызовет DiffUtil и обновит только изменившиеся элементы
            adapter.submitList(posts)
        }
    }
}
