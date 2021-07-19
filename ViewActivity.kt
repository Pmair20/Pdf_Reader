package com.example.pdfviewerappkotlin

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_view.*

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        if(intent != null){
            val viewType = intent.getStringExtra("ViewType")

            if(!TextUtils.isEmpty(viewType) || viewType != null){
                if(viewType.equals("assets")){
                    pdf_view.fromAsset("Example_PDF_Kotlin_Wikipedia.pdf")
                        .password(null) // Only if have password.
                        .defaultPage(0)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true) // Double tap to zoom
                        .onTap { false }
                        .onRender{nbPages, pageWidth, pageHeight ->
                            pdf_view.fitToWidth() // Fit to screen size
                        }
                        .invalidPageColor(Color.RED)
                        .load()
                }
                else if(viewType.equals("storage")){
                    val selectedPDF = Uri.parse(intent.getStringExtra("FileUri"))
                    pdf_view.fromUri(selectedPDF)
                        .defaultPage(0)
                        .enableSwipe(true)
                        .swipeHorizontal(false)
                        .enableDoubletap(true) // Double tap to zoom
//                        .onRender{nbPages, pageWidth, pageHeight ->
//                            pdf_view.fitToWidth() // Fit to screen size
//                        }
                        .load()
                }
            }
        }
    }
}
