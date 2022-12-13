package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.ui.QuizListFragmentDirections
import com.example.quizapp.data.model.Quiz


class QuizAdapter(
    private val dataset: List<Quiz>
) : RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    private val filteredDataset: List<Quiz> = dataset.filter { it.isValid() }

    class QuizViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val imageImageView: ImageView = view.findViewById(R.id.image)
        val titleTextView: TextView = view.findViewById(R.id.title)
        val categoryTextView: TextView = view.findViewById(R.id.category)
        val difficultyTextView: TextView = view.findViewById(R.id.difficulty)
        val descriptionTextView: TextView = view.findViewById(R.id.description)
        val numberOfQuestionsTextView: TextView = view.findViewById(R.id.number_of_questions)
        val playButton: Button = view.findViewById(R.id.play)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): QuizViewHolder {
        val layout = LayoutInflater
            .from(viewGroup.context)
            .inflate(R.layout.item_quiz_view, viewGroup, false)
        return QuizViewHolder(layout)
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        val context = holder.itemView.context
        val item = filteredDataset[position]
        holder.imageImageView.setImageResource(item.imageResourceId)
        holder.titleTextView.text = item.title.toString()
        holder.categoryTextView.text = item.category.toString()
        holder.difficultyTextView.text = item.difficulty.toString()
        holder.numberOfQuestionsTextView.text = context.resources.getQuantityString(
            R.plurals.questions,
            item.getNumberOfQuestions(),
            item.getNumberOfQuestions()
        )
        holder.descriptionTextView.text = item.description
        holder.playButton.setOnClickListener {
            val action =
                QuizListFragmentDirections.actionQuizListFragmentToQuizDetailFragment(id = item.id)
            holder.view.findNavController().navigate(action)
        }
    }

    override fun getItemCount() = filteredDataset.size
}