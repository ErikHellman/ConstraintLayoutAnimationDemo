package se.hellsoft.myapplication

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.OvershootInterpolator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private var selectedView: View? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main)
    selectedView = null
    left.setOnClickListener {
      if (selectedView != left) {
        updateConstraints(R.layout.activity_main_left)
        selectedView = left
      } else {
        toDefault()
      }
    }
    middle.setOnClickListener {
      if (selectedView != middle) {
        updateConstraints(R.layout.activity_main_middle)
        selectedView = middle
      } else {
        toDefault()
      }
    }
    right.setOnClickListener {
      if (selectedView != right) {
        updateConstraints(R.layout.activity_main_right)
        selectedView = right
      } else {
        toDefault()
      }
    }

    root.setOnClickListener {
      toDefault()
    }
  }

  private fun toDefault() {
    if (selectedView != null) {
      updateConstraints(R.layout.activity_main)
      selectedView = null
    }
  }

  fun updateConstraints(@LayoutRes id: Int) {
    val newConstraintSet = ConstraintSet()
    newConstraintSet.clone(this, id)
    newConstraintSet.applyTo(root)
    val transition = ChangeBounds()
    transition.interpolator = OvershootInterpolator()
    TransitionManager.beginDelayedTransition(root, transition)
  }
}
