package ro.twodoors.booknotes

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
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

fun View.scaler() {
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2f)
    val animator = ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY)
    animator.repeatCount = 1
    animator.repeatMode = ObjectAnimator.REVERSE
    animator.disableViewDuringAnimation(this)
    animator.start()
}

private fun ObjectAnimator.disableViewDuringAnimation(view: View) {

    // This extension method listens for start/end events on an animation and disables
    // the given view for the entirety of that animation.

    addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationStart(animation: Animator?) {
            view.isEnabled = false
        }

        override fun onAnimationEnd(animation: Animator?) {
            view.isEnabled = true
        }
    })
}

// Fade the view out to completely transparent
fun View.fader() {
    val animator = ObjectAnimator.ofFloat(this, View.ALPHA, 0f)
    animator.disableViewDuringAnimation(this)
    animator.start()
}


