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

import androidx.annotation.LayoutRes
import com.raywenderlich.android.collagedroid.R.layout.fragment_template_1
import com.raywenderlich.android.collagedroid.R.layout.fragment_template_2
import com.raywenderlich.android.collagedroid.R.layout.fragment_template_3

enum class TemplateType(@LayoutRes val layout: Int, val photo1: PhotoInfo, val photo2: PhotoInfo, val photo3: PhotoInfo) {

  FIRST(fragment_template_1,
      PhotoInfo(3, 4),
      PhotoInfo(3, 4),
      PhotoInfo(9, 21)),

  SECOND(fragment_template_2,
      PhotoInfo(9, 21),
      PhotoInfo(3, 4),
      PhotoInfo(3, 4)),

  THIRD(fragment_template_3,
      PhotoInfo(3, 4),
      PhotoInfo(3, 4),
      PhotoInfo(16, 9));
}

class PhotoInfo(val aspectRatioX: Int, val aspectRatioY: Int)