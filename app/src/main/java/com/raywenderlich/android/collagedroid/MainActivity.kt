/*
 *  Copyright (c) 2018 Razeware LLC
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 *  distribute, sublicense, create a derivative work, and/or sell copies of the
 *  Software in any work that is designed, intended, or marketed for pedagogical or
 *  instructional purposes related to programming, coding, application development,
 *  or information technology.  Permission for such use, copying, modification,
 *  merger, publication, distribution, sublicensing, creation of derivative works,
 *  or sale is expressly withheld.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 *
 */

package com.raywenderlich.android.collagedroid

import android.os.Bundle
import android.support.annotation.DrawableRes
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.raywenderlich.android.collagedroid.R.drawable.android_kotlin
import com.raywenderlich.android.collagedroid.R.drawable.android_happy
import com.raywenderlich.android.collagedroid.R.drawable.android_jetpack

class MainActivity : AppCompatActivity() {
  private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_home -> {
        changeScreen(TemplateType.FIRST)
        changeDroidImage(android_jetpack)
        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_dashboard -> {
        changeScreen(TemplateType.SECOND)
        changeDroidImage(android_kotlin)

        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_notifications -> {
        changeScreen(TemplateType.THIRD)
        changeDroidImage(android_happy)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    navigation.selectedItemId = R.id.navigation_home
  }

  private fun changeScreen(templateType: TemplateType) {
    val fragment = CollageFragment.newInstance(templateType)
    val tag = fragment.javaClass.simpleName

    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment, tag)
        .commit()
  }

  private fun changeDroidImage(@DrawableRes drawable: Int) {
    droid_image.setImageResource(drawable)
    animateDroidImage()
  }

  private fun animateDroidImage() {
    val anim = RotateAnimation(0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    anim.interpolator = LinearInterpolator()
    anim.repeatCount = 1
    anim.duration = 700

    droid_image.startAnimation(anim)
  }
}
