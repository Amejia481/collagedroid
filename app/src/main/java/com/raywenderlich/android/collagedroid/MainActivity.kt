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
import androidx.annotation.DrawableRes
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.raywenderlich.android.collagedroid.R.drawable.android_kotlin
import com.raywenderlich.android.collagedroid.R.drawable.android_happy
import com.raywenderlich.android.collagedroid.R.drawable.android_jetpack

class MainActivity : AppCompatActivity() {

  private lateinit var droidImage: ImageView
  private lateinit var navigation: BottomNavigationView

  private val mOnNavigationItemSelectedListener = OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_first -> {
        changeScreen(TemplateType.FIRST, android_jetpack)

        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_second -> {
        changeScreen(TemplateType.SECOND, android_kotlin)

        return@OnNavigationItemSelectedListener true
      }
      R.id.navigation_third -> {
        changeScreen(TemplateType.THIRD, android_happy)
        return@OnNavigationItemSelectedListener true
      }
    }
    false
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    bindUI()
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    navigation.selectedItemId = R.id.navigation_first
  }

  private fun changeScreen(templateType: TemplateType, @DrawableRes drawable: Int) {
    val fragment = CollageFragment.newInstance(templateType)
    val tag = fragment.javaClass.simpleName

    supportFragmentManager
        .beginTransaction()
        .replace(R.id.fragment_container, fragment, tag)
        .commit()

    changeDroidImage(drawable)
  }

  private fun changeDroidImage(@DrawableRes drawable: Int) {
    droidImage.setImageResource(drawable)
    animateDroidImage()
  }

  private fun animateDroidImage() {
    val anim = RotateAnimation(0f, 360.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
    with(anim) {
      interpolator = LinearInterpolator()
      repeatCount = 1
      duration = 700
    }

    droidImage.startAnimation(anim)
  }

  private fun bindUI() {
    navigation = findViewById(R.id.navigation)
    droidImage = findViewById(R.id.droid_image)
  }
}
