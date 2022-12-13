package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.R
import com.example.quizapp.databinding.FragmentQuizResultBinding
import com.example.quizapp.model.QuizViewModel
import java.io.ByteArrayOutputStream


class QuizResultFragment : Fragment() {
    private var _binding: FragmentQuizResultBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizResultBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.apply {
            quizResultFragment = this@QuizResultFragment
            viewModel = sharedViewModel
            quizScore.text = getString(
                R.string.quiz_score,
                sharedViewModel.score,
                sharedViewModel.quiz.getNumberOfQuestions()
            ) // TODO from view binding to data binding
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun goToHome() {
        val action = QuizResultFragmentDirections.actionQuizResultFragmentToQuizListFragment()
        findNavController().navigate(action)
    }

    fun restartQuiz() {
        val action = QuizResultFragmentDirections.actionQuizResultFragmentToQuizDetailFragment(
            sharedViewModel.quiz.id
        )
        findNavController().navigate(action)
    }

    fun shareResult() {
        val bitmap = getBitmapFromView(binding.card)
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_STREAM, bitmap?.let { getBitmapUri(it) })
        intent.type = "image/jpeg"
        startActivity(Intent.createChooser(intent, "Share Result"))
    }

    // TODO Move to .util package (?) - and call from viewmodel (?)

    private fun getBitmapFromView(view: View): Bitmap? {
        // Define a bitmap with the same size as the view
        val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        // Bind a canvas to it
        val canvas = Canvas(returnedBitmap)
        // Get the view's background
        val bgDrawable = view.background
        if (bgDrawable != null) // Has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas) else  // Does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE)
        // Draw the view on the canvas
        view.draw(canvas)
        // Return the bitmap
        return returnedBitmap
    }

    private fun getBitmapUri(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(
            requireContext().contentResolver,
            bitmap,
            "result",
            null
        )
        return Uri.parse(path)
    }

}