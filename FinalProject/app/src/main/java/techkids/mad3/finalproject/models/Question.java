package techkids.mad3.finalproject.models;

/**
 * Created by TrungNT on 7/8/2016.
 */
public class Question {
    private int id_question;
    private int id_sub;
    private int kind_of_math;
    private String content_question;
    private String answer_a;
    private String answer_b;
    private String answer_c;

    public void setAnswer_d(String answer_d) {
        this.answer_d = answer_d;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public void setId_sub(int id_sub) {
        this.id_sub = id_sub;
    }

    public void setKind_of_math(int kind_of_math) {
        this.kind_of_math = kind_of_math;
    }

    public void setContent_question(String content_question) {
        this.content_question = content_question;
    }

    public void setAnswer_a(String answer_a) {
        this.answer_a = answer_a;
    }

    public void setAnswer_b(String answer_b) {
        this.answer_b = answer_b;
    }

    public void setAnswer_c(String answer_c) {
        this.answer_c = answer_c;
    }

    public void setAnswer_right(String answer_right) {
        this.answer_right = answer_right;
    }

    public void setExplane(String explane) {
        this.explane = explane;
    }

    private String answer_d;
    private String answer_right;
    private String explane;
}
