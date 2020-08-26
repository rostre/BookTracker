package ro.twodoors.booknotes

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast

fun Int.toDP(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics).toInt()
}

fun Int.toPx(): Int {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX,
        this.toFloat(),
        Resources.getSystem().displayMetrics).toInt()
}

fun Context.showToast( message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

