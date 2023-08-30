package com.dailyrounds.shelfapp.common

enum class SortKey {
    TITLE,
    HITS,
    FAVS;

    companion object {
        fun getValue(key: SortKey): String {
            return when(key) {
                TITLE -> "Title"
                HITS -> "Hits"
                FAVS -> "Favs"
            }
        }
    }
}