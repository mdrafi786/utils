package com.mdrafi.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import java.lang.Exception


data class GlideUtils(
    var context: Context? = null,
    var url: String? = null,
    var imageView: ImageView? = null,
    var cacheImage: Boolean = false,
    var placeholder: Int? = null,
    var errorPlaceholder: Int? = null,
    var callback: (Boolean?, Exception?) -> Unit,
) {
    fun load() {
        if (context != null && imageView != null) {
            when {
                placeholder != null -> {
                    loadWithPlaceholder(
                        context,
                        url,
                        imageView,
                        placeholder,
                        cacheImage,
                        callback
                    )
                }
                placeholder != null && errorPlaceholder != null -> {
                    loadWithPlaceholderAndErrorPlaceholder(
                        context,
                        url,
                        imageView,
                        placeholder,
                        errorPlaceholder,
                        cacheImage,
                        callback
                    )
                }
                else -> {
                    load(context, url, imageView, cacheImage, callback)
                }
            }
        }
    }
}

private fun load(
    context: Context?,
    url: String?,
    imageView: ImageView?,
    cacheImage: Boolean = false,
    callback: (Boolean?, Exception?) -> Unit
) {
    if (context != null && imageView != null)
        Glide.with(context)
            .load(url)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(!cacheImage)
            .centerCrop()
            .thumbnail(0.5f)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback(false, e)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback(true, null)
                    return false
                }
            })
            .into(imageView)
}

private fun loadWithPlaceholder(
    context: Context?,
    url: String?,
    imageView: ImageView?,
    placeholder: Int?,
    cacheImage: Boolean = false,
    callback: (Boolean?, Exception?) -> Unit
) {
    if (context != null && imageView != null && placeholder != null)
        Glide.with(context)
            .load(url)
            .dontAnimate()
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(!cacheImage)
            .centerCrop()
            .thumbnail(0.5f)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback(false, e)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback(true, null)
                    return false
                }
            })
            .into(imageView)
}

private fun loadWithPlaceholderAndErrorPlaceholder(
    context: Context?,
    url: String?,
    imageView: ImageView?,
    placeholder: Int?,
    errorPlaceholder: Int?,
    cacheImage: Boolean = false,
    callback: (Boolean?, Exception?) -> Unit
) {
    if (context != null && imageView != null && placeholder != null && errorPlaceholder != null)
        Glide.with(context)
            .load(url)
            .dontAnimate()
            .placeholder(placeholder)
            .error(errorPlaceholder)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(!cacheImage)
            .thumbnail(0.5f)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback(false, e)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    callback(true, null)
                    return false
                }
            })
            .into(imageView)
}

