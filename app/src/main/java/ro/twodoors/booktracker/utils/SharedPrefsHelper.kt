package ro.twodoors.booktracker.utils

import android.content.Context
import android.content.SharedPreferences

const val BOOKS = "books"
const val ITEM_TYPE_KEY = "item_type_key"

class SharedPrefsHelper {

    companion object {
        private fun preferences(context: Context): SharedPreferences =
            context.getSharedPreferences(BOOKS, Context.MODE_PRIVATE)

        fun setItemType(context: Context, itemType: ItemType) {
            preferences(context).edit()
                .putString(ITEM_TYPE_KEY, itemType.name)
                .apply()
        }

        fun getItemType(context: Context): String =
            preferences(context).getString(ITEM_TYPE_KEY, ItemType.LIST.name)!!
    }
}