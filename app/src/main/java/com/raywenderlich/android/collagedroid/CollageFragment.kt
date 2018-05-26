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

import android.app.Activity.RESULT_OK
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import android.content.Intent

class CollageFragment : Fragment(), View.OnClickListener {

  private lateinit var templateType: TemplateType
  private lateinit var photo1: ImageView
  private lateinit var photo2: ImageView
  private lateinit var photo3: ImageView
  private lateinit var selectedPhoto: ImageView

  companion object {
    private const val ARG_TEMPLATE_TYPE = "ARG_TEMPLATE_TYPE"

    fun newInstance(templateType: TemplateType): CollageFragment {
      val fragment = CollageFragment()
      val args = Bundle()
      args.putString(ARG_TEMPLATE_TYPE, templateType.name)
      fragment.arguments = args
      return fragment
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {

      templateType = TemplateType.valueOf(it.getString(ARG_TEMPLATE_TYPE))
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val rootView = inflater.inflate(templateType.layout, container, false)

    bindUI(rootView)

    return rootView
  }

  private fun bindUI(rootView: View) {
    photo1 = rootView.findViewById(R.id.photo_1)
    photo2 = rootView.findViewById(R.id.photo_2)
    photo3 = rootView.findViewById(R.id.photo_3)

    photo1.setOnClickListener(this)
    photo2.setOnClickListener(this)
    photo3.setOnClickListener(this)

    photo1.tag = templateType.photo1
    photo2.tag = templateType.photo2
    photo3.tag = templateType.photo3

  }

  override fun onClick(view: View) {
    selectedPhoto = view as ImageView
    val photoInfo = selectedPhoto.tag as PhotoInfo

    showCropView(photoInfo)

  }

  private fun showCropView(photoInfo: PhotoInfo) {
    CropImage.activity()
        .setGuidelines(CropImageView.Guidelines.ON)
        .setAspectRatio(photoInfo.aspectRatioX, photoInfo.aspectRatioY)
        .start(context as Context, this)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
      val result = CropImage.getActivityResult(data)
      if (resultCode == RESULT_OK) {
        val resultUri = result.uri
        selectedPhoto.setImageURI(resultUri)
      }
    }
  }
}
