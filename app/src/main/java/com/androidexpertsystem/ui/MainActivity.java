package com.androidexpertsystem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.androidexpertsystem.R;
import com.androidexpertsystem.expert.ESProvider;
import com.androidexpertsystem.expert.Fact;
import com.androidexpertsystem.expert.FactParser;
import com.androidexpertsystem.expert.Question;
import com.androidexpertsystem.expert.RuleParser;
import com.androidexpertsystem.utils.UpdateUi;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.androidexpertsystem.utils.PathUtils.readRaw;

public class MainActivity extends AppCompatActivity implements UpdateUi {

    private static final String TAG = MainActivity.class.getCanonicalName();
    @BindView(R.id.btn_false)
    Button mFalseButton;

    @BindView(R.id.btn_true)
    Button mTrueButton;

    @BindView(R.id.current_answer)
    TextView mCurrentAnswer;

    @BindView(R.id.question)
    TextView mQuestion;


    @BindView(R.id.result_text)
    TextView mResult;

    private ESProvider ESP;

    private int mPosition = 0;

    private ArrayList<Question> questions;
    private ArrayList<Fact> facts;
    private ArrayList<Fact> tmpfacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ESP = new ESProvider(this, new FactParser(readRaw(this, R.raw.knowledgebase).getAbsolutePath()),
                new RuleParser(readRaw(this, R.raw.ruleset).getAbsolutePath()));

        questions = new ArrayList<>(ESP.getRuleRepo().getRuleMap().values());

        updateViews(questions.get(0).getQuestion(), questions.get(0).getAnswerEval().getInputPattern()[0],
                questions.get(0).getAnswerEval().getInputPattern()[1], String.format("%s / 18", 0));
    }

    private void updateViews(String question, String falseText, String trueText, String current) {
        mQuestion.setText(question);
        mFalseButton.setText(falseText);
        mTrueButton.setText(trueText);
        mCurrentAnswer.setText(current);
    }

    @OnClick(R.id.btn_false)
    public void onFalseClick() {
        if (mPosition < questions.size()) {
            for (String ID : ESP.getRuleRepo().getRuleMap().keySet()) {
                if (questions.get(mPosition).getQuestion().equals(ESP.getRuleRepo().getRuleMap().get(ID).getQuestion())) {
                    ESP.collectAnswers(ID, questions.get(mPosition), String.valueOf(mFalseButton.getText()));
                    ++mPosition;
                    onUpdate(questions.get(mPosition));
                    preEvaluate();
                    break;
                }
            }

        } else {
            onResult();
        }
    }

    @OnClick(R.id.btn_true)
    public void onTrueClick() {
        if (mPosition < questions.size()) {
            for (String ID : ESP.getRuleRepo().getRuleMap().keySet()) {
                if (questions.get(mPosition).getQuestion().equals(ESP.getRuleRepo().getRuleMap().get(ID).getQuestion())) {
                    ESP.collectAnswers(ID, questions.get(mPosition), String.valueOf(mTrueButton.getText()));
                    ++mPosition;
                    onUpdate(questions.get(mPosition));
                    preEvaluate();
                    break;
                }
            }

        } else {
            onResult();
        }
    }

    @OnClick(R.id.result)
    public void onResult() {
        ArrayList<Fact> facts = ESP.preEvaluate();
        if (facts.size() == 0){
            facts = this.tmpfacts;
        }
        ArrayList<String> names = new ArrayList<>();
        for (Fact fact : facts) {
            names.add(fact.getDescription());
        }
        Intent intent = new Intent(this, ResultActivity.class);
        Bundle b = new Bundle();
        b.putStringArrayList("list", names);
        intent.putExtras(b);
        startActivity(intent);
    }

    private void preEvaluate() {
        StringBuilder builder = new StringBuilder();
        facts = ESP.preEvaluate();
        if (facts.size() > 1){
            tmpfacts = facts;
        }
        if (facts.size() <= 1) {
            onResult();
            return;
        }
        for (Fact fact : facts) {
            builder.append(fact.getDescription()).append("\n");
        }
        mResult.setText(builder);
    }

    @Override
    public void onUpdate(Question question) {
        updateViews(question.getQuestion(), question.getAnswerEval().getInputPattern()[0],
                question.getAnswerEval().getInputPattern()[1], String.format("%s / 18", mPosition));
    }
}
