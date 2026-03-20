package ru.vanukov.myfirstapp.adapter

import ru.vanukov.myfirstapp.dto.Post

interface OnPostInteractionListener {
    fun onLike(post: Post) {}
    fun onShare(post: Post) {}
    fun onEdit(post: Post) {}
    fun onRemove(post: Post) {}
    fun onAvatarClick(post: Post) {}
}
