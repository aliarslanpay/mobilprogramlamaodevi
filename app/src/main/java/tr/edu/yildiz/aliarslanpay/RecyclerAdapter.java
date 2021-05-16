package tr.edu.yildiz.aliarslanpay;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PostHolder> {

    private ArrayList<String> questionList;
    private ArrayList<String> trueOptionList;
    private ArrayList<String> option2List;
    private ArrayList<String> option3List;
    private ArrayList<String> option4List;
    private ArrayList<String> option5List;

    public RecyclerAdapter(ArrayList<String> questionList, ArrayList<String> trueOptionList, ArrayList<String> option2List, ArrayList<String> option3List, ArrayList<String> option4List, ArrayList<String> option5List) {
        this.questionList = questionList;
        this.trueOptionList = trueOptionList;
        this.option2List = option2List;
        this.option3List = option3List;
        this.option4List = option4List;
        this.option5List = option5List;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater  layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {

        holder.questionText.setText(questionList.get(position));
        holder.trueOptionText.setText(trueOptionList.get(position));
        holder.option2Text.setText(option2List.get(position));
        holder.option3Text.setText(option3List.get(position));
        holder.option4Text.setText(option4List.get(position));
        holder.option5Text.setText(option5List.get(position));

    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder {

        TextView questionText, trueOptionText, option2Text, option3Text, option4Text, option5Text;
        Button addFile, updateButton, deleteButton;
        ImageView imageView;
        public PostHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView2);
            questionText = itemView.findViewById(R.id.r_questionText);
            trueOptionText = itemView.findViewById(R.id.r_trueOption);
            option2Text = itemView.findViewById(R.id.r_option2);
            option3Text = itemView.findViewById(R.id.r_option3);
            option4Text = itemView.findViewById(R.id.r_option4);
            option5Text = itemView.findViewById(R.id.r_option5);
            addFile = itemView.findViewById(R.id.r_addFileButton);
            updateButton = itemView.findViewById(R.id.r_updateButton);
            deleteButton = itemView.findViewById(R.id.r_deleteButton);
        }
    }

}
